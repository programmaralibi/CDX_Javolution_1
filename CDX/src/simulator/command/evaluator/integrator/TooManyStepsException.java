package command.evaluator.integrator;

public class TooManyStepsException extends RuntimeException
{
	public TooManyStepsException()
	{
		super("Too many steps were taken in the integration.");
	}
}
