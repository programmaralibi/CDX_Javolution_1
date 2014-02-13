package command.evaluator.functions;

import command.evaluator.ComplexNumber;
import command.evaluator.EvaluationContext;
import command.evaluator.EvaluationException;
import command.evaluator.Expr;
import command.evaluator.Function;

public class Coth extends Function {
	public Coth(Expr inside) {
		super(inside);
	}

	//Override
	public double getValue(double d, EvaluationContext ec) {
		return (Math.exp(d) + Math.exp(-d)) / (Math.exp(d) - Math.exp(-d));
	}

	//Override
	public ComplexNumber getValue(ComplexNumber c, EvaluationContext ec) {
		throw new EvaluationException("Not implemented yet.");
	}

	//Override
	public String getName() {
		return "coth";
	}
	//Override
	public Expr make(Expr e) { return new Coth(e); }

}
