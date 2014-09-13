package edu.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import edu.test.palindrome.AppTest;
import edu.test.palindrome.ArgumentsTest;
@RunWith(Suite.class)
@Suite.SuiteClasses({
   ArgumentsTest.class,
   AppTest.class
})
public class JunitTestSuite {   
}