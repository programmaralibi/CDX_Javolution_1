package command.evaluator.functions;
import command.evaluator.*;

public class ArcCsch extends Function
{
	public ArcCsch(Expr inside)
	{
		super(inside);
	}
	//Override
	public double getValue(double d, EvaluationContext ec)
	{
		//x =/= 0
		return Math.log(1/d + Math.sqrt(1+d*d)/Math.abs(d));
	}
	//Override
	public ComplexNumber getValue(ComplexNumber c, EvaluationContext ec)
	{
		throw new EvaluationException("Imaginary arguments are not supported for this function.");
	}
	//Override
	public String getName() { return "ArcCsch"; }
	//Override
	public Expr make(Expr e) { return new ArcCsch(e); }

}
