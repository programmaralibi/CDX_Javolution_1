
package realtime;

public class MemoryArea extends MemoryAreaWrapper {

	private final javax.realtime.MemoryArea realArea;

	boolean isReal() {
		return true;
	}


	Object getRealArea() {
		return realArea;
	}

	protected MemoryArea ( javax.realtime.MemoryArea realArea ) {
		this.realArea = realArea;
	}

	public static MemoryArea getMemoryArea(Object o) {
		return new MemoryArea( javax.realtime.MemoryArea.getMemoryArea(o) );
		// note that we cannot easily have a HashMap mapping RTSJ Area -> Wrapper Area,
		// because of memory reference restrictions ... where would the HashMap live ?

		// maybe we could instead somehow extend RTSJ areas to hold the reference to the
		// Wrapper Area ; maybe it would confuse the RTSJ implementation, I don't know
	}

	public void executeInArea(Runnable logic) {
		realArea.executeInArea( logic );
	}

	public void enter(Runnable logic) {
		realArea.enter(logic);
	}

	public long memoryConsumed() {
		return realArea.memoryConsumed();
	}

	public long memoryRemaining() {
		return realArea.memoryRemaining();
	}

	public Object newInstance(Class type) throws IllegalAccessException,
	InstantiationException  {

		return realArea.newInstance(type);
	}

	public String toString() {
		return "proxy memory area of "+realArea.toString();
	}

	public int hashCode() {
		return realArea.hashCode();
	}

	public boolean equals(Object obj) {

		if (obj instanceof MemoryArea) {
			return realArea.equals( ((MemoryArea)obj).realArea);
		} else {
			return realArea.equals(obj);  
		}
	}

}
