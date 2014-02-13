package command.evaluator;

public class FuncCallExpr extends Expr
{
	private ExprList argList; // contains Exprs
	private String name;
	
	public FuncCallExpr(String name, ExprList argList)
	{
		this.name = name;
		this.argList = argList;
	}
	/*
	public double evaluate(EvaluationContext e) throws UndefinedVarException
	{
		double result;
		if(! isValidCall(e))
		{
			throw new IllegalArgumentException("Function either not defined or has wrong number of parameters.");
		}
		CustomFunction c = (CustomFunction)e.getVarExpr(name);
		Object[] values = argList.getParams();
		e.beginVarScope();
			for(int i = 0; i < c.numParams(); i++)
			{
				e.setVarValue(c.getParamName(i),(Expr)values[i]);
			}
			result = c.getFuncDecl().evaluate(e);
		e.endVarScope();
		return result;
	}
	*/
	//Override
	public Expr simplify(EvaluationContext e, int expandOption)
	{
		if(expandOption == Expr.VAR_EXPAND_NONE)
			return new FuncCallExpr(name,argList);
		
		if(expandOption == Expr.VAR_EXPAND_CUR_SCOPE &&
		   ! e.varIsDefinedInCurrentScope(name))
			return new FuncCallExpr(name,argList);
		
		Expr result = null;
		if(! isValidCall(e))
		{
			return new FuncCallExpr(name,argList.simplify(e,expandOption));
		}
		try
		{
			CustomFunction c = (CustomFunction)e.getVarExpr(name);
			Object[] values = argList.getItems();
			e.beginVarScope();
				for(int i = 0; i < c.numParams(); i++)
				{
					
					//e.setVarValue(c.getParamName(i),((Expr)values[i]),expandOption);
					//we want to evaluate the function to a number.
					//thus, simplify the arguments
					e.setVarValue(c.getParamName(i),((Expr)values[i]).simplify(e,expandOption),expandOption);
				}
				result = c.getFuncDecl().simplify(e,expandOption);
			e.endVarScope();
		}
		catch(UndefinedVarException uve)
		{
			//this will not happen since we check first
		}
		
		return result;
	}
	
	//Override
	public void print(int indentLevel)
	{
		for(int i = 0; i < indentLevel; i++)
			System.out.print(" ");
		System.out.println("FuncCallExpr");
		for(int i = 0; i < indentLevel+1; i++)
			System.out.print(" ");
		System.out.println("Name: " + name);
		for(int i = 0; i < indentLevel+1; i++)
			System.out.print(" ");
		System.out.println("Arguments: " + argList);
	}	
		
	//Override
	public String toString(EvaluationContext e)
	{
		return name + "(" + argList.toString(e) + ")";
	}
		
	private boolean isValidCall(EvaluationContext ec)
	{
		if(ec.varIsDefined(name))
		{
			try
			{
				Expr e = ec.getVarExpr(name);
	
				if (e instanceof CustomFunction)
				{
					CustomFunction c = ((CustomFunction)e);
					
					if(c.numParams() == argList.getItemCount())
					{
						return true;
					}
					else
					{
						throw new IllegalArgumentException("The function \"" + name + "\" takes " + c.numParams() + " parameter(s).");
					}
				}
				else
				{
					return false;
				}
			}
			catch(UndefinedVarException uve)
			{
				//this should not happen
				return false;
			}
		}			
		else
		{
			return false;
		}
	
	}
}