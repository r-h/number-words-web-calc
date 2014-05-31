package io.github.r_h.simple_web_calc.logic.expression;

import io.github.r_h.simple_web_calc.logic.number.NumberParseException;
import io.github.r_h.simple_web_calc.logic.number.NumberParser;
import io.github.r_h.simple_web_calc.logic.operation.Operator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Parses a string into an expression.
 * 
 * @author rhertrampf
 *
 */
public class ExpressionParser {
	
	private final static String SPACE = " ";
	
	private final String theExpression;
	
	public ExpressionParser(String theExpression) {
		this.theExpression = theExpression;
	}
	
	/**
	 * Parses a string into the according expression.
	 * 
	 * @return an Expression
	 */
	public Expression parse() throws ExpressionParseException{
		Expression result = null;		
		List<String> expressionParts = tokenizeExpression();
		removeEmptyExpressionParts(expressionParts);
		//expression has only 3 parts per definition
		if (expressionParts.size()==3) {
			int firstOperand;
			int secondOperand;
			try {
				// a first operand as number
				firstOperand = new NumberParser(expressionParts.get(0)).parse();
				checkOperandIsInRange(firstOperand);
				// a second operand as number
				secondOperand = new NumberParser(expressionParts.get(2)).parse();
				checkOperandIsInRange(firstOperand);
			} catch (NumberParseException npe) {
				throw new ExpressionParseException("Invalid number in expected operand because :"+npe.getMessage());
			}
			// and a operator		
			Operator anOperator = Operator.getCalculationOperationByText(expressionParts.get(1));
			if (anOperator != null) {
				result = new Expression(firstOperand, anOperator, secondOperand);
			} else {
				throw new ExpressionParseException("Invalid operator in expression !");
			}
		} else {
			throw new ExpressionParseException("Expression has unexpected structure !");
		}
		return result;
	}

	/**
	 * Removes empty Strings form the given list.
	 * 
	 * @param expressionParts list of parts as String
	 */
	private void removeEmptyExpressionParts(List<String> expressionParts) {
		int i = 0;
		for (String expressionText : expressionParts) {
			if ("".equals(expressionText)) {				
				expressionParts.remove(i);
			}
			i++;
		}
	}

	/**
	 * @return the several parts in the expression
	 */
	private ArrayList<String> tokenizeExpression() {
		return new ArrayList<String>(Arrays.asList(theExpression.split(SPACE)));
	}
	
	/**
	 * Checks if the operand is in the allowed range.
	 * 
	 * @param theOperand the operand
	 * @return true if operand is in range else false
	 */
	private void checkOperandIsInRange(int theOperand) throws ExpressionParseException {
		if (!( 9999999 >= theOperand && theOperand >= -9999999)) {
			throw new ExpressionParseException("Operand is out of range !");
		}
		
	}

}
