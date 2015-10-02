
package org.appfuse.common.util.jdbc.java;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Properties;

public class JDBCTask extends DbConnectionTask {

    /**
     * Used for caching loaders / driver. This is to avoid getting an
     * OutOfMemoryError when calling this task multiple times in a row.
     */
    private static Hashtable loaderMap = new Hashtable(3);

    private boolean caching = true;

    private String[] classpath;

    private URLClassLoader loader;

    /**
     * DB driver.
     */
    private String driver = null;

    /**
     * DB url.
     */
    private String url = null;

    /**
     * User name.
     */
    private String userId = null;

    /**
     * Password
     */
    private String password = null;

    /**
     * Gets the classpath.
     * 
     * @return Returns a Path
     */
    public String[] getClasspath() {
        return classpath;
    }
    
    /**
     * Sets the classpath for loading the driver.
     * 
     * @param classpath
     *            The classpath to set
     */
    public void setClasspath(String[] classpath) {
        this.classpath = classpath;
    }

    public void isCaching(boolean value) {
        caching = value;
    }
    
    /**
     * Caching loaders / driver. This is to avoid getting an OutOfMemoryError
     * when calling this task multiple times in a row; default: true
     * 
     * @param enable
     */
    public void setCaching(boolean enable) {
        caching = enable;
    }

    /**
     * Gets the url.
     * 
     * @return Returns a String
     */
    public String getUrl() {
        return url;
    }
    
    /**
     * Sets the database connection URL; required.
     * 
     * @param url
     *            The url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets the password.
     * 
     * @return Returns a String
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Sets the password; required.
     * 
     * @param password
     *            The password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the userId.
     * 
     * @return Returns a String
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Set the user name for the connection; required.
     * 
     * @param userId
     *            The userId to set
     */
    public void setUserid(String userId) {
        this.userId = userId;
    }

    
    public URLClassLoader getLoader(String[] classpath) {
        URL[] urls = new URL[classpath.length];
        for (int i = 0; i < classpath.length; i++) {
            try {
                urls[i] = new URL("file:" + classpath[i]);
            } catch (MalformedURLException e) {
                throw new DbConnectionException(e);
            }
        }
        URLClassLoader loader = new URLClassLoader(urls);
        return loader;
    }

    /**
     * Creates a new Connection as using the driver, url, userid and password
     * specified.
     * 
     * The calling method is responsible for closing the connection.
     * 
     * @return Connection the newly created connection.
     * @throws DbConnectionException
     *             if the UserId/Password/Url is not set or there is no suitable
     *             driver or the driver fails to load.
     */
    public Connection getConnection() throws DbConnectionException {
        if (userId == null) {
            throw new DbConnectionException("User Id attribute must be set!");
        }
        if (password == null) {
            throw new DbConnectionException("Password attribute must be set!");
        }
        if (url == null) {
            throw new DbConnectionException("Url attribute must be set!");
        }
        try {

            setLog("connecting to " + getUrl());
            Properties info = new Properties();
            info.put("user", getUserId());
            info.put("password", getPassword());
            Connection conn = getDriver().connect(getUrl(), info);

            if (conn == null) {
                // Driver doesn't understand the URL
                throw new SQLException("No suitable Driver for " + url);
            }

            return conn;
        } catch (SQLException e) {
            throw new DbConnectionException(e);
        }

    }

    /**
     * Class name of the JDBC driver; required.
     * 
     * @param driver
     *            The driver to set
     */
    public void setDriver(String driver) {
        this.driver = driver;
    }
    
    /**
     * Gets an instance of the required driver. Uses the ant class loader and
     * the optionally the provided classpath.
     * 
     * @return Driver
     * @throws DbConnectionException
     */
    public Driver getDriver() throws DbConnectionException {
        if (driver == null) {
            throw new DbConnectionException("Driver attribute must be set!");
        }

        Driver driverInstance = null;
        try {
            Class dc;
            if (classpath != null) {
                synchronized (loaderMap) {
                    if (caching) {
                        loader = (URLClassLoader) loaderMap.get(driver);
                    }
                    if (loader == null) {
                        setLog("Loading " + driver
                                + " using URLClassLoader with classpath ");
                        loader = getLoader(classpath);
                        if (caching) {
                            loaderMap.put(driver, loader);
                        }
                    } else {
                        setLog("Loading " + driver
                                + " using a cached AntClassLoader.");
                    }
                }
                dc = loader.loadClass(driver);
            } else {
                setLog("Loading " + driver + " using system loader.");
                dc = Class.forName(driver);
            }
            driverInstance = (Driver) dc.newInstance();
        } catch (ClassNotFoundException e) {
            throw new DbConnectionException("Class Not Found: JDBC driver " + driver
                    + " could not be loaded");
        } catch (IllegalAccessException e) {
            throw new DbConnectionException("Illegal Access: JDBC driver " + driver
                    + " could not be loaded");
        } catch (InstantiationException e) {
            throw new DbConnectionException("Instantiation Exception: JDBC driver "
                    + driver + " could not be loaded");
        }
        return driverInstance;
    }

}
