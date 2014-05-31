package io.github.r_h.simple_web_calc.logic.number;

import org.apache.log4j.Logger;

/**
 * Parses a string representation of a number.
 * 
 * @author rhertrampf
 *
 */
public class NumberParser {
	
	private static final Logger LOG = Logger.getLogger(NumberParser.class);
	
	private final String aNumber;
	
	public NumberParser(String aNumber) {
		this.aNumber = aNumber;
	}
	
	public int parse() throws NumberParseException{
		int result=0;
		try {			
			// remove dots and parse int...
			result = Integer.parseInt(aNumber.replaceAll("\\.", ""));
		} catch (NumberFormatException nfe) {
			LOG.debug("Its not a number in parsable int format, lets try a numberword");
			try {
				result = new NumberWordParser(aNumber).parse();	
			} catch (NumberWordException nwe) {
				throw new NumberParseException("Could not parse number : {"+aNumber+"} because :"+nwe.getMessage());
			}
			
		} 
		return result;
	}

}
