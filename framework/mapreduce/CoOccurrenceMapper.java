package framework.mapreduce;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CoOccurrenceMapper extends AbstractMapper<String, Integer> {
    
    public CoOccurrenceMapper(String contents) {
        super(contents);
    }

    @Override
    public List<Pair<String, Integer>> map() throws Exception {
        try {
            printInput();
            String outContents = Utility.toLowerCase(this.getContents());
            String[] lines = Utility.splitWords(outContents, "\r\n");
            List<String> coOccurrences = new LinkedList<String>();
            for(String line : lines) {
                String[] rawWords = Utility.splitWords(line, "\\s+");
                for(int i=0; i<rawWords.length; i++)
                    for(int j=i+1; j<rawWords.length; j++)
                        coOccurrences.add(rawWords[i] + "------>" + rawWords[j]);
            }
            String[] collectionPairs = coOccurrences.toArray(lines);
            if(this.isInCombine())
                setMapOutput(Utility.sortWords(Utility.combine(collectionPairs)));
            else
                setMapOutput(Utility.sortWords(collectionPairs));
            printOutput(this.getMapOutput());
            return this.getMapOutput();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw ex;
        }
    }
}
