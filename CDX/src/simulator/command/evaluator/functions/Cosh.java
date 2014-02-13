package command.evaluator.functions;

import command.evaluator.ComplexNumber;
import command.evaluator.EvaluationContext;
import command.evaluator.Expr;
import command.evaluator.Function;

public class Cosh extends Function {
	public Cosh(Expr inside) {
		super(inside);
	}

	//Override
	public double getValue(double d, EvaluationContext ec) {
		return (Math.exp(d) + Math.exp(-d)) / 2;
	}

	//Override
	public ComplexNumber getValue(ComplexNumber c, EvaluationContext ec) {
		return new ComplexNumber(cosh(c.getRealValue(), null) * Math.cos(c.getImaginaryValue()), sinh(c.getRealValue(),
				null)
				* Math.sin(c.getImaginaryValue()));
	}
	//Override
	public Expr make(Expr e) { return new Cosh(e); }

	//Override
	public String getName() {
		return "cosh";
	}

}
