package command.evaluator.functions;

import command.evaluator.ComplexNumber;
import command.evaluator.EvaluationContext;
import command.evaluator.Expr;
import command.evaluator.Function;

public class Cos extends Function {
	public Cos(Expr inside) {
		super(inside);
	}

	//Override
	public double getValue(double d, EvaluationContext ec) {
		return Math.cos(d);
	}

	//Override
	public ComplexNumber getValue(ComplexNumber c, EvaluationContext ec) {
		return new ComplexNumber(cosh(c.getImaginaryValue(), null) * Math.cos(c.getRealValue()), -1
				* Math.sin(c.getRealValue()) * sinh(c.getImaginaryValue(), null));
	}

	//Override
	public String getName() {
		return "Cos";
	}
	//Override
	public Expr make(Expr e) { return new Cos(e); }

}
