package framework.mapreduce.test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import framework.mapreduce.AbstractMapper;
import framework.mapreduce.CoOccurrenceMapper;
import framework.mapreduce.ComputeFrameWork;
import framework.mapreduce.GroupByPair;
import framework.mapreduce.Pair;
import framework.mapreduce.Reducer;
import framework.mapreduce.Utility;

public class CoOccurrenceTest {

    public static void main(String[] args) throws URISyntaxException {  
        URI filePath = WordCountTest.class.getResource("testDataForW1D2.txt").toURI();
        exectue(filePath);
    }

    public static void exectue(URI filePath) {
        String fileContents = null;
        try {
            fileContents = Utility.loadFileIntoMemory(filePath);
            AbstractMapper<String, Integer> mapper = new CoOccurrenceMapper(fileContents);
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

}
