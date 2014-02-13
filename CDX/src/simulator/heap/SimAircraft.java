package heap;

import command.evaluator.EvaluationContext;
import command.evaluator.Expr;
import command.evaluator.NumberExpr;
import command.evaluator.StmtListExpr;

/**
 * The <code>BaseAircraft</code> class is a default implementation of aircraft
 * in the simulation. This class implements the basic functionality of the
 * <code>Aircraft</code> interface, with no frills attached. It records the
 * optimal speed and altitude of the aircraft as well as its source and
 * destination airports.
 * 
 * @author Ben L. Titzer
 */
public class SimAircraft {

	public static final float DEFAULT_OPTIMAL_SPEED = 600;
	public static final float DEFAULT_OPTIMAL_ALTITUDE = 9;
	protected String callsign;
	protected SimAirport destination;
	protected float speed;
	protected float altitude;
	protected Expr[] expression;
	protected EvaluationContext ec;
	protected Vector3d last_position;

	public SimAircraft(String callsign, EvaluationContext e,
			String[] expression_, SimAirport source, SimAirport destination)
			throws Exception {
		this(callsign, source, destination);
		expression = new Expr[expression_.length];
		for (int i = 0; i < expression_.length; i++) {
			Expr exp = ((StmtListExpr) e.createTree(expression_[i])).getExpr();
			expression[i] = exp.simplify(e);
		}
	}

	/**
	 * The constructor for the <code>BaseAircraft</code> class implements the
	 * basic functionality of an aircraft in the simulation. It takes parameters
	 * that describe the callsign of the aircraft, its starting and ending
	 * destinations, its optimal cruising speed, and its optimal cruising
	 * altitude.
	 * 
	 * @param callsign
	 *            the unique string identifying this aircraft
	 * @param source
	 *            the airport that represents the source of this aircraft
	 * @param destination
	 *            the airport that represents the destination of this aircraft
	 */
	protected SimAircraft(String callsign, SimAirport source,
			SimAirport destination) {
		this.callsign = callsign;
		this.destination = destination;
		this.speed = DEFAULT_OPTIMAL_SPEED;
		this.altitude = DEFAULT_OPTIMAL_ALTITUDE;
	}

	public Vector3d evaluatePosition(EvaluationContext e)
			throws NumberFormatException {
		float[] result = new float[expression.length];
		for (int i = 0; i < result.length; i++) {
			Expr cur_res = expression[i].evaluate(e);
			if (!(cur_res instanceof NumberExpr))
				throw new NumberFormatException("Got type: "
						+ cur_res.getClass().toString());
			result[i] = (float) ((NumberExpr) cur_res).getRealValue();
		}
		return last_position = new Vector3d(result[0], result[1], result[2]);
	}

	// Override
	public String toString() {
		return getCallSign() + "@" + last_position;
	}

	/**
	 * The <code>getCallSign</code> method of the Aircraft class returns a
	 * unique string identifying this aircraft. This string is guaranteed to be
	 * unique from all other callsigns of aircraft currently in the simulation.
	 * 
	 * @returns the callsign of the aircraft
	 */
	public String getCallSign() {
		return callsign;
	}

	/**
	 * The <code>getDestination</code> method of the Aircraft class returns a
	 * reference to the <code>Airport</code> at which this aircraft intends to
	 * land.
	 * 
	 * @returns a reference to the destination airport
	 */
	public SimAirport getDestination() {
		return destination;
	}
}
