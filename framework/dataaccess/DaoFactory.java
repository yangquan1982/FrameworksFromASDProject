package framework.dataaccess;

public interface DaoFactory {
    public GenericDaoAccess<?, ?> createDao(String table);
}
