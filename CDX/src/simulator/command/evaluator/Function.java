package command.evaluator;

public abstract class Function extends Expr {
	protected Expr inside;

	public Function(Expr inside) {
		this.inside = inside;
	}

	//Override
	public Expr simplify(EvaluationContext ec, int expandOption) {
		Expr newInside = inside.simplify(ec, expandOption);
		if (newInside instanceof NumberExpr) {
			if (((NumberExpr) newInside).isReal()) {
				return RealNumber.createRealNumber(getValue((NumberExpr) newInside, ec));
			} else {
				return getValue((ComplexNumber) newInside, ec);
			}
		} else if (newInside instanceof List) {
			Functionizer fun = new Functionizer(this);
			((List) newInside).applyToAll(fun);
			return fun.getList().simplify(ec, expandOption);
		} else {
			Object o = null;
			Object params[] = { newInside };
			o = make(newInside);
			return (Expr) o;
		}
	}

	abstract public Expr make(Expr e);

	/*
	 * public double evaluate(EvaluationContext e) throws UndefinedVarException { double
	 * insideValue; if(this instanceof Sin || this instanceof Cos || this instanceof Tan) {
	 * insideValue = e.convertToRadians(inside.evaluate(e)); } else { insideValue =
	 * inside.evaluate(e); } return getValue(insideValue); }
	 */
	//Override
	public String toString(EvaluationContext e) {
		return getName() + "(" + inside.toString(e) + ")";
	}

	//Override
	public void print(int indentLevel) {
		String typeName = getName();

		for (int i = 0; i < indentLevel; i++)
			System.out.print(" ");

		System.out.println("Function: " + typeName);
		inside.print(indentLevel + 1);
	}

	public abstract ComplexNumber getValue(ComplexNumber c, EvaluationContext ec);

	public abstract double getValue(double d, EvaluationContext ec);

	public abstract String getName();

	public double getValue(NumberExpr n, EvaluationContext ec) {
		return getValue(n.getValue(), ec);
	}

	protected double sinh(double d, EvaluationContext ec) {
		return (Math.exp(d) - Math.exp(-d)) / 2;
	}

	protected double cosh(double d, EvaluationContext ec) {
		return (Math.exp(d) + Math.exp(-d)) / 2;
	}

	class Functionizer implements ListApplicator {
		// create a new List, such that, for example, each member of this list
		// is the sine of the element in the previous list ( = the List that the applicator is
		// called on)

		ExprList newExprList;
		ExprList curLocation;
		Function cl;

		private Functionizer(Function cl) {
			newExprList = null;
			curLocation = null;
			this.cl = cl;
		}

		public void apply(Object o, int termNumber, int totalTerms) {
			Function newFunc = createFunc(o);

			if (newExprList == null) {
				newExprList = new ExprList(newFunc, null);
				curLocation = newExprList;
			} else {
				ExprList nextList = new ExprList(newFunc, null);
				curLocation.setNext(nextList);
				curLocation = nextList;
			}
		}

		private Function createFunc(Object o) {
			return (Function) cl.make((Expr) o);
		}

		public List getList() {
			return new List(newExprList);
		}

	}
}
