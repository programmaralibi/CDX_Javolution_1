package immortal.persistentScope;

import immortal.Constants;
import immortal.FrameSynchronizer;
import immortal.ImmortalEntry;
import immortal.NanoClock;
import immortal.RawFrame;
import immortal.persistentScope.transientScope.TransientDetectorScopeEntry;

import realtime.LTMemory;
import realtime.NoHeapRealtimeThread;
import realtime.PeriodicParameters;
import realtime.PriorityParameters;
import realtime.RealtimeThread;

/** 
 * This thread is the periodic real-time threads that periodically wakes-up to run the collision detector.
 * Its constructor runs in immortal memory. The instance lives in immortal memory. 
 * The thread itself runs in the persistent detector scope.
 */
public class PersistentDetectorScopeEntry extends NoHeapRealtimeThread {

	public PersistentDetectorScopeEntry(final PriorityParameters p, final PeriodicParameters q, final LTMemory l) {
		super(p, q, null, l, null, null);
	}

	public boolean stop = false;

	public void run() {

		NoiseGenerator noiseGenerator = new NoiseGenerator();
		final LTMemory transientDetectorScope = new LTMemory(immortal.Constants.TRANSIENT_DETECTOR_SCOPE_SIZE, immortal.Constants.TRANSIENT_DETECTOR_SCOPE_SIZE);
		try {
			final TransientDetectorScopeEntry cd = new TransientDetectorScopeEntry(new StateTable(), Constants.GOOD_VOXEL_SIZE);

			if (immortal.Constants.DEBUG_DETECTOR) {
				System.out.println("Detector thread is "+Thread.currentThread());
				System.out.println("Entering detector loop, detector thread priority is "+
						+Thread.currentThread().getPriority()+
						" (NORM_PRIORITY is "+Thread.NORM_PRIORITY+
						", MIN_PRIORITY is "+Thread.MIN_PRIORITY+
						", MAX_PRIORITY is "+Thread.MAX_PRIORITY+")");  
			}

			while (!stop) {

				for(;;) {
					boolean missed = !RealtimeThread.waitForNextPeriod();

					long now = NanoClock.now();

					if (ImmortalEntry.recordedDetectorReleaseTimes < ImmortalEntry.detectorReleaseTimes.length) {
						ImmortalEntry.detectorReportedMiss [ ImmortalEntry.recordedDetectorReleaseTimes ] = missed;
						ImmortalEntry.detectorReleaseTimes[ ImmortalEntry.recordedDetectorReleaseTimes ] = now;
						ImmortalEntry.recordedDetectorReleaseTimes++;
					}

					if (!missed) break;
					ImmortalEntry.reportedMissedPeriods ++;
				}

				runDetectorInScope(cd, transientDetectorScope, noiseGenerator);
			}
			System.out.println("Detector is finished, processed all frames.");

		} catch (final Throwable t) {
			throw new Error(t);
		}
	}

	/*
	public static void p(final String s) {
		System.out.println(s);
	}

	public static void report(final MemoryArea outer, final MemoryArea inner) {
		p("\t" + ImmortalMemory.instance() + " consumed: " + ImmortalMemory.instance().memoryConsumed() + " remain: "
				+ ImmortalMemory.instance().memoryRemaining());
		p("\t" + outer + " consumed: " + outer.memoryConsumed() + " remain: " + outer.memoryRemaining());
		p("\t" + inner + " consumed: " + inner.memoryConsumed() + " remain: " + inner.memoryRemaining());
	}
	 */


	public void runDetectorInScope(final TransientDetectorScopeEntry cd, final LTMemory transientDetectorScope, final NoiseGenerator noiseGenerator) {

		if (immortal.Constants.SYNCHRONOUS_DETECTOR) {
			FrameSynchronizer.waitForProducer();
		}

		final RawFrame f = immortal.ImmortalEntry.frameBuffer.getFrame();
		if (f == null) {
			ImmortalEntry.frameNotReadyCount++;
			return;
		}

		if ( (immortal.ImmortalEntry.framesProcessed + immortal.ImmortalEntry.droppedFrames) == immortal.Constants.MAX_FRAMES) {
			stop = true;
			return;
		}  // should not be needed, anyway

		final long heapFreeBefore = Runtime.getRuntime().freeMemory();
		final long timeBefore = NanoClock.now();

		noiseGenerator.generateNoiseIfEnabled();

		cd.setFrame(f);

		// actually runs the detection logic in the given scope
		transientDetectorScope.enter(cd);

		final long timeAfter = NanoClock.now();
		final long heapFreeAfter = Runtime.getRuntime().freeMemory();

		if (ImmortalEntry.recordedRuns < ImmortalEntry.maxDetectorRuns) {
			ImmortalEntry.timesBefore[ ImmortalEntry.recordedRuns ] = timeBefore;
			ImmortalEntry.timesAfter[ ImmortalEntry.recordedRuns ] = timeAfter;
			ImmortalEntry.heapFreeBefore[ ImmortalEntry.recordedRuns ] = heapFreeBefore;
			ImmortalEntry.heapFreeAfter[ ImmortalEntry.recordedRuns ] = heapFreeAfter;

			ImmortalEntry.recordedRuns ++;
		}

		immortal.ImmortalEntry.framesProcessed++;

		if ( (immortal.ImmortalEntry.framesProcessed + immortal.ImmortalEntry.droppedFrames) == immortal.Constants.MAX_FRAMES) {
			stop = true;
		}
	}

	public void start() {

		ImmortalEntry.detectorThreadStart = NanoClock.now();
		super.start();
	}
}