package command.evaluator;

public class MatrixExpr extends Expr
{
	RowList rows;
	public MatrixExpr(RowList rows)
	{
		this.rows = rows;
	}
	//Override
	public Expr simplify(EvaluationContext e, int expandOption)
	{
		//turns the linked lists into a 2D array
		//which is used to create a matrix object, which is returned
		//RowList simplifiedRowList = rows.simplify(e,expandOption);
		//simplify while creating matrix
		int numrows = rows.getItemCount();
		int numcols = rows.getRowAt(1).getItemCount();
		
		Expr[][] data = new Expr[numrows][numcols];
		for(int row = 1; row <= numrows; row++)
		{
			ExprList curRow = rows.getRowAt(row);
			for(int col = 1; col <= numcols; col++)
			{
				data[row-1][col-1] = curRow.getExprAt(col).simplify(e,expandOption);
			}
		}
		return new Matrix(data);
	}
	//Override
	public String toString(EvaluationContext e)
	{
		return "[" + rows.toString(e) + "]";
	}
	//Override
	public void print(int indentLevel)
	{
		printSpaces(indentLevel);
		System.out.println("MatrixCreateExpr");
		rows.print(indentLevel + 1);
	}
}
