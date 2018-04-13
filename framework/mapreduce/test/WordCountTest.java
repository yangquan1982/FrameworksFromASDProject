package framework.mapreduce.test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import framework.mapreduce.*;

public class WordCountTest {
    
    public final static String input1 = "\"cat bat\" mat-pat mum.edu sat.\r\n" +
            "fat 'rat eat cat' mum_cs mat";

    public final static String input2 = "bat-hat mat pat \"oat\r\n" +
            "hat rat mum_cs eat oat-pat";

    public final static String input3 = "zat lat-cat pat jat.\r\n" +
            "hat rat. kat sat wat";
    
    public final static String[] input_splits = {input1, input2, input3};

    public static void main(String[] args) throws URISyntaxException {  
        //URI filePath = WordCountTest.class.getResource("testDataForW1D1.txt").toURI();
        //wordCount(filePath);
        w1d4Task();
    }

    public static void wordCount(URI filePath) {
        String fileContents = null;
        try {
            fileContents = Utility.loadFileIntoMemory(filePath);
            AbstractMapper<String, Integer> mapper = new WordCountMapper(fileContents);
            List<Pair<String, Integer>> mapResults = mapper.map();
            List<GroupByPair<String, Integer>> reduceInput = ComputeFrameWork.convertInputFormat(mapResults);
            Reducer<String, Integer> reducer = new Reducer<String, Integer>(reduceInput);
            reducer.reduce();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public static void w1d3Task() {
        WordCount wordCount = new WordCount(3, 4);
        try {
            wordCount.compute(input_splits);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public static void w1d4Task() {
        InMapperWordCount wordCount = new InMapperWordCount(3, 4);
        try {
            wordCount.compute(input_splits);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
