package command.evaluator.functions;

import command.evaluator.ComplexNumber;
import command.evaluator.EvaluationContext;
import command.evaluator.Expr;
import command.evaluator.Function;

public class Ln extends Function {
	public Ln(Expr inside) {
		super(inside);
	}

	//Override
	public double getValue(double d, EvaluationContext ec) {
		return Math.log(d);
	}

	//Override
	public ComplexNumber getValue(ComplexNumber c, EvaluationContext ec) {
		double realPart = Math.log(c.getMagnitude().getRealValue());
		double imagPart = Math.atan(c.getImaginaryValue() / c.getRealValue());
		return new ComplexNumber(realPart, imagPart);
	}

	//Override
	public String getName() {
		return "ln";
	}
	//Override
	public Expr make(Expr e) { return new Ln(e); }

}
