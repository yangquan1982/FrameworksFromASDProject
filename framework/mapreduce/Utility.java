package framework.mapreduce;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {

    public static String loadFileIntoMemory(URI pathToFile) throws IOException {
        final String rawFileContents = new String(Files.readAllBytes(Paths.get(pathToFile)));
        return rawFileContents;
    }

    public static String toLowerCase(String content) throws Exception {
        if(content == null)
            throw new Exception("The content should not be null");
        
        else
            return content.toLowerCase();
    }

    public static String[] splitWords(String content, String separator) throws Exception {
        if(content == null)
            throw new Exception("The content should not be null");
        final String[] words = content.split(separator);
        return words;
    }

    public static String[] filterWords(String[] words) {
        List<String> targets = new ArrayList<String>();
        for(String word : words) {
            word = word.trim();
            if(word.isEmpty())
                continue;
            if(word.endsWith("."))
                word = word.substring(0, word.length()-1);
            if(word.startsWith("\"") || word.startsWith("'"))
                word = word.substring(1, word.length());
            if(word.endsWith("\"") || word.endsWith("'"))
                word = word.substring(0, word.length()-1);
            if(trueWord(word)) {
                if(word.contains("-")) {
                    String[] subs = word.split("-");
                    targets.addAll(Arrays.asList(subs));
                } else
                    targets.add(word);
            }
        }
        String[] list = new String[targets.size()];
        return targets.toArray(list) ;
    }

    public static boolean trueWord(String word) {
        Pattern p = Pattern.compile("^[a-z-]*$");
        Matcher m = p.matcher(word);
        return m.find();
    }
    
    public static Map<String, Integer> combine(String[] words) {
        Map<String, Integer> wordsCountMap = new HashMap<String, Integer>();
        for(String word : words) {
            if(wordsCountMap.containsKey(word))
                wordsCountMap.put(word, wordsCountMap.get(word) + 1);
            else
                wordsCountMap.put(word, 1);
        }
        return wordsCountMap;
    }

    public static List<Pair<String, Integer>> sortWords(Map<String, Integer> wordsCountMap) {
        List<Pair<String, Integer>> pairList = new ArrayList<Pair<String, Integer>>();
        for(Map.Entry<String, Integer> entry : wordsCountMap.entrySet()) {
            pairList.add(new Pair<String, Integer>(entry.getKey(), entry.getValue()));
        }
        Collections.sort(pairList);
        return pairList;
    }
    
    public static List<Pair<String, Integer>> sortWords(String[] words) {
        List<Pair<String, Integer>> pairList = new ArrayList<Pair<String, Integer>>();
        for(String word : words) {
            pairList.add(new Pair<String, Integer>(word, 1));
        }
        Collections.sort(pairList);
        return pairList;
    }
    
    public static List<Pair<String, Integer>> sortWords(Map<String, Integer> wordsCountMap, Comparator<Pair<String, Integer>> c) {
        List<Pair<String, Integer>> pairList = new ArrayList<Pair<String, Integer>>();
        for(Map.Entry<String, Integer> entry : wordsCountMap.entrySet()) {
            pairList.add(new Pair<String, Integer>(entry.getKey(), entry.getValue()));
        }
        Collections.sort(pairList, c);
        return pairList;
    }

    public static void mapOutput(List<Pair<String, Integer>> words) {
        for(Pair<String, Integer> pair : words) {
            System.out.println("<" + pair.getT().toString() + "," + "1>");
        }
    }
}

