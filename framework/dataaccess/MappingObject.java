package framework.dataaccess;

import java.util.HashMap;
import java.util.Map;

public abstract class MappingObject {
    //used for SQL generation
    Map<String, String> mappedValues = new HashMap<String,String>();

    public Map<String, String> getMappedValues() {
        return mappedValues;
    }

    public void setMappedValues(Map<String, String> mappedValues) {
        this.mappedValues = mappedValues;
    }
    
    abstract public void addMappedValues();
}
