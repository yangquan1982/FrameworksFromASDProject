package framework.mapreduce;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

//This class simulates Hadoop computation framework
public abstract class ComputeFrameWork {
    public static final boolean DEBUG = true; 

    private int numberOfSplits ;
    private int numberOfReducers;
    private Mapper[] mappers;
    private Reducer<String, Integer>[] reducers;
    
    private boolean inCombine = true;

    @SuppressWarnings("unchecked")
    public ComputeFrameWork(int numberOfSplits, int numberOfReducers) {
        System.out.println("Number of Input-Splits: " + numberOfSplits);
        System.out.println("Number of Reducers:" +  numberOfReducers);
        this.numberOfSplits = numberOfSplits;
        this.numberOfReducers = numberOfReducers;
        this.mappers = new Mapper[numberOfSplits];
        for(int j=0; j<numberOfSplits; j++)
            mappers[j] = new Mapper();
        this.reducers = new Reducer[numberOfReducers];
        for(int j=0; j<numberOfReducers; j++)
            reducers[j] = new Reducer<String, Integer>();
    }

    public int getNumberOfSplits() {
        return numberOfSplits;
    }

    public void setNumberOfSplits(int numberOfSplits) {
        this.numberOfSplits = numberOfSplits;
    }

    public int getNumberOfReducers() {
        return numberOfReducers;
    }

    public void setNumberOfReducers(int numberOfReducers) {
        this.numberOfReducers = numberOfReducers;
    }

    public Mapper[] getMappers() {
        return mappers;
    }

    public void setMappers(Mapper[] mappers) {
        this.mappers = mappers;
    }

    public Reducer<String, Integer>[] getReducers() {
        return reducers;
    }

    public void setReducers(Reducer<String, Integer>[] reducers) {
        this.reducers = reducers;
    }
    
    public boolean isInCombine() {
        return inCombine;
    }

    public void setInCombine(boolean inCombine) {
        this.inCombine = inCombine;
        for(int j=0; j<numberOfSplits; j++)
            mappers[j].setInCombine(inCombine);
    }

    abstract int getPartition(String key);
    
    public void setInputSplits(String[] splits) {
        int i = 0;
        for(String lines : splits) {
            System.out.println("Mapper " + i + " Input");
            System.out.println(lines);
            mappers[i].assignTask(lines);
            i++;
        }
    }
    
    public void mapperComputing() throws Exception {
        for(int i=0; i<mappers.length; i++) {
            System.out.println("Mapper " + i + " Output");
            Utility.mapOutput(mappers[i].map());
        }
    }
    
    public void shuffle() throws Exception {
        Map<Integer, List<Pair<String, Integer>>> totalTasks = new TreeMap<Integer, List<Pair<String, Integer>>>();
        for(int i=0; i<mappers.length; i++) {
            List<Pair<String, Integer>> output = mappers[i].getMapOutput();
            Map<Integer, List<Pair<String, Integer>>> map = new TreeMap<Integer, List<Pair<String, Integer>>>();
            for(Pair<String, Integer> pair : output) {
                int partiton = getPartition(pair.getT());
                if(!map.containsKey(partiton)) {
                    map.put(partiton, new ArrayList<Pair<String, Integer>>());
                }
                map.get(partiton).add(pair);
            }
            printShuffle(i, map);
            for(Map.Entry<Integer, List<Pair<String, Integer>>> entry : map.entrySet()) {
                if(!totalTasks.containsKey(entry.getKey())) {
                    totalTasks.put(entry.getKey(), new ArrayList<Pair<String, Integer>>());
                }
                totalTasks.get(entry.getKey()).addAll(entry.getValue());
            }
        } 
        sendTasksToReducer(totalTasks);
    }
    
    private void printShuffle(int mapperNumber, Map<Integer, List<Pair<String, Integer>>> pairsMap) {
        for(int i=0; i<numberOfReducers; i++) {
            List<Pair<String, Integer>> pairs = pairsMap.get(i);
            System.out.println("Pairs send from Mapper " + mapperNumber + " Reducer " + i);
            if(pairs == null) continue;
            for(Pair<String, Integer> pair : pairs) {
                System.out.println("<"+ pair.getT() + "," + pair.getValue() + ">");
            }
        }
    }
    
    public void sendTasksToReducer(Map<Integer, List<Pair<String, Integer>>> totalTasks) {
        for(int i=0; i<numberOfReducers; i++) {
            List<GroupByPair<String, Integer>> input = ComputeFrameWork.convertInputFormat(totalTasks.get(i));
            reducers[i].acceptTask(input);
            System.out.println("Reducer " +  i  + " input");
            reducers[i].printReduceInput(input);
        }
    }
    
    public void reducerComputing() throws Exception {
        for(int i=0; i<numberOfReducers; i++) {
            System.out.println("Reducer " +  i  + " output");
            reducers[i].printReduceOutput(reducers[i].reduce());
        }
    }
    
    //The map reduce process
    public void compute(String[] splits) throws Exception {
        setInputSplits(splits);
        mapperComputing();
        shuffle();
        reducerComputing();
    }
    
    //2.  Iterating through the sorted list of Key-Value Pair, create a new list of GroupByPair 
    //so that all values associated with the same key are grouped together and kept in one GroupByPair.
    public static List<GroupByPair<String, Integer>> convertInputFormat(List<Pair<String, Integer>> split_input) {
        Map<String, List<Integer>> countMap = new TreeMap<String, List<Integer>>();
        for(Pair<String, Integer> pair : split_input) {
            String key = pair.getT();
            if(countMap.containsKey(key)) {
                countMap.get(key).add(pair.getValue());
            } else {
                countMap.put(key, new ArrayList<Integer>());
                countMap.get(key).add(pair.getValue());
            }
        }
        List<GroupByPair<String, Integer>> gPair = new ArrayList<GroupByPair<String, Integer>>();
        for(Map.Entry<String, List<Integer>> entry : countMap.entrySet()) {
            gPair.add(new GroupByPair<String, Integer>(entry.getKey(), entry.getValue()));
        }
        return gPair;
    }
}

