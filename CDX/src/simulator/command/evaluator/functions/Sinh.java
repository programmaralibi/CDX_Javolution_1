package command.evaluator.functions;

import command.evaluator.ComplexNumber;
import command.evaluator.EvaluationContext;
import command.evaluator.Expr;
import command.evaluator.Function;

public class Sinh extends Function {
	public Sinh(Expr inside) {
		super(inside);
	}

	//Override
	public double getValue(double d, EvaluationContext ec) {
		return (Math.exp(d) - Math.exp(-d)) / 2;
	}

	//Override
	public ComplexNumber getValue(ComplexNumber n, EvaluationContext ec) {
		return new ComplexNumber(sinh(n.getRealValue(), null) * Math.cos(n.getImaginaryValue()), cosh(n.getRealValue(),
				null)
				* Math.sin(n.getImaginaryValue()));
	}

	//Override
	public String getName() {
		return "sinh";
	}
	//Override
	public Expr make(Expr e) { return new Sinh(e); }

}
