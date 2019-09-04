package com.qianfeng.bean;

public class UserBean {
	private String userName;
	private String userPwd;
	private String userNick;
	private String userSex;
	private String userAge;
	private String userPhoto;
	/**
	 * ¸öÈËÐûÑÔ
	 */
	private String userDesc;
	public UserBean(String userName, String userPwd, String userNick, String userSex, String userAge, String userPhoto,
			String userDesc) {
		super();
		this.userName = userName;
		this.userPwd = userPwd;
		this.userNick = userNick;
		this.userSex = userSex;
		this.userAge = userAge;
		this.userPhoto = userPhoto;
		this.userDesc = userDesc;
	}
	public UserBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserNick() {
		return userNick;
	}
	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getUserAge() {
		return userAge;
	}
	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}
	public String getUserPhoto() {
		return userPhoto;
	}
	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}
	public String getUserDesc() {
		return userDesc;
	}
	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}
}
