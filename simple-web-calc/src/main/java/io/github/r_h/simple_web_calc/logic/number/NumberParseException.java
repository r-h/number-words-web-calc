package io.github.r_h.simple_web_calc.logic.number;

/**
 * Indicates an error when parsing a number.
 * 
 * @author rhertrampf
 *
 */
public class NumberParseException extends Exception {

	private static final long serialVersionUID = -3886182639814722944L;

	public NumberParseException() {
		super();
	}
	
	public NumberParseException(String message) {
		super(message);		
	}

	public NumberParseException(Throwable cause) {
		super(cause);
	}

	public NumberParseException(String message, Throwable cause) {
		super(message, cause);
	}

}
