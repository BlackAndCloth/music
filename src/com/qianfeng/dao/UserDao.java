package com.qianfeng.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.qianfeng.bean.UserBean;
import com.qianfeng.jdbcutils.JdbcUtils;

public class UserDao {
	
	/**
	 * 添加用户信息
	 */
	public void insertUser(UserBean user){
		Connection conn = JdbcUtils.getConnection();
		String sql = "insert into tb_user(user_account,user_pwd,user_nickname,user_sex,user_age,user_photo,user_desc)"
				+ "values(?,?,?,?,?,?,?)";
		PreparedStatement prep;
		try {
			prep = conn.prepareStatement(sql);
			prep.setString(1, user.getUserName());
			prep.setString(2, user.getUserPwd());
			prep.setString(3, user.getUserNick());
			prep.setString(4, user.getUserSex());
			prep.setInt(5, Integer.parseInt(user.getUserAge()));
			prep.setString(6, user.getUserPhoto());
			prep.setString(7, user.getUserDesc());
			//添加
			prep.executeUpdate();
			JdbcUtils.release(conn, prep, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
