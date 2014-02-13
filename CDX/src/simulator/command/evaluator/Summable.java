package command.evaluator;

public interface Summable 
{
	public Expr getSum(EvaluationContext e, int expandOption);
	public String toString(EvaluationContext e);
	public void print(int indentLevel);
}
