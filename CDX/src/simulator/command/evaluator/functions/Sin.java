package command.evaluator.functions;

import command.evaluator.ComplexNumber;
import command.evaluator.EvaluationContext;
import command.evaluator.Expr;
import command.evaluator.Function;

public class Sin extends Function {
	public Sin(Expr inside) {
		super(inside);
	}

	//Override
	public double getValue(double d, EvaluationContext ec) {
		return Math.sin(d);
	}

	//Override
	public ComplexNumber getValue(ComplexNumber n, EvaluationContext ec) {
		return new ComplexNumber(cosh(n.getImaginaryValue(), null) * Math.sin(n.getRealValue()), Math.cos(n
				.getRealValue())
				* sinh(n.getImaginaryValue(), null));
	}

	//Override
	public String getName() {
		return "sin";
	}

	//Override
	public Expr make(Expr e) {
		return new Sin(e);
	}

}
