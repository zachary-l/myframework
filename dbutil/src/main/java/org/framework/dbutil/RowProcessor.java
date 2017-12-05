package org.framework.dbutil;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class RowProcessor {
    /**
     * 返回一个type类型的实体的结果集
     */
    public  static <T> T toBean(ResultSet rs,Class<T> type) throws SQLException {
     return (T) BeanUtil.createBean(rs,type);
    }

    /**
     * 返回一个Object[]的结果集
     */
    public static Object[] toArray(ResultSet rs)throws SQLException{
        ResultSetMetaData rsd = rs.getMetaData();
        Object[] array = new Object[rsd.getColumnCount()];
        for(int i = 0;i<array.length;i++){
            array[i] = rs.getObject(i+1);
        }
        return array;
    }

    public static Map<String,Object> toMap(ResultSet rs)throws SQLException{
        Map<String,Object> map = new HashMap<>();
        ResultSetMetaData rsd = rs.getMetaData();
        for(int i = 1;i<=rsd.getColumnCount();i++){
            map.put(rsd.getColumnLabel(i),rs.getObject(i));
        }
        return map;
    }
    public static <T> T toValue(ResultSet rs,int columnindex,Class<T> type)throws SQLException{
        Object value=rs.getObject(columnindex);
        if(value!=null){
            String  columnName = rs.getMetaData().getColumnLabel(columnindex);
            value = ColumnUtil.columnConvert(rs,columnName,type);
        }
        return (T)value;
    }
}
