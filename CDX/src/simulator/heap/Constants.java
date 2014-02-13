package heap;

/**
 * The <code>Constants</code> class encapsulates the constants in the
 * simulation, such as how large the simulation region is, maximum altitude,
 * maximum velocity, framerates, etc.
 * 
 * @author Ben L. Titzer
 */
final class Constants {

	public static final float MAX_X_EXTENT = 5000; // maximum map size in x
													// direction (km)
	public static final float MAX_Y_EXTENT = 5000; // maximum map size in y
													// direction (km)
	public static final float MAX_ALTITUDE = 12; // maximum flight altitude
													// (km)
	public static final float PROXIMITY_RADIUS = 1; // radius of an aircraft

	public static final float MAX_SPEED = 1000; // maximum speed in km/h

	public static final int MIN_FPS = 1; // frames per second
	public static final int MAX_FPS = 1000; // frames per second
	public static final int MIN_CRAFT = 5; // minimum number of craft that must
											// be supported
	public static final int MAX_CRAFT = 4096; // maximum number of craft in
												// any simulation

	public static final float MIN_TIMESCALE = 1; // speed relative to reality
	public static final float MAX_TIMESCALE = 1000; // speed relative to reality

}
