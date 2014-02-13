package command.evaluator.functions;

import command.evaluator.ComplexNumber;
import command.evaluator.EvaluationContext;
import command.evaluator.EvaluationException;
import command.evaluator.Expr;
import command.evaluator.Function;

public class ArcTan extends Function {
	public ArcTan(Expr inside) {
		super(inside);
	}

	//Override
	public double getValue(double d, EvaluationContext ec) {
		return Math.atan(d);
	}

	//Override
	public ComplexNumber getValue(ComplexNumber c, EvaluationContext ec) {
		throw new EvaluationException("Imaginary arguments are not supported for this function.");
	}
	//Override
	public Expr make(Expr e) { return new ArcTan(e); }

	//Override
	public String getName() {
		return "Arctan";
	}
}
