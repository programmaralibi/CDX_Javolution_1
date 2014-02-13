package command.evaluator.functions;

import command.evaluator.ComplexNumber;
import command.evaluator.EvaluationContext;
import command.evaluator.Expr;
import command.evaluator.Function;

public class Tan extends Function {
	public Tan(Expr inside) {
		super(inside);
	}

	//Override
	public double getValue(double v, EvaluationContext ec) {
		return Math.tan(v);
	}

	//Override
	public ComplexNumber getValue(ComplexNumber n, EvaluationContext ec) {
		ComplexNumber cosPart = new ComplexNumber(cosh(n.getImaginaryValue(), null) * Math.cos(n.getRealValue()), -1
				* Math.sin(n.getRealValue()) * sinh(n.getImaginaryValue(), null));
		ComplexNumber sinPart = new ComplexNumber(cosh(n.getImaginaryValue(), null) * Math.sin(n.getRealValue()), Math
				.cos(n.getRealValue())
				* sinh(n.getImaginaryValue(), null));
		return sinPart.divide(cosPart);
	}
	//Override
	public Expr make(Expr e) { return new Tan(e); }

	//Override
	public String getName() {
		return "Tan";
	}
}
