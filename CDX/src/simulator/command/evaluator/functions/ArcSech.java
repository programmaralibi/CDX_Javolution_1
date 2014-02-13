package command.evaluator.functions;

import command.evaluator.ComplexNumber;
import command.evaluator.EvaluationContext;
import command.evaluator.EvaluationException;
import command.evaluator.Expr;
import command.evaluator.Function;

public class ArcSech extends Function {
	public ArcSech(Expr inside) {
		super(inside);
	}

	//Override
	public double getValue(double d, EvaluationContext ec) {
		// 0 < x <= 1
		return Math.log((1 + Math.sqrt(1 - d * d)) / d);
	}

	//Override
	public ComplexNumber getValue(ComplexNumber c, EvaluationContext ec) {
		throw new EvaluationException("Imaginary arguments are not supported for this function.");
	}

	//Override
	public String getName() {
		return "ArcSech";
	}
	//Override
	public Expr make(Expr e) { return new ArcSech(e); }

}
