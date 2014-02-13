package command.evaluator.functions;

import command.evaluator.ComplexNumber;
import command.evaluator.EvaluationContext;
import command.evaluator.Expr;
import command.evaluator.Function;
import command.evaluator.NumberExpr;

public class Sec extends Function {
	public Sec(Expr inside) {
		super(inside);
	}

	//Override
	public double getValue(double d, EvaluationContext ec) {
		return 1 / Math.cos(d);
	}

	//Override
	public ComplexNumber getValue(ComplexNumber n, EvaluationContext ec) {
		ComplexNumber cosPart = new ComplexNumber(cosh(n.getImaginaryValue(), null) * Math.cos(n.getRealValue()), -1
				* Math.sin(n.getRealValue()) * sinh(n.getImaginaryValue(), null));
		return (ComplexNumber) NumberExpr.ONE.divide(cosPart);
	}
	//Override
	public Expr make(Expr e) { return new Sec(e); }

	//Override
	public String getName() {
		return "sec";
	}
}
