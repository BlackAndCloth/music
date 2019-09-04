package com.qianfeng.jdbcutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author yaojin
 *	�Լ���װjdbc������
 */
public class JdbcUtils {
	//final���εı��������ظ��޸�
	//final���ε��಻�ܱ��̳�
	//final���εķ������ܱ���д
	//����
	private static final String driver = "com.mysql.jdbc.Driver";
	//���ݿ�url
	private static final String url = "jdbc:mysql://localhost:3306/db_music_system";
	//�û���
	private static final String user = "root";
	//����
	private static final String password = "123456";
	
	//��������
	//���ȼ��أ�ֻ���ʼ��һ��
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	//��ȡ����
	public static Connection getConnection(){
		try {
			Connection conn = DriverManager.getConnection(url, user, password);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	//�ͷ���Դ
	public static void release(Connection conn,Statement stmt,ResultSet res){
		//�³��ֿ�ָ���쳣
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
