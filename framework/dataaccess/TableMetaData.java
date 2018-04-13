package framework.dataaccess;

import java.util.HashMap;
import java.util.Map;

public class TableMetaData {
    private Map<Integer,ColumnMetaData>  metaData = new HashMap<Integer, ColumnMetaData>();
    
    public void addColumn(int order, ColumnMetaData cdata) {
        metaData.put(Integer.valueOf(order), cdata);
    }
    
    public ColumnMetaData getColumnData(int order) {
        return metaData.get(order);
    }
    
    public Map<Integer,ColumnMetaData> getMetaData(){
        return this.metaData;
    }
    
    public int getFieldsSize() {
        return metaData.size();
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
    }
}
