package test.framework.dbutil;

import java.sql.*;


public class DBUtil {

	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/demo?useUnicode=true&character=utf-8";
	private static String user = "root";
	private static String password ="123456";
	
	/**
	 * 注册驱动
	 */
	static {
		try{
			Class.forName(driver);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取数据库连接
	 */
	public static Connection getConnection(){
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(url, user, password);
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 关闭数据库资源
	 */
	public static void close(ResultSet rs, Statement st, Connection conn){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(st != null){
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println(getConnection());
	}
}
