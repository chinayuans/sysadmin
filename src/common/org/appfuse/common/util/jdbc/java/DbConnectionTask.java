
package org.appfuse.common.util.jdbc.java;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Vector;

public abstract class DbConnectionTask {

    public abstract Connection getConnection();

    private StringBuffer log = new StringBuffer();
    
    private int goodSql = 0;

    private int totalSql = 0;

    /**
     * Database connection
     */
    private Connection conn = null;

    /**
     * files to load
     */
    private Vector filesets = new Vector();

    /**
     * SQL statement
     */
    private Statement statement = null;

    /**
     * SQL input file
     */
    private File srcFile = null;

    /**
     * SQL input command
     */
    private String sqlCommand = "";

    /**
     * SQL transactions to perform
     */
    private Vector transactions = new Vector();

    /**
     * SQL Statement delimiter
     */
    private String delimiter = ";";

    /**
     * delimiters we support, "normal" and "row"
     */
    /**
     * The delimiter type indicating whether the delimiter will
     * only be recognized on a line by itself
     */
    private String delimiterType = "normal";

    /**
     * Print SQL results.
     */
    private boolean print = false;

    /**
     * Print header columns.
     */
    private boolean showheaders = true;

    /**
     * Results Output file.
     */
    private File output = null;


    /**
     * Action to perform if an error is found
     **/
    private String onError = "abort";

    /**
     * Encoding to use when reading SQL statements from a file
     */
    private String encoding = null;

    /**
     * Append to an existing file or overwrite it?
     */
    private boolean append = false;

    /**
     * Keep the format of a sql block?
     */
    private boolean keepformat = false;

    /**
     * Argument to Statement.setEscapeProcessing
     *
     * @since Ant 1.6
     */
    private boolean escapeProcessing = true;

    
    /**
     * RDBMS Product needed for this SQL.
     **/
    private String rdbms = null;

    /**
     * RDBMS Version needed for this SQL.
     **/
    private String version = null;

    
    /**
     * Autocommit flag. Default value is false
     */
    private boolean autocommit = false;
    
    /**
     * Set the name of the SQL file to be run.
     * Required unless statements are enclosed in the build file
     */
    public void setSrc(File srcFile) {
        this.srcFile = srcFile;
    }

    /**
     * Set an inline SQL command to execute.
     * NB: Properties are not expanded in this text.
     */
    public void setSQLText(String sql) {
        this.sqlCommand = sql;
    }

    /**
     * Adds a set of files (nested fileset attribute).
     */
    public void addFileset(File file) {
        filesets.addElement(file);
    }


    /**
     * Add a SQL transaction to execute
     */
    public Transaction createTransaction() {
        Transaction t = new Transaction();
        transactions.addElement(t);
        return t;
    }

    /**
     * Set the file encoding to use on the SQL files read in
     *
     * @param encoding the encoding to use on the files
     */
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    /**
     * Set the delimiter that separates SQL statements. Defaults to &quot;;&quot;;
     * optional
     *
     * <p>For example, set this to "go" and delimitertype to "ROW" for
     * Sybase ASE or MS SQL Server.</p>
     */
    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    /**
     * Set the delimiter type: "normal" or "row" (default "normal").
     *
     * <p>The delimiter type takes two values - normal and row. Normal
     * means that any occurrence of the delimiter terminate the SQL
     * command whereas with row, only a line containing just the
     * delimiter is recognized as the end of the command.</p>
     */
    public void setDelimiterType(String delimiterType) {
        this.delimiterType = delimiterType;
    }

    /**
     * Print result sets from the statements;
     * optional, default false
     */
    public void setPrint(boolean print) {
        this.print = print;
    }

    /**
     * Print headers for result sets from the
     * statements; optional, default true.
     */
    public void setShowheaders(boolean showheaders) {
        this.showheaders = showheaders;
    }

    /**
     * Set the output file;
     * optional, defaults to the Ant log.
     */
    public void setOutput(File output) {
        this.output = output;
    }

