package framework.dataaccess;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;

import shopping.util.ConfigurationHelper;

public class ConnectionPool implements DataSourceInterface {

    private GenericObjectPool gPool = null;
    
    private static Map<JDBCConnectionInfo, DataSource> connMap = new HashMap<JDBCConnectionInfo, DataSource>();
    
    private JDBCConnectionInfo connectionInfo;
    private PoolConfigure configure;
    private DataSource dataSource;
    
    public ConnectionPool() throws Exception {
        JDBCConnectionInfo connectionInfo = new JDBCConnectionInfo(ConfigurationHelper.getConfiguration("DriverName"), 
                ConfigurationHelper.getConfiguration("CONNECTION_STRING"), "", "");
        this.connectionInfo = connectionInfo;
        if(!connMap.containsKey(connectionInfo))
            setUpPool();
    }
        
    public ConnectionPool(JDBCConnectionInfo connectionInfo) throws Exception {
        this.connectionInfo = connectionInfo;
        if(!connMap.containsKey(connectionInfo))
            setUpPool();
    }
    
    public ConnectionPool(JDBCConnectionInfo connectionInfo, PoolConfigure configure) 
            throws Exception {
        this.connectionInfo = connectionInfo;
        this.configure = configure;
        if(!connMap.containsKey(connectionInfo))
            setUpPool();
    }

    public PoolConfigure getConfigure() {
        return configure;
    }

    public void setConfigure(PoolConfigure configure) {
        this.configure = configure;
    }

    @SuppressWarnings("unused")
    public DataSource setUpPool() throws Exception {
        if(connectionInfo == null)
            throw new Exception("The connection info hasn't been set");

        Class.forName(connectionInfo.getJDBC_DRIVER());

        // Creates an Instance of GenericObjectPool
        gPool = new GenericObjectPool();
        if (configure != null) {
            PoolBuilder pb = new PoolBuilder(this, configure);
            gPool = pb.build().getConnectionPool();
        }

        // Creates a ConnectionFactory Object Which Will Be Use by the Pool to Create the Connection Object!
        ConnectionFactory cf = new DriverManagerConnectionFactory(connectionInfo.getJDBC_DB_URL(), 
                               connectionInfo.getJDBC_USER(), connectionInfo.getJDBC_PASS());

        // Creates a PoolableConnectionFactory That Will Wraps the Connection Object Created by the ConnectionFactory to Add Object Pooling Functionality!
        PoolableConnectionFactory pcf = new PoolableConnectionFactory(cf, gPool, null, null, false, true);
        dataSource = new PoolingDataSource(gPool);
        connMap.put(connectionInfo, dataSource);
        return dataSource;
    }

    public GenericObjectPool getConnectionPool() {
        return gPool;
    }

    // This Method Is Used To Print The Connection Pool Status
    private void printDbStatus() {
        System.out.println("Max.: " + getConnectionPool().getMaxActive() + "; Active: " + getConnectionPool().getNumActive() + "; Idle: " + getConnectionPool().getNumIdle());
    }
    
    public JDBCConnectionInfo getConnectionInfo() {
        return connectionInfo;
    }

    public void setConnectionInfo(JDBCConnectionInfo connectionInfo) {
        this.connectionInfo = connectionInfo;
    }
    
    public ConnectionPool setMaxActive(int maxActive) {
        this.getConnectionPool().setMaxActive(maxActive);
        return this;
    } 
    
    public ConnectionPool setMinSize(int minSize) {
        this.getConnectionPool().setMaxIdle(minSize);
        return this;
    }
    
    public Connection getConnection() throws SQLException {
        return connMap.get(connectionInfo).getConnection();
    }
    
    public static void main(String[] args) throws Exception {
        getMetaData();
        ResultSet rsObj = null;
        Connection connObj = null;
        PreparedStatement pstmtObj = null;
        ConnectionPool pool = new ConnectionPool();
        try {   

            // Performing Database Operation!
            System.out.println("\n=====Making A New Connection Object For Db Transaction=====\n");
            connObj = pool.getConnection();
            pool.printDbStatus(); 

            pstmtObj = connObj.prepareStatement("SELECT * FROM Customer");
            rsObj = pstmtObj.executeQuery();
            while (rsObj.next()) {
                System.out.println("Username: " + rsObj.getString("username"));
            }
            System.out.println("\n=====Releasing Connection Object To Pool=====\n");            
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                // Closing ResultSet Object
                if(rsObj != null) {
                    rsObj.close();
                }
                // Closing PreparedStatement Object
                if(pstmtObj != null) {
                    pstmtObj.close();
                }
                // Closing Connection Object
                if(connObj != null) {
                    connObj.close();
                }
            } catch(Exception sqlException) {
                sqlException.printStackTrace();
            }
        }
        pool.printDbStatus();
    }
    
    private static void getMetaData() {
        ResultSet rsObj = null;
        Connection connObj = null;
        PreparedStatement pstmtObj = null;
        ConnectionPool pool = null;
        DatabaseMetaData databaseMetaData = null;
        try {   
            pool = new ConnectionPool();
            connObj = pool.getConnection();
            databaseMetaData = connObj.getMetaData();
            //Print TABLE_TYPE "TABLE"
            ResultSet resultSet = databaseMetaData.getTables(null, null, "order", new String[]{"TABLE"});
            System.out.println("Printing TABLE_TYPE \"TABLE\" ");
            System.out.println("----------------------------------");
            while(resultSet.next()){
              System.out.println(resultSet.getString("TABLE_NAME"));
            }
            
            //Get primary key of a table
            resultSet = databaseMetaData.getPrimaryKeys(null, null, "order");
            while(resultSet.next()){
                //System.out.println("The primary key is: ");
                System.out.println(resultSet.getString("COLUMN_NAME"));
                
            }
            
            ResultSet columns = databaseMetaData.getColumns(null,null, "order", null);
            while(columns.next()){
                String columnName = columns.getString("COLUMN_NAME");
                String datatype = columns.getString("DATA_TYPE");
                String columnsize = columns.getString("COLUMN_SIZE");
                String decimaldigits = columns.getString("DECIMAL_DIGITS");
                String isNullable = columns.getString("IS_NULLABLE");
                String is_autoIncrment = columns.getString("IS_AUTOINCREMENT");
                //Printing results
                System.out.println(columnName + "---" + datatype + "---" + columnsize + "---" + decimaldigits + "---" + isNullable + "---" + is_autoIncrment);
            }
            // Performing Database Operation!
            System.out.println("\n=====Releasing Connection Object To Pool=====\n");            
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                // Closing ResultSet Object
                if(rsObj != null) {
                    rsObj.close();
                }
                // Closing PreparedStatement Object
                if(pstmtObj != null) {
                    pstmtObj.close();
                }
                // Closing Connection Object
                if(connObj != null) {
                    connObj.close();
                }
            } catch(Exception sqlException) {
                sqlException.printStackTrace();
            }
        }
    }
}
