package command.evaluator;

public abstract class Stmt extends ProgLine
{
	//Override
	public Expr evaluate(EvaluationContext e)
	{
			execute(e);
			return null;
	}
	public abstract void execute(EvaluationContext e);
	
	public abstract String toString(EvaluationContext e);
}
