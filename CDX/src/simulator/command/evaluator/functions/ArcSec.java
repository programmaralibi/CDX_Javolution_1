package command.evaluator.functions;
import command.evaluator.ComplexNumber;
import command.evaluator.EvaluationContext;
import command.evaluator.EvaluationException;
import command.evaluator.Expr;
import command.evaluator.Function;
public class ArcSec extends Function
{
	public ArcSec(Expr inside)
	{
		super(inside);
	}
	//Override
	public double getValue(double d, EvaluationContext ec)
	{
		return Math.acos(1/d);
	}
	//Override
	public ComplexNumber getValue(ComplexNumber c, EvaluationContext ec)
	{
		throw new EvaluationException("Imaginary arguments are not supported for this function.");
	}
	//Override
	public String getName() { return "Arcsec"; }
	//Override
	public Expr make(Expr e) { return new ArcSec(e); }

}
