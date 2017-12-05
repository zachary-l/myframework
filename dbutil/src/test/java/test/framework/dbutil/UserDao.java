package test.framework.dbutil;

import org.framework.dbutil.ResultSetHandler;
import org.framework.dbutil.SQLExectutor;
import org.framework.dbutil.handler.ArrayHandler;
import org.framework.dbutil.handler.ArrayListHandler;
import org.framework.dbutil.handler.BeanHandler;
import org.framework.dbutil.handler.BeanListHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao {

    public Users findUserById(int uid) {
        String sql = "select * from USERS_INFO where U_ID = ?";
        SQLExectutor se = new SQLExectutor(DBUtil.getConnection());
        ResultSetHandler<Users> handler = new BeanHandler<>(Users.class);
        Users users = null;
        try {
            users = se.executeQuery(sql, handler, uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    public Object[] findUserById2(int uid) {
        String sql = "select * from USERS_INFO where U_ID = ?";
        SQLExectutor se = new SQLExectutor(DBUtil.getConnection());
        ResultSetHandler<Object[]> handler = new ArrayHandler();
        Object[] users = null;
        try {
            users = se.executeQuery(sql, handler, uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    public List<Users> find(){
        List<Users> list = null;
        String sql = "SELECT * FROM USERS_INFO";
        SQLExectutor se = new SQLExectutor(DBUtil.getConnection());
        ResultSetHandler<List<Users>> handler = new BeanListHandler<>(Users.class);
        try {
            list = se.executeQuery(sql,handler);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<Object[]> find2(){
        List<Object[]> list = null;
        return null;
    }

    public int inster(Users users) {
        String sql = "INSERT INTO USERS_INFO(U_NAME,U_AGE) VALUES(?,?);";
        int row = 0;
        SQLExectutor se = new SQLExectutor(DBUtil.getConnection());
        try {
            row = se.executeUpdate(sql, users.getUserName(), users.getAge());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return row;
    }
    public int[] executeBatch(Object[][] obj){
        String sql = "INSERT INTO USERS_INFO(U_NAME,U_AGE) VALUES(?,?);";
        int[] row=null;
        SQLExectutor se = new SQLExectutor(DBUtil.getConnection());
        try {
            row = se.executeBatch(sql,obj);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    public static void main(String[] args) {

    }
}
