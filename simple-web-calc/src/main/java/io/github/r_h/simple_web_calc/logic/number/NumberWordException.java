package io.github.r_h.simple_web_calc.logic.number;

/**
 * Exception that indicates, that there is something wrong with a number word
 * 
 * @author rhertrampf
 *
 */
public class NumberWordException extends Exception {

	
	private static final long serialVersionUID = 6171633435141168410L;

	
	public NumberWordException() {
		
	}

	public NumberWordException(String message) {
		super(message);
	
	}

	
	public NumberWordException(Throwable cause) {
		super(cause);
		
	}

	public NumberWordException(String message, Throwable cause) {
		super(message, cause);
		
	}

}
