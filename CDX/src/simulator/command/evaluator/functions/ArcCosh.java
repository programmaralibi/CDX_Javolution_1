package command.evaluator.functions;
import command.evaluator.*;
public class ArcCosh extends Function
{
	public ArcCosh(Expr inside)
	{
		super(inside);
	}
	//Override
	public double getValue(double d, EvaluationContext ec)
	{
		//need |x| >= 1
		//TODO:  Throw "value outside of function domain exception"
		//or return imaginary number
		return Math.log(d + Math.sqrt(d*d-1));
	}
	//Override
	public ComplexNumber getValue(ComplexNumber c, EvaluationContext ec)
	{
		throw new EvaluationException("Imaginary arguments are not supported for this function.");
	}
	//Override
	public String getName() { return "ArcCosh"; }
	//Override
	public Expr make(Expr e) { return new ArcCosh(e); }
}
