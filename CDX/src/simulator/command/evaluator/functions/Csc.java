package command.evaluator.functions;

import command.evaluator.ComplexNumber;
import command.evaluator.EvaluationContext;
import command.evaluator.Expr;
import command.evaluator.Function;
import command.evaluator.NumberExpr;

public class Csc extends Function {
	public Csc(Expr inside) {
		super(inside);
	}

	//Override
	public double getValue(double d, EvaluationContext ec) {
		return 1 / Math.sin(d);
	}

	//Override
	public ComplexNumber getValue(ComplexNumber n, EvaluationContext ec) {
		ComplexNumber sinPart = new ComplexNumber(cosh(n.getImaginaryValue(), null) * Math.sin(n.getRealValue()), Math
				.cos(n.getRealValue())
				* sinh(n.getImaginaryValue(), null));
		return (ComplexNumber) NumberExpr.ONE.divide(sinPart);
	}

	//Override
	public String getName() {
		return "csc";
	}
	//Override
	public Expr make(Expr e) { return new Csc(e); }

}
