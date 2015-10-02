package org.appfuse.common.util.regexpression;


import org.appfuse.common.util.regexpression.ValidatorUtil;

import junit.framework.TestCase;

public class ValidatorUtilTest extends TestCase {

	public ValidatorUtilTest(String name) {
		super(name);
	}

	public final void testIsNotNull() {
        assertTrue(ValidatorUtil.isNotNull(""));
        assertFalse(ValidatorUtil.isNotNull(null));
	}

	public final void testIsNull() {
        assertFalse(ValidatorUtil.isNull(""));
        assertTrue(ValidatorUtil.isNull(null));
	}

	public final void testIsNumeric() {
		assertFalse(ValidatorUtil.isNumeric("ab?$ab"));
		assertFalse(ValidatorUtil.isNumeric("ab3ab"));
        assertTrue(ValidatorUtil.isNumeric("34335"));
	}

	public final void testHasNoSpaces() {
        assertFalse(ValidatorUtil.hasNoSpaces(" xxxf dfdf"));
        assertTrue(ValidatorUtil.hasNoSpaces("ffdfd"));
	}

	public final void testHasSpaces() {
        assertTrue(ValidatorUtil.hasSpaces(" xxxf dfdf"));
        assertFalse(ValidatorUtil.hasSpaces("fdfdfdfdfdf"));
	}

	public final void testHasValidCharacters() {
		/**
		 * \ * ? " < > | / , ^ & : ; = + % ' # $ { } [ ]
		 */
        assertFalse(ValidatorUtil.hasValidCharacters("ab\\ab"));
        assertFalse(ValidatorUtil.hasValidCharacters("ab*ab"));
		assertFalse(ValidatorUtil.hasValidCharacters("ab?ab"));
		assertFalse(ValidatorUtil.hasValidCharacters("ab\"ab"));
		assertFalse(ValidatorUtil.hasValidCharacters("ab<ab"));
		assertFalse(ValidatorUtil.hasValidCharacters("ab>ab"));
		assertFalse(ValidatorUtil.hasValidCharacters("ab|ab"));
		assertFalse(ValidatorUtil.hasValidCharacters("ab/ab"));
		assertFalse(ValidatorUtil.hasValidCharacters("ab,ab"));
		assertFalse(ValidatorUtil.hasValidCharacters("ab^ab"));
		assertFalse(ValidatorUtil.hasValidCharacters("ab&ab"));
		assertFalse(ValidatorUtil.hasValidCharacters("ab:ab"));
		assertFalse(ValidatorUtil.hasValidCharacters("ab;ab"));
		assertFalse(ValidatorUtil.hasValidCharacters("ab=ab"));
		assertFalse(ValidatorUtil.hasValidCharacters("ab+ab"));
		assertFalse(ValidatorUtil.hasValidCharacters("ab%ab"));
		assertFalse(ValidatorUtil.hasValidCharacters("ab'ab"));
		assertFalse(ValidatorUtil.hasValidCharacters("ab#ab"));
		assertFalse(ValidatorUtil.hasValidCharacters("ab$ab"));
		assertFalse(ValidatorUtil.hasValidCharacters("ab{ab"));
		assertFalse(ValidatorUtil.hasValidCharacters("ab}ab"));
		assertFalse(ValidatorUtil.hasValidCharacters("ab[ab"));
		assertFalse(ValidatorUtil.hasValidCharacters("ab]ab"));
		
		assertFalse(ValidatorUtil.hasValidCharacters("\\*?\"<>|/,^&:;=+%'#${}[]"));
		assertTrue(ValidatorUtil.hasValidCharacters("fdfd3434fdf"));
	}

