package command.evaluator.functions;

import command.evaluator.ComplexNumber;
import command.evaluator.EvaluationContext;
import command.evaluator.EvaluationException;
import command.evaluator.Expr;
import command.evaluator.Function;

public class ArcSinh extends Function {
	public ArcSinh(Expr inside) {
		super(inside);
	}

	//Override
	public double getValue(double d, EvaluationContext ec) {
		// -Infinity < x < Infinity
		return Math.log(d + Math.sqrt(d * d + 1));
	}

	//Override
	public ComplexNumber getValue(ComplexNumber c, EvaluationContext ec) {
		throw new EvaluationException("Imaginary arguments are not supported for this function.");
	}
	//Override
	public Expr make(Expr e) { return new ArcSinh(e); }

	//Override
	public String getName() {
		return "ArcSinh";
	}
}
