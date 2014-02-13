package command.evaluator.functions;

import command.evaluator.ComplexNumber;
import command.evaluator.EvaluationContext;
import command.evaluator.Expr;
import command.evaluator.Function;

public class Cot extends Function {
	public Cot(Expr inside) {
		super(inside);
	}

	//Override
	public double getValue(double d, EvaluationContext ec) {
		return 1 / Math.tan(d);
	}

	//Override
	public ComplexNumber getValue(ComplexNumber n, EvaluationContext ec) {
		ComplexNumber cosPart = new ComplexNumber(cosh(n.getImaginaryValue(), null) * Math.cos(n.getRealValue()), -1
				* Math.sin(n.getRealValue()) * sinh(n.getImaginaryValue(), null));
		ComplexNumber sinPart = new ComplexNumber(cosh(n.getImaginaryValue(), null) * Math.sin(n.getRealValue()), Math
				.cos(n.getRealValue())
				* sinh(n.getImaginaryValue(), null));
		return cosPart.divide(sinPart);
	}

	//Override
	public String getName() {
		return "cot";
	}
	//Override
	public Expr make(Expr e) { return new Cot(e); }

}
