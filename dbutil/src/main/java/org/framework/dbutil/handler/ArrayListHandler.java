package org.framework.dbutil.handler;

import org.framework.dbutil.RowProcessor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ArrayListHandler extends AbstractListHandler<Object[]> {
    @Override
    protected Object[] getRow(ResultSet rs) throws SQLException {
        return RowProcessor.toArray(rs);
    }

}
