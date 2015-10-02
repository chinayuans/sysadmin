package org.appfuse.common.util.resource;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * Resource bundle utilities 
 */
public class ResourceBundleUtil
{
	/** Class constants follow */
	private static final String S_RESOURCE_BUNDLE_NAME = 
		"org.appfuse.common.util.resource.resourceBundle";
	
	
	/**
	 * Returns the locale specific string for the given
	 * resource bundle key
	 * <p>
	 * @param sKey	The given resource bundle key
	 * <p>
	 * @return	The required locale specific string if the
	 * 			key was found in the resource bundle, else
	 * 			it simply returns the key itself
	 */
	public static String getLocaleString( String sKey )
	{
		ResourceBundle resourcebundleWS = 
			ResourceBundle.getBundle( S_RESOURCE_BUNDLE_NAME );
			
		try
		{
			String sString = resourcebundleWS.getString( sKey );
			return sString;
		}
		catch ( Throwable e )
		{
			return sKey;	 
		}
	}
	
	/**
	 * Returns the locale specific string for the given
	 * resource bundle key
	 * <p>
	 * @param sKey		The given resource bundle key
	 * @param sParam	The params for the localized message
	 * <p>
	 * @return	The required locale specific string if the
	 * 			key was found in the resource bundle, else
	 * 			it simply returns the key itself
	 */
	public static String getLocaleString( String sKey,Object[] sParam )
	{
		ResourceBundle resourcebundleWS = 
			ResourceBundle.getBundle( S_RESOURCE_BUNDLE_NAME );
			
		try
		{
			String sString = resourcebundleWS.getString( sKey );
			return MessageFormat.format( sString, sParam);
		}
		catch ( Throwable e )
		{
			return sKey;	 
		}
	}
	
	/**
	 * Returns the locale specific string for the given
	 * resource bundle key
	 * <p>
	 * @param sKey		The given resource bundle key
	 * @param sParam	The params for the localized message
	 * @param sResourceBundle	The resource bundle to load
	 * <p>
	 * @return	The required locale specific string if the
	 * 			key was found in the resource bundle, else
	 * 			it simply returns the key itself
	 */
	public static String getResourceBundleLocaleString( String sKey,Object[] sParam,String sResourceBundle)
	{
		ResourceBundle resourcebundleWS = 
			ResourceBundle.getBundle( sResourceBundle );
			
		try
		{
			String sString = resourcebundleWS.getString( sKey );
			return MessageFormat.format( sString, sParam);
		}
		catch ( Throwable e )
		{
			return sKey;	 
		}
	}
	
	
	/**
	 * Returns the locale specific string for the given
	 * resource bundle key and given resource bundle
	 * <p>
	 * @param sKey				The given resource bundle key
	 * @param sResourceBundle	The resource bundle to load
	 * <p>
	 * @return	The required locale specific string if the
	 * 			key was found in the resource bundle, else
	 * 			it simply returns the key itself
	 */
	public static String getResourceBundleLocaleString( String sKey,String sResourceBundle )
	{
		ResourceBundle resourcebundleWS = 
			ResourceBundle.getBundle( sResourceBundle );
			
		try
		{
			String sString = resourcebundleWS.getString( sKey );
			return sString;
		}
		catch ( Throwable e )
		{
			return sKey;	 
		}
	}
}
