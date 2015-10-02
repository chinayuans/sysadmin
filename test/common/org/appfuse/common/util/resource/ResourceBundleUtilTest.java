package org.appfuse.common.util.resource;

import java.util.Date;

import org.appfuse.common.util.resource.ResourceBundleUtil;

import junit.framework.TestCase;

public class ResourceBundleUtilTest extends TestCase {

	private final String resourceBundleName2="com.joson.customAnt.resourceBundle2";
	
	public ResourceBundleUtilTest(String name) {
		super(name);
	}

	public final void testGetLocaleStringString() {
		String sKey="key1";
		System.out.println(ResourceBundleUtil.getLocaleString(sKey));
		
	}

	public final void testGetLocaleStringStringObjectArray() {
		String sKey="key2";
		System.out.println(ResourceBundleUtil.getLocaleString
				(sKey,new Object[] {"yuanjs",new Date(),new Date()}));
	}

	public final void testGetResourceBundleLocaleStringStringObjectArrayString() {
		String sKey="key1";
		System.out.println(ResourceBundleUtil.getResourceBundleLocaleString(sKey,resourceBundleName2));
	}

	public final void testGetResourceBundleLocaleStringStringString() {
		String sKey="key2";
		System.out.println(ResourceBundleUtil.getResourceBundleLocaleString
				(sKey,new Object[] {"yuajs",new Date(),new Date()},resourceBundleName2));
	}

}
