package edu.test.palindrome;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import edu.test.model.PalindromeResult;

/**
 * @author Sergio Bilello
 * 
 * 
 * This solution is a modified implementation of the Manacher’s Algorithm
 * Time Complexity O(N)
 * 
 * Write an algorithm to find the 3 longest unique palindromes in a string.
 * For the 3 longest palindromes, report the palindrome, start index and length in descending order of length.
 * Any tests should be included with the submission.
 *
 * For example, the output for string, "sqrrqabccbatudefggfedvwhijkllkjihxymnnmzpop" should be:
 *
 * Text: hijkllkjih, Index: 23, Length: 10
 * Text: defggfed, Index: 13, Length: 8
 * Text: abccba, Index: 5 Length: 6 
 */
public class App {
	
    
	public static void main( String[] args ){
    	
		/** check arguments**/
		if (!checkArgs(args))
			return;
		
		String inputString = args[0];
		
		List<PalindromeResult> results = getPalindromeResultList(inputString);
    	
		if (results.size()>0){
    		System.out.println(results);
    	}
    	else{
    		System.out.println(Constants.EMPTY_RESULTS_LIST_MESSAGE);
    	}
    }
    
	
    public static List<PalindromeResult> getPalindromeResultList(String inputString){
    	
    	List<PalindromeResult> results = new LinkedList<PalindromeResult>(); 
    	
    	for (int i=0;i<Constants.MAX_ITERATIONS;i++){
    		if (longestPalindrome(inputString,results)){
    			PalindromeResult res = results.get(i);
    			int index = res.getIndex();
    			int length = res.getLength();
    			String begin = inputString.substring(0,index);
	    		String end = inputString.substring(index+length,inputString.length());
	    		inputString = begin + end;
    		}
    		else {
    			break;
    		}
    	}
    	return results;
    }
	
	
    /** function to preprocess string **/
    private static String preProcess(String str){
        int len = str.length();
        if (len == 0) 
            return "^$";
        String s = "^";
        for (int i = 0; i < len; i++)
            s += "#" + str.charAt(i);         
        s += "#$";
        return s;
    }

    /** function to get largest palindromic substring **/
    private static boolean longestPalindrome(String str, List<PalindromeResult> results){

        /** preprocess string **/
        char[] s = preProcess(str).toCharArray();
        int N = s.length;
        int[] p = new int[N + 1];
        int id = 0, mx = 0;
        for (int i = 1; i < N - 1; i++) 
        {
             p[i] = mx > i ? Math.min(p[2 * id - i], mx - i) : 0;
             while (s[i + 1 + p[i]] == s[i - 1 - p[i]]) 
                 p[i]++;
             if (i + p[i] > mx) {
                 mx = i + p[i];
                 id = i;
             }
        }

        /** length of largest palindrome **/
        int maxLen = 0;

        /** position of center of largest palindrome **/
        int centerIndex = 0;
        for (int i = 1; i < N - 1; i++) {
            if (p[i] > maxLen) {
                maxLen = p[i];
                centerIndex = i;
            }
        }

        /** starting index of palindrome **/
        int pos = (centerIndex - 1 - maxLen)/2;
        String text = str.substring(pos , pos + maxLen);
        
        /** single character is not a palindrome **/
        if (text.length()>1){
        	PalindromeResult resObj =  new PalindromeResult(text,pos,maxLen);
        	results.add(resObj);
        	return true;
        }
        return false;
    }
  
    public static boolean checkArgs(String[] args) {
    	if (args==null) {
			throw new NullPointerException("ERROR: args is null");
		}
    	if (args.length > 1) {
			System.err.println(Constants.TOO_MANY_ARGUMENTS);
			return false;
		}
		if (args.length == 0) {
			System.err.println(Constants.USAGE);
			return false;
		}
		if (StringUtils.isEmpty(args[0])) {
			System.err.println(Constants.EMPTY_STRING);
			return false;
		}
		if (args[0].length() > Constants.MAX_STRING_LENGTH) {
			System.err.println(Constants.MAX_STRING_LENGTH_MESSAGE + Constants.MAX_STRING_LENGTH);
			return false;
		}
		if (args[0].length() == 1 ) {
			System.err.println(Constants.ONE_CHARACTER);
			return false;
		}
		return true;
	}
}