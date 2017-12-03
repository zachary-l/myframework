package org.framework.dbutil.handler;

import org.framework.dbutil.RowProcessor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class MapListHandler extends AbstractListHandler<Map<String,Object>> {
    @Override
    protected Map<String, Object> getRow(ResultSet rs) throws SQLException {
        return RowProcessor.toMap(rs);
    }
}
