package dump;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class DumpToJavaCode {

	public static final int NUMS_PER_LINE = 100;
	// input file
	// output file
	public static void main(String[] args) {

		try {
			DataInputStream ds = new DataInputStream(new FileInputStream(args[0]));		
			PrintWriter pw = new PrintWriter( new File(args[1]) );

			// the binary format:
			//   nframes <INT>
			//
			//   nplanes <INT> 1
			//   positions <FLOAT> nplanes*3
			//   lengths <INT> nplanes
			//   callsigns_length <INT> 1
			//   callsigns <BYTE> callsigns_length

			int nframes = ds.readInt();

			System.err.println("Will generate dump for "+nframes+" frames.");
			pw.println("// Automatically generated file from binary dump "+args[0]);
			pw.println();
			pw.println("package heap;");
			pw.println();
			pw.println("public class Simulator extends PrecompiledSimulator {");
			pw.println();
			pw.println("  public Simulator() {");
			pw.println("    positions = new Object["+nframes+"];");
			pw.println("    lengths = new Object["+nframes+"];");
			pw.println("    callsigns = new Object["+nframes+"];");
			pw.println();

			for(int frameIndex=0; frameIndex<nframes;frameIndex++) {

				int nplanes = ds.readInt();
				pw.print("    positions["+frameIndex+"] = new float[] { ");

				for(int i=0; i<nplanes; i++) {
					pw.print(ds.readFloat());
					pw.print("f, ");
					pw.print(ds.readFloat());
					pw.print("f, ");
					pw.print(ds.readFloat());
					pw.print("f");
					if ((i+1) < nplanes) {
						pw.print(", ");
						if (i*3 % NUMS_PER_LINE == NUMS_PER_LINE-1) {
							pw.println();
							pw.print("      ");
						}
					}
				}
				pw.println(" };");

				pw.print("    lengths["+frameIndex+"] = new int[] { ");
				for(int i=0;i<nplanes;i++) {
					pw.print(ds.readInt());
					if ((i+1) < nplanes) {
						pw.print(", ");
						if (i % NUMS_PER_LINE == NUMS_PER_LINE-1) {
							pw.println();
							pw.print("      ");
						}
					}

				}
				pw.println(" };");

				int callsigns_length = ds.readInt();

				pw.print("    callsigns["+frameIndex+"] = new byte[] { ");

				byte[] callsigns = new byte[callsigns_length];
				ds.read(callsigns);
				for(int i=0;i<callsigns.length;i++) {
					pw.print( callsigns[i] );
					if ((i+1) < callsigns.length) {
						pw.print(", ");
						if (i % NUMS_PER_LINE == NUMS_PER_LINE-1) {
							pw.println();
							pw.print("      ");
						}
					}
				}
				pw.println(" };");
			}			
			pw.println("  }");
			pw.println("}");
			pw.close();
			ds.close();

		} catch (FileNotFoundException e) {
			throw new RuntimeException("Cannot open file with frames binary dump "+e);
		} catch (IOException e) {
			throw new RuntimeException("Error reading frames binary dump "+e);
		}
		System.err.println("Done.");
	}

}
