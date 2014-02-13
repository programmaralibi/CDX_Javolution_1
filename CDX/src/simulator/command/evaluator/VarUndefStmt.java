package command.evaluator;

public class VarUndefStmt extends Stmt
{
	String var;
	public VarUndefStmt(String var)
	{
		this.var = var;
	}
	//Override
	public void execute(EvaluationContext e)
	{
		e.unDefineVar(var);
	}
	//Override
	public String toString(EvaluationContext e)
	{
		return "undefine " + var;
	}
}
