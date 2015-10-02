package org.appfuse.common.util.regexpression;

public class ReplaceUtil {
	private ReplaceUtil() {
	}
	
	public static String replaceAll(String searchedChars,String pattern, String replacement) {
		return searchedChars.replaceAll(pattern,replacement);
	}
}
