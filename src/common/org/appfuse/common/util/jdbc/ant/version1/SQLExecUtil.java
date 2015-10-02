package org.appfuse.common.util.jdbc.ant.version1;

import java.io.File;
import java.net.URL;
import java.util.Properties;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.SQLExec;

public class SQLExecUtil {

	// keys used in properties.
	public final static String DRIVER = "driver";

	public final static String USER = "user";

	public final static String PASSWORD = "password";

	public final static String URL = "url";

	public final static String PATH = "path";

	public final static String SQL = "sql";

	// set field of connection information.
	private String driver = "driver";

	private String user = "user";

	private String password = "password";

	private String url = "url";

	private String path = "path";

	private String sqlText = "sql";
	
	public SQLExecUtil() {
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getSqlText() {
		return sqlText;
	}

	public void setSqlText(String sqlText) {
		this.sqlText = sqlText;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	/**
	 * run a sql tasks multiple times.
	 * 
	 * @param caching
	 *            should caching be enabled ?
	 * @param catchexception
	 *            true to catch exception for each call, false if not.
	 * @param print
	 *            true to print console, false if not.            
	 */
	public void executeSQL(boolean caching, boolean catchexception,boolean print) {
		Properties props = 
			getProperties(getDriver(), getUser(), getPassword(),getUrl(), getSqlText());

		SQLExec sqlExec= new SQLExec();
		sqlExec.setProject(new Project());
		sqlExec.setDriver(props.getProperty(DRIVER));
		sqlExec.setUserid(props.getProperty(USER));
		sqlExec.setPassword(props.getProperty(PASSWORD));
		sqlExec.setUrl(props.getProperty(URL));
		sqlExec.createClasspath().setLocation(new File(props.getProperty(PATH)));
		sqlExec.addText(props.getProperty(SQL));
		sqlExec.setCaching(caching);
		sqlExec.setPrint(print);
		try {
			sqlExec.execute();
		} catch (BuildException e) {
			if (!catchexception) {
				throw e;
			}
		}
	}

	/** helper method to build properties */
	private Properties getProperties(String driver, String user, String pwd,String url, String sql) {
		Properties props = new Properties();
		props.put(DRIVER, driver);
		props.put(USER, user);
		props.put(PASSWORD, pwd);
		props.put(URL, url);

		String path = findResourcePath(props.getProperty(DRIVER));
		if (path==null){
			path=getPath();
		}
		
		setPath(path);
		props.put(PATH, path);
		props.put(SQL, sql);
		return props;
	}

	/**
	 * try to find the path from a resource (jar file or directory name) so that
	 * it can be used as a classpath to load the resource.
	 */
	private String findResourcePath(String resource) {
		resource = resource.replace('.', '/') + ".class";
		URL url = getClass().getClassLoader().getResource(resource);
		if (url == null) {
			return null;
		}
		String u = url.toString();
		if (u.startsWith("jar:file:")) {
			int pling = u.indexOf("!");
			return u.substring("jar:file:".length(), pling);
		} else if (u.startsWith("file:")) {
			int tail = u.indexOf(resource);
			return u.substring("file:".length(), tail);
		}
		return null;
	}
}
