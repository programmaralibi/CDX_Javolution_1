package command.evaluator;

public class RowList extends ObjectList
{
	//private ExprList row;
	//private RowList nextRow;
	public RowList(ExprList row, RowList nextRow)
	{
		super(row,nextRow);
	}
	public RowList(ExprList row)
	{
		super(row);
	}
	//Override
	public Object[] getItems()
	{
		ExprList[] rows = new ExprList[getItemCount()];
		return getItems(rows,0);
	}
	public void setNext(RowList next)
	{
		this.next = next;
	}
	public void setRow(ExprList e)
	{
		this.obj = e;
	}
	public ExprList getRowAt(int index)
	{
		//returns obj at (1 based) index
		return (ExprList)super.getItemAt(index);
	}
		public RowList simplify(EvaluationContext e, int expandOption)
	{
		if(next != null)
		{
			return new RowList(((ExprList)obj).simplify(e,expandOption),((RowList)next).simplify(e,expandOption));
		}
		else
		{
			return new RowList(((ExprList)obj).simplify(e,expandOption),null);
		}
	}
		//Override
		public String toString(EvaluationContext e)
	{
		
		String thisObj;
		if(obj instanceof Printable)
		{
			thisObj = ((Printable)obj).toString(e);
		}
		else
		{
			thisObj = obj.toString();
		}
		if(next == null)
		{
			return "[" + thisObj + "]";
		}
		else
		{
			return "[" + thisObj + "]" + next.toString(e);
		}
	}
		
		
}
