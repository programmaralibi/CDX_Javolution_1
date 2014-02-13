package heap;

/**
 * The <code>ZeroVectorException</code> exception is thrown by utilities that
 * perform calculations on vectors. If a particular operation is undefined for
 * the zero vector, a <code>ZeroVectorException</code> exception is thrown.
 * The motivation for this class (as opposed to using a standard
 * <code>ArithmeticException</code>) is to allow filtering based on the type
 * of the exception.
 * 
 * @author Ben L. Titzer
 */
public class ZeroVectorException extends ArithmeticException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7813060674441411059L;

	/**
	 * The only constructor for the <code>ZeroVectorException</code> class
	 * takes a string as an argument and simply calls the super constructor.
	 * 
	 * @param msg
	 *            a message describing the operation that caused the exception
	 */
	public ZeroVectorException(String msg) {
		super(msg);
	}
}
