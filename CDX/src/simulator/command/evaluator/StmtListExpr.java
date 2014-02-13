package command.evaluator;

public class StmtListExpr extends Expr
{
	private StmtList sl;
	private Expr e;
	public StmtListExpr(StmtList sl, Expr e)
	{
		this.sl = sl;
		this.e = e;
	}
	/*
	public double evaluate(EvaluationContext ec) throws UndefinedVarException
	{
		sl.execute(ec);
		return e.evaluate(ec);
	}
	*/
	//Override
	public String toString(EvaluationContext ec)
	{
		return (sl == null ? "" : sl.toString(ec)) + " " + e.toString(ec);
	}
	//Override
	public Expr simplify(EvaluationContext c, int expandOption)
	{
	
		if (sl != null) sl.execute(c);
		return new StmtListExpr(sl,e.simplify(c,expandOption));
	}
	public Expr getExpr() { return e; }
	//Override
	public void print(int indentLevel)
	{
		for(int i = 0; i < indentLevel; i++)
			System.out.print(" ");
		System.out.println("Statement List Expression");
		for(int i = 0; i < indentLevel + 1; i++)
			System.out.print(" ");
		System.out.println("StmtList: " + sl.toString(null));
		for(int i = 0; i < indentLevel + 1; i++)
			System.out.print(" ");
		System.out.println("Expr");
		e.print(indentLevel + 2);
	}
}
