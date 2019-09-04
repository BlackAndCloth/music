package com.qianfeng.jdbcutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author yaojin
 *	自己封装jdbc工具类
 */
public class JdbcUtils {
	//final修饰的变量不能重复修改
	//final修饰的类不能被继承
	//final修饰的方法不能被重写
	//驱动
	private static final String driver = "com.mysql.jdbc.Driver";
	//数据库url
	private static final String url = "jdbc:mysql://localhost:3306/db_music_system";
	//用户名
	private static final String user = "root";
	//密码
	private static final String password = "123456";
	
	//加载驱动
	//优先加载，只会初始化一次
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	//获取链接
	public static Connection getConnection(){
		try {
			Connection conn = DriverManager.getConnection(url, user, password);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	//释放资源
	public static void release(Connection conn,Statement stmt,ResultSet res){
		//怕出现空指针异常
		try {
			if(conn!=null){
				conn.close();
			}
			if(stmt!=null){
				stmt.close();
			}
			if(res!=null){
				res.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
