package framework.dataaccess;

public class PoolBuilder {
    private ConnectionPool pool; 
    private PoolConfigure configure;
    
    public PoolBuilder(ConnectionPool pool, PoolConfigure configure) {
        this.pool = pool;
        this.configure = configure;
    }
    
    public ConnectionPool getPool() {
        return pool;
    }

    public void setPool(ConnectionPool pool) {
        this.pool = pool;
    }

    public PoolConfigure getConfigure() {
        return configure;
    }

    public void setConfigure(PoolConfigure configure) {
        this.configure = configure;
    }
    
    public ConnectionPool build() {
        return pool.setMaxActive(configure.getMaxSize()).setMinSize(configure.getMinsize());
    }
}