    /**
     * whether output should be appended to or overwrite
     * an existing file.  Defaults to false.
     *
     * @since Ant 1.5
     */
    public void setAppend(boolean append) {
        this.append = append;
    }


    /**
     * Action to perform when statement fails: continue, stop, or abort
     * optional; default &quot;abort&quot;
     */
    public void setOnerror(String onError) {
        this.onError = onError;
    }

    /**
     * whether or not format should be preserved.
     * Defaults to false.
     *
     * @param keepformat The keepformat to set
     */
    public void setKeepformat(boolean keepformat) {
        this.keepformat = keepformat;
    }

    /**
     * Set escape processing for statements.
     *
     * @since Ant 1.6
     */
    public void setEscapeProcessing(boolean enable) {
        escapeProcessing = enable;
    }
    
    /**
     * Execute task only if the lower case product name
     * of the DB matches this
     * @param rdbms The rdbms to set
     */
    public void setRdbms(String rdbms) {
        this.rdbms = rdbms;
    }

    /**
     * Sets the version string, execute task only if
     * rdbms version match; optional.
     * @param version The version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    
    /**
     * Gets the rdbms.
     * @return Returns a String
     */
    public String getRdbms() {
        return rdbms;
    }

    /**
     * Gets the version.
     * @return Returns a String
     */
    public String getVersion() {
        return version;
    }
   
    /**
     * Gets the autocommit.
     * @return Returns a boolean
     */
    public boolean isAutocommit() {
        return autocommit;
    }

    /**
     * Auto commit flag for database connection;
     * optional, default false.
     * @param autocommit The autocommit to set
     */
    public void setAutocommit(boolean autocommit) {
        this.autocommit = autocommit;
    }
    
    public void setLog(String message){
        log.append(message+"\r\n");
    }

    public StringBuffer getLog() {
        return log;
    }
    
    /**
     * Load the sql file and then execute it
     */
    public void execute() throws DbConnectionException {
        Vector savedTransaction = (Vector) transactions.clone();
        String savedSqlCommand = sqlCommand;

        sqlCommand = sqlCommand.trim();

        try {
            if (srcFile == null && sqlCommand.length() == 0
                && filesets.isEmpty()) {
                if (transactions.size() == 0) {
                    throw new DbConnectionException("Source file or fileset, "
                                             + "transactions or sql statement "
                                             + "must be set!");
                }
            }

            if (srcFile != null && !srcFile.exists()) {
                throw new DbConnectionException("Source file does not exist!");
            }

            
            String[] srcFiles =new String[filesets.size()];
            // deal with the filesets
            for (int i = 0; i < filesets.size(); i++) {
                File fs = (File) filesets.elementAt(i);
                String srcDir = fs.getAbsolutePath();
                srcFiles[i]=srcDir;
            }
            
            // Make a transaction for each file
            for (int j = 0; j < srcFiles.length; j++) {
                Transaction t = createTransaction();
                t.setSrc(new File(srcFiles[j]));
            }
            

            // Make a transaction group for the outer command
            Transaction t = createTransaction();
            t.setSrc(srcFile);
            t.addText(sqlCommand);
            conn = getConnection();
            if (!isValidRdbms(conn)) {
                return;
            }
            try {
            	conn.setAutoCommit(isAutocommit());
                statement = conn.createStatement();
                statement.setEscapeProcessing(escapeProcessing);

                PrintStream out = System.out;
                try {
                    if (output != null) {
                        setLog("Opening PrintStream to output file " + output);
                        out = new PrintStream(
                                  new BufferedOutputStream(
                                      new FileOutputStream(output
                                                           .getAbsolutePath(),
                                                           append)));
                    }

                    // Process all transactions
                    for (Enumeration e = transactions.elements();
                         e.hasMoreElements();) {

                        ((Transaction) e.nextElement()).runTransaction(out);
                        if (!isAutocommit()) {
                            setLog("Committing transaction");
                            conn.commit();
                        }
                    }
                } finally {
                    if (out != null && out != System.out) {
                        out.close();
                    }
                }
            } catch (IOException e) {
                if (!isAutocommit() && conn != null && onError.equals("abort")) {
                    try {
                        conn.rollback();
                    } catch (SQLException ex) {
                        // ignore
                    }
                }
                throw new DbConnectionException(e);
            } catch (SQLException e) {
                if (!isAutocommit() && conn != null && onError.equals("abort")) {
                    try {
                        conn.rollback();
                    } catch (SQLException ex) {
                        // ignore
                    }
                }
                throw new DbConnectionException(e);
            } finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException ex) {
                    // ignore
                }
            }

