package framework.dataaccess;

public class JDBCConnectionInfo {
    // JDBC Driver Name & Database URL
    private String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    private String JDBC_DB_URL = "jdbc:mysql://localhost:3306/tutorialDb";

    // JDBC Database Credentials
    private String JDBC_USER = "root";
    private String JDBC_PASS = "admin@123";
    
    public String getJDBC_DRIVER() {
        return JDBC_DRIVER;
    }
    public void setJDBC_DRIVER(String jDBC_DRIVER) {
        JDBC_DRIVER = jDBC_DRIVER;
    }
    public String getJDBC_DB_URL() {
        return JDBC_DB_URL;
    }
    public void setJDBC_DB_URL(String jDBC_DB_URL) {
        JDBC_DB_URL = jDBC_DB_URL;
    }
    public String getJDBC_USER() {
        return JDBC_USER;
    }
    public void setJDBC_USER(String jDBC_USER) {
        JDBC_USER = jDBC_USER;
    }
    public String getJDBC_PASS() {
        return JDBC_PASS;
    }
    public void setJDBC_PASS(String jDBC_PASS) {
        JDBC_PASS = jDBC_PASS;
    }
    public JDBCConnectionInfo(String jDBC_DRIVER, String jDBC_DB_URL, String jDBC_USER, String jDBC_PASS) {
        JDBC_DRIVER = jDBC_DRIVER;
        JDBC_DB_URL = jDBC_DB_URL;
        JDBC_USER = jDBC_USER;
        JDBC_PASS = jDBC_PASS;
    }

}
