package io.github.r_h.simple_web_calc.logic.operation;


/**
 * This operation defines the subtraction.
 * 
 * @author rhertrampf
 *
 */
/* package */ class SubtractionOperation extends AbstractExceptionHandledOperation {

	/**
	 * Subtracts the two operands.
	 */
	@Override
	protected long executeInternal(long aOperand, int otherOperand) {		
		return aOperand-otherOperand;
	}

}