            setLog(goodSql + " of " + totalSql+ " SQL statements executed successfully");
        } finally {
            transactions = savedTransaction;
            sqlCommand = savedSqlCommand;
        }
    }
    
    /**
     * Verify we are connected to the correct RDBMS
     */
    protected boolean isValidRdbms(Connection conn) {
        if (rdbms == null && version == null) {
            return true;
        }

        try {
            DatabaseMetaData dmd = conn.getMetaData();

            if (rdbms != null) {
                String theVendor = dmd.getDatabaseProductName().toLowerCase();

                setLog("RDBMS = " + theVendor);
                if (theVendor == null || theVendor.indexOf(rdbms) < 0) {
                    setLog("Not the required RDBMS: " + rdbms);
                    return false;
                }
            }

            if (version != null) {
                // XXX maybe better toLowerCase(Locale.US)
                String theVersion = dmd.getDatabaseProductVersion().toLowerCase();

                setLog("Version = " + theVersion);
                if (theVersion == null
                        || !(theVersion.startsWith(version)
                        || theVersion.indexOf(" " + version) >= 0)) {
                    setLog("Not the required version: \"" + version + "\"");
                    return false;
                }
            }
        } catch (SQLException e) {
            // Could not get the required information
            setLog("Failed to obtain required RDBMS information");
            return false;
        }

        return true;
    }

    /**
     * read in lines and execute them
     */
    protected void runStatements(Reader reader, PrintStream out)
        throws SQLException, IOException {
        StringBuffer sql = new StringBuffer();
        String line = "";

        BufferedReader in = new BufferedReader(reader);

        while ((line = in.readLine()) != null) {
            if (!keepformat) {
                line = line.trim();
            }
//            line = getProject().replaceProperties(line);
            if (!keepformat) {
                if (line.startsWith("//")) {
                    continue;
                }
                if (line.startsWith("--")) {
                    continue;
                }
                StringTokenizer st = new StringTokenizer(line);
                if (st.hasMoreTokens()) {
                    String token = st.nextToken();
                    if ("REM".equalsIgnoreCase(token)) {
                        continue;
                    }
                }
            }

            if (!keepformat) {
                sql.append(" " + line);
            } else {
                sql.append("\n" + line);
            }

            // SQL defines "--" as a comment to EOL
            // and in Oracle it may contain a hint
            // so we cannot just remove it, instead we must end it
            if (!keepformat) {
                if (line.indexOf("--") >= 0) {
                    sql.append("\n");
                }
            }
            if ((delimiterType.equals("normal")
                 && sql.toString().endsWith(delimiter))
                ||
                (delimiterType.equals("row")
                 && line.equals(delimiter))) {
                execSQL(sql.substring(0, sql.length() - delimiter.length()),
                        out);
                sql.replace(0, sql.length(), "");
            }
        }
        // Catch any statements not followed by ;
        if (!sql.equals("")) {
            execSQL(sql.toString(), out);
        }
    }


    /**
     * Exec the sql statement.
     */
    protected void execSQL(String sql, PrintStream out) throws SQLException {
        // Check and ignore empty statements
        if ("".equals(sql.trim())) {
            return;
        }

        ResultSet resultSet = null;
        try {
            totalSql++;
            setLog("SQL: " + sql);

            boolean ret;
            int updateCount = 0, updateCountTotal = 0;

            ret = statement.execute(sql);
            updateCount = statement.getUpdateCount();
            resultSet = statement.getResultSet();
            do {
                if (!ret) {
                    if (updateCount != -1) {
                        updateCountTotal += updateCount;
                    }
                } else {
                    if (print) {
                        printResults(resultSet, out);
                    }
                }
                ret = statement.getMoreResults();
                if (ret) {
                    updateCount = statement.getUpdateCount();
                    resultSet = statement.getResultSet();
                }
            } while (ret);

            setLog(updateCountTotal + " rows affected");

            if (print) {
                StringBuffer line = new StringBuffer();
                line.append(updateCountTotal + " rows affected");
                setLog(line.toString()+"\n");
            }

            SQLWarning warning = conn.getWarnings();
            while (warning != null) {
                setLog(warning + " sql warning");
                warning = warning.getNextWarning();
            }
            conn.clearWarnings();
            goodSql++;
        } catch (SQLException e) {
            setLog("Failed to execute: " + sql);
            if (!onError.equals("continue")) {
                throw e;
            }
            setLog(e.toString());
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }

    /**
     * print any results in the statement
     * @deprecated use {@link #printResults(java.sql.ResultSet, java.io.PrintStream)
     *             the two arg version} instead.
     * @param out the place to print results
     * @throws SQLException on SQL problems.
     */
    protected void printResults(PrintStream out) throws SQLException {
        ResultSet rs = null;
        rs = statement.getResultSet();
        try {
            printResults(rs, out);
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }
    
    /**
     * print any results in the result set.
     * @param rs the resultset to print information about
     * @param out the place to print results
     * @throws SQLException on SQL problems.
     * @since Ant 1.6.3
     */
    protected void printResults(ResultSet rs, PrintStream out) throws SQLException {
        if (rs != null) {
            setLog("Processing new result set.");
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            StringBuffer line = new StringBuffer();
            if (showheaders) {
                for (int col = 1; col < columnCount; col++) {
                     line.append(md.getColumnName(col));
                     line.append(",");
                }
                line.append(md.getColumnName(columnCount));
                setLog(line.toString()+"\n");
                line = new StringBuffer();
            }
            while (rs.next()) {
                boolean first = true;
                for (int col = 1; col <= columnCount; col++) {
                    String columnValue = rs.getString(col);
                    if (columnValue != null) {
                        columnValue = columnValue.trim();
                    }

                    if (first) {
                        first = false;
                    } else {
                        line.append(",");
                    }
                    line.append(columnValue);
                }
                setLog(line.toString()+"\n");
                line = new StringBuffer();
            }
        }
        setLog("\n");
    }


    /**
     * Contains the definition of a new transaction element.
     * Transactions allow several files or blocks of statements
     * to be executed using the same JDBC connection and commit
     * operation in between.
     */
    public class Transaction {
        private File tSrcFile = null;
        private String tSqlCommand = "";

        /**
         *
         */
        public void setSrc(File src) {
            this.tSrcFile = src;
        }

        /**
         *
         */
        public void addText(String sql) {
            this.tSqlCommand += sql;
        }

        /**
         *
         */
        private void runTransaction(PrintStream out)
            throws IOException, SQLException {
            if (tSqlCommand.length() != 0) {
                setLog("Executing commands");
                runStatements(new StringReader(tSqlCommand), out);
            }

            if (tSrcFile != null) {
                setLog("Executing file: " + tSrcFile.getAbsolutePath());
                Reader reader =
                    (encoding == null) ? new FileReader(tSrcFile)
                                       : new InputStreamReader(
                                             new FileInputStream(tSrcFile),
                                             encoding);
                try {
                    runStatements(reader, out);
                } finally {
                    reader.close();
                }
            }
        }
    }
}
