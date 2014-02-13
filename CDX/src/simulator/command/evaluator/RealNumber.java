package command.evaluator;
public abstract class RealNumber extends NumberExpr
{
	//Override
	public abstract double getValue();
	//public RealNumber toPower(double d);
	//public RealNumber toPower(RealNumber n);
	//public NumberExpr toPower(NumberExpr n);
	public abstract RealNumber add(RealNumber n);
	//public RealNumber add(double n);
	public abstract RealNumber subtract(RealNumber n);
	//public RealNumber subtract(double n);
	public abstract RealNumber multiply(RealNumber n);
	public abstract RealNumber divide(RealNumber n);
	
	public abstract boolean isGreaterThanOrEqualTo(RealNumber n);
	public abstract boolean isLessThanOrEqualTo(RealNumber n);
	public abstract boolean isGreaterThan(RealNumber n);
	public abstract boolean isLessThan(RealNumber n);
	public abstract boolean isEqualTo(RealNumber n);
	
	//public RealNumber multiply(double n);
	//public RealNumber divide(double n);
	public boolean isGreaterThanOrEqualTo(double n)
	{
		return isGreaterThanOrEqualTo(createRealNumber(n));
	}
	
	public boolean isLessThanOrEqualTo(double n)
	{
		return isLessThanOrEqualTo(createRealNumber(n));
	}
	public boolean isGreaterThan(double n)
	{
		return isGreaterThan(createRealNumber(n));
	}
	public boolean isLessThan(double n)
	{
		return isLessThan(createRealNumber(n));
	}
	
	public boolean isEqualTo(double n)
	{
		return isEqualTo(createRealNumber(n));
	}
	
	
	//public RealNumber reciprocal();
	
	
	public static RealNumber createRealNumber(double real)
	{
		RealNumber realPart;
		
		if( Math.abs((long)real) - Math.abs(real) == 0)
		{
			realPart = new RationalNumber((long)real,1);
		}
		else
		{
			realPart = new IrrationalNumber(real);
		}
		return realPart;
	}
	public static RealNumber copy(RealNumber r)
	{
		if(r instanceof RationalNumber)
		{
			return new RationalNumber((RationalNumber)r);
		}
		else
		{
			return new IrrationalNumber((IrrationalNumber)r);
		}
	}
}
