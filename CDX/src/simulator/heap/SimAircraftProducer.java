package heap;

import javacp.util.Enumeration;
import javacp.util.Vector;

import command.evaluator.EvaluationContext;

class SimAircraftProducer {

	Vector test_aircrafts = new Vector();
	EvaluationContext e = new EvaluationContext();
	double cur_time;

	/**
	 * Call this to add an aircraft; expression is an array of parametrizations
	 * in x, y, and z.
	 */
	public void addAircraft(String call_sign, String[] expression,
			SimAirport source, SimAirport destination) throws Exception {
		// System.out.println("call_sign = "+call_sign);
		test_aircrafts.addElement(new SimAircraft(call_sign, e, expression,
				source, destination));
	}

	public void addInitialAircraft(SimulationRegion region,
			FrameFactory.Builder builder) {
		cur_time = 0.0;
		e.setVarValue("t", EvaluationContext.TYPE_NUMBER, cur_time);
		Enumeration crafts = test_aircrafts.elements();
		while (crafts.hasMoreElements()) {
			SimAircraft craft = (SimAircraft) crafts.nextElement();
			builder.addPosition(craft, craft.evaluatePosition(e));
		}
	}

	public void addAircraft(SimulationRegion region, SimFrame prev,
			FrameFactory.Builder builder, double delta) {
	}

	public void computeNewPositions(SimulationRegion region,
			FrameFactory.Mutator mutator, double delta) {
		cur_time += delta;
		e.setVarValue("t", EvaluationContext.TYPE_NUMBER, cur_time);

		Vector3d vec = new Vector3d();
		while (mutator.hasNext()) {
			SimAircraft craft = mutator.getCurrent(vec);
			Vector3d new_pos = craft.evaluatePosition(e);
			mutator.updateCurrent(new_pos);
			mutator.advance();
		}

	}

}
