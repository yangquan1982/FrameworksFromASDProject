package framework.mapreduce;

public class InMapperWordCount extends ComputeFrameWork {
    
    public InMapperWordCount(int numberOfSplits, int numberOfReducers) {
        super(numberOfSplits, numberOfReducers);
        setInCombine(true);
    }

    @Override
    public int getPartition(String key) {
        return (int) key.hashCode() % getNumberOfReducers();
    }
}
