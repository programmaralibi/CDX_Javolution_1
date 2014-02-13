package command.evaluator.functions;

import command.evaluator.ComplexNumber;
import command.evaluator.EvaluationContext;
import command.evaluator.EvaluationException;
import command.evaluator.Expr;
import command.evaluator.Function;

public class ArcSin extends Function {
	public ArcSin(Expr inside) {
		super(inside);
	}

	//Override
	public double getValue(double d, EvaluationContext ec) {
		return Math.asin(d);
	}

	//Override
	public ComplexNumber getValue(ComplexNumber c, EvaluationContext ec) {
		throw new EvaluationException("Imaginary arguments are not supported for this function.");
	}

	//Override
	public String getName() {
		return "Arcsin";
	}

	//Override
	public Expr make(Expr e) {
		return new ArcSin(e);
	} 

}
