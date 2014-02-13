package command.evaluator.functions;
import command.evaluator.*;

public class ArcCos extends Function
{
	public ArcCos(Expr inside)
	{
		super(inside);
	}
	//Override
	public double getValue(double d, EvaluationContext ec)
	{
		return Math.acos(d);
	}
	//Override
	public ComplexNumber getValue(ComplexNumber c, EvaluationContext ec)
	{
		throw new EvaluationException("Imaginary arguments are not supported for this function.");
	}
	//Override
	public String getName() { return "Arccos"; }
	//Override
	public Expr make(Expr e) { return new ArcCos(e); }

}
