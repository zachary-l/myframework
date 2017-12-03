package org.framework.dbutil.handler;

import org.framework.dbutil.ResultSetHandler;
import org.framework.dbutil.RowProcessor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ColumnHandler<T> implements ResultSetHandler<T> {
    private int columnindex;
    private Class<T> type;
    public ColumnHandler(int columnindex,Class<T> type){
        this.columnindex=columnindex;
        this.type=type;
    }
    @Override
    public T handle(ResultSet rs) throws SQLException {
        return rs.next()? RowProcessor.toValue(rs,columnindex,type):null;
    }
}
