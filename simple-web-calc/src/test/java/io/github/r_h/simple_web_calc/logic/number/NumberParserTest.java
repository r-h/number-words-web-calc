package io.github.r_h.simple_web_calc.logic.number;

import static org.junit.Assert.assertEquals;
import io.github.r_h.simple_web_calc.logic.number.NumberParseException;
import io.github.r_h.simple_web_calc.logic.number.NumberParser;

import org.junit.Test;

public class NumberParserTest {

	
	@Test
	public void testTrivialParse() throws NumberParseException{
		assertEquals(Integer.valueOf(-385687), Integer.valueOf(new NumberParser("-385687").parse()));
	}
	
	@Test
	public void testThousandDotParse() throws NumberParseException{
		assertEquals(Integer.valueOf(1000000), Integer.valueOf(new NumberParser("1.000.000").parse()));
	}
	
	@Test
	public void testNumberWordParse() throws NumberParseException {
		assertEquals(Integer.valueOf(392834), Integer.valueOf(new NumberParser("dreihundertzweiundneunzigtausendachthundertvierunddreißig").parse()));
	}

}
