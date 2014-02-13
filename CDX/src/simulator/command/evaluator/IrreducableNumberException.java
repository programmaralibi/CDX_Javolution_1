package command.evaluator;
public class IrreducableNumberException extends IllegalArgumentException
{
	String data;
	IrreducableNumberException(String data)
	{
		this.data = data;
	}
	String getInfo()
	{
		return data;
	}
}
