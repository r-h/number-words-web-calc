package io.github.r_h.simple_web_calc.logic.expression;

/**
 * Indicates an Error in the expression parsing.
 * 
 * @author rhertrampf
 *
 */
public class ExpressionParseException extends Exception {


	private static final long serialVersionUID = 842859281644600201L;


	public ExpressionParseException() {
		super();
	}

	public ExpressionParseException(String message) {
		super(message);
	}

	public ExpressionParseException(Throwable cause) {
		super(cause);
	}

	public ExpressionParseException(String message, Throwable cause) {
		super(message, cause);
	}

}
