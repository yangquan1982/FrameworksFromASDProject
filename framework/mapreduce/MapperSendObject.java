package framework.mapreduce;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MapperSendObject<K extends Comparable<K>, V> implements Serializable {

    private static final long serialVersionUID = -2279691310570587763L;
    private List<Pair<K, V>> sendObject = new ArrayList<Pair<K, V>>();
    
    public MapperSendObject(List<Pair<K, V>> sendObject) {
        this.sendObject = sendObject;
    }
    
    public List<Pair<K, V>> getMapperSendObject() {
         return sendObject;
    }
    
    public void addNewSendPair(Pair<K, V> newPair) {
        this.sendObject.add(newPair); 
    }
}
