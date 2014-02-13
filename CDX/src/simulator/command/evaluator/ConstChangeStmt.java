package command.evaluator;

public class ConstChangeStmt extends Stmt
{
	private String var;
	private boolean newConstState; //true if constant, false if not constant
	public ConstChangeStmt(String var, boolean newConstState)
	{
		this.var = var;
		this.newConstState = newConstState;
	}
	//Override
	public void execute(EvaluationContext e)
	{
		if(newConstState)
		{
			e.makeVarConstant(var);
		}
		else
		{
			e.makeVarNonConstant(var);
		}
	}
	//Override
	public String toString(EvaluationContext e)
	{
		return (newConstState ? "freeze" : "unfreeze") + "(" + var + ")";
	}
}
