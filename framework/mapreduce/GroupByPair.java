package framework.mapreduce;

import java.io.Serializable;
import java.util.List;

//1.  Create a GroupByPair with two data members. First member is Key. Second member is a list of Values.
public class GroupByPair<K extends Comparable<K>, V> implements Comparable<GroupByPair<K, V>>,Serializable {

    private static final long serialVersionUID = 8318814216717367973L;

    private K k;
    private List<V> v;

    GroupByPair(K k, List<V> v){
        this.k = k;
        this.v = v;
    }

    public K getKey() {
        return k;
    }

    public List<V> getValue() {
        return v;
    }

    @Override
    public int compareTo(GroupByPair<K, V> o) {
      return getKey().compareTo(o.getKey());
    }
}

