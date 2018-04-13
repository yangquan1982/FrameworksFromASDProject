package framework.common;

import java.sql.SQLException;

public interface Functor<T, R> {

    void execute(T t) throws SQLException;

    R getValue();
}

