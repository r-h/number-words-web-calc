/**
 * 
 */
package io.github.r_h.simple_web_calc.logic.operation;


/**
 * This operation defines the addition operation.
 * 
 * @author rhertrampf
 *
 */
/* package */ class AdditionOperation extends AbstractExceptionHandledOperation {

	/**
	 * Simple adds the two operands together.
	 * 	
	 */
	@Override
	public long executeInternal(long aOperand, int otherOperand) {		
		return  aOperand + otherOperand;
	}

}
