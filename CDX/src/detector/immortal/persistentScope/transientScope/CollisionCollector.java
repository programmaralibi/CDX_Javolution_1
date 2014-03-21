package immortal.persistentScope.transientScope;

import javolution.util.FastSet;
import javolution.util.FastTable;

/**
 * Collects collisions in lists and then returns a list of collisions where
 * no two are equal.
 * @author Filip Pizlo
 */
class CollisionCollector {
	/** A hash set of collisions.  */
	private FastSet collisions = new FastSet();

	/** Add some collisions.  */
	public void addCollisions(FastTable collisions) {
		this.collisions.addAll(collisions); 
	}

	/** Get the list of collisions.   */
	public FastTable getCollisions() {
		FastTable table = new FastTable();
		table.addAll(collisions);  
		return table;
	}
}
