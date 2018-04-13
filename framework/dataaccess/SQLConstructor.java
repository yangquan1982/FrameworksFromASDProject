package framework.dataaccess;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SQLConstructor <T extends MappingObject, ID extends Serializable> {
    private String tableName;
    private TableMetaData tableMetaData = new TableMetaData();
    private Map<String, String> valueMap = new HashMap<String, String>();
    private Class cls;//use reflection

    private String ID;
    
    public SQLConstructor(String tableName, Class cls, String ID) {
        this.tableName = tableName;
        this.cls = cls;
        this.ID = ID;
    }
    
    public String getTableName() {
        return tableName;
    }
    
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    
    public Class getCls() {
        return cls;
    }

    public void setCls(Class cls) {
        this.cls = cls;
    }

    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        ID = iD;
    }
    
    private void getMetaData() {
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
            ResultSet resultSet = databaseMetaData.getTables(null, null, tableName, new String[]{"TABLE"});
            System.out.println("Printing TABLE_TYPE \"TABLE\" ");
            System.out.println("----------------------------------");
            while(resultSet.next()){
              System.out.println(resultSet.getString("TABLE_NAME"));
            }
            
            //Get primary key of a table
            resultSet = databaseMetaData.getPrimaryKeys(null, null, tableName);
            while(resultSet.next()){
                System.out.println("The primary key is: ");
                System.out.println(resultSet.getString(4));
            }
            
            ResultSet columns = databaseMetaData.getColumns(null,null, tableName, null);
            int i=0;
            while(columns.next()){
                String columnName = columns.getString("COLUMN_NAME");
                String datatype = columns.getString("DATA_TYPE");
                String columnsize = columns.getString("COLUMN_SIZE");
                String decimaldigits = columns.getString("DECIMAL_DIGITS");
                String isNullable = columns.getString("IS_NULLABLE");
                String is_autoIncrment = columns.getString("IS_AUTOINCREMENT");
                tableMetaData.addColumn(i, new ColumnMetaData(i, columnName, datatype));
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
    //use java reflection
    private void getEntityInfo() {
        
    }
    private void constructFakeValue() {
        for(int i=0; i<tableMetaData.getFieldsSize(); i++) {
            this.valueMap.put(tableMetaData.getColumnData(i).getColumnName(), new String(""));
        }
    }
    public void replaceTableValues(T t) {
        
    }
    public String constructInsert(T t) {
        replaceTableValues(t);
        StringBuffer insertSQL = new StringBuffer(256);
        Map<String, String> mappedValue = t.getMappedValues();
        insertSQL.append("insert into ");
        insertSQL.append(tableName + "(");  
        Set<String> keys = mappedValue.keySet();
        int i = 0;
        for (String keyName : keys) {
            if(i == keys.size()-1)
                insertSQL.append(keyName);
            else
                insertSQL.append(keyName + ",");
            i++;
        }
        insertSQL.append(") vales (");
        Collection<String> values = mappedValue.values();
        i = 0;
        for (String value : values) {
            if(i == keys.size()-1)
                insertSQL.append(value);
            else
                insertSQL.append(value + ",");
            i++;
        }
        insertSQL.append(")");
        return insertSQL.toString();
    }
    
    //UPDATE table_name
    //SET column1 = value1, column2 = value2, ...
    //WHERE condition; 
    public String constructUpdate(T t, ID id, Map<String,String> updatedValues) {
        replaceTableValues(t);
        StringBuffer updateSQL = new StringBuffer(256);
        updateSQL.append("update ").append(tableName).append(" set ");
        for (Map.Entry<String, String> entry : updatedValues.entrySet()) {
            updateSQL.append(entry.getKey()).append("=").append(entry.getValue()).append(" ");
        }
        updateSQL.append( "where " + ID);
        return updateSQL.toString();
    }
    
    public String constructSelect(ID id) {
        StringBuffer selectSQL = new StringBuffer(128);
        selectSQL.append("select * from ");
        selectSQL.append(tableName);
        selectSQL.append(" where ");
        selectSQL.append(ID);
        selectSQL.append("=\"");
        selectSQL.append(id);
        selectSQL.append("=\"");
        return selectSQL.toString();
    }
    
    public String constructDelete(ID id) {
        StringBuffer deleteSQL = new StringBuffer(128);
        deleteSQL.append("delete from ");
        deleteSQL.append(tableName);
        deleteSQL.append(" where ");
        deleteSQL.append(ID);
        deleteSQL.append("=\"");
        deleteSQL.append(id);
        deleteSQL.append("=\"");
        return deleteSQL.toString();
    }
    
    public void prepareConstructSQL() {
        getMetaData();
        getEntityInfo();
        constructFakeValue();
    }
    
    public TableMetaData getTableMetaData() {
        return tableMetaData;
    }

    public void setTableMetaData(TableMetaData tableMetaData) {
        this.tableMetaData = tableMetaData;
    }

    public Map<String, String> getValueMap() {
        return valueMap;
    }

    public void setValueMap(Map<String, String> valueMap) {
        this.valueMap = valueMap;
    }
    
    public static void main(String[] args) {
        
        
    }
}
