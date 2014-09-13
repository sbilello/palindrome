package edu.test.palindrome;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import junitx.framework.ListAssert;

import org.apache.commons.lang.StringUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;

import edu.test.model.PalindromeResult;

public class AppTest {
	
	@Rule
	public final StandardOutputStreamLog log = new StandardOutputStreamLog();

	@Test
	public void questionTest() {
		List<PalindromeResult> expected = new LinkedList<PalindromeResult>();
		PalindromeResult one = new PalindromeResult("hijkllkjih", 23, 10);
		PalindromeResult two = new PalindromeResult("defggfed", 13, 8);
		PalindromeResult three = new PalindromeResult("abccba", 5, 6);
		expected.add(one);
		expected.add(two);
		expected.add(three);
		String inputString = "sqrrqabccbatudefggfedvwhijkllkjihxymnnmzpop";
		List<PalindromeResult> actual = App.getPalindromeResultList(inputString);
		ListAssert.assertEquals(expected, actual);
	}
	
	@Test
	public void questionOutputTest() {
		String inputString = "sqrrqabccbatudefggfedvwhijkllkjihxymnnmzpop";
		String[] args= {inputString};
		App.main(args);
		String result = "[Text: hijkllkjih Index: 23 Length: 10, Text: defggfed Index: 13 Length: 8, Text: abccba Index: 5 Length: 6]";
		assertEquals(result,StringUtils.trim(log.getLog()));
	}
	
	
	@Test
	public void emptyResultListTest() {
		List<PalindromeResult> expected = new LinkedList<PalindromeResult>();
		String inputString = "nopalindrome";
		List<PalindromeResult> actual = App.getPalindromeResultList(inputString);
		ListAssert.assertEquals(expected, actual);
	}
	
	@Test
	public void emptyResultMessageTest() {
		String[] args= {"nopalindrome"};
		App.main(args);
		assertEquals(Constants.EMPTY_RESULTS_LIST_MESSAGE,StringUtils.trim(log.getLog()));
	}
	
	@Test
	public void palindromeNested() {
		String[] args= {"cicciociccio"};
		App.main(args);
		String result = "[Text: icci Index: 1 Length: 4, Text: icci Index: 3 Length: 4, Text: coc Index: 0 Length: 3]";
		assertEquals(result,StringUtils.trim(log.getLog()));
	}
	
}
