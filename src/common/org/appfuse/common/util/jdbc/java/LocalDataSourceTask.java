package org.appfuse.common.util.jdbc.java;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class LocalDataSourceTask extends DbConnectionTask {

    private String jndiName = null;
    
    public String getJndiName() {
        return jndiName;
    }

    public void setJndiName(String jndiName) {
        this.jndiName = jndiName;
    }

    /**
     * In this way, below code should be run in application server.If do this,
     * you just may look up a datasource from server's inside and get a connection.
     */
    public Connection getConnection() {
        Connection conn;
        try {
            InitialContext ic = new InitialContext();
            if (getJndiName()==null) {
                throw new DbConnectionException("Jndi name with null is not allowed.");
            }
            DataSource ds = (DataSource) ic.lookup(getJndiName());
            conn = ds.getConnection();
        } catch (NamingException e) {
           throw new DbConnectionException(e);
        } catch (SQLException e) {
           throw new DbConnectionException(e);
        }
        return conn;
    }
}
