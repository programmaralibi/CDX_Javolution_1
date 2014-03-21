package immortal.persistentScope.transientScope;

import java.util.ListIterator;

import javolution.util.FastTable;

/**
 * Represents a definite collision that has occured.
 * @author Filip Pizlo
 */
class Collision {
	/** The aircraft that were involved.  */
	private FastTable aircraft;

	/** The location where the collision happened. */
	private Vector3d location;

	/** Construct a Collision with a given set of aircraft and a location.  */
	public Collision(FastTable aircraft, Vector3d location) {
/*		this.aircraft = new FastTable(aircraft);
		Collections.sort(this.aircraft);
		this.location = location;
*/
		this.aircraft = FastTable.of(aircraft);
		this.aircraft.sort();
		this.location = location;
	}

	/** Construct a Coollision with two aircraft an a location. */
	public Collision(Aircraft one, Aircraft two, Vector3d location) {
		aircraft = new FastTable();
		aircraft.add(one);
		aircraft.add(two);
		aircraft.sort();
		this.location = location;
	}

	/** Returns the list of aircraft involved. You are not to modify this list. */
	public FastTable getAircraftInvolved() { return aircraft; }

	/** Returns the location of the collision. You are not to modify this location. */
	public Vector3d getLocation() { return location; }

	/** Returns a hash code for this object. It is based on the hash codes of the aircraft. */

	public int hashCode() {
		int ret = 0;
		for (ListIterator iter = aircraft.listIterator(); iter.hasNext();) 
			ret += ((Aircraft) iter.next()).hashCode();	
		return ret;
	}

	/** Determines collision equality. Two collisions are equal if they have the same aircraft.*/

	public boolean equals(Object _other) {
		if (_other == this)  return true;
		if (!(_other instanceof Collision)) return false;
		Collision other = (Collision) _other;
		FastTable a = getAircraftInvolved();
		FastTable b = other.getAircraftInvolved();
		if (a.size() != b.size()) return false;
		ListIterator ai = a.listIterator();
		ListIterator bi = b.listIterator();
		while (ai.hasNext()) 
			if (!ai.next().equals(bi.next())) return false;		
		return true;
	}

	/** Returns a helpful description of this object. */

	public String toString() {
		StringBuffer buf = new StringBuffer("Collision between ");
		boolean first = true;
		for (ListIterator iter = getAircraftInvolved().listIterator(); iter.hasNext();) {
			if (first) first = false;
			else buf.append(", ");	    
			buf.append(iter.next().toString());
		}
		buf.append(" at "); buf.append(location.toString());
		return buf.toString();
	}
}
