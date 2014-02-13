package command.evaluator;

public class FuncDeclStmt extends Stmt
{
	private String name;
	private ParamList params;
	private Expr definition;
	
	public FuncDeclStmt(String name, ParamList params, Expr definition)
	{
		this.name = name;
		this.params = params;
		this.definition = definition;
	}
	
	//ex:  define f(x,y,z) = x^5 + sin(y) - cos(z)^3
	//Override
	public void execute(EvaluationContext e)
	{
		CustomFunction c = new CustomFunction(name,(String[])params.getParams(),definition);
		e.setVarValue(name,c,VariableTable.TYPE_FUNCTION);
	}
	//Override
	public String toString(EvaluationContext e)
	{
		return "define " + name + "(" + (params == null ? "" : params.toString(e)) + ")" + " = " + definition;
	}
}
