package heap;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.StringTokenizer;

public class SimMediator {
	public static final FrameFactory frameFactory = new FrameFactory();
	public static final int DEFAULT_RESOLUTION = 600;
	public static final float MIN_Y = 0.0f;
	public static final float MIN_X = 0.0f;
	public static final float MAX_Y = 1000.0f;
	public static final float MAX_X = 1000.0f;

	public Simulator buildSimulator(final String s) {
		final SimAircraftProducer prod = new SimAircraftProducer();
		final float param_min_x = MIN_X;
		final float param_min_y = MIN_Y;
		final float param_max_x = MAX_X;
		final float param_max_y = MAX_Y;
		final Simulator.Parameters params = new Simulator.Parameters();
		params.fps = immortal.Constants.FPS;
		params.max_craft = Constants.MAX_CRAFT;
		params.timescale = immortal.Constants.TIME_SCALE;
		params.max_frames = immortal.Constants.MAX_FRAMES;

		MasterAirportCollection.readAirportAndNoFlyConfig("sim.conf");
		createSimulation(s, prod);

		final SimulationRegion region = new SimulationRegion(
				new RectangularRegion(param_min_x, param_min_y, param_max_x,
						param_max_y), null, null);
		params.director = prod;
		params.producer = prod;
		params.region = region;
		return new Simulator(params);
	}

	// SuppressWarnings("deprecation")
	protected static void createSimulation(final String filename,
			final SimAircraftProducer prod) {
		DataInputStream fin = null;
		try {
			fin = new DataInputStream(new FileInputStream(filename));
		} catch (final FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (true) {
			String line = null;
			try {
				line = fin.readLine();
			} catch (final IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (line == null)
				break;
			final StringTokenizer tox = new StringTokenizer(line, "\t");
			if (!tox.hasMoreElements())
				break;
			final String command = tox.nextToken();
			final String name = tox.nextToken();
			final String[] param = new String[] { tox.nextToken(),
					tox.nextToken(), tox.nextToken() };
			try {
				prod.addAircraft(name, param, MasterAirportCollection
						.lookup(tox.nextToken()), MasterAirportCollection
						.lookup(tox.nextToken()));
			} catch (final Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
