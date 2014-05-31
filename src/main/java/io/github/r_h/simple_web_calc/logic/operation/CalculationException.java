package io.github.r_h.simple_web_calc.logic.operation;

/**
 * 
 * @author rhertrampf
 *
 */
public class CalculationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4544295584971105296L;

	public CalculationException() {
		super();		
	}

	public CalculationException(String arg0, Throwable arg1) {
		super(arg0, arg1);

	}

	public CalculationException(String arg0) {
		super(arg0);
	}

	public CalculationException(Throwable arg0) {
		super(arg0);
	}

}
