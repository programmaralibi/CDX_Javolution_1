package heap;

/**
 * The <code>BaseSimulationRegion</code> class tracks the airports and no fly
 * zones that are part of a simulation region. It encapsulates this information
 * for both the simulator and any client code that may be interested in
 * collision detection, for example.
 * 
 * @author Ben L. Titzer
 */
class SimulationRegion {

	private final RectangularRegion region;

	/**
	 * The constructor for the <code>SimulatorRegion</code> class takes
	 * parameters describing the region that the simulation covers, the airports
	 * that lie within the region, and the no-fly zones that have been
	 * established within the region.
	 * 
	 * @param r
	 *            the <code>RectangularRegion</code> object that represents
	 *            the legal extent of the simulation
	 * @param a
	 *            an array of <code>Airport</code> objects that represent the
	 *            position and characteristics of the airports in the simulation
	 * @param nf
	 *            an array of <code>Region</code> objects that represent the
	 *            regions where no airplanes are permitted
	 */
	public SimulationRegion(RectangularRegion r, SimAirport[] a, Region[] nf) {
		region = r;
	}

	/**
	 * The <code>getLegalRegion</code> method returns a
	 * <code>RectangularRegion</code> object that describes the legal region
	 * in which the simulation takes place.
	 * 
	 * @returns the <code>RectangularRegion</code> that encloses the
	 *          simulation region
	 */
	public RectangularRegion getLegalRegion() {
		return region;
	}
}
