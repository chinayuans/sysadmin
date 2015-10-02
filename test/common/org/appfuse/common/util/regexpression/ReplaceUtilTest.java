package org.appfuse.common.util.regexpression;

import junit.framework.TestCase;

public class ReplaceUtilTest extends TestCase {

	/*
	 * Test method for 'org.appfuse.common.util.regexpression.ReplaceUtil.replaceAll(String, String, String)'
	 */
	public void testReplaceAll() {
		String replacedChars=ReplaceUtil.replaceAll("abc234343","(.*)(\\d.*)","$0 | $1 | $2");
		System.out.println(replacedChars);
		
		replacedChars=ReplaceUtil.replaceAll("abc234343","(.*)(\\d.*)","$0 \\\\ $1 \\\\ $2");
		System.out.println(replacedChars);
		
		replacedChars=ReplaceUtil.replaceAll("abc234343","(.*)(\\d.*)","$0 \\r $1 \\n $2");
		System.out.println(replacedChars);
		
		replacedChars=ReplaceUtil.replaceAll("$dfdfdf$#%%%%%5334343$ ","\\$[^#]","");
		System.out.println(replacedChars);
		
		replacedChars=ReplaceUtil.replaceAll("$dfdfdf$#%%%%%5334343$","\\$[^#]?","");
		System.out.println("L:"+replacedChars);
		
		replacedChars=ReplaceUtil.replaceAll("$dfdfdf$#%%%%%5334343$ ","\\$[^#]","$0");
		System.out.println(replacedChars);
		
		replacedChars=ReplaceUtil.replaceAll("$dfdfdf$#%%%%%5334343$ ","\\$[^#]","$1");
		System.out.println(replacedChars);
		
		replacedChars=ReplaceUtil.replaceAll("'fff'''f","\'"," ");
		System.out.println(replacedChars);
	}

}
