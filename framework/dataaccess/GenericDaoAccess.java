package framework.dataaccess;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Predicate;

import framework.common.Functor;

public interface GenericDaoAccess <T, ID extends Serializable> extends DataAccess<T, ID> {
     
     void insert(T entity) throws SQLException;

     void update(T entity) throws SQLException;

     T getById(ID id) throws SQLException;

     List<T> getAll() throws SQLException;

     <R> void executeAll(Functor<T, R> func, Predicate<T> p) throws SQLException;
     
     <S extends T> S save(S entity) throws SQLException;
     
     <S extends T> Iterable<S> save(Iterable<S> entities) throws SQLException;
     
     T findOne(ID id) throws SQLException;
     
     boolean exists(ID id) throws SQLException;
     
     Iterable<T> findAll() throws SQLException;
     
     Iterable<T> findAll(Iterable<ID> ids) throws SQLException;
     
     long count() throws SQLException;

     void delete(ID id) throws SQLException;
     
     void delete(T entity) throws SQLException;

     void delete(Iterable<? extends T> entities) throws SQLException;

     void deleteAll() throws SQLException;
}
