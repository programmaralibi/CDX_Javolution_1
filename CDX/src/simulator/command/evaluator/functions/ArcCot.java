package command.evaluator.functions;
import command.evaluator.*;
public class ArcCot extends Function
{
	public ArcCot(Expr inside)
	{
		super(inside);
	}
	//Override
	public double getValue(double d, EvaluationContext ec)
	{
		return Math.atan(1/d);
	}
	//Override
	public ComplexNumber getValue(ComplexNumber c, EvaluationContext ec)
	{
		throw new EvaluationException("Imaginary arguments are not supported for this function.");
	}
	//Override
	public String getName() { return "Arccot"; }
	//Override
	public Expr make(Expr e) { return new ArcCot(e); }

}
