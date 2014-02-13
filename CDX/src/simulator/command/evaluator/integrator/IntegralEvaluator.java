package command.evaluator.integrator;

import command.evaluator.EvaluationContext;
import command.evaluator.Expr;
import command.evaluator.NumberExpr;
import command.evaluator.RealNumber;

public class IntegralEvaluator extends Integrator {
	private Expr func;
	private NumberExpr lowerBound;
	private NumberExpr upperBound;
	// private EvaluationContext ec;
	private String varName;
	private int expandOption;

	public IntegralEvaluator(Expr expression, String variable, NumberExpr lowerBound, NumberExpr upperBound,
			EvaluationContext ec, int expandOption) {
		super(ec);
		this.func = expression;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		// this.ec = ec;
		this.varName = variable;
		this.expandOption = expandOption;
	}

	public NumberExpr integrate() {
		return RealNumber.createRealNumber(integrate(lowerBound.getValue(), upperBound.getValue()));
	}

	//Override
	protected void beforeIntegration() {
		ec.beginVarScope();
	}

	//Override
	protected void afterIntegration() {
		ec.endVarScope();
	}

	//Override
	public double function(double param) {
		ec.setVarValue(varName, RealNumber.createRealNumber(param), EvaluationContext.TYPE_NUMBER, EvaluationContext.VAR_EXPAND_ALL);
		Expr simplified = func.simplify(ec, expandOption);
		if (!(simplified instanceof NumberExpr)) { throw new command.evaluator.EvaluationException(
				"The function you are trying to integrate contains undefined variables."); }
		return ((NumberExpr) simplified).getValue();
	}
}
