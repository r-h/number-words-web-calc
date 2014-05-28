package io.github.r_h.simple_web_calc.logic.operation;

import io.github.r_h.simple_web_calc.logic.IToken;

import java.util.HashMap;
import java.util.Map;

/**
 * Enumerates the available operators
 * and their textual and character representation.
 * 
 * @author rhertrampf
 *
 */
public enum Operator implements IToken{
	
	
	ADDITION('+',"plus", new AdditionOperation()),
	SUBTRACTION('-', "minus", new SubtractionOperation()),
	MULTIPLICATION('*', "mal", new MultiplicationOperation()),
	DIVISION('/', "durch", new DivisionOperation());
	
	Operator(char characterRepresentation, 
			String textualRepresentation, ICalculationOperation theCalculation) {
		this.ordinalRepresentation = characterRepresentation;
		this.textualRepresentation = textualRepresentation;
		this.theCalculation = theCalculation;
		
	}
	
	/**
	 * The character representation.
	 */
	private final char ordinalRepresentation;
	/**
	 * The textual representation, in lower case.
	 */
	private final String textualRepresentation;
	
	/**
	 * The calculation operation.
	 */
	private final ICalculationOperation theCalculation;	

	/**
	 * Contains all CalculationOperations by characterRepresenation AND textualRepresentation as keys.
	 */
	private final static Map<String, Operator> ALL_CALC_OPS_BY_CHAR_AND_TEXT = new HashMap<String, Operator>();
	
	// Initialize search for CalculationOperation by characterRepresentation OR textualRepresentation
	static {
		for (Operator calcOp: Operator.values()) {
			ALL_CALC_OPS_BY_CHAR_AND_TEXT.put(""+(char)calcOp.getOrdinalRepresentation(), calcOp);
			ALL_CALC_OPS_BY_CHAR_AND_TEXT.put(calcOp.getTextualRepresentation(), calcOp);
		}
	}

	
	@Override
	public String getTextualRepresentation() {
		
		return textualRepresentation;
	}

	
	@Override
	public int getOrdinalRepresentation() {
		return ordinalRepresentation;
	}

	public ICalculationOperation getTheCalculation() {
		return theCalculation;
	}
	
	public int calculate(int firstOperand, int secondOperand) throws CalculationException{
		return theCalculation.execute(firstOperand, secondOperand);
	}
	
	/**
	 * Gets the corresponding CalculationOperation if the given String
	 * is a character or textual representation of one.
	 * 
	 * @param aString a String
	 * @return a CalculationOperation or null if there is none for the given string
	 */
	public final static Operator getCalculationOperationByText(String aString) {
		return ALL_CALC_OPS_BY_CHAR_AND_TEXT.get(aString);
	}
	
}
