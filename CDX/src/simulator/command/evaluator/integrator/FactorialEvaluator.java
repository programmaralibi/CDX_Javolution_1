package command.evaluator.integrator;
import command.evaluator.EvaluationContext;
public class FactorialEvaluator extends Integrator
{
	private double val;
				
	public FactorialEvaluator(double val, EvaluationContext ec)
	{
		super(ec);
		this.val = val;
	}
	public double eval() throws TooManyStepsException
	{
		if(doublesEqual(val,0.0))
			return 1.0;

		if (isPositiveInteger(val))
		{
			double fact = 1;
			for(int k = 1; k <= (int)val; k++)
				fact *= k;
			return fact;
		}
		
		//return integrate(0,2500,5000);
		//return integrate(0,5000,10000);
		return integrate(0,500000000);
	
	} // end of eval method
	
	//Override
	protected double function(double x)
	{
		//return Math.pow(x,val - 1)*Math.exp(-1*x);
		return Math.pow(x,val)*Math.exp(-1*x);
	}
	
	private boolean doublesEqual(double v1, double v2)
	{
		double tolerence = 1E-7;
		if(Math.abs(v2 - v1) <= tolerence)
			return true;
		else
			return false;
	}

	private boolean isPositiveInteger(double v)
	{
	if (v <= 0) return false;
	int intPortion = (int)v;
	if (v - intPortion == 0)
		return true;
	else
		return false;
	}
	
}
