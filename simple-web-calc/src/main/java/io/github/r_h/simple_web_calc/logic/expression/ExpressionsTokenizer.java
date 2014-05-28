/**
 * 
 */
package io.github.r_h.simple_web_calc.logic.expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Splits a string into multiple expressions.
 * 
 * @author rhertrampf
 *
 */
public class ExpressionsTokenizer {

	private static final String LINEBREAK ="\n";
	
	private final String theExpressions;
	
	public ExpressionsTokenizer(String theExpressions) {
		this.theExpressions = theExpressions;
	}
	
	/**
	 * Splits the expressions into a list of expressions.
	 * 
	 * @return the list of expressions
	 */
	public List<Expression> tokenize() throws ExpressionParseException {
		List<Expression> result = new ArrayList<Expression>();		
		if (theExpressions!=null) {
			List<String> expressionsList = Arrays.asList(theExpressions.split(LINEBREAK));
			// clean list and add to result
			for (String expressionText : expressionsList) {
				if (!"".equals(expressionText)) {
					// remove \r and parse further...
					Expression anExpression = new ExpressionParser(expressionText.replaceAll("\r", "")).parse();
					result.add(anExpression);
				}
			}
		} else {
			throw new NullPointerException("Null expressions cant be tokenized !");
		}				
		return result;
		
	}
}
