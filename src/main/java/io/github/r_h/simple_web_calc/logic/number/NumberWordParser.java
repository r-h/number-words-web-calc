package io.github.r_h.simple_web_calc.logic.number;

import io.github.r_h.simple_web_calc.logic.IToken;
import io.github.r_h.simple_web_calc.logic.operation.CalculationException;
import io.github.r_h.simple_web_calc.logic.operation.Operator;

import java.util.List;

/**
 * 
 * Parses a German number word into an integer.
 * E.g. "einemillionvierhunderttausendachthundertzweiundfünfzig" translates to 1400852.
 * 
 * @author rhertrampf
 *
 */
public class NumberWordParser {
	
	private String aNumberWord;
	
	public NumberWordParser(String aNumberWord) {
		this.aNumberWord = aNumberWord;
	}
	
	/**
	 * Parses the number word into an integer.
	 * 
	 * @return the result
	 * @throws NumberWordException if something went wrong
	 */
	public int parse() throws NumberWordException {		
		List<IToken> theTokens = new NumberWordTokenizer(aNumberWord).tokenize();
		int result= 0;
		int chunk =	extractIntFromStart(theTokens);
		// Number word is complex
		if (theTokens.size()>1) {
			try {
				do {
					Operator theOperator = extractOperatorFromStart(theTokens);
					int secondOperand = extractIntFromStart(theTokens);
					chunk = theOperator.calculate(chunk, secondOperand);
					if (isEndOfChunk(secondOperand)) {
						result += chunk;
						chunk = 0;
					} 
				} while(!theTokens.isEmpty());
			} catch (CalculationException ce) {
				throw new NumberWordException("Failed building number word because :"+ce.getMessage()); 
			}
			// number word is corrupt
		} else if (theTokens.size()==1) {
			throw new NumberWordException("Unexpected number word structure !");
		}
		return result+chunk;
		
	}
	
	/**
	 * Returns the first token of the given list and removes it from the list.
	 * 
	 * @param theList the list of tokens.
	 * @return a token
	 * @throws NumberWordException if no token can be retrieved and removed
	 */
	private IToken extractTokenFromStart(List<IToken> theList)throws NumberWordException {
		IToken result;
		if (theList.size()>0) {
			result = theList.remove(0);
			
		} else {
			throw new NumberWordException("Unexpected end of number word !");
		}
		return result;
	}
	
	/**
	 * Returns the first element in the list as operator and removes it from the list.
	 * 
	 * @param theList the list of tokens
	 * @return an Operation
	 * @throws NumberWordException the first element in the list is no operator or list empty.
	 */
	private Operator extractOperatorFromStart(List<IToken> theList) throws NumberWordException {
		Operator result = null;
		IToken aToken = extractTokenFromStart(theList);
		if (aToken instanceof Operator) {
			result = (Operator) aToken;
		} else {
			throw new NumberWordException("Unexpected token in number word !");
		}
		return result;
	}
	
	/**
	 * Returns the first element in the list as number and removes it from the list.
	 * 
	 * @param theList the list of tokens
	 * @return an Number
	 * @throws NumberWordException the first element in the list is no number or list empty.
	 */
	private int extractIntFromStart(List<IToken> theList) throws NumberWordException {
		Number result = null;
		IToken aToken = extractTokenFromStart(theList);
		if (aToken instanceof Number) {
			result = (Number) aToken;
		} else {
			throw new NumberWordException("Unexpected token in number word !");
		}
		return result.getOrdinalRepresentation();
	}
	
	
	/**
	 * Checks whether the given integer marks the end of an chunk.
	 * 
	 * @param anInt an integer 
	 * @return true if the given integer marks the end of an chunk else false
	 */
	private boolean isEndOfChunk(int anInt) {
		return anInt == Number.MILLION.getOrdinalRepresentation() || anInt == Number.THOUSAND.getOrdinalRepresentation();
	}	

	

}
