package command.evaluator.functions;
import command.evaluator.ComplexNumber;
import command.evaluator.EvaluationContext;
import command.evaluator.EvaluationException;
import command.evaluator.Expr;
import command.evaluator.Function;
public class ArcCoth extends Function
{
	public ArcCoth(Expr inside)
	{
		super(inside);
	}
	//Override
	public double getValue(double d, EvaluationContext ec)
	{
		// |x| > 1
		return .5 * Math.log((d + 1) / (d - 1));
	}
	//Override
	public ComplexNumber getValue(ComplexNumber c, EvaluationContext ec)
	{
		throw new EvaluationException("Imaginary arguments are not supported for this function.");
	}
	//Override
	public String getName() { return "ArcTanh"; }
	//Override
	public Expr make(Expr e) { return new ArcTanh(e); }

}
