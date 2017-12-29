package org.framework.dbutil;

import java.sql.*;

public class SQLExecutor {
    private Connection connection;
    private boolean autoClose = true;
    public SQLExecutor(Connection connection){
        this.connection=connection;
    }
    /**
     * 执行DQL操作
     */
    public <T> T executeQuery(String sql,ResultSetHandler<T> handler,Object...args){
        if(connection==null){
            throw new SQLExecutorException("collection is null");
        }
        if(sql==null){
            close();
            throw new SQLExecutorException("sql is null");
        }
        if(handler==null){
            close();
            throw new SQLExecutorException("handler is null");
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
            throw new SQLExecutorException("exector query fail",e);
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
    public void executeUpdate(String sql,Object...args) {
        if(connection==null){
            throw new SQLExecutorException("collection is null");
        }
        if(sql==null){
            close();
            throw new SQLExecutorException("sql is null");
        }
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            setParameters(ps, args);
            ps.executeUpdate();
        }catch (SQLException e) {
            throw new SQLExecutorException("exector update fail",e);
        } finally {
            close(ps);
            if (autoClose) {
                close();
            }
        }
    }
    /**
     * 批量增删改操作
     */
    public void executeBatch(String sql,Object[][] args){
        if(connection==null){
            throw new SQLExecutorException("collection is null");
        }
        if(sql==null){
            close();
            throw new SQLExecutorException("sql is null");
        }
        PreparedStatement ps = null;
        try {
            ps=connection.prepareStatement(sql);

            for(int i=0;i<args.length;i++){
                setParameters(ps,args[i]);
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            throw  new SQLExecutorException(" exector executeBatch fail",e );
        } finally {
            close(ps);
            if(autoClose){
                close();
            }
        }
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
            throw new TransactionException("beginTranstaction is fail",e);
        }
    }

    /**
     * 提交事务
     */
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new TransactionException("commit is fail",e);
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
            throw new TransactionException("rollback is fail",e);
        } finally {
            close();
        }
    }

    /**
     * 关闭结果集
     * @param rs
     */
    private void close(ResultSet rs) {
        if (rs != null)
            try {
                rs.close();
            } catch (SQLException e) {
            throw new CloseResourcesException("close is fail",e);
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
                throw new CloseResourcesException("close is fail",e);
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
            throw new CloseResourcesException("close is fail",e);
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