	public final void testHasInvalidCharacters() {
		/**
		 * \ * ? " < > | / , ^ & : ; = + % ' # $ { } [ ]
		 */
		assertTrue(ValidatorUtil.hasInvalidCharacters("ab\\ab"));
		assertTrue(ValidatorUtil.hasInvalidCharacters("ab*ab"));
		assertTrue(ValidatorUtil.hasInvalidCharacters("ab?ab"));
		assertTrue(ValidatorUtil.hasInvalidCharacters("ab\"ab"));
		assertTrue(ValidatorUtil.hasInvalidCharacters("ab<ab"));
		assertTrue(ValidatorUtil.hasInvalidCharacters("ab>ab"));
		assertTrue(ValidatorUtil.hasInvalidCharacters("ab|ab"));
		assertTrue(ValidatorUtil.hasInvalidCharacters("ab/ab"));
		assertTrue(ValidatorUtil.hasInvalidCharacters("ab,ab"));
		assertTrue(ValidatorUtil.hasInvalidCharacters("ab^ab"));
		assertTrue(ValidatorUtil.hasInvalidCharacters("ab&ab"));
		assertTrue(ValidatorUtil.hasInvalidCharacters("ab:ab"));
		assertTrue(ValidatorUtil.hasInvalidCharacters("ab;ab"));
		assertTrue(ValidatorUtil.hasInvalidCharacters("ab=ab"));
		assertTrue(ValidatorUtil.hasInvalidCharacters("ab+ab"));
		assertTrue(ValidatorUtil.hasInvalidCharacters("ab%ab"));
		assertTrue(ValidatorUtil.hasInvalidCharacters("ab'ab"));
		assertTrue(ValidatorUtil.hasInvalidCharacters("ab#ab"));
		assertTrue(ValidatorUtil.hasInvalidCharacters("ab$ab"));
		assertTrue(ValidatorUtil.hasInvalidCharacters("ab{ab"));
		assertTrue(ValidatorUtil.hasInvalidCharacters("ab}ab"));
		assertTrue(ValidatorUtil.hasInvalidCharacters("ab[ab"));
		assertTrue(ValidatorUtil.hasInvalidCharacters("ab]ab"));
		
		assertTrue(ValidatorUtil.hasInvalidCharacters("\\*?\"<>|/,^&:;=+%'#${}[]"));
		assertFalse(ValidatorUtil.hasInvalidCharacters("fdfd3434fdf"));
	}

	public final void testIsBoolean() {
        assertTrue(ValidatorUtil.isBoolean("true"));
        assertTrue(ValidatorUtil.isBoolean("TRUE"));
        assertTrue(ValidatorUtil.isBoolean("false"));
        assertTrue(ValidatorUtil.isBoolean("FALSE"));
        assertFalse(ValidatorUtil.isBoolean("1"));
	}

	public final void testIsHostName() {
        assertTrue(ValidatorUtil.isHostName("www.ibm.cn"));
        assertTrue(ValidatorUtil.isHostName("computer"));
        assertFalse(ValidatorUtil.isHostName("193.334."));
        assertFalse(ValidatorUtil.isHostName("yyy*yyy"));
	}

	public final void testIsOneMatched() {
        String[] patterns={"^[0-9a-zA-Z*]+$"};
        assertTrue(ValidatorUtil.isOneMatched(patterns, "*Local"));
        assertTrue(ValidatorUtil.isOneMatched(patterns, "L*Local"));
        assertTrue(ValidatorUtil.isOneMatched(patterns, "LL*"));
        assertTrue(ValidatorUtil.isOneMatched(patterns, "*1Loc2al"));
        assertTrue(ValidatorUtil.isOneMatched(patterns, "*3L*oc3al"));
        assertFalse(ValidatorUtil.isOneMatched(patterns, ""));
        
        patterns=new String[]{"^[^(*LOCAL|*SYSBAS)].*$"};
        assertFalse(ValidatorUtil.isOneMatched(patterns, "*LOCAL"));
        assertFalse(ValidatorUtil.isOneMatched(patterns, "*SYSBAS"));
        assertTrue(ValidatorUtil.isOneMatched(patterns, "wps"));
	}

	public final void testIsAllMatched() {
        String[] patterns={"^[0-9a-zA-Z*]+$","^[^&()]+$"};
        assertTrue(ValidatorUtil.isAllMatched(patterns, "*Local"));
	}

	public final void testIsValidFiles() {
//        assertTrue(ValidatorUtil.isValidFiles("c:/",new String[]{"",""}));
	}

	public final void testIsValidDir() {
        assertTrue(ValidatorUtil.isValidDir("c:/"));
        assertFalse(ValidatorUtil.isValidDir("f"));
	}
    public final void testIsValidDirCharsOnWindows() {
        /**
         * windows
         */
        assertTrue(ValidatorUtil.isValidDirCharsOnWindows("c:/wasb"));
        assertFalse(ValidatorUtil.isValidDirCharsOnWindows("c:/8*[]/"));
        assertFalse(ValidatorUtil.isValidDirCharsOnWindows("eddf"));
        assertFalse(ValidatorUtil.isValidDirCharsOnWindows("/eddf"));
    }
    
    public final void testIsValidDirCharsOnUnix() {
        /**
         * unix
         */
        assertTrue(ValidatorUtil.isValidDirCharsOnUnix("/opt/"));
        assertFalse(ValidatorUtil.isValidDirCharsOnUnix("c:/8*[]/"));
        assertFalse(ValidatorUtil.isValidDirCharsOnUnix("/8*[]/"));
    }
    
    public void testEvaluateRegex() throws Exception {
    	assertTrue(ValidatorUtil.evaluateRegex("a*b","aaaaaaab"));
    	assertFalse(ValidatorUtil.evaluateRegex("a*b","aaaaaaaa"));
	}
}
