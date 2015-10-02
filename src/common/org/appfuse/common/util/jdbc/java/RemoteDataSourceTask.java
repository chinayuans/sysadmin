package org.appfuse.common.util.jdbc.java;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RemoteDataSourceTask extends DbConnectionTask {

    private String jndiName = null;
    
    private String PROVIDER_URL = null;
    
    private String FACTORY_INITIAL = null;
    
    public String getJndiName() {
        return jndiName;
    }

    public void setJndiName(String jndiName) {
        this.jndiName = jndiName;
    }

    public String getFactory_Initial() {
        return FACTORY_INITIAL;
    }

    /**
     * weblogic : weblogic.jndi.WLInitialContextFactory
     * websphere: com.ibm.websphere.naming.WsnInitialContextFactory
     * @param factory_initial
     */
    public void setFactory_Initial(String factory_initial) {
        FACTORY_INITIAL = factory_initial;
    }

    public String getProvider_Url() {
        return PROVIDER_URL;
    }

    /**
     * weblogic :  t3://9.125.63.216:7001
     * websphere:  iiop://9.125.63.216:2809
     * @param provider_url
     */
    public void setProvider_Url(String provider_url) {
        PROVIDER_URL = provider_url;
    }

    /**
     * In this way , it will create a new connection from datasource and it is required to have jdbc drivers
     * in client jvm. It could not get a original connection from datasource connection pool.
     * 
     * 1. This approach will have jdbc drivers in client jvm. Namely, classpath requires jdbc drivers.
     * 2. This approach will utilise local jdbc drivers to create a db connection from datasource.
     *    It could not get a connection from datasource connection pool.
     * 3. due to this reason, if datasource is created with derby-embedded, then you can never get a connection
     *    from datasource. Because derby-embedded only supports a db connection.
     */ 
    public Connection getConnection() {
        Connection conn;
        try {
            Hashtable env = new Hashtable();

            env.put(Context.PROVIDER_URL, PROVIDER_URL);
            env.put(Context.INITIAL_CONTEXT_FACTORY,FACTORY_INITIAL);
            InitialContext initCtx = new InitialContext(env);

            DataSource ds = (DataSource) initCtx.lookup(getJndiName());            
            if (getJndiName()==null) {
                throw new DbConnectionException("jndi is null");
            }
            
            conn = ds.getConnection();
        } catch (NamingException e) {
           throw new DbConnectionException(e);
        } catch (SQLException e) {
           throw new DbConnectionException(e);
        }
        return conn;
    }
}
