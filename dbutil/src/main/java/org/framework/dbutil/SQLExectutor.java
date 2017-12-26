package org.framework.dbutil;

import java.sql.*;
import java.util.Collection;

public class SQLExectutor {
    private Connection connection;
    private boolean autoClose = true;
    public SQLExectutor(Connection connection){
        this.connection=connection;
    }
    /**
     * 执行DQL操作
     */
    public <T> T executeQuery(String sql,ResultSetHandler<T> handler,Object...args)throws SQLException{
        if(connection==null){
            throw new SQLException("collection is null");
        }
        if(sql==null){
            close();
            throw new SQLException("sql is null");
        }
        if(handler==null){
            close();
            throw new SQLException("handler is null");
        }
        PreparedStatement ps = null;
        ResultSet rs = null;
        T t = null;
        try {
            ps = connection.prepareStatement(sql);
            setParameters(ps,args);
            rs=ps.executeQuery();
            t = handler.handle(rs);
        } catch (SQLException e) {
            rethrow(e);
        } finally {
            close(ps);
            close(rs);
            if(autoClose){
                close();
            }
        }
        return t;
    }

    /**
     * 执行DML操作
     */
    public int executeUpdate(String sql,Object...args) throws SQLException {
        if(connection==null){
            throw new SQLException("collection is null");
        }
        if(sql==null){
            close();
            throw new SQLException("sql is null");
        }
        PreparedStatement ps = null;
        int row= 0;
        try {
            ps = connection.prepareStatement(sql);
            setParameters(ps, args);
            row = ps.executeUpdate();
        }catch (SQLException e) {
            rethrow(e);
        } finally {
            close(ps);
            if (autoClose) {
                close();
            }
        }

        return row;
    }
    /**
     * 批量增删改操作
     */
    public int[] executeBatch(String sql,Object[][] args)throws SQLException{
        if(connection==null){
            throw new SQLException("collection is null");
        }
        if(sql==null){
            close();
            throw new SQLException("sql is null");
        }
        PreparedStatement ps = null;
        int[] row = null;
        try {
            ps=connection.prepareStatement(sql);

            for(int i=0;i<args.length;i++){
                setParameters(ps,args[i]);
                ps.addBatch();
            }
            row = ps.executeBatch();
        } catch (SQLException e) {
            rethrow(e);
        } finally {
            close(ps);
            if(autoClose){
                close();
            }
        }

        return row;
    }


    /**
     * 设置替换参数
     * @param ps
     * @param args
     * @throws SQLException
     */
    private void setParameters(PreparedStatement ps, Object[] args)
            throws SQLException {
        for (int i = 0; i < args.length; i++) {
            ps.setObject(i + 1, args[i]);
        }
    }

    /**
     * 开启事务
     */
    public void beginTranstaction() {
        try {
            autoClose = false;
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 提交事务
     */
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    /**
     * 事务回滚
     */
    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    /**
     * 关闭结果集
     *
     * @param rs
     */
    private void close(ResultSet rs) {
        if (rs != null)
            try {
                rs.close();
            } catch (SQLException e) {
            }
    }

    /**
     * 关闭Statement
     *
     * @param st
     */
    private void close(Statement st) {
        if (st != null)
            try {
                st.close();
            } catch (SQLException e) {
            }
    }

    /**
     * 关闭连接
     */
    private void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
        }
    }

    /**
     * 异常重抛
     */
    private void rethrow(SQLException cause)
            throws SQLException {
        String msg = cause.getMessage() == null ? "" : cause.getMessage();
        SQLException e = new SQLException(msg, cause.getSQLState(), cause.getErrorCode());
        e.setNextException(cause);
        throw e;
    }

}
