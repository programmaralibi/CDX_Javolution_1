package command.evaluator;

public class SetBoolOptionStmt extends Stmt
{
	private int option;
	private boolean value;
	
	//set an option that can be either on or off
	public SetBoolOptionStmt(Integer option, Boolean value)
	{
		this.option = option.intValue();
		this.value = value.booleanValue();
	}
	
	//enumerate the true/false options here
	public static final int DISPLAY_FRACTIONS = 0;
	
	//Override
	public void execute(EvaluationContext e)
	{
		switch(option)
		{
		case DISPLAY_FRACTIONS:
			e.setDisplayFractions(value);
			break;
		default:
			throw new EvaluationException("Invalid option.");
		}
		
	}
	private String getOptionName()
	{
		switch(option)
		{
		case DISPLAY_FRACTIONS:
			return "DISPLAY_FRACTIONS";
		default:
			return null;
		}
	}
	//Override
	public String toString(EvaluationContext e)
	{
		return "set " + getOptionName() + " = " + value;
	}
	
	
}
