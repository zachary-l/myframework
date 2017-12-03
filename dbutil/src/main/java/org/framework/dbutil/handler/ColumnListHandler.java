package org.framework.dbutil.handler;

import org.framework.dbutil.RowProcessor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ColumnListHandler<T> extends AbstractListHandler<T> {
    private int columnindex;
    private Class<T> type;
    public ColumnListHandler(int columnindex,Class<T> type){
        this.columnindex=columnindex;
        this.type=type;

    }

    @Override
    protected T getRow(ResultSet rs) throws SQLException {
        return RowProcessor.toValue(rs,columnindex,type);
    }
}
