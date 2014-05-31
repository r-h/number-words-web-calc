package io.github.r_h.simple_web_calc.logic.number;

import io.github.r_h.simple_web_calc.logic.IToken;
import io.github.r_h.simple_web_calc.logic.operation.Operator;

import java.util.ArrayList;
import java.util.List;

/**
 * Translates a number word in its several tokens.
 * 
 * @author rhertrampf
 *
 */
public class NumberWordTokenizer {
		
	private final static String UND_WORD = "und";
	
	/**
	 * Number word in lower case.
	 */
	private final String aNumberWord;
	
	public NumberWordTokenizer(String aNumberWord) {
		this.aNumberWord = aNumberWord.toLowerCase();
	}

	/**
	 * Translate the number word in its tokens.
	 * By definition: the resulting list contains alternating numbers and operators .  
	 * 
	 * @return List of tokens 
	 * @throws NumberWordException if there are illegal / unknown parts.
	 */
	public List<IToken> tokenize()throws NumberWordException {
		List<IToken> result = new ArrayList<IToken>();
		IToken lastToken = null;
		StringBuffer wordBuffer = new StringBuffer(aNumberWord);
		// check for unary minus operator
		if (aNumberWord.startsWith(Operator.SUBTRACTION.getTextualRepresentation())) {
			// keep list sanitiy...
			result.add(Number.ZERO);
			IToken subOp = Operator.SUBTRACTION;
			removeTokenFromBufferAndAddToList(wordBuffer, subOp, result);
			lastToken = subOp;
		}
		do {			
			Number currentToken = getNumberFromBeginning(wordBuffer.toString());
			if (currentToken != null) {
				// its a simple number
				if (isNumber(lastToken)) {
					// add operators for list sanity...
					int lastNumber = lastToken.getOrdinalRepresentation();
					int currentNumber = currentToken.getOrdinalRepresentation();
					if (lastNumber < currentNumber) {
						result.add(Operator.MULTIPLICATION);
						
					} else if (lastNumber > currentNumber) {
						result.add(Operator.ADDITION);
						
					} else {
						// this is something illegal
						throw new NumberWordException("Unexpected token in number word !");
					}			
				} 
				removeTokenFromBufferAndAddToList(wordBuffer, currentToken, result);
			} else {
				// can still be the legal UND_WORD
				if (wordBuffer.toString().startsWith(UND_WORD) && (lastToken !=null && !isOperator(lastToken))) {
					wordBuffer.delete(0 , UND_WORD.length());
					result.add(Operator.ADDITION);
				} else {
					// this is a illegal
					throw new NumberWordException("Unexpected token in number word !");
				}
			} 
			lastToken = currentToken;
		    
		} while(wordBuffer.length()>0);
		
		return result;
	}
	
	/**
	 * Removes the given textual representation of the token 
	 * from the StringBuffer and adds it to the List.
	 * 
	 * @param aStringBuffer the StringBuffer
	 * @param atoken the token
	 * @param aList the List
	 */
	private void removeTokenFromBufferAndAddToList(StringBuffer aStringBuffer, IToken aToken, List<IToken> aList) {
		aStringBuffer.delete(0, aToken.getTextualRepresentation().length());
		aList.add(aToken);
	}
	
	/**
	 * Retrieves a number from the beginning of the string.
	 *  
	 * @param aString the string
	 * @return token or null if no number could be retrieved
	 */
	private Number getNumberFromBeginning(String aString) {
		Number result=null;
		for (Number number : Number.values()) {
			if (aString.startsWith(number.getTextualRepresentation())) {
				result = number;
				break;
			}
		}		
		return result;
	}
	
	/**
	 * Determines whether the give token is a number or not.
	 * 
	 * @param aToken the token
	 * @return true if the token was a number else false
	 */
	private boolean isNumber(IToken aToken) {
		
	 return (aToken instanceof Number);
	}
	
	/**
	 * Determines whether the given token is a operator or not.
	 * 
	 * @param aToken the token
	 * @return true if the token was a operator else false
	 */
	private boolean isOperator(IToken aToken) {
		
	 return (aToken instanceof Operator);
	}
	
	
	
}
