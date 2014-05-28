package io.github.r_h.simple_web_calc.logic.operation;


/**
 * This defines the multiplication operation.
 * 
 * @author rhertrampf
 *
 */
/* package */class MultiplicationOperation extends AbstractExceptionHandledOperation {
	
	/**
	 * Multiplies the given operands.
	 */
	@Override
	protected long executeInternal(long aOperand, int otherOperand) {		
		return aOperand*otherOperand;
	}

}
