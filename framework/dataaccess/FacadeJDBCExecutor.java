package framework.dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class FacadeJDBCExecutor {
    
    private Connection conn;
    private Statement stat;
    private PreparedStatement pstmt;
    
    public FacadeJDBCExecutor() {
        try {
            ConnectionPool pool = new ConnectionPool();
            conn = pool.getConnection();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public boolean executeSQL(String sql) {
        boolean executeSuccess = true;
        try {
            stat = conn.createStatement();
            executeSuccess = stat.execute(sql);
        } catch(SQLException ex) {
            executeSuccess = false;
        } finally {
            try {
                stat.close();
            } catch(SQLException ex) {
                ex.printStackTrace();
            }
        }
        return executeSuccess;
    }
    
    //int executeUpdate(String sql) throws SQLException;
    public int executeUpdate(String sql) {
        int affectedRecords = -1;
        try {
            stat = conn.createStatement();
            affectedRecords = stat.executeUpdate(sql);
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                stat.close();
            } catch(SQLException ex) {
                ex.printStackTrace();
            }
        }
        return affectedRecords;
    }
    
    public int[] executeBatch(List<String> sqlList) {
        int sqlSize = sqlList.size();
        int[] returnValues = new int[sqlSize];
        String[] sqls = new String[sqlSize];
        sqls = sqlList.toArray(sqls);
        try {
            stat = conn.createStatement();
            conn.setAutoCommit(false);
            returnValues = stat.executeBatch();
            conn.commit();
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                stat.close();
            } catch(SQLException ex) {
                ex.printStackTrace();
            }
        }
        return returnValues;
    }
    
    public int executePrepareSQL(String sql, Map<Integer, Object> parameters) {
        int returnValue = -1;
        try {
            pstmt = conn.prepareStatement(sql);
            for(Map.Entry<Integer, Object> entry : parameters.entrySet()) {
                pstmt.setObject(entry.getKey(), entry.getValue());
            }
            returnValue = pstmt.executeUpdate();
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                stat.close();
            } catch(SQLException ex) {
                ex.printStackTrace();
            }
        }
        return returnValue;
    }
    
    public int executePrepareSQL(String sql, List<Map<Integer, Object>> parameters) {
        int returnValue = -1;
        try {
            pstmt = conn.prepareStatement(sql);
            for(Map<Integer, Object> parameter : parameters) {
                for(Map.Entry<Integer, Object> entry : parameter.entrySet()) {
                    pstmt.setObject(entry.getKey(), entry.getValue());
                }
                returnValue = pstmt.executeUpdate();
                if(returnValue == -1) return -1;
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                stat.close();
            } catch(SQLException ex) {
                ex.printStackTrace();
            }
        }
        return returnValue;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
}
