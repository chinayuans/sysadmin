
package org.appfuse.common.util.jdbc.ant.version2;

import java.io.File;
import java.net.URL;
import java.util.Properties;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.SQLExec;
import org.apache.tools.ant.taskdefs.SQLExec.DelimiterType;
import org.apache.tools.ant.taskdefs.SQLExec.OnError;

public class SQLExecUtil {

	// keys used in properties.
	public final static String DRIVER = "driver";

	public final static String USER = "user";

	public final static String PASSWORD = "password";

	public final static String URL = "url";

	public final static String CLASSPATH = "classpath";

	// set field of connection information.
	private String driver = "driver";

	private String user = "user";

	private String password = "password";

	private String url = "url";

    /**
     * jdbc driver classpath
     */
	private String classpath = "classpath";

    /**
     * sql statement
     */
	private String sqlText = "";
    
    /**
     * file which contains many sql statements
     */
    private String src = "src";

    private String delimiter=";";

    /**
     * value: row,normal
     */
    private String delimiterType=DelimiterType.NORMAL;

    private boolean autocommit = false;

    private String encoding = null;

    private boolean keepformat = false;

    private boolean showheaders = true;

    private String output = null;

    private boolean apppend = false;

    /**
     * value: continue, stop, abort
     */
    private String onerror = "abort";

    private String rdbms = null;

    private String version  = null;

    private boolean escapeprocessing = true;
    
    private boolean caching = true;
    
    private boolean throwException = true;
    
    private boolean print = false;
	
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

	public String getClasspath() {
		return classpath;
	}

	public void setClasspath(String classpath) {
		this.classpath = classpath;
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
	
	public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
    
    public boolean isApppend() {
        return apppend;
    }

    public void setApppend(boolean apppend) {
        this.apppend = apppend;
    }

    public boolean isAutocommit() {
        return autocommit;
    }

    public void setAutocommit(boolean autocommit) {
        this.autocommit = autocommit;
    }

    public String getDelimiter() {
        return delimiter;
    }

    /**
     * 
     * @param delimiter  by default, value is ;
     */
    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getDelimiterType() {
        return delimiterType;
    }

    /**
     * @param delimiterType  value: row,normal
     */
    public void setDelimiterType(String delimiterType) {
        this.delimiterType = delimiterType;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public boolean isEscapeprocessing() {
        return escapeprocessing;
    }

    public void setEscapeprocessing(boolean escapeprocessing) {
        this.escapeprocessing = escapeprocessing;
    }

    public boolean isKeepformat() {
        return keepformat;
    }

    public void setKeepformat(boolean keepformat) {
        this.keepformat = keepformat;
    }

    public String getOnerror() {
        return onerror;
    }

    /**
     *       
     * @param onerror  value: continue, stop, abort
     */
    public void setOnerror(String onerror) {
        this.onerror = onerror;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getRdbms() {
        return rdbms;
    }

    public void setRdbms(String rdbms) {
        this.rdbms = rdbms;
    }

    public boolean isShowheaders() {
        return showheaders;
    }

    public void setShowheaders(boolean showheaders) {
        this.showheaders = showheaders;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isCaching() {
        return caching;
    }

    public void setCaching(boolean caching) {
        this.caching = caching;
    }

    public boolean isPrint() {
        return print;
    }

    public void setPrint(boolean print) {
        this.print = print;
    }

    public boolean isThrowException() {
        return throwException;
    }

    public void setThrowException(boolean throwException) {
        this.throwException = throwException;
    }

    /**
	 * run a sql tasks multiple times.	
	 */
	public void executeSQL() {
		Properties props = 
			getProperties(getDriver(), getUser(), getPassword(),getUrl());

		SQLExec sqlExec= new SQLExec();
		sqlExec.setProject(new Project());
		sqlExec.setDriver(props.getProperty(DRIVER));
		sqlExec.setUserid(props.getProperty(USER));
		sqlExec.setPassword(props.getProperty(PASSWORD));
		sqlExec.setUrl(props.getProperty(URL));
		sqlExec.createClasspath().setLocation(new File(props.getProperty(CLASSPATH)));
		sqlExec.addText(getSqlText());
		sqlExec.setCaching(isCaching());
		sqlExec.setPrint(isPrint());
                
        sqlExec.setSrc(new File(getSrc()));
        sqlExec.setDelimiter(getDelimiter());
        
        sqlExec.setAutocommit(isAutocommit());
        sqlExec.setEncoding(getEncoding());
        sqlExec.setKeepformat(isKeepformat());
        sqlExec.setShowheaders(isShowheaders());
       
        File outputFile= getOutput()!=null ? new File(getOutput()): null;
        sqlExec.setOutput(outputFile);
        sqlExec.setAppend(isApppend());
        sqlExec.setRdbms(getRdbms());
        sqlExec.setVersion(getVersion());
        sqlExec.setEscapeProcessing(isEscapeprocessing());
        
        DelimiterType delimiterType=new SQLExec.DelimiterType();
        delimiterType.setValue(getDelimiterType());
        sqlExec.setDelimiterType(delimiterType);
        
        OnError onError=new SQLExec.OnError();
        onError.setValue(getOnerror());
        sqlExec.setOnerror(onError);
        
		try {
			sqlExec.execute();
		} catch (BuildException e) {
			if (isThrowException()) {
				throw e;
			}
		}
	}

	/** helper method to build properties */
	private Properties getProperties(String driver, String user, String pwd,String url) {
		Properties props = new Properties();
		props.put(DRIVER, driver);
		props.put(USER, user);
		props.put(PASSWORD, pwd);
		props.put(URL, url);

        /**
         * first search the driver class from current classloader,
         * if cannot find, then search it with ant classloader which specifies a classpath(getClasspath()).
         */
		String driverClasspath = findResourcePath(props.getProperty(DRIVER));
		if (driverClasspath==null){
			driverClasspath=getClasspath();
		}
		
		setClasspath(driverClasspath);
		props.put(CLASSPATH, driverClasspath);
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
