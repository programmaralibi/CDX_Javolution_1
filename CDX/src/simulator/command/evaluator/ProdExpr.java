package command.evaluator;

public class ProdExpr extends Expr
{
	Multipliable expr;
	
	public ProdExpr(Multipliable expr)
	{
		this.expr = expr;
	}
	//Override
	public Expr simplify(EvaluationContext e, int expandOption)
	{
		return expr.getProduct(e,expandOption);
	}
	//Override
	public void print(int indentLevel)
	{
		for(int i = 0; i < indentLevel; i++)
			System.out.print(" ");
		System.out.println("ProdExpr");
		expr.print(indentLevel + 1);
	}
	
	
	//Override
	public String toString(EvaluationContext e)
	{
		return "sum(" + expr.toString(e) + ")";
	}
}

