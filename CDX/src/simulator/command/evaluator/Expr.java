package command.evaluator;

public abstract class Expr extends ProgLine
{
	
	public static final int VAR_EXPAND_CUR_SCOPE = 1;
	public static final int VAR_EXPAND_ALL = 2;
	public static final int VAR_EXPAND_NONE = 3;
	
	//public abstract double evaluate(EvaluationContext e) throws UndefinedVarException;
	//Override
	public Expr evaluate(EvaluationContext e)
	{
		return simplify(e,VAR_EXPAND_ALL);
	}
	//force the program to try to reduce expr to a number
	//should simplify variables to numbers, etc.
	//even if can't reduce whole thing
	public abstract Expr simplify(EvaluationContext e, int expandOption);
	public Expr simplify(EvaluationContext e)
	{
		return simplify(e,VAR_EXPAND_ALL);
	}
		
	//public abstract boolean evaluatesToNumber();
	//implement this later
	public abstract String toString(EvaluationContext e);
	public abstract void print(int indentLevel);
	
	public void print() { print(0);		}
	public void printSpaces(int num)
	{
		for(int i = 0; i < num; i++)
			System.out.print(" ");
	}
	//used mostly in matrix manipulation
	private Expr performOp(int op, Expr other)
	{
		return new OpExpr(this,op,other);
	}
	public Expr addExpr(Expr e) { return performOp(OpExpr.PLUS,e); }
	public Expr subtractExpr(Expr e) { return performOp(OpExpr.MINUS,e); }
	public Expr multiplyExpr(Expr e) { return performOp(OpExpr.MULT,e); }
	public Expr divideExpr(Expr e) { return performOp(OpExpr.DIV,e); }
	public Expr exprReciprocal() { return new OpExpr(NumberExpr.ONE,OpExpr.DIV,this); }
		
}
