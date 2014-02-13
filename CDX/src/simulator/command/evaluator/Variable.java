package command.evaluator;

public class Variable extends Expr
{
	private String name;
	Expr index; //row
	Expr col;
	public Variable(String name)
	{
		this.name = name;
		this.index = null;
	}
	public Variable(String name, Expr index)
	{
		//for lists
		this.name = name;
		this.index = index;
		this.col = null;
	}
	public Variable(String name, Expr row, Expr col)
	{
		//for matrices
		this.name = name;
		this.index = row;
		this.col = col;
	}
	public Variable(Variable v)
	{
		this.name = v.getName();
		this.index = v.getIndex();
		this.col = v.getCol();
	}
	/*
	public double evaluate(EvaluationContext e) throws UndefinedVarException
	{
	if (e.varIsDefined(name))
	{
	return e.getVarValue(name);
	}
	else
	{
	throw new UndefinedVarException("The variable '" + name + "' is undefined.");
	}
	}
	*/
	//Override
	public Expr simplify(EvaluationContext ec, int expandOption)
	{

		if(expandOption == Expr.VAR_EXPAND_NONE ||
		   expandOption == Expr.VAR_EXPAND_CUR_SCOPE &&
		   ! ec.varIsDefinedInCurrentScope(name) ||
		   ! ec.varIsDefined(name)&&index==null&&col==null)
			return new Variable(this);
		
		
		try
		{
			Expr varValue = ec.getVarExpr(name);
			if(varValue instanceof Variable &&
			   ((Variable)varValue).getName().equals(this.name))
			{
				//prevent infinite recursion
				return new Variable(this);
			}
			
			if(index == null && col == null)
			{
				return ec.getVarExpr(name).simplify(ec,expandOption);
			}
			else if(col == null & index != null)
			{
				//is a list
				if(!(ec.varIsDefined(name)))
				{
					throw new EvaluationException("The list '" + name + "' is not defined.");
				}
				if(ec.getVarType(name) != VariableTable.TYPE_LIST)
				{
					throw new EvaluationException("'" + name + "' is not a list.");
				}
				
				int newIndex = evaluateIndex(index,ec,expandOption);
				
				try
				{
					return ((List)varValue).elementAt(newIndex);
				}
				catch(NumberFormatException nfe)
				{
					throw new NumberFormatException("The index specified for the list '" + name + "' is invalid.  It must be an integer.");
				}
				catch(IndexOutOfBoundsException iob)
				{
					throw new IndexOutOfBoundsException("The valid indices for the list '" + name + "' are between 1 and " + ((List)varValue).getItemCount() + ".");
				}
				
			}
			else if(col != null && index != null)
			{
				//matrix
				if(! ec.varIsDefined(name))
				{
					throw new EvaluationException("The matrix '" + name + "' is not defined.");
				}
				if(ec.getVarType(name) != VariableTable.TYPE_MATRIX)
				{
					throw new EvaluationException("'" + name + "' is not a matrix.");
				}
				
				int rowNew = evaluateIndex(index,ec,expandOption);
				int colNew = evaluateIndex(col,ec,expandOption);
				
				Matrix m = (Matrix)ec.getVarExpr(name);
				if(rowNew < 0 || rowNew > m.getNumRows() ||
				   colNew < 0 || colNew > m.getNumCols())
				{
					throw new EvaluationException("The indices specified for the matrix '" + name + "' are invalid.");
				}
				return m.getEntry(rowNew-1,colNew-1);
				
				
			}
			else
			{
				throw new EvaluationException("Unknown variable type.");
			}
			
		}
		catch(UndefinedVarException undef)
		{
			//this won't happen since we check first
			return null;
		}
	}
	

	private int evaluateIndex(Expr index, EvaluationContext e, int expandOption)
	{
		Expr simIndex = index.simplify(e,expandOption);
		if(!(simIndex instanceof NumberExpr))
		{
			throw new EvaluationException("The index of '" + name + "' specified does not evaluate to a number.");
		}
		if(!(simIndex instanceof RealNumber))
		{
			throw new EvaluationException("Invalid index for '" + name + "'.");
		}
		double newIndex = ((RealNumber)simIndex).getValue();
		if(newIndex != (int)newIndex)
		{
			throw new EvaluationException("Invalid index for '" + name + "'.");
		}
		return (int)newIndex;
	}
	
	public String getName() { return name;   }
	public Expr getRow()	{ return index;  }
	public Expr getIndex()	{ return  index; }
	public Expr getCol()	{ return col;	 }
	
	//Override
	public String toString(EvaluationContext e)
	{
		if(index == null)
			return name ;
		else if(col == null)
		{
			return name + "[" + index.toString(e) + "]";
		}
		else
		{
			return name + "[" + index.toString(e) + ", " + col.toString(e) + "]";
		}
	}
	//Override
	public void print(int indentLevel)
	{
		for(int i = 0; i < indentLevel; i++)
			System.out.print(" ");
		System.out.println("Variable: " + name);
		for(int i = 0; i < indentLevel; i++)
			System.out.print(" ");
		System.out.println("Index:");
		index.print(indentLevel + 1);
	}
}
