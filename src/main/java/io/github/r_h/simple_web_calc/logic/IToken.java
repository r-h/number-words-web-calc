package io.github.r_h.simple_web_calc.logic;

/**
 * A token has an ordinal and a textual representation.
 * 
 * @author rhertrampf
 *
 */
public interface IToken {

	/**
	 * 
	 * @return the textual representation.
	 */
	public String getTextualRepresentation();

	/**
	 * 
	 * @return the ordinal representation.
	 */
	public int getOrdinalRepresentation();
	
}
