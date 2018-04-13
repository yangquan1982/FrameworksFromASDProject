package framework.dataaccess;

import javax.sql.DataSource;

public interface DataSourceInterface {
    public DataSource setUpPool() throws Exception;
}
