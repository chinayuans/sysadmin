/**
 * Title		: CharsetConvert.java 
 * Description	: 常用字符集转换类
 * @author		: wufei
 * @version		: 1.0
 * @date		: 2004/06/21 14:00
 */

package org.appfuse.service.util;

import java.io.UnsupportedEncodingException;

public class CharsetConvert
{

	/**
	 * @param strIn
	 * @return
	 * @throws Exception
	 */
	public static String ISO2GB(String strIn)
	{
		String strOut = null;
		try
		{

			if (strIn == null || (strIn.trim()).equals(""))
				return strIn;
			byte[] b = strIn.getBytes("ISO8859_1");
			strOut = new String(b, "GB2312");
			//strOut =new String(strIn.getByte("ISO8859_1"),"GB2312");

		}
		catch (UnsupportedEncodingException e)
		{
			
			e.printStackTrace();
		}
		return strOut;
	}

	/**
	 * @param strIn
	 * @return
	 * @throws Exception
	 */
	public static String GB2ISO(String strIn)
	{
		String strOut = null;
		try
		{

			if (strIn == null || (strIn.trim()).equals(""))
				return strIn;
			byte[] b = strIn.getBytes("GB2312");
			strOut = new String(b, "ISO8859_1");
			//strOut =new String(strIn.getByte("GB2312"),"ISO8859_1");
		}
		catch (UnsupportedEncodingException e)
		{
			
			e.printStackTrace();
		}
		return strOut;
	}

}