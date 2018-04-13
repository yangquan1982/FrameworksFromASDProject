package framework.mapreduce;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMapper<K extends Comparable<K>, V> {
    private String contents = null;
    
    private List<Pair<K, V>> mapOutput = null;
    
    private boolean inCombine = true;

    public AbstractMapper() {
        mapOutput = new ArrayList<Pair<K, V>>();
    }

    public AbstractMapper(String contents) {
        this.contents = contents;
        mapOutput = new ArrayList<Pair<K, V>>();
    }
    
    public void assignTask(String contents) {
        this.contents = contents;
    }
    
    public List<Pair<K, V>> getMapOutput() {
        return mapOutput;
    }

    public void setMapOutput(List<Pair<K, V>> mapOutput) {
        this.mapOutput = mapOutput;
    }
    
    public boolean isInCombine() {
        return inCombine;
    }

    public void setInCombine(boolean inCombine) {
        this.inCombine = inCombine;
    }
    
    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    
    public abstract List<Pair<K,V>> map() throws Exception;

    /*
    public List<Pair<String, Integer>> map() throws Exception {
        try {
            printInput();
            contents = Utility.toLowerCase(contents);
            String[] rawWords = Utility.splitWords(contents, "\\,|\\s|\n");
            rawWords = Utility.filterWords(rawWords);
            if(inCombine)
                mapOutput = Utility.sortWords(Utility.combine(rawWords));
            else
                mapOutput = Utility.sortWords(rawWords);
            printOutput(mapOutput);
            return mapOutput;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw ex;
        }
    }
    */
    
    protected void printInput() {
        if(ComputeFrameWork.DEBUG) {
            System.out.println("----------------------------------------------");
            System.out.println("---------------Mapper Input-------------------");
            System.out.println("----------------------------------------------");
            System.out.println(contents);
            System.out.println();
            System.out.println();
        }
    }
    
    protected void printOutput(List<Pair<String, Integer>> mapResults) {
        if(ComputeFrameWork.DEBUG) {
            System.out.println("----------------------------------------------");
            System.out.println("---------------Mapper Output-------------------");
            System.out.println("----------------------------------------------");
            Utility.mapOutput(mapResults);
            System.out.println();
            System.out.println();
        }
    }
}
