package command.evaluator;

public abstract class ProgLine implements Printable
{
	public abstract Expr evaluate(EvaluationContext e) throws UndefinedVarException;
	/*
	public abstract String toString(EvaluationContext e);
	public String toString()
	{
		return toString(null);
	}
	*/
}
