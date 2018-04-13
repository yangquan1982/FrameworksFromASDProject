package framework.dataaccess;

public class ColumnMetaData {
    private int order;
    private String columnName;
    private DataType type;
    
    public ColumnMetaData(int order, String columnName, DataType type) {
        this.order = order;
        this.columnName = columnName;
        this.type = type;
    }
    
    public ColumnMetaData(int order, String columnName, String type) {
        this.order = order;
        this.columnName = columnName;
        this.type = DataType.getDataType(Integer.valueOf(type));
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public DataType getType() {
        return type;
    }

    public void setType(DataType type) {
        this.type = type;
    }
    
}
