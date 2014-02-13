package command.evaluator;

public class CustomFunction extends Expr
{
	//this is just used to hold the declaration
	//in the var table -- not to be evaluated
	private String name;
	private String[] paramNames;
	private Expr funcDecl;
	
	CustomFunction(String name, String[] paramNames, Expr funcDecl)
	{
		this.paramNames = paramNames;
		this.funcDecl = funcDecl;
		this.name = name;
	}
	public int numParams()
	{
		return paramNames.length;
	}
	/*
	public double evaluate(EvaluationContext e) throws UndefinedVarException
	{
		return new CustomFunction(paramNames,funcDecl.evaluate(e));
	}
	*/
	//Override
	public Expr simplify(EvaluationContext e, int expandOption)
	{
		return new CustomFunction(name,paramNames,funcDecl.simplify(e,expandOption));
	}
	
	public Expr getFuncDecl()
	{
		return funcDecl;
	}
	public String getParamName(int paramNumber)
	{
		return paramNames[paramNumber];
	}
	
	//Override
	public String toString(EvaluationContext e) 
	{
		String result = name + "(";
		for(int i = 0; i < paramNames.length; i++)
		{
			if( i != paramNames.length - 1)
			{
				result = result + paramNames[i] + ", ";
			}
			else
			{
				result = result + paramNames[i];
			}
		}
		result = result + ") = " + funcDecl.toString(e);
		return result;
	}
	//Override
	public void print(int indentLevel) { }
	
}
