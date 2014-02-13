package command.evaluator;

public class RowRedEchFormExpr extends Expr
{
	private Expr expr;
	public RowRedEchFormExpr(Expr e)
	{
		expr = e;
	}
	//Override
	public Expr simplify(EvaluationContext e, int expandOption)
	{
		Expr simple = expr.simplify(e,expandOption);
		if(simple instanceof Matrix)
		{
			return ((Matrix)simple).getRowReducedEchelonForm().simplify(e,expandOption);
		}
		else
		{
			return new RowRedEchFormExpr(simple);
		}
	}
	//Override
	public void print(int indentlevel)
	{
		printSpaces(indentlevel);
		System.out.println("RowRedEchFormExpr");
		expr.print(indentlevel + 1);
	}
	//Override
	public String toString(command.evaluator.EvaluationContext e)
	{
		return "fullRowReduce(" + expr.toString(e) + ")";
	}
			
}

