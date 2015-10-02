package org.appfuse.common.util.classloader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;
import java.util.Vector;

import org.apache.log4j.Logger;



/**
 * 用来加载类，classpath下的资源文件，属性文件等。
 * getExtendResource(StringrelativePath)方法，可以使用../符号来加载classpath外部的资源。
 */
public class ClassLoaderUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ClassLoaderUtil.class);
	
	private static final String S_ERROR_ADDING_TO_CLASSPATH = "Error adding the given URL to the classpath";

    private static final String S_ADD_URL_METHOD_NAME = "addURL";
    
    private static final String S_JAR_FILE_PATTERN = "^.*\\.jar$";
    
    private ClassLoaderUtil() {
	}
    
	/**
	 * 加载Java类。 使用全限定类名
	 */
	public static Class loadClass(String className) {
		if (logger.isDebugEnabled()) {
			logger.debug("loadClass() - start");
		}

		try {
			Class returnClass = getClassLoader().loadClass(className);
			if (logger.isDebugEnabled()) {
				logger.debug("loadClass() - end");
			}
			return returnClass;
		} catch (ClassNotFoundException e) {
			logger.error("loadClass("+className+")", e);
			if (logger.isDebugEnabled()) {
				logger.debug("loadClass() - end");
			}
			
			throw new RuntimeException("class not found '" + className + "'", e);
		}
		
	}

	/**
	 * 得到类加载器
	 */
	public static ClassLoader getClassLoader() {
		if (logger.isDebugEnabled()) {
			logger.debug("getClassLoader() - start");
		}

		ClassLoader returnClassLoader = ClassLoaderUtil.class.getClassLoader();
		if (logger.isDebugEnabled()) {
			logger.debug("getClassLoader() - end");
		}
		return returnClassLoader;
	}

	/**
	 * 提供相对于classpath的资源路径，返回文件的输入流
	 * @param relativePath  必须传递资源的相对路径。是相对于classpath的路径。
	 *                      如果需要查找classpath外部的资源，需要使用 ../来查找
	 */
	public static URL getResource(String relativePath) {
		if (logger.isDebugEnabled()) {
			logger.debug("getResource("+relativePath+") - start");
		}
		
		if (relativePath.indexOf("../") < 0) {
			URL returnURL = getClassLoader().getResource(relativePath);
			
			if (logger.isDebugEnabled()) {
				logger.debug("returned value:"+returnURL.getPath());
				logger.debug("getResource() - end");
			}
			return returnURL;
		} else {
			URL returnURL = getExtendedResource(relativePath);
			if (logger.isDebugEnabled()) {
				logger.debug("returned value:"+returnURL.getPath());
				logger.debug("getResource() - end");
			}
			return returnURL;
		}
	}
	
	/**
	 * 提供相对于classpath的资源路径，返回文件的输入流
	 * @param relativePath  必须传递资源的相对路径。是相对于classpath的路径。
	 *                      如果需要查找classpath外部的资源，需要使用 ../来查找
	 */
	public static InputStream getResourceAsStream(String relativePath) {
		if (logger.isDebugEnabled()) {
			logger.debug("getResourceAsStream("+relativePath+") - start");
		}

		if (relativePath.indexOf("../") < 0) {
			InputStream returnInputStream = getClassLoader().getResourceAsStream(relativePath);
			if (logger.isDebugEnabled()) {
				logger.debug("getResourceAsStream() - end");
			}
			return returnInputStream;
		} else {
			InputStream returnInputStream = getExtendedResourceAsStream(relativePath);
			if (logger.isDebugEnabled()) {
				logger.debug("getResourceAsStream() - end");
			}
			return returnInputStream;
		}
	}

	/**
	 * @param relativePath 必须传递资源的相对路径。是相对于classpath的路径。
	 *                     如果需要查找classpath外部的资源，需要使用 ../来查找
	 * @return
	 */
	private static InputStream getExtendedResourceAsStream(String relativePath) {
		if (logger.isDebugEnabled()) {
			logger.debug("getExtendedResourceAsStream() - start");
		}

		URL url = getExtendedResource(relativePath);
		
		if (url != null) {
			InputStream returnInputStream = null;
			try {
				returnInputStream = url.openStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (logger.isDebugEnabled()) {
				logger.debug("getExtendedResourceAsStream() - end");
			}
			return returnInputStream;
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("getExtendedResourceAsStream() - end");
			}
			return null;
		}
	}

	/**
	 * @param relativePath 必须传递资源的相对路径。是相对于classpath的路径。
	 *                     如果需要查找classpath外部的资源，需要使 用../来查找
	 * @return 资源的绝对URL 
	 */
	private static URL getExtendedResource(String relativePath) {
		if (logger.isDebugEnabled()) {
			logger.debug("getExtendedResource() - start");
		}
		
		/**
		 *  relativePath: "/org/../../web.xml"  --->
		 *  relativePath: "org/../../web.xml"
 		 */
		if (relativePath.substring(0, 1).equals("/")) {
			relativePath = relativePath.substring(1);
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("relativePath:"+relativePath);
		}
		
		/**
		 *  relativePath: "org/../../web.xml"  --->
		 *  relativePath_prefix: "org/../../"
		 *  relativePath_suffix: "web.xml"
 		 */
		String relativePath_prefix = relativePath.substring(0, relativePath.lastIndexOf("../") + 3);
		
		if (logger.isDebugEnabled()) {
			logger.debug("relativePath_prefix:"+relativePath_prefix);
		}
		
		String relativePath_suffix = relativePath.substring(relativePath.lastIndexOf("../") + 3);
		
		if (logger.isDebugEnabled()) {
			logger.debug("relativePath_suffix:"+relativePath_suffix);
		}
		
		/**
		 * get the number of "../" occurrences in relativePath_prefix
		 *  relativePath_prefix: "org/../../"
		 *  containSum:  2
		 */
		int containSum = containSum(relativePath_prefix, "../");
		if (logger.isDebugEnabled()) {
			logger.debug("containSum:"+containSum);
		}
		
		/**
		 *  relativePath_prefix: "org/../../"
		 *  relativePath_pathPart:  "org/"
		 */
		String relativePath_pathPart = relativePath_prefix.substring(0,relativePath_prefix.indexOf("../"));
		if (logger.isDebugEnabled()) {
			logger.debug("relativePath_pathPart:"+relativePath_pathPart);
		}
		/**
		 * classpathAbsolutePath:
		 *    file:/D:/work/eclipse3.1-work/work/sysadmin/WebRoot/WEB-INF/classes/
		 */
		String classpathAbsolutePath = getAbsolutePathOfClassLoaderClassPath();
		
		/**
		 * absoluteFullClasspath:  file:/D:/work/eclipse3.1-work/work/sysadmin/WebRoot/WEB-INF/classes/org/
		 */
		String absoluteFullClasspath = classpathAbsolutePath + relativePath_pathPart;
		if (logger.isDebugEnabled()) {
			logger.debug("absoluteFullClasspath:"+absoluteFullClasspath);
		}
		
		/**
		 * absoluteClasspath:  file:/D:/work/eclipse3.1-work/work/sysadmin/WebRoot/WEB-INF/
		 */
		String absoluteClasspath = cutLastString(absoluteFullClasspath, "/", containSum);
		if (logger.isDebugEnabled()) {
			logger.debug("absoluteClasspath:"+absoluteClasspath);
		}
		
		/**
		 * resourceAbsolutePath: file:/D:/work/eclipse3.1-work/work/sysadmin/WebRoot/WEB-INF/web.xml
		 */
		String resourceAbsolutePath = absoluteClasspath + relativePath_suffix;
		
		if (logger.isDebugEnabled()) {
			logger.debug("resourceAbsolutePath:"+resourceAbsolutePath);
		}
		
		URL resourceAbsoluteURL = null;
		try {
			resourceAbsoluteURL = new URL(resourceAbsolutePath);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getExtendedResource() - end");
		}
		return resourceAbsoluteURL;
	}
	
	/**
	 * 提供相对于classpath的资源路径，返回属性对象，它是一个散列表 
	 * @paramresource 
	 * @return
	 */
	public static Properties getProperties(String resource) {
		if (logger.isDebugEnabled()) {
			logger.debug("getProperties() - start");
		}

		Properties properties = new Properties();
		try {
			properties.load(getResourceAsStream(resource));
		} catch (IOException e) {
			logger.error("getProperties()", e);

			throw new RuntimeException("couldn't load properties file '"
					+ resource + "'", e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getProperties() - end");
		}
		return properties;
	}

	/**
	 * 得到本Class所在的ClassLoader的Classpath的绝对路径。 
	 * URL形式的 
	 * @return
	 */
	public static String getAbsolutePathOfClassLoaderClassPath() {
		if (logger.isDebugEnabled()) {
			logger.debug("getAbsolutePathOfClassLoaderClassPath() - start");
		}
		
		String absolutePath = getClassLoader().getResource("").toString();
		if (logger.isDebugEnabled()) {
			logger.debug("absolutePath:"+absolutePath);
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAbsolutePathOfClassLoaderClassPath() - end");
		}
		return absolutePath;
	}

	/**
	 * count the number of dest's occurrences in source. 
	 * @param source
	 * @param dest
	 * @return
	 */
	private static int containSum(String source, String dest) {
		if (logger.isDebugEnabled()) {
			logger.debug("containSum() - start");
		}

		int containSum = 0;
		int destLength = dest.length();
		int location = source.indexOf(dest) ;
		
		while (location >= 0) {
			containSum = containSum + 1;
			source = source.substring(location + destLength);
			location = source.indexOf(dest) ;
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("containSum:"+containSum);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("containSum() - end");
		}
		return containSum;
	}

	private static String cutLastString(String source, String dest, int num) {
		if (logger.isDebugEnabled()) {
			logger.debug("cutLastString() - start");
		}

		// String cutSource=null;
		for (int i = 0; i < num; i++) {
			source = source.substring(0, source.lastIndexOf(dest, source.length() - 2) + 1);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("cutLastString() - end");
		}
		return source;
	}
	
	
	public static ClassLoader getBootStrapClassLoader() {
		//      return URL.class.getClassLoader();
		//      return URLClassLoader.class.getClassLoader().getSystemClassLoader().getParent().getParent();
		return URLClassLoader.class.getClassLoader();
	}

	public static ClassLoader getExtClassLoader() {
		return URLClassLoader.class.getClassLoader().getSystemClassLoader().getParent();
	}

	public static ClassLoader getSystemClassLoader() {
		return URLClassLoader.class.getClassLoader().getSystemClassLoader();
	}

	public static StringBuffer getExtClassLoaderClasspath() {
		StringBuffer bf = new StringBuffer();
		URLClassLoader loader = (URLClassLoader) getExtClassLoader();
		bf.append("Ext classloader urls:\n");

		for (int i = 0; i < loader.getURLs().length; i++) {
			bf.append(loader.getURLs()[i] + "\n");
		}

		return bf;
	}

	public static StringBuffer getSystemClassLoaderClasspath() {
		StringBuffer bf = new StringBuffer();
		URLClassLoader loader = (URLClassLoader) getSystemClassLoader();
		bf.append("System classloader urls:\n");
		for (int i = 0; i < loader.getURLs().length; i++) {
			bf.append(loader.getURLs()[i] + "\n");
		}
		return bf;
	}

	/**
	 * Adds the given URL to the ext classloader's classpath
	 * @param urlToAddToClassPath
	 *            The URL to add to the classpath
	 */
	public static void addURLToExtClassLoaderClasspath(URL urlToAddToClassPath)
			throws IOException {

		try {
			URLClassLoader systemClassLoader = (URLClassLoader) getExtClassLoader();

			Method method = URLClassLoader.class.getDeclaredMethod(
					S_ADD_URL_METHOD_NAME, new Class[] { URL.class });
			method.setAccessible(true);

			method.invoke(systemClassLoader,new Object[] { urlToAddToClassPath });
		} catch (Throwable t) {
			throw new IOException(S_ERROR_ADDING_TO_CLASSPATH);
		}
	}

	/**
	 * Adds the given URL to the system classloader's classpath
	 * @param urlToAddToClassPath
	 *            The URL to add to the classpath
	 */
	public static void addURLToSystemClassLoaderClasspath(
			URL urlToAddToClassPath) throws IOException {

		try {
			URLClassLoader systemClassLoader = (URLClassLoader) getSystemClassLoader();

			Method method = URLClassLoader.class.getDeclaredMethod(
					S_ADD_URL_METHOD_NAME, new Class[] { URL.class });
			method.setAccessible(true);

			method.invoke(systemClassLoader,new Object[] { urlToAddToClassPath });
		} catch (Throwable t) {
			throw new IOException(S_ERROR_ADDING_TO_CLASSPATH);
		}
	}

	/**
	 * Adds the given file to the Ext classloader's classpath
	 * @param sFilePath
	 *            The path to the file to add to the classpath
	 */
	public static void addFileToExtClassLoaderClasspath(String sFilePath)
			throws IOException {
		addURLToExtClassLoaderClasspath(new File(sFilePath).toURL());
	}

	/**
	 * Adds the given file to the system classloader's classpath
	 * @param sFilePath
	 *            The path to the file to add to the classpath
	 */
	public static void addFileToSystemClassLoaderClasspath(String sFilePath)
			throws IOException {
		addURLToSystemClassLoaderClasspath(new File(sFilePath).toURL());
	}

	/**
	 * Adds all files in a directory to the Ext classloader's classpath
	 * @param sDir
	 *            The directory path
	 */
	public static void addAllJarsToExtClassLoaderClasspath(String sDir)
			throws IOException {
		String[] asJARFiles = getAllFilesMatchingThisPatternIgnoreCase(sDir,
				S_JAR_FILE_PATTERN);

		for (int i = 0; i < asJARFiles.length; i++) {
			String sThisJARFilePath = new File(sDir, asJARFiles[i]).getAbsolutePath();
			addFileToExtClassLoaderClasspath(sThisJARFilePath);
		}
	}

	/**
	 * Adds all files in a directory to the System classloader's classpath
	 * @param sDir
	 *            The directory path
	 */
	public static void addAllJarsToSystemClassLoaderClasspath(String sDir)
			throws IOException {
		String[] asJARFiles = getAllFilesMatchingThisPatternIgnoreCase(sDir,
				S_JAR_FILE_PATTERN);

		for (int i = 0; i < asJARFiles.length; i++) {
			String sThisJARFilePath = new File(sDir, asJARFiles[i]).getAbsolutePath();
			addFileToSystemClassLoaderClasspath(sThisJARFilePath);
		}
	}

	/**
	 * Adds all files in a directory to the Ext classloader's classpath
	 * @param sDir
	 *            The directory path
	 * @param regexpFilter
	 *           a regular expression that is used to filter some files in a directory. 
	 */
	public static void addAllFilesToExtClassLoaderClasspathWithReg(String sDir,
			String regexpFilter) throws IOException {
		String[] asFilteredFiles = getAllFilesMatchingThisPatternIgnoreCase(
				sDir, regexpFilter);

		for (int i = 0; i < asFilteredFiles.length; i++) {
			String filteredFilePath = new File(sDir, asFilteredFiles[i]).getAbsolutePath();
			addFileToExtClassLoaderClasspath(filteredFilePath);
		}
	}

	/**
	 * Adds all files in a directory to the System classloader's classpath
	 * @param sDir
	 *            The directory path
	 * @param regexpFilter
	 *           a regular expression that is used to filter some files in a directory. 
	 */
	public static void addAllFilesToSystemClassLoaderClasspathWithReg(
			String sDir, String regexpFilter) throws IOException {
		String[] asFilteredFiles = getAllFilesMatchingThisPatternIgnoreCase(
				sDir, regexpFilter);

		for (int i = 0; i < asFilteredFiles.length; i++) {
			String filtedFilesPath = new File(sDir, asFilteredFiles[i]).getAbsolutePath();
			addFileToSystemClassLoaderClasspath(filtedFilesPath);
		}
	}

	/**
	 * Returns the set of all files in the given directory
	 * <p>
	 * 
	 * @param sDirectoryPath
	 *            The directory path
	 *            <p>
	 * @return The required list of files
	 */
	private static String[] getAllFiles(String sDirectoryPath) {

		if (sDirectoryPath == null) {
			return new String[0];
		}

		File fileThisDirectory = new File(sDirectoryPath);

		if (fileThisDirectory.isDirectory() == false) {
			return new String[0];
		} else {
			String[] asFileList = fileThisDirectory.list();
			if (asFileList == null || asFileList.length == 0) {
				return asFileList;
			}
			return asFileList;
		}
	}

	/**
	 * Returns the set of all files in the given directory, matching the given
	 * pattern (ignoring case)
	 * <p>
	 * 
	 * @param sDirectoryPath
	 *            The directory path
	 * @param sPattern
	 *            The pattern to match against
	 *            <p>
	 * @return The required list of files
	 */
	private static String[] getAllFilesMatchingThisPatternIgnoreCase(
			String sDirectoryPath, String sPattern) {

		String[] asAllFiles = getAllFiles(sDirectoryPath);

		Vector vsFiltered = new Vector();
		for (int i = 0; i < asAllFiles.length; i++) {
			if (asAllFiles[i].toLowerCase().matches(sPattern.toLowerCase())) {
				vsFiltered.add(asAllFiles[i]);
			}
		}

		String[] asFilteredFiles = new String[vsFiltered.size()];
		for (int i = 0; i < vsFiltered.size(); i++) {
			asFilteredFiles[i] = vsFiltered.elementAt(i).toString();
		}

		return asFilteredFiles;
	}
}
