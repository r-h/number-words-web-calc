package io.github.r_h.simple_web_calc.logic.operation;

/**
 * Base implementation for an operation, which does some exception handling in case of errors.
 * 
 * @author rhertrampf
 *
 */
/*package */ abstract class AbstractExceptionHandledOperation implements ICalculationOperation {

	/**
	 * Simply execute this operation.
	 * 
	 * @param aOperand, the first operand.
	 * @param otherOperand, the second operand.
	 * @return the result.
	 */
	protected abstract long executeInternal(long aOperand, int otherOperand);
	
	/**
	 * @see io.github.r_h.simple_web_calc.logic.operation.ICalculationOperation#execute(int, int)
	 */
	@Override
	public final int execute(int aOperand, int otherOperand)
			throws CalculationException {
		long result;
		try {
			result = executeInternal(((long) aOperand), otherOperand);
	        if (result > Integer.MAX_VALUE) {
	        	throw new CalculationException("We got an overflow in the result !");
	        } else if (result < Integer.MIN_VALUE) {
	        	throw new CalculationException("We got an underflow in the result !");
	        }
			} catch (Exception ex) {
				throw new CalculationException("The following calculation error occured :"+ ex.getMessage());
			}
			return (int)result;
		
	}

}
