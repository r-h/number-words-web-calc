package io.github.r_h.simple_web_calc.logic.number;

import io.github.r_h.simple_web_calc.logic.IToken;
import io.github.r_h.simple_web_calc.logic.operation.Operator;

import java.util.Arrays;
import java.util.List;

import junit.framework.AssertionFailedError;

import org.junit.Test;


/**
 * @author rhertrampf
 *
 */
public class NumberWordTokenizerTest {
	
	
	/**
	 * Test method for {@link com.gateprotect.simplecalc.logic.numbers.NumberWordTokenizer#tokenize()}.
	 */
	@Test
	public void testSimpleAtomicTokenize() throws Exception{
		String numberWord = "FünF";
		List<IToken> expectedList = Arrays.asList((IToken)Number.FIVE);
		assertEqualsList(expectedList, getTokenized(numberWord));
	}
	
	/**
	 * Test method for {@link com.gateprotect.simplecalc.logic.numbers.NumberWordTokenizer#tokenize()}.
	 */
	@Test
	public void testComplexMillionTokenize() throws Exception{
		String numberWord = "minuseinemillionvierhunderttausendachthundertzweiundfünfzig";
		List<IToken> expected = Arrays.asList((IToken)Number.ZERO, Operator.SUBTRACTION, Number.DIFFERENT_ONE, Operator.MULTIPLICATION, Number.MILLION, Operator.ADDITION, 
				Number.FOUR, Operator.MULTIPLICATION, Number.HUNDRED, Operator.MULTIPLICATION, Number.THOUSAND, Operator.ADDITION, Number.EIGHT, 
				Operator.MULTIPLICATION, Number.HUNDRED, Operator.ADDITION, Number.TWO, Operator.ADDITION, Number.FIFTY);
		assertEqualsList(expected, getTokenized(numberWord));
	}
	
	/**
	 * Test method for {@link com.gateprotect.simplecalc.logic.numbers.NumberWordTokenizer#tokenize()}.
	 */
	@Test(expected=NumberWordException.class)
	public void testInvalidTokenTokenize() throws Exception{
		String numberWord = "zweihundertdrölfzig";
		getTokenized(numberWord);
	}

	/**
	 * Gets the given text tokenized.
	 * 
	 * @param text the text
	 * @return List of tokens
	 * @throws NumberWordException if tokenizing fails
	 */
	private List<IToken> getTokenized(String text) throws NumberWordException {
		return new NumberWordTokenizer(text).tokenize();
	}
	
	/**
	 * Asserts that the two given list are equal in a way that 
	 * both lists have the same size, 
	 * and all corresponding pairs of elements in the two lists are equal. 
	 * (Two elements e1 and e2 are equal if (e1==null ? e2==null : e1.equals(e2)).) 
	 * 
	 * @param expected expected List
	 * @param actual actual List
	 * @throws AssertionFailedError if the two list are not content equal
	 */
	protected void assertEqualsList(List<?> expected, List<?> actual) throws AssertionFailedError {
		if ((expected == null) && (actual == null)) {
			return;
		}
		if (((expected != null) && (actual == null)) || ((expected == null) && (actual != null))) {
			throw new AssertionFailedError("One element is null, the other not");
		}
		if (expected != null && actual != null) {
			if (expected.size() != actual.size()) {
				throw new AssertionFailedError("Size doesn't equals");
			}
			if (!expected.containsAll(actual)) {
				throw new AssertionFailedError("Expected list does not contain all elements of actual list !");
			}
		}
	}
}
