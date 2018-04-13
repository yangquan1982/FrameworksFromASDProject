package framework.mapreduce;

import java.io.IOException;
import java.util.List;

public class WordCountMapper extends AbstractMapper<String, Integer> {
    
    public WordCountMapper(String contents) {
        super(contents);
    }

    @Override
    public List<Pair<String, Integer>> map() throws Exception {
        try {
            printInput();
            String outContents = Utility.toLowerCase(this.getContents());
            String[] rawWords = Utility.splitWords(outContents, "\\,|\\s|\n");
            rawWords = Utility.filterWords(rawWords);
            if(this.isInCombine())
                setMapOutput(Utility.sortWords(Utility.combine(rawWords)));
            else
                setMapOutput(Utility.sortWords(rawWords));
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
