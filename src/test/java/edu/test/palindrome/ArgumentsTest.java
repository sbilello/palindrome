package edu.test.palindrome;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang.StringUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardErrorStreamLog;
/*
 * Test cases for checkArgs method in App.java
 *  
 */
public class ArgumentsTest {
	@Rule
	public final StandardErrorStreamLog log = new StandardErrorStreamLog();
	
	@Test(expected = NullPointerException.class)
	public void nullPointerException() {
		String[] args = null ;
		App.checkArgs(args);
	}
	
	@Test
	public void emptyArray() {
		String[] args = {} ;
		assertFalse(App.checkArgs(args));
		assertEquals(Constants.USAGE,StringUtils.trim(log.getLog()));
	}
	
	@Test
	public void oneCharacter() {
		String[] args = {"1"};
		assertFalse(App.checkArgs(args));
		assertEquals(Constants.ONE_CHARACTER,StringUtils.trim(log.getLog()));
	}
	
	@Test
	public void tooManyArguments() {
		String[] args = {"1","2"};
		assertFalse(App.checkArgs(args));
		assertEquals(Constants.TOO_MANY_ARGUMENTS,StringUtils.trim(log.getLog()));
	}
	
	@Test
	public void emptyString() {
		String[] args = {""};
		assertFalse(App.checkArgs(args));
		assertEquals(Constants.EMPTY_STRING,StringUtils.trim(log.getLog()));
	}
	
	@Test
	public void underLimit() {
		StringBuilder input = new StringBuilder();
		for (int i = 0; i <= 100; i++)
			input.append("a");
		String[] args = {input.toString()};
		assertTrue(App.checkArgs(args));
	}
	
	@Test
	public void maximumLength() {
		StringBuilder input = new StringBuilder();
		for (int i = 0; i <= 1000; i++)
			input.append("a");
		String[] args = {input.toString()};
		assertFalse(App.checkArgs(args));
		assertEquals(Constants.MAX_STRING_LENGTH_MESSAGE+Constants.MAX_STRING_LENGTH,StringUtils.trim(log.getLog()));
	}
}
