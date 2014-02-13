package command.evaluator;

public class IntegrateExpr extends Expr
{
	private Expr function;
	private Expr lowerBound;
	private Expr upperBound;
	private String var;
	
	public IntegrateExpr(Expr function, String var, Expr lowerBound, Expr upperBound)
	{
		this.function = function;
		this.var = var;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}
	
	//Override
	public Expr simplify(EvaluationContext e, int expandOption)
	{
		//think about what to do with expandOption
		//if lowerBound simplifies to a number
		//and upperBound simplifies to a number,
		//evaluate it
		
		Expr low = lowerBound.simplify(e,expandOption);
		Expr high = upperBound.simplify(e,expandOption);
		if(low instanceof NumberExpr &&
			high instanceof NumberExpr)
	    {
			return (new command.evaluator.integrator.
				IntegralEvaluator(function,var,(NumberExpr)low,(NumberExpr)high,e,expandOption)).integrate();
		}
		else
		{
			return new IntegrateExpr(function.simplify(e,expandOption),
									 var,low,high);
		}
	}
	//Override
	public String toString(EvaluationContext e)
	{
		return "int(" + function.toString(e) + ", " + var + ", " + lowerBound.toString(e) + ", " + upperBound.toString(e) + ")";
	}
	
	
	//Override
	public void print(int indentLevel)
	{
		printSpaces(indentLevel);
		System.out.println("IntegrateExpr");
		printSpaces(indentLevel + 1);
		System.out.println("Function");
		function.print(indentLevel + 2);
		printSpaces(indentLevel + 1);
		System.out.println("Variable: " + var);
		printSpaces(indentLevel + 1);
		System.out.println("Lower Bound");
		lowerBound.print(indentLevel + 2);
		printSpaces(indentLevel + 1);
		System.out.println("Upper Bound");
		upperBound.print(indentLevel + 2);
	}
		
																  
}
