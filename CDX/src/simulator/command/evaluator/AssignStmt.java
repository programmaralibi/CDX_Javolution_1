package command.evaluator;

public class AssignStmt extends Stmt
{
	private String varName;
	private Expr index;
	private Expr col;
	private Expr value;
	public AssignStmt(Variable v, Expr value)
	{
		this.varName = v.getName();
		this.index = v.getIndex();
		this.col = v.getCol();
		this.value = value;
	}
	public AssignStmt(String varName, Expr value)
	{
		this.varName = varName;
		this.value = value;
		this.index = null;
		this.col = null;
	}
	public AssignStmt(String varName, Expr index, Expr value)
	{
		this.varName = varName;
		this.value = value;
		this.index = index;
		this.col = null;
	}
	public AssignStmt(String varName, Expr row, Expr col, Expr value)
	{
		this.varName = varName;
		this.index = row;
		this.col = col;
		this.value = value;
	}
	//Override
	public void execute(EvaluationContext e) 
	{
		
		if(index == null)
		{
			//it's a variable
			int type;
			if(value instanceof List || value instanceof CreateListExpr)
			{
				type = VariableTable.TYPE_LIST;
			}
			else if(value instanceof CustomFunction)
			{
				type = VariableTable.TYPE_FUNCTION;
			}
			else if(value instanceof Matrix || value instanceof MatrixExpr || value instanceof RowRedEchFormExpr)
			{
				type = VariableTable.TYPE_MATRIX;
			}
			else
			{
				type = VariableTable.TYPE_NUMBER;
			}
			e.setVarValue(varName,value,type);
		}
		else if(col == null)
		{
			//it should be a list
			if(!e.varIsDefined(varName))
			{
				throw new EvaluationException("The list " + varName + " is not defined.");
			}
			if(!(e.getVarType(varName) == VariableTable.TYPE_LIST))
		    {
				throw new EvaluationException("'" + varName + "' is not a list.");
			}
			List curList;
			int newIndex = evaluateIndex(index,e);
			
			curList = (List)e.getVarExpr(varName);
			if(newIndex <= 0 || newIndex > curList.getItemCount())
			{
				throw new EvaluationException("The index " + newIndex + " is outside of the valid range for the list '" + varName + "'.");
			}
			curList.setElementAt(newIndex,value);
		}
		else
		{
			//it should be a matrix
			if(!e.varIsDefined(varName))
			{
				throw new EvaluationException("The matrix " + varName + " is not defined.");
			}
			if(!(e.getVarType(varName) == VariableTable.TYPE_MATRIX))
		    {
				throw new EvaluationException("'" + varName + "' is not a matrix.");
			}
			Matrix m;
			int r = evaluateIndex(index,e);
			int c = evaluateIndex(col,e);
			
			m = (Matrix)e.getVarExpr(varName);
						
			if(r <= 0 || r > m.getNumRows() ||
			   c <= 0 || c > m.getNumCols())
			{
				throw new EvaluationException("The row or column for '" + varName + "' is outside of the valid range.");
			}
			m.setEntry(r-1,c-1,value);
		}
	}
	
	private int evaluateIndex(Expr index, EvaluationContext e)
	{
		Expr simIndex = index.simplify(e,EvaluationContext.VAR_EXPAND_ALL);
		if(!(simIndex instanceof NumberExpr))
		{
			throw new EvaluationException("The index of '" + varName + "' specified does not evaluate to a number.");
		}
		if(!(simIndex instanceof RealNumber))
		{
			throw new EvaluationException("Invalid index for '" + varName + "'.");
		}
		double newIndex = ((RealNumber)simIndex).getValue();
		if(newIndex != (int)newIndex)
		{
			throw new EvaluationException("Invalid index for '" + varName + "'.");
		}
		return (int)newIndex;
	}
		
	//Override
	public String toString(EvaluationContext e)
	{
		if(col == null & index == null)
		{
			return "define " + varName + " = " + value.toString(e);
		}
		else if(col == null)
		{
			return varName + "[" + index.toString(e) + "] = " + value.toString(e);
		}
		else
		{
			return varName + "[" + index.toString(e) + "][" + col.toString(e) + "] = " + value.toString(e);
		}
	}
}
