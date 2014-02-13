package command.evaluator;
						  
public abstract class NumberExpr extends Expr
{
	//public static double PI = Math.PI;
	//public static double E = Math.E;
	
	//private double value;
	
	//public NumberExpr(double value)	{	this.value = value;	}	
		
	//public double evaluate(EvaluationContext e) { return value; }
	public static final RationalNumber ZERO = new RationalNumber(0,1);
	public static final RationalNumber ONE = new RationalNumber(1,1);
	public static final RationalNumber MINUSONE = new RationalNumber(-1,1);
	
	//Override
	public abstract String toString(EvaluationContext e);
	/*
	public Expr simplify(EvaluationContext e, int expandOption)
	{
		return new NumberExpr(value);
	}
	*/
	
	//this works for now -- CHANGE LATER!!
	public double getValue() { return getRealValue(); }
	
	public abstract  NumberExpr multiply(NumberExpr n);
	public abstract  NumberExpr divide(NumberExpr n);
	public abstract  NumberExpr add(NumberExpr n);
	public abstract  NumberExpr subtract(NumberExpr n);
	public abstract  NumberExpr mod(NumberExpr n);
	public abstract	 NumberExpr toPower(RealNumber n);
	public abstract  NumberExpr reciprocal();
	
	public abstract double getRealValue();
	public abstract double getImaginaryValue();
	public abstract RealNumber getRealPart();
	public abstract RealNumber getImaginaryPart();
	
	public NumberExpr toPower(NumberExpr n)
	{
		if(n instanceof ComplexNumber)
		{
			return ComplexNumber.makeComplex(this).toPower(n);
		}
		else
		{
			return toPower((RealNumber)n);
		}
	}
	
	public NumberExpr multiply(double n)
	{
		return multiply(RealNumber.createRealNumber(n));
	}
	public NumberExpr divide(double n)
	{
		return divide(RealNumber.createRealNumber(n));
	}
	public NumberExpr add(double n)
	{
		return add(RealNumber.createRealNumber(n));
	}
	public NumberExpr subtract(double n)
	{
		return subtract(RealNumber.createRealNumber(n));
	}
		
	public NumberExpr toPower(double n)
	{
			return toPower(RealNumber.createRealNumber(n));
	}	

	/*
	public NumberExpr add(NumberExpr n) { return new NumberExpr(value + n.getValue()); }
	public NumberExpr subtract(NumberExpr n) { return new NumberExpr(value - n.getValue()); }
	public NumberExpr multiply(NumberExpr n) { return new NumberExpr(value * n.getValue()); }
	public NumberExpr divide(NumberExpr n) { return new NumberExpr(value / n.getValue()); }
	public NumberExpr mod(NumberExpr n) { return new NumberExpr(value % n.getValue()); }
	public NumberExpr pow(NumberExpr n) { return new NumberExpr(Math.pow(value,n.getValue())); }
	*/
	

	
	public static NumberExpr createNumber(double real, double complex)
	{
		RealNumber realPart = null;
		RealNumber imagPart = null;
		if( Math.abs((long)real) - Math.abs(real) == 0)
		{
			realPart = new RationalNumber((long)real,1);
		}
		else
		{
			realPart = new IrrationalNumber(real);
		}
		
		if( Math.abs((long)complex) - Math.abs(complex) == 0)
		{
			imagPart = new RationalNumber((long)complex,1);
		}
		else
		{
			imagPart = new IrrationalNumber(complex);
		}
		return new ComplexNumber(realPart,imagPart);
	}
		
	public static NumberExpr createNumber(double d)
	{
		return createNumber(d,0);
	}
	public ComplexNumber makeComplex()
	{
		return ComplexNumber.makeComplex(this);
	}
	/*
	public void print(int indentLevel)
	{
		for(int i = 0; i < indentLevel; i++)
			System.out.print(" ");
			
		System.out.println("NumberExpr: " + value);
	
	}
	*/
	public boolean isReal()
	{
		return (this instanceof RealNumber || getImaginaryValue() == 0);
	}
	public boolean isComplex()
	{
		return (this instanceof ComplexNumber && getImaginaryValue() != 0);
	}
		
}
