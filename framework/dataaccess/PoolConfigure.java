package framework.dataaccess;

public class PoolConfigure {
    private int minsize = 10;
    private int maxSize = 30;

    public int getMinsize() {
        return minsize;
    }

    public void setMinsize(int minsize) {
        this.minsize = minsize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public PoolConfigure(int minsize, int maxSize) {
        this.minsize = minsize;
        this.maxSize = maxSize;
    }
}
