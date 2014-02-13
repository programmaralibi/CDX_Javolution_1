package command.evaluator;

public class ExprList extends ObjectList
{
	public ExprList(Expr arg, ExprList next)
	{
		super(arg,next);
	}
	public ExprList(Expr arg)
	{
		super(arg);
	}
		
	//Override
	public Object[] getItems()
	{
		Expr[] paramNames = new Expr[getItemCount()];
		return getItems(paramNames,0);
	}
	public void setNext(ExprList next)
	{
		this.next = next;
	}
	public void setExpr(Expr e)
	{
		this.obj = e;
	}
	public Expr getExprAt(int index)
	{
		//returns object at (1 based) index
		return (Expr)super.getItemAt(index);
	}
	public void setExprAt(int index, Expr expr)
	{
		super.setItemAt(index,expr);
	}
	public ExprList simplify(EvaluationContext e, int expandOption)
	{
		if(next != null)
		{
			return new ExprList(((Expr)obj).simplify(e,expandOption),((ExprList)next).simplify(e,expandOption));
		}
		else
		{
			return new ExprList(((Expr)obj).simplify(e,expandOption),null);
		}
	}
	//Override
	public String toString(EvaluationContext e)
	{
		if(next == null)
		{
			return ((ProgLine)obj).toString(e);
		}
		else
		{
			return ((ProgLine)obj).toString(e) + ", " + next.toString(e);
		}
	}
		

}


