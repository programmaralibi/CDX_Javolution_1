package heap;

/**
 * The <code>RectangularRegion</code> class encapsulates the idea of a region
 * shaped as a rectangle. It is an implementation of the <code>Region</code>
 * interface, and thus can be used within the simulation to represent a region
 * for any particular purpose.
 * 
 * @author Ben L. Titzer
 */
public class RectangularRegion implements Region {

	public final float min_x;
	public final float min_y;
	public final float max_x;
	public final float max_y;

	protected int hashcode;

	/**
	 * The constructor for the <code>RectangularRegion</code> class constructs
	 * a new region that represents a rectanglar region within the three
	 * dimensional space. The limits are on the xy plane: the z portion of the
	 * region is unbounded.
	 * 
	 * @param min_x
	 *            the minimum x coordinate
	 * @param min_y
	 *            the minimum y coordinate
	 * @param max_x
	 *            the maximum x coordinate
	 * @param max_y
	 *            the maximum y coordinate
	 */
	public RectangularRegion(float min_x, float min_y, float max_x, float max_y) {
		this.min_x = min_x;
		this.min_y = min_y;
		this.max_x = max_x;
		this.max_y = max_y;
	}

	/**
	 * The <code>contains</code> method determines whether a specified point
	 * lies within the region.
	 * 
	 * @param point
	 *            the point which might be contained in the region
	 * @returns true if this region contains the point specified
	 */
	public boolean contains(Vector3d point) {
		if (point.x > max_x)
			return false;
		if (point.y > max_y)
			return false;
		if (point.x < min_x)
			return false;
		if (point.y < min_y)
			return false;
		return true;
	}

	/**
	 * The <code>distance</code> method determines the shortest distance from
	 * the given point to the edge of the region. In the case where the point is
	 * contained in the region, this method will return 0.
	 * 
	 * @param point
	 *            the point to calculate the distance from
	 * @returns the shortest straight line distance from this point to the edge
	 *          of the region, 0 if the point is contained in the region
	 */
	public float distance(Vector3d point) {
		float xl = min_x - point.x, xr = point.x - max_x;
		float yl = min_y - point.y, yr = point.y - max_y;

		// lies between x bounds
		if ((xl * xr) >= 0) {
			if ((yl * yr) >= 0)
				return 0; // contains
			return max(yl, yr);
		}

		// lies between y bounds
		if ((yl * yr) >= 0) {
			return max(xl, xr);
		}

		// corner region
		xl = max(xl, xr);
		yl = max(yl, yr);
		return (float) Math.sqrt(xl * xl + yl * yl);
	}

	// Override
	public boolean equals(Object o) throws ClassCastException {
		try {
			return equals((RectangularRegion) o);
		} catch (ClassCastException e) {
			return false;
		}
	}

	public boolean equals(RectangularRegion r) {
		if (r.min_x != min_x)
			return false;
		if (r.min_y != min_y)
			return false;
		if (r.max_x != max_x)
			return false;
		if (r.max_y != max_y)
			return false;
		return true;
	}

	// Override
	public int hashCode() {
		if (hashcode == 0) {
			int mx = (int) ((min_x * 67) + (max_x * 31));
			int my = (int) ((min_y * 59) + (max_y * 23));
			// TODO: is this an efficient hash code?
			hashcode = mx * my + mx + my + 145;
		}
		return hashcode;
	}

	private float max(float a, float b) {
		return (a > b) ? a : b;
	}
}
