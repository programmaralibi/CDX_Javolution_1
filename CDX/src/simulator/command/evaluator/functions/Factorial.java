package command.evaluator.functions;

import command.evaluator.ComplexNumber;
import command.evaluator.EvaluationContext;
import command.evaluator.EvaluationException;
import command.evaluator.Expr;
import command.evaluator.Function;
import command.evaluator.integrator.TooManyStepsException;

public class Factorial extends Function {
	public Factorial(Expr inside) {
		super(inside);
	}

	//Override
	public double getValue(double d, EvaluationContext ec) throws TooManyStepsException {
		// return new evaluator.integrator.FactorialEvaluator(d,ec).eval();
		if ((int) d == d) {
			return factorial((int) d);
		} else {
			return Math.exp(gammaln(d + 1.0));
		}
	}

	//Override
	public ComplexNumber getValue(ComplexNumber c, EvaluationContext ec) {
		throw new EvaluationException("Imaginary arguments are not supported for this function.");
	}

	//Override
	public String getName() {
		return "Factorial";
	}
	//Override
	public Expr make(Expr e) { return new Factorial(e); }

	// overrides Function class method
	//Override
	public String toString() {
		return inside.toString() + "!";
	}

	// based on Numerical Recipes in C, p. 214
	private static final double coef[] = { 76.18009172947146, -86.50532032941677, 24.01409824083091,
			-1.231739572450155, 0.1208650973866179e-2, -0.5395239384953e-5 };

	private double gammaln(double xx) throws EvaluationException {
		double x, y, tmp, ser;
		int j;
		y = x = xx;
		tmp = x + 5.5;
		tmp -= (x + 0.5) * Math.log(tmp);
		ser = 1.000000000190015;
		for (j = 0; j <= 5; j++) {
			ser += coef[j] / ++y;
		}
		return -tmp + Math.log(2.5066282746310005 * ser / x);
	}

	private static double factorials[] = initFactorials();
	private static int ntop = 4;

	private static double[] initFactorials() {
		double[] temp = new double[33];
		temp[0] = 1.0;
		temp[1] = 1.0;
		temp[2] = 2.0;
		temp[3] = 6.0;
		temp[4] = 24.0;
		return temp;
	}

	// based on Numerical Recipes in C, p. 214
	public double factorial(int n) {
		int j;
		if (n < 0) throw new EvaluationException("You cannot take the factorial of a negative number.");

		if (n > 32) { return Math.exp(gammaln(n + 1.0)); }
		// expand table, if necessary
		while (ntop < n) {
			j = ntop++;
			factorials[ntop] = factorials[j] * ntop;
		}
		return factorials[n];
	}
}
