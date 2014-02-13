package command.evaluator.integrator;
import command.evaluator.EvaluationContext;

public abstract class Integrator
{
	private int maxSteps; //max # of steps = 2^maxSteps
	protected EvaluationContext ec;
	
	Integrator(EvaluationContext ec)
	{
		maxSteps = 20;
		this.ec = ec;
	}
	/*
public static double integrate(Expr func, double a, double b, double n)
{
	int i;
	double s = 0;
	double w = (b - a) / n;
	for(i = 1; i <= n; i++)
	{
		//s+= w*(func.getValue(a+(i-1)*w) + 4*func.getValue(a-w/2+i*w) + func.getValue(a+i*w))/6;
	}
	return s;
}
*/

protected abstract double function(double param);
	
public double integrate(double a, double b) throws TooManyStepsException
{
	
	beforeIntegration();
	IterativeTrapIntegrator inttrap = new IterativeTrapIntegrator(a,b);
	int j;
	double s, st, oldst, olds;
	oldst = olds = -1.0e30;
	for(j = 1; j <= maxSteps; j++)
	{
		st = inttrap.iterate();
		s = (4.0*st - oldst)/3.0; //S = (4/3)S2n - (1/3)Sn
		if(Math.abs(s - olds) < ec.getTol()*Math.abs(olds))
		{
			afterIntegration();
			return s;
		}
		olds = s;
		oldst = st;
	}
	afterIntegration();
	throw new TooManyStepsException();

}

protected void beforeIntegration() { }
protected void afterIntegration() { }

/*
public double integrate(double a, double b, double n)
{
	int i;
	double s = 0;
	double w = (b - a) / n;
	for(i = 1; i <= n; i++)
	{
		s+= w*(function(a+(i-1)*w) + 4*function(a-w/2+i*w) + function(a+i*w))/6;
	}
	return s;
}
*/

		
	

	class IterativeTrapIntegrator
	{
		//adapted from Numerical Recipes in C, p. 137
		
		
		
		private int curIter;
		private double s;
		double b;
		double a;
		
		IterativeTrapIntegrator(double a, double b)
		{
			this.b = b;
			this.a = a;
			curIter = 1;
			s = 0;
		}
		/*
		This computes the "curIter"th stage of refinement of an
		extended trapezoidal rule.  When curIter = 1, the routine
		returns the crudest estimate of the integral from a to b.
		Subsequent calls with curIter = 2,3,4,.. in that order will
		improve the accuracy by adding 2^(n-2) additional interior points
		*/
		public double iterate()
		{
			double x, tnm, sum, del;
			int it, j;
			if(curIter == 1)
			{
				curIter++;
				return ( s = 0.5*(b-a)*(function(a) + function(b)));
			}
			else
			{
				for(it = 1, j= 1; j < curIter - 1; j++)
				{
					it <<= 1;
				}
				
				tnm = it;
				del = (b - a)/tnm;	//this is the spacing of the points to be added
				x = a + .5*del;
				for(sum = 0.0,j = 1; j <= it; j++,x+=del)
				{
					sum += function(x);
					if(Double.isInfinite(sum))
						break;
				}
				
				s = .5*(s + (b-a)*sum / tnm);
				curIter++;
				
				return s;
			}
		}
	}	
			
		
		
}


