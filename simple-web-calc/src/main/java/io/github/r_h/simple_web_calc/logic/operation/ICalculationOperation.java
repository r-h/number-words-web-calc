package io.github.r_h.simple_web_calc.logic.operation;


/**
 * 
 * Defines a calculation operation, which can be executed on operands.
 * 
 * @author rhertrampf
 *
 */
public interface ICalculationOperation {
	
	/**
	 * Executes this operation on two ints.
	 *  
	 * @param aOperand, the first operand 
	 * @param otherOperand, the second operand
	 * @return the result
	 * @throws CalculationException if calculation went wrong
	 */
	public int execute(int aOperand, int otherOperand) throws CalculationException;

}
