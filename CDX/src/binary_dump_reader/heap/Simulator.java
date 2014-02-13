package heap;

import immortal.ImmortalEntry;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Simulator {

	public static void dumpStats() {}

	// args[0] ... file with the binary dump
	public static void generate(final String[] args) {

		synchronized (ImmortalEntry.initMonitor) {

			if (!immortal.Constants.PRESIMULATE) {
				ImmortalEntry.simulatorReady = true;
				ImmortalEntry.initMonitor.notifyAll();
			}

			while (!ImmortalEntry.detectorReady) {
				try {
					ImmortalEntry.initMonitor.wait();
				} catch (InterruptedException e) {
				}
			}
		}


		try {
			DataInputStream ds = new DataInputStream(new FileInputStream(args[0]));		

			int nframes = ds.readInt();

			if (nframes < immortal.Constants.MAX_FRAMES) {
				throw new RuntimeException("Not enough frames in binary dump.");
			}

			// the binary format:
			//   nframes <INT>
			//
			//   nplanes <INT> 1
			//   positions <FLOAT> nplanes*3
			//   lengths <INT> nplanes
			//   callsigns_length <INT> 1
			//   callsigns <BYTE> callsigns_length


			for(int frameIndex=0; frameIndex<immortal.Constants.MAX_FRAMES;frameIndex++) {

				int nplanes = ds.readInt();
				float[] positions = new float[nplanes*3];
				for(int i=0; i<nplanes; i++) {
					positions[3*i] = ds.readFloat();
					positions[3*i+1] = ds.readFloat();
					positions[3*i+2] = ds.readFloat();
				}
				int[] lengths = new int[nplanes];
				for(int i=0;i<nplanes;i++) {
					lengths[i] = ds.readInt();
				}
				int callsigns_length = ds.readInt();
				byte[] callsigns = new byte[callsigns_length];
				ds.read(callsigns);

				immortal.ImmortalEntry.frameBuffer.putFrame( positions, lengths, callsigns);
			}

			ds.close();

		} catch (FileNotFoundException e) {
			throw new RuntimeException("Cannot open file with frames binary dump "+e);
		} catch (IOException e) {
			throw new RuntimeException("Error reading frames binary dump "+e);
		}

		if (immortal.Constants.PRESIMULATE) {
			synchronized (ImmortalEntry.initMonitor) {
				ImmortalEntry.simulatorReady = true;
				ImmortalEntry.initMonitor.notifyAll();
			}
		}
	}
}

