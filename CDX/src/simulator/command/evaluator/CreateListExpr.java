package command.evaluator;

public class CreateListExpr extends Expr implements Summable, Multipliable
{
	Expr expression;
	String var;
	Expr startExpr;
	Expr endExpr;
	Expr incrementExpr;
	
	public CreateListExpr(Expr expression, 
						  Variable var, 
						  Expr start,
						  Expr end,
						  Expr increment)
	{
		this.expression = expression;
		this.var = (var).getName();
		this.startExpr = start;
		this.endExpr = end;
		this.incrementExpr = increment;
	}
	
	public Expr getSum(EvaluationContext e, int expandOption)
	{
		//Expr simplified = simplify(e,expandOption)
		Expr simplified = simplify(e,expandOption);
		if (simplified instanceof CreateListExpr)
		{
			return new SumExpr((CreateListExpr)simplified);
		}
		else
		{
			return ((List)simplified).getSum(e,expandOption);
		}
	}
	
	public Expr getProduct(EvaluationContext e, int expandOption)
	{
		//Expr simplified = simplify(e,expandOption)
		Expr simplified = simplify(e,expandOption);
		if (simplified instanceof CreateListExpr)
		{
			return new SumExpr((CreateListExpr)simplified);
		}
		else
		{
			return ((List)simplified).getSum(e,expandOption);
		}
	}
	//Override
	public Expr simplify(EvaluationContext e, int expandOption)
	{
		int expand;
		if(expandOption == VAR_EXPAND_NONE)
		{
			expand = VAR_EXPAND_CUR_SCOPE;
		}
		else
		{
			expand = VAR_EXPAND_ALL;
		}
		Expr startExpr = this.startExpr.simplify(e,expandOption);
		Expr endExpr = this.endExpr.simplify(e,expandOption);
		Expr incrementExpr = this.incrementExpr.simplify(e,expandOption);
		
		double start;
		double end;
		double increment;
		
		if(startExpr instanceof NumberExpr &&
		   incrementExpr instanceof NumberExpr &&
		   endExpr instanceof NumberExpr)
		{
			//in the future, this should not be necessary,
			//as the expr class would probably have
			//built-in add, subtract, multiply, and devide functions
			start = ((NumberExpr)startExpr).getValue();
			end = ((NumberExpr)endExpr).getValue();
			increment = ((NumberExpr)incrementExpr).getValue();
		}
		else
		{
			return new CreateListExpr(expression,new Variable(var),startExpr,endExpr,incrementExpr);
		}
		
		//do variable checking
		if(increment == 0 ||
		   increment < 0 && end > start ||
		   increment > 0 && end < start)
		{
			throw new IllegalArgumentException("The parameters to 'seq' were invalid.\nThe correct syntax is seq(Expression,variable,start,end,increment)");
		}
		
		//unclear what to do if expandOption = EXPAND_CUR_SCOPE
		ExprList theList = null;
		ExprList curList = null;
		e.beginVarScope();
		//double s,e,i; //start, end, increment
		/*
		if(s > e && i > 0) return error
		if(s < e && i < 0) return error
		
		*/
		double i = start;
		//for(double i = start; ( start < end ) ? i <= end : i >= end ; i += increment)
		do
		{
			e.setVarValue(var,RealNumber.createRealNumber(i),VariableTable.TYPE_LIST,expand);
			ExprList nextList = new ExprList(expression.simplify(e,expand));
			if(theList == null)
			{
				theList = nextList;
				curList = nextList;
			}
			else
			{
				curList.setNext(nextList);
				curList = nextList;
			}
			i += increment;
		}
			while( increment > 0 && i <= end && start < end ||
				   increment < 0 && i >= end && start > end ||
				   i == end && start == end);
		
		e.endVarScope();
		return new List(theList);
	}
	
	//Override
	public String toString(EvaluationContext e)
	{
		return "seq( " + expression.toString(e) + ", " + var + ", " + startExpr + ", " + endExpr + ", " + incrementExpr + " )";
	}
	
	//Override
	public void print(int indentLevel)
	{
		for(int i = 0; i < indentLevel; i++)
			System.out.print(" ");
		System.out.println("CreateListExpr");
		expression.print(indentLevel + 1);
		for(int i = 0; i < indentLevel+1; i++)
			System.out.print(" ");
		System.out.println("Start");
		startExpr.print(indentLevel + 2);
		for(int i = 0; i < indentLevel+1; i++)
			System.out.print(" ");
		System.out.println("End");
		endExpr.print(indentLevel + 2);
		for(int i = 0; i < indentLevel+1; i++)
			System.out.print(" ");
		System.out.println("Increment");
		incrementExpr.print(indentLevel + 2);
	}
						  
						  
}
