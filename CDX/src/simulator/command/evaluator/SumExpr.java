package command.evaluator;

public class SumExpr extends Expr
{
	Summable expr;
	
	public SumExpr(Summable expr)
	{
		this.expr = expr;
	}
	//Override
	public Expr simplify(EvaluationContext e, int expandOption)
	{
		return expr.getSum(e,expandOption);
	}
	//Override
	public void print(int indentLevel)
	{
		for(int i = 0; i < indentLevel; i++)
			System.out.print(" ");
		System.out.println("SumExpr");
		expr.print(indentLevel + 1);
	}
	
	
	//Override
	public String toString(EvaluationContext e)
	{
		return "sum(" + expr.toString(e) + ")";
	}
}
