package command.evaluator.functions;

import command.evaluator.ComplexNumber;
import command.evaluator.EvaluationContext;
import command.evaluator.EvaluationException;
import command.evaluator.Expr;
import command.evaluator.Function;

public class Tanh extends Function {
	public Tanh(Expr inside) {
		super(inside);
	}

	//Override
	public double getValue(double d, EvaluationContext ec) {
		return (Math.exp(d) - Math.exp(-d)) / (Math.exp(d) + Math.exp(-d));
	}

	//Override
	public ComplexNumber getValue(ComplexNumber n, EvaluationContext ec) {
		throw new EvaluationException("Not implemented yet.");
	}
	//Override
	public Expr make(Expr e) { return new Tanh(e); }

	//Override
	public String getName() {
		return "tanh";
	}
}
