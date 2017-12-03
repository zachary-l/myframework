package org.framework.dbutil.handler;

import org.framework.dbutil.ResultSetHandler;
import org.framework.dbutil.RowProcessor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BeanHandler<T> implements ResultSetHandler<T> {
    private Class<T> type;
    public BeanHandler(Class<T> type){
        this.type=type;
    }
    @Override
    public T handle(ResultSet rs) throws SQLException {
        return rs.next()?RowProcessor.toBean(rs,type) :null;
    }
}
