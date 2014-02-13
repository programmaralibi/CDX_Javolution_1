package command.evaluator;

public class StmtList extends Stmt
{
	private Stmt theStmt;
	private StmtList next;
	
	public StmtList(Stmt statement)
	{
		theStmt = statement;
		next = null;
	}
	public StmtList(Stmt statement, StmtList next)
	{
		theStmt = statement;
		this.next = next;
	}
	//Override
	public void execute(EvaluationContext e)
	{
		theStmt.execute(e);
		next.execute(e);
	}
	//Override
	public String toString(EvaluationContext e)
	{
		return theStmt.toString(e) +  (next == null ? "" : ", " + next.toString(e));
	}
}
