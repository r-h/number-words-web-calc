package io.github.r_h.simple_web_calc.logic.number;

import io.github.r_h.simple_web_calc.logic.IToken;



/**
 * Represents a number, which has a textual and an ordinal representation.
 * For example: ("eins", 1) and ("two", 2) but also ("ein", 1) like in "einhundert" and ("eine", 1) like in "einemillion".
 * 
 * @author rhertrampf
 * 
 */
public enum Number implements IToken {

	/**
	 * Ordered by the ordinal and string representation, descending ! 
	 */
	MILLION("million", 1000000),
	THOUSAND("tausend", 1000),
	HUNDRED("hundert", 100),
	NINETY("neunzig", 90),
	EIGHTY("achtzig", 80),
	SEVENTY("siebzig", 70),
	SIXTY("sechzig", 60),
	FIFTY("fünfzig", 50),
	FORTY("vierzig", 40),
	THIRTY("dreißig", 30),
	TWENTY("zwanzig", 20),
	SEVENTEEN("siebzehn", 17),
	SIXTEEN("sechzehn", 16),
	TWELVE("zwölf", 12),
	ELEVEN("elf", 11),
	TEN("zehn", 10),
	NINE("neun", 9),
	EIGHT("acht", 8),
	SEVEN("sieben", 7),
	SIX("sechs", 6),
	FIVE("fünf", 5),
	FOUR("vier", 4),
	THREE("drei", 3),
	TWO("zwei", 2),
	ONE("eins", 1),
	DIFFERENT_ONE("eine", 1),
	ANOTHER_ONE("ein", 1),	
	ZERO("null",0);

	
	/**
	 * The textual representation, per definition always lower case.
	 */
	private String textualRepresentation;
	
	/**
	 * The ordinal representation.
	 */
	private int ordinalRepresentation;
	
	@Override
	public String getTextualRepresentation() {
		
		return textualRepresentation;
	}

	@Override
	public int getOrdinalRepresentation() {
		return ordinalRepresentation;
	}
	
	Number(String textualRepresentation, int ordinalRepresentation) {
		this.textualRepresentation = textualRepresentation;
		this.ordinalRepresentation = ordinalRepresentation;
	}
		
	


}
