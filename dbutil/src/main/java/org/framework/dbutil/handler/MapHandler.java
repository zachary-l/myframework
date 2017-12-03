package org.framework.dbutil.handler;

import org.framework.dbutil.ResultSetHandler;
import org.framework.dbutil.RowProcessor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class MapHandler implements ResultSetHandler<Map<String,Object>> {
    @Override
    public Map<String,Object> handle(ResultSet rs) throws SQLException {
        return rs.next()?RowProcessor.toMap(rs):null;
    }
}
