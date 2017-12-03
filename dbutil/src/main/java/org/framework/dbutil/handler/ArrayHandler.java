package org.framework.dbutil.handler;

import org.framework.dbutil.ResultSetHandler;
import org.framework.dbutil.RowProcessor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArrayHandler implements ResultSetHandler<Object[]>{
    @Override
    public Object[] handle(ResultSet rs) throws SQLException {
        return rs.next() ? RowProcessor.toArray(rs) : null;
    }
}
