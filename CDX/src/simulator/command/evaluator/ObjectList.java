package command.evaluator;

public class ObjectList implements Printable
{
	//usages
	//1)  When defining function, holds names of parameters - ParamList
	//2)  When using function, holds values of arguments - ExprList
	//3)  The rows of a matrix - RowList
	//4)  The expresions within a row of a matrix - ExprList 
	protected Object obj;
	protected ObjectList next;
	
	public ObjectList(Object obj, ObjectList next)
	{
		this.obj = obj;
		this.next = next;
	}
	public ObjectList(Object obj)
	{
		this.obj = obj;
		this.next = null;
	}
	public int getItemCount()
	{
		if(next == null)
		{
			return 1;
		}
		else
		{
			return 1 + next.getItemCount();
		}
	}
	public Object getObj() { return obj; }
	public ObjectList getNextObjList() { return next; }
	public Object[] getItems()
	{
		Object[] paramNames = new Object[getItemCount()];
		return getItems(paramNames,0);
	}
	public void applyToEach(ListApplicator a)
	{
		applyToEach(a,1,getItemCount());
	}
	public void applyToList(List2ListApplicator la, ExprList other)
	{
		//if(other.getItemCount() != getItemCount)
		//{
		//	throw new EvaluationException("The lists must be the same length.");
		//}
		//I'm relaxing the restruction that lists need to be
		//same length for more flexibility -- 
		//list length can be checked elsewhere
		applyToList(la,other,1,getItemCount());
	}
	private void applyToEach(ListApplicator a, int termNumber, int totalTerms)
	{
		
		a.apply(obj,termNumber,totalTerms);
		if(next != null)
			next.applyToEach(a,termNumber+1,totalTerms);
	}
	private void applyToList(List2ListApplicator la, ObjectList other, int termNumber, int totalTerms)
	{
		la.apply(obj,other != null ? other.getObj() : null,termNumber,totalTerms);
		if(next != null)
		{
			next.applyToList(la,other != null && other.getNextObjList() != null ? other.getNextObjList() : null,termNumber+1,totalTerms);
		}
	}
	public Object getItemAt(int location)
	{
		//1st item = 1
		
		if(location > getItemCount() || location <= 0)
		{
			throw new IndexOutOfBoundsException("The list you referenced only has " + getItemCount() + " items.  The valid indices are between 1 and " + getItemCount() + ".");
		}
		ObjectList curList = this;
		for(int i = 1; i < location; i++)
		{
			//do this location - 1 times
			curList = curList.next;
		}
		return curList.obj;
	}
	public void setItemAt(int location, Object obj)
	{
				//1st item = 1
		
		if(location > getItemCount() || location <= 0)
		{
			throw new IndexOutOfBoundsException("The list you referenced only has " + getItemCount() + " items.  The valid indices are between 1 and " + getItemCount() + ".");
		}
		ObjectList curList = this;
		for(int i = 1; i < location; i++)
		{
			//do this location - 1 times
			curList = curList.next;
		}
		curList.obj = obj;
	}
	//called recursively; when done, names contains the values/names of the parameters
	public Object[] getItems(Object[] names, int position)
	{
		names[position] = obj;
		if(next != null)
		{
			return next.getItems(names,position + 1);
		}
		else
		{
			return names;
		}
	}
	public void print(int indentLevel)
	{
		for(int i = 0; i < indentLevel; i++)
			System.out.print(" ");
		System.out.println(getClass().getName());
		if(obj instanceof Expr)
		{
			((Expr)obj).print(indentLevel + 1);
		}
		else
		{
			for(int i = 0; i < indentLevel + 1; i++)
			{
				System.out.println(obj.getClass().getName() + ": " + obj.toString());
			}
		}
		if(next != null)
			next.print(indentLevel + 1);
	}

	public String toString(EvaluationContext e)
	{
		if(next == null)
			return ((Printable)obj).toString(e);
		else
			return ((Printable)obj).toString(e) + ", " + next.toString(e);
	}
		
	
	
}
