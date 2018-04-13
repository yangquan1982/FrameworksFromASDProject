package framework.dataaccess;

public enum DataType {
    TEXT(4), INTEGER(12), REAL(6);
    
    private final int type;
    
    DataType(int type){
        this.type = type;
    }
    
    public int getDataType() {
        return this.type;
    }
    
    public static DataType getDataType(int value) {
        switch (value) {
        case 4:
            return TEXT;
        case 12:
            return INTEGER;
        case 6:
            return REAL;
        default:
            return TEXT;
        }
    }
}
