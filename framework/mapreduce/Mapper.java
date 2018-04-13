package framework.mapreduce;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//TODO : will make it more generic
public class Mapper {

    private String contents = null;
    
    private List<Pair<String, Integer>> mapOutput = null;
    
    private boolean inCombine = true;

    public Mapper() {
        mapOutput = new ArrayList<Pair<String, Integer>>();
    }

    public Mapper(String contents) {
        this.contents = contents;
    }
    
    public void assignTask(String contents) {
        this.contents = contents;
    }
    
    public List<Pair<String, Integer>> getMapOutput() {
        return mapOutput;
    }

    public void setMapOutput(List<Pair<String, Integer>> mapOutput) {
        this.mapOutput = mapOutput;
    }
    
    public boolean isInCombine() {
        return inCombine;
    }

    public void setInCombine(boolean inCombine) {
        this.inCombine = inCombine;
    }

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
    
    private void printInput() {
        if(ComputeFrameWork.DEBUG) {
            System.out.println("----------------------------------------------");
            System.out.println("---------------Mapper Input-------------------");
            System.out.println("----------------------------------------------");
            System.out.println(contents);
            System.out.println();
            System.out.println();
        }
    }
    
    private void printOutput(List<Pair<String, Integer>> mapResults) {
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



