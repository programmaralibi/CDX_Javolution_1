package heap;

/**
 * The <code>CylindricalRegion</code> class represents a region in the 3d
 * space enclosed by a cylinder. The cylinder is oriented such that the axis of
 * the cylinder is parallel to the z-axis: thus it is a cylinder sitting upright
 * on the xy plane.
 * 
 * @author Ben L. Titzer
 */
class ReadableCylindricalRegion implements Region {
	private final Vector2d center;
	private final float radius;
	private final float radiusSq;
	private final float min_z;
	private final float max_z;
	protected int hashcode;

	/**
	 * The constructor for the <code>CylindricalRegion</code> class constructs
	 * a new region that represents a cylinder within the three dimensional
	 * space of the simulation region. It takes parameters that describe the
	 * center, radius, and height of the cylinder.
	 * 
	 * @param center
	 *            the center of the cylinder in the XY plane
	 * @param radius
	 *            the radius of the cylinder
	 * @param min_z
	 *            the minimum z coordinate of the cylinder
	 * @param max_z
	 *            the maximum z coordinate of the cylinder
	 */
	public ReadableCylindricalRegion(Vector2d center, float radius,
			float min_z, float max_z) {
		this.center = new Vector2d(center);
		this.radius = radius;
		this.radiusSq = radius * radius;
		this.min_z = min_z;
		this.max_z = max_z;
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
		if (point.z < min_z)
			return false;
		if (point.z > max_z)
			return false;
		if (VectorMath.sqDistance(center, point) > radiusSq)
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
		float d = VectorMath.sqDistance(center, point);
		float zl = min_z - point.z, zr = point.z - max_z;

		// above, below, or inside the cylinder
		if (d <= radiusSq) {
			if (zl * zr >= 0)
				return 0; // inside cylinder
			return max(zl, zr); // above or below
		}

		// to the side of the cylinder
		d = (float) (Math.sqrt(d) - radius);
		if (zl * zr >= 0)
			return d;

		// diagonal to the cylinder
		float zm = max(zr, zl);
		return (float) Math.sqrt(d * d + zm * zm);
	}

	// Override
	public boolean equals(Object o) {
		try {
			return equals((ReadableCylindricalRegion) o);
		} catch (ClassCastException e) {
			return false;
		}
	}

	public boolean equals(ReadableCylindricalRegion s) {
		if (!s.center.equals(this.center))
			return false;
		if (s.radius != this.radius)
			return false;
		if (s.min_z != this.min_z)
			return false;
		if (s.max_z != this.max_z)
			return false;
		return true;
	}

	// Override
	public int hashCode() {
		if (hashcode == 0) {
			// TODO: is this an efficient hash code?
			hashcode = center.hashCode()
					+ (int) (33 * (radius + min_z + max_z));
		}
		return hashcode;
	}

	private float max(float a, float b) {
		return (a > b) ? a : b;
	}

	public void getPosition(Vector2d dest) {
		dest.x = center.x;
		dest.y = center.y;
	}

	public float getRadius() {
		return radius;
	}
}
