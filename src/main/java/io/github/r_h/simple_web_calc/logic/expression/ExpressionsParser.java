package io.github.r_h.simple_web_calc.logic.expression;

import io.github.r_h.simple_web_calc.logic.operation.CalculationException;

import java.util.ArrayList;
import java.util.List;

/**
 * Parses a multiple expressions into multiple results.
 * 
 * @author rhertrampf
 *
 */
public class ExpressionsParser {
	
	private String theExpressions;
	
	
	public ExpressionsParser(String theExpressions) {
		this.theExpressions = theExpressions;
	}
	
	/**
	 * Parses the expressions into results.
	 * 
	 * @return the results
	 */
	public List<Integer> parse() throws ExpressionParseException {
		List<Integer> result = new ArrayList<Integer>();
		List<Expression> expressionList = new ExpressionsTokenizer(theExpressions).tokenize();
		for (Expression expression : expressionList) {
			try {							
				result.add(expression.evaluate());
			} catch (CalculationException ce) {
				throw new ExpressionParseException("Expression could not be parsed because :"+ce.getMessage());
			}
		}
		
		return result;
	}

}
