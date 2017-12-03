package org.framework.dbutil;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BeanUtil {
    private static final Map<Class<?>, Object> primitiveDefaults = new HashMap<Class<?>, Object>();

    static {
        primitiveDefaults.put(Integer.TYPE, Integer.valueOf(0));
        primitiveDefaults.put(Short.TYPE, Short.valueOf((short) 0));
        primitiveDefaults.put(Byte.TYPE, Byte.valueOf((byte) 0));
        primitiveDefaults.put(Float.TYPE, Float.valueOf(0f));
        primitiveDefaults.put(Double.TYPE, Double.valueOf(0d));
        primitiveDefaults.put(Long.TYPE, Long.valueOf(0L));
        primitiveDefaults.put(Boolean.TYPE, Boolean.FALSE);
        primitiveDefaults.put(Character.TYPE, Character.valueOf((char) 0));
    }

    public static Object createBean(ResultSet rs, Class<?> type) throws SQLException {
        try {
            Object instance = type.newInstance();
            ResultSetMetaData rsd = rs.getMetaData();
            int len = rsd.getColumnCount();
            for (int i = 1; i <= len; i++) {
                String columnName = rsd.getColumnLabel(i);
                setAttribute(columnName, type, instance, rs);
            }
            return instance;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException(e);
        }
    }

    private static void setAttribute(String columnName, Class<?> clazz, Object instance, ResultSet rs) throws NoSuchFieldException, SQLException, IllegalAccessException {
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            //获得字段的注解，anno.value()获得字段注解的值，f.getName()获得字段名
            Column anno = f.getAnnotation(Column.class);
            if (columnName.equalsIgnoreCase(anno.value())) {
                Object value = ColumnUtil.columnConvert(rs, columnName, f.getType());
                if (value == null && f.getType().isPrimitive()) {
                    //字段值
                    value = primitiveDefaults.get(f.getType());
                }
                f.setAccessible(true);
                f.set(instance, value);
                break;
            }
        }
    }
}
