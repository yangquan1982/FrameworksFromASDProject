package framework.mapreduce;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Reducer<K extends Comparable<K>, V> {

    private List<GroupByPair<K, V>> reducerInput = null;
    
    public Reducer() {
        this.reducerInput = new ArrayList<GroupByPair<K, V>>();
    }

    public Reducer(List<GroupByPair<K, V>> reducerInput) {
        this.reducerInput = reducerInput;
    }
    
    public void acceptTask(List<GroupByPair<K, V>> reducerInput) {
        this.reducerInput = reducerInput;
    }

    //4.   Iterate through GroupBy list ¡°sum¡± the values and print out (key, sum) pairs.
    public Map<K, Integer> reduce() {
        printInput();
        Map<K, Integer> countMap = new TreeMap<K, Integer>();
        for(GroupByPair<K, V> pair : reducerInput) {
            List<V> listV = pair.getValue();
            K k = pair.getKey();
            int count = 0;
            for(V var : listV)
                count+=(int)var;
            countMap.put(k, count);
        }
        printOutput(countMap);
        return countMap;
    }
    
    //3.  Print the GroupByPair list
    private void printInput() {
        if(ComputeFrameWork.DEBUG) {
            System.out.println("----------------------------------------------");
            System.out.println("---------------Reducer Input-------------------");
            System.out.println("----------------------------------------------");
            printReduceInput(reducerInput);
            System.out.println();
            System.out.println();
        }
    }
    
    private void printOutput(Map<K, Integer> reduceResults) {
        if(ComputeFrameWork.DEBUG) {
            System.out.println("----------------------------------------------");
            System.out.println("---------------Reducer Output-------------------");
            System.out.println("----------------------------------------------");
            printReduceOutput(reduceResults);
            System.out.println();
            System.out.println();
        }
    }
    
    public void printReduceInput(List<GroupByPair<K, V>> words) {
        StringBuffer outputBuffer = new StringBuffer();
        int pos = 0;
        for(GroupByPair<K, V> pair : words) {
            outputBuffer.append("<");
            outputBuffer.append(pair.getKey()).append(", [");
            List<V> m = pair.getValue();
            if(m.size() > 0)
                outputBuffer.append(m.get(0));
            for(int j=1; j< m.size(); j++){
                outputBuffer.append(",").append(m.get(j));
            }
            outputBuffer.append("]>");
            pos++;
            if(pos != words.size())
                outputBuffer.append("\r\n");
        }
        System.out.println(outputBuffer.toString());
    }
    
    public void printReduceOutput(Map<K, Integer> reduceResults) {
        for(Map.Entry<K, Integer> entry : reduceResults.entrySet()) {
            System.out.println("<" + entry.getKey() + "," + entry.getValue() + ">");
        }
    }
}

