package framework.mapreduce;

public class WordCount extends ComputeFrameWork {

    public WordCount(int numberOfSplits, int numberOfReducers) {
        super(numberOfSplits, numberOfReducers);
        setInCombine(false);
    }

    @Override
    public int getPartition(String key) {
        return (int) key.hashCode() % getNumberOfReducers();
    }
}

