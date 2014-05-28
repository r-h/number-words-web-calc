package io.github.r_h.simple_web_calc.logic.operation;


/**
 * This class defines the division operation.
 * 
 * @author rhertrampf
 *
 */
/* package */ class DivisionOperation extends AbstractExceptionHandledOperation {

	/**
	 * Divides <b>aOperand<b> through <b>otherOperand<b>.
	 */
	@Override
	protected long executeInternal(long aOperand, int otherOperand) { 
		return aOperand/otherOperand;
	}

}
