package heap;

import java.util.NoSuchElementException;

/**
 * The <code>BaseFrame</code> class is the default implementation of the
 * <code>Frame</code> interface that the Simulator uses to represent the
 * positions of aircraft within the simulation at specific instant of time.
 * 
 * @author Ben L. Titzer
 */
public class SimFrame {

	public final SimAircraft aircraft[];
	public final Vector3d positions[];
	protected int used;
	protected double timestamp;

	/**
	 * The constructor for the <code>BaseFrame</code> class takes the maximum
	 * number of aircraft and constructs a new frame to represent the positions
	 * of the aircraft.
	 * 
	 * @param max_craft
	 *            the maximum number of aircraft in the simulation at any given
	 *            time
	 * @param timestamp
	 *            the timestamp representing what time this frame corresponds to
	 *            in the simulation
	 */
	SimFrame(int max_craft, double timestamp) {
		this.aircraft = new SimAircraft[max_craft];
		this.positions = new Vector3d[max_craft];
		this.timestamp = timestamp;
	}

	public class PositionIterator {
		protected int cursor;

		public boolean hasNext() {
			return cursor < used;
		}

		public SimAircraft next(Vector3d vec) throws NoSuchElementException {
			if (cursor >= used)
				throw new NoSuchElementException("Iterator exhausted.");
			int which = cursor++;
			vec.set(positions[which]);
			return aircraft[which];
		}
	}

	/**
	 * The <code>iterator</code> method returns an iterator for this frame
	 * that will iterate over all of the positions of aircraft recorded in this
	 * frame.
	 */
	public SimFrame.PositionIterator iterator() {
		return new PositionIterator();
	}

}