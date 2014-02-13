package command.evaluator;

public class UndefinedVarException extends RuntimeException
{
	public UndefinedVarException(String e)
	{
		super(e);
	}
	public UndefinedVarException()
	{
		super("A variable is undefined, so the expression cannot be evaluated.");
	}
}
