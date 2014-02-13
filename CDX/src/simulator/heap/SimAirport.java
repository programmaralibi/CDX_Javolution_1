package heap;

/**
 * This class encapsulates an airport within the simulation, including its
 * position on the map as well as its incoming (planes per hour) capacity.
 * 
 * @author Ben L. Titzer
 */
class SimAirport {

	protected final Vector2d position;
	protected final int capacity;
	protected final Region noflyzone;
	protected final String code;

	/**
	 * The constructor for the <code>BaseAircraft</code> class takes an
	 * airport code, a 2d position, a no-fly region, and a capacity as arguments
	 * and produces a new instance.
	 * 
	 * @param code
	 *            the unique string name of this airport
	 * @param pos
	 *            the 2d center of the airport
	 * @param nofly
	 */
	public SimAirport(String code, Vector2d pos, Region nofly, int cap) {
		this.position = new Vector2d(pos);
		this.noflyzone = nofly;
		this.code = code;

		if (!nofly.contains(new Vector3d(pos.x, pos.y, 0)))
			throw new IllegalArgumentException(
					"Invalid centerpoint: not contained in no-fly zone");
		if (cap < 1)
			throw new IllegalArgumentException(
					"Invalid airport landing capacity");

		this.capacity = cap;
	}

	/**
	 * The <code>getAirportCode</code> method returns a unique string
	 * identifying the identity of this airport. It is guaranteed to be unique
	 * across the airports in the simulation.
	 * 
	 * @returns a string representing the name of the airport
	 */
	public String getAirportCode() {
		return code;
	}

	/**
	 * The <code>getNoFlyZone</code> method returns the region that describes
	 * the no-fly zone surrounding the airport.
	 * 
	 * @returns an instance of the <code>Region</code> interface that
	 *          describes the no-fly zone
	 */
	public Region getNoFlyZone() {
		return noflyzone;
	}

	/**
	 * The <code>contains</code> method determines whether a position lies
	 * within the airport's region. This allows the Simulator to detect when an
	 * aircraft has landed within at the airport
	 * 
	 * @param pos
	 *            a 3d vector describing the point which to test
	 * @returns true if the point is contained within the Airport's region
	 * @returns false if the point is not contained within the Airport's region
	 */
	public boolean contains(Vector3d pos) {
		return noflyzone.contains(pos);
	}

	/**
	 * The <code>capacity</code> method returns the landing capacity of the
	 * airport that represents the maximum number of aircraft that can land per
	 * hour.
	 */
	public int capacity() {
		return capacity;
	}

	public Vector2d getPosition() {
		return position;
	}

}
