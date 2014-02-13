package command.evaluator.functions;
import command.evaluator.ComplexNumber;
import command.evaluator.EvaluationContext;
import command.evaluator.Expr;
import command.evaluator.Function;

public class AbsoluteValue extends Function
{
	public AbsoluteValue(Expr inside)
	{
		super(inside);
	}

	public double getValue(double d, EvaluationContext ec)
	{
		return Math.abs(d);
	}

	public ComplexNumber getValue(ComplexNumber n, EvaluationContext ec)
	{
		return new ComplexNumber(Math.abs(n.getImaginaryValue()),
				Math.abs(n.getRealValue()));
	}


	public String getName() { return "abs"; }

	public Expr make(Expr e) { return new AbsoluteValue(e); }
}
