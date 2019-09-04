package com.qianfeng.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import com.qianfeng.bean.UserBean;
import com.qianfeng.dao.UserDao;

@WebServlet("/InsertUserServlet")
public class InsertUserServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取当前服务器的路径
		//this.getServletContext():获取servlet上下文
		String serverPath = this.getServletContext().getRealPath("/");
		//本地项目绝对路径
		String absolutePath = "F:\\jee-ws\\music_system\\WebContent\\";
		//定义相对路径
		String savepath = "img";
		//查看服务器中的img文件夹是否存在,不存在，创建，存在就直接使用
		File serverFile = new File(serverPath+savepath);
		//判断本地绝对路径是否存在img文件夹
		File absoluteFile = new File(absolutePath+savepath);
		if(!serverFile.exists()){
			//创建目录：mkdir   创建文件：createNewFile()
			serverFile.mkdir();
		}
		if(!absoluteFile.exists()){
			absoluteFile.mkdir();
		}
		//创建DiskFileItemFactory
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//创建上传文件解析器
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			//解析请求获得表单项集合
			List<FileItem> list = upload.parseRequest(req);
			//创建用户对象
			UserBean userBean = new UserBean();
			for(FileItem item:list){
				//判断是普通表单项还是文件表单项
				//isFormField():普通表单项返回未true，上传文件表单项返回为false
				if(item.isFormField()){
					//获取当前表单项的值
					String value = item.getString("utf-8");
					System.out.println(value);
					//使用switch去匹配name值
					switch(item.getFieldName()){
					case "userName":
						userBean.setUserName(value);
						break;
					case "userPwd":
						userBean.setUserPwd(value);
						break;
					case "userAge":
						userBean.setUserAge(value);
						break;
					case "userNick":
						userBean.setUserNick(value);
						break;
					case "userSex":
						userBean.setUserSex(value);
						break;
					case "userDesc":
						userBean.setUserDesc(value);
						break;
					}
				}else{
					//上传文件表单项
					//给用户设置图片路径
					//item.getName():1.jpg   数据库设置路径：img/1.jpg
					savepath = savepath+"/"+item.getName();
					userBean.setUserPhoto(savepath);
					//图片上传
					//输入：读   输出：写
					//通过上传文件表单项获取输入流，将图片读到内存中，然后使用服务器本地路径获取输出流
					//将内存中的图片写到本地
					Streams.copy(item.getInputStream(), new FileOutputStream(serverPath+savepath), true);
					//本地项目上传
					Streams.copy(item.getInputStream(), new FileOutputStream(absolutePath+savepath), true);
				}
			}
			//调用添加数据的方法
			new UserDao().insertUser(userBean);
			req.setAttribute("user", userBean);
			//跳转到index.jsp
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	
}
