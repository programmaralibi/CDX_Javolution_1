package command.evaluator;

public class ParamList extends ObjectList
{
	public ParamList(String paramName, ParamList next)
	{
		super(paramName,next);
	}
	public ParamList(String paramName)
	{
		super(paramName);
	}
	
	public Object[] getParams()
	{
		String[] paramNames = new String[getItemCount()];
		return getItems(paramNames,0);
	}
	
}
