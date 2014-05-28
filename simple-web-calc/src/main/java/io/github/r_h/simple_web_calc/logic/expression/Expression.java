package io.github.r_h.simple_web_calc.logic.expression;

import io.github.r_h.simple_web_calc.logic.operation.CalculationException;
import io.github.r_h.simple_web_calc.logic.operation.Operator;



/**
 * A simple expression, consisting of two integers and an operator.
 * 
 * @author rhertrampf
 *
 */
/* package */ class Expression {
	
	private final int firstOperand;
	private final int secondOperand;
	
	private final Operator theOperator;

	/* package */ Expression(int firstOperand, Operator theOperator, int secondOperand) {
		super();
		this.firstOperand = firstOperand;
		this.secondOperand = secondOperand;
		this.theOperator = theOperator;
	}
	
	/**
	 * Evaluates this expression.
	 * 
	 * @return the result
	 * @throws CalculationException when calculation fails
	 */
	public int evaluate() throws CalculationException{
		return theOperator.calculate(firstOperand, secondOperand);
	}

}
