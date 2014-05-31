package io.github.r_h.simple_web_calc.logic.expression;

import static org.junit.Assert.assertEquals;
import io.github.r_h.simple_web_calc.logic.expression.ExpressionParser;

import org.junit.Test;

public class ExpressionParserTest {

	
	@Test
	public void testSimpleParse() throws Exception{
		assertEquals(Integer.valueOf(4), Integer.valueOf(new ExpressionParser("12 durch 3").parse().evaluate()));
	}
	
	@Test
	public void testMixedParse() throws Exception {
		assertEquals(Integer.valueOf(2744), Integer.valueOf(new ExpressionParser("dreihundertzweiundneunzig mal 7").parse().evaluate()));
	}
	
	

}
