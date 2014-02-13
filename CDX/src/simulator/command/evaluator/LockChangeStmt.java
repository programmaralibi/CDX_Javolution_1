package command.evaluator;

public class LockChangeStmt extends Stmt
{
	private boolean newLockState;	//true if locked, false if notlocked
	private String var;
	public LockChangeStmt(String var, boolean newLockState)
	{
		this.var = var;
		this.newLockState = newLockState;
	}
	
	//Override
	public void execute(EvaluationContext e)
	{
		if(newLockState)
		{
			e.lockVar(var);
		}
		else
		{
			e.unLockVar(var);
		}
	}
	
	//Override
	public String toString(EvaluationContext e)
	{
		return (newLockState ? "lock" : "unlock") + "(" + var + ")";
	}
}
