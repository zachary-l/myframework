package org.framework.dbutil.handler;

import org.framework.dbutil.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *返回不同类型的集合的模板方法
 * @param <T>
 */
public abstract class AbstractListHandler<T> implements ResultSetHandler<List<T>>{

    @Override
    public List<T> handle(ResultSet rs) throws SQLException {
        List<T> rows = new ArrayList<T>();
        while(rs.next()){
            rows.add(getRow(rs));
        }
        return rows;
    }
    protected  abstract  T getRow(ResultSet rs)throws SQLException;
}
