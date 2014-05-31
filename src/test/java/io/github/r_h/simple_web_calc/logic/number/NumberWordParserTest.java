package io.github.r_h.simple_web_calc.logic.number;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NumberWordParserTest {

	@Test
	public void testParse() throws NumberWordException {
		String numberWord = "einemillionvierhunderttausendachthundertzweiundfünfzig";
		int literalNumber = 1400852;
		assertEquals(literalNumber, new NumberWordParser(numberWord).parse());
	}

	@Test(expected=NumberWordException.class)
	public void testIllegalParse() throws NumberWordException {
		String numberWord = "hundertzweiund";
		new NumberWordParser(numberWord).parse();
	}
}
