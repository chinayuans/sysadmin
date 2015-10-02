package org.appfuse.common.util.regexpression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.TestCase;

public class RegExpressionTest extends TestCase {
	public RegExpressionTest(String name) {
		super(name);
	}

	/*
	 * Groups and capturing: Capturing groups are numbered by counting their
	 * opening parentheses from left to right. In the expression ((A)(B(C))),
	 * for example, there are four such groups:
	 * 
	 * 1 ((A)(B(C))) 2 (A) 3 (B(C)) 4 (C)
	 * 
	 * Group zero always stands for the entire expression.
	 * 
	 * Capturing groups are so named because, during a match, each subsequence
	 * of the input sequence that matches such a group is saved. The captured
	 * subsequence may be used later in the expression, via a back reference,
	 * and may also be retrieved from the matcher once the match operation is
	 * complete.
	 * 
	 * The captured input associated with a group is always the subsequence that
	 * the group most recently matched. If a group is evaluated a second time
	 * because of quantification then its previously-captured value, if any,
	 * will be retained if the second evaluation fails. Matching the string
	 * "aba" against the expression (a(b)?)+, for example, leaves group two set
	 * to "b". All captured input is discarded at the beginning of each match.
	 * 
	 * Groups beginning with (? are pure, non-capturing groups that do not
	 * capture text and do not count towards the group total.
	 */
	public void testExpression1() throws Exception {
		String regularExpress = "((abc)([%$#]*)(\\d*)([a-z]+))";
		String searchedCharacters = "abc%%$$$###76934348adddfdffff";
		Pattern pattern = Pattern.compile(regularExpress);
		Matcher matcher = pattern.matcher(searchedCharacters);
//		System.out.println("matcher.group():" + matcher.group());
		while (matcher.find()) {
			for (int i = 0; i < matcher.groupCount(); i++) {
				System.out.println("group(" + i + "):" + matcher.group(i));
			}
		}
	}

	public void testExpression2() throws Exception {
		String regularExpress = "(a(b)?)+";
		String searchedCharacters = "aba";
		Pattern pattern = Pattern.compile(regularExpress);
		Matcher matcher = pattern.matcher(searchedCharacters);
//		System.out.println("matcher.group():" + matcher.group());
		while (matcher.find()) {
			for (int i = 0; i < matcher.groupCount(); i++) {
				System.out.println("group(" + i + "):" + matcher.group(i));
			}
		}
	}

	public void testExpression3() throws Exception {
		String regularExpress = "(\\d)";
		String searchedCharacters = "1";
		Pattern pattern = Pattern.compile(regularExpress);
		Matcher matcher = pattern.matcher(searchedCharacters);
		System.out.println("matcher.group():" + matcher.group());
		while (matcher.find()) {
			for (int i = 0; i < matcher.groupCount(); i++) {
				System.out.println("group(" + i + "):" + matcher.group(i));
			}
		}
	}

	public void testGroup() throws Exception {
		String regEx = "[+|-]?(\\d+(\\.\\d*)?)|(\\.\\d+)";
		String str = "a b c d e 1 2 3 4.5 .5";
		Pattern pattern = Pattern.compile(regEx);
		Matcher m = pattern.matcher(str);
		while (m.find()) {
			for (int i = 0; i <= m.groupCount(); i++) {
				System.out.println("Group " + i + ": " + m.group(i)); // Group
				// i
				// substring
			}
		}
	}

	public void testGroup0() throws Exception {
		Pattern pat = Pattern.compile("X+");
		Matcher mat = pat.matcher("X XX XXX");

		while (mat.find())
			System.out.println("Match: " + mat.group());

	}

	public void testappendReplacement() throws Exception {
		String joke = "dog day daughter daut did do done date";
		String regEx = "d";

		Pattern doggone = Pattern.compile(regEx);
		Matcher m = doggone.matcher(joke);

		StringBuffer newJoke = new StringBuffer();
		while (m.find())
			m.appendReplacement(newJoke, "g");
		m.appendTail(newJoke);
		System.out.println(newJoke.toString());

	}
	
	public void testappendTail() throws Exception {
		String joke = "dog " + "dogs";
	    String regEx = "dog";

	    Pattern doggone = Pattern.compile(regEx);
	    Matcher m = doggone.matcher(joke);

	    StringBuffer newJoke = new StringBuffer();
	    while (m.find()) {
	      m.appendReplacement(newJoke, "goat");
	    }
	    m.appendTail(newJoke);
	    System.out.println(newJoke.toString());
	}
}
