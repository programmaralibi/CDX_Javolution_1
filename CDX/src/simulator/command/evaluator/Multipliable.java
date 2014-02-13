package command.evaluator;

public interface Multipliable
{
	public Expr getProduct(EvaluationContext e, int expandOption);
	public String toString(EvaluationContext e);
	public void print(int indentLevel);
}
