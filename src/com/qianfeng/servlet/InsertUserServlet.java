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
		//��ȡ��ǰ��������·��
		//this.getServletContext():��ȡservlet������
		String serverPath = this.getServletContext().getRealPath("/");
		//������Ŀ����·��
		String absolutePath = "F:\\jee-ws\\music_system\\WebContent\\";
		//�������·��
		String savepath = "img";
		//�鿴�������е�img�ļ����Ƿ����,�����ڣ����������ھ�ֱ��ʹ��
		File serverFile = new File(serverPath+savepath);
		//�жϱ��ؾ���·���Ƿ����img�ļ���
		File absoluteFile = new File(absolutePath+savepath);
		if(!serverFile.exists()){
			//����Ŀ¼��mkdir   �����ļ���createNewFile()
			serverFile.mkdir();
		}
		if(!absoluteFile.exists()){
			absoluteFile.mkdir();
		}
		//����DiskFileItemFactory
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//�����ϴ��ļ�������
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			//���������ñ����
			List<FileItem> list = upload.parseRequest(req);
			//�����û�����
			UserBean userBean = new UserBean();
			for(FileItem item:list){
				//�ж�����ͨ������ļ�����
				//isFormField():��ͨ�����δtrue���ϴ��ļ������Ϊfalse
				if(item.isFormField()){
					//��ȡ��ǰ�����ֵ
					String value = item.getString("utf-8");
					System.out.println(value);
					//ʹ��switchȥƥ��nameֵ
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
					//�ϴ��ļ�����
					//���û�����ͼƬ·��
					//item.getName():1.jpg   ���ݿ�����·����img/1.jpg
					savepath = savepath+"/"+item.getName();
					userBean.setUserPhoto(savepath);
					//ͼƬ�ϴ�
					//���룺��   �����д
					//ͨ���ϴ��ļ������ȡ����������ͼƬ�����ڴ��У�Ȼ��ʹ�÷���������·����ȡ�����
					//���ڴ��е�ͼƬд������
					Streams.copy(item.getInputStream(), new FileOutputStream(serverPath+savepath), true);
					//������Ŀ�ϴ�
					Streams.copy(item.getInputStream(), new FileOutputStream(absolutePath+savepath), true);
				}
			}
			//����������ݵķ���
			new UserDao().insertUser(userBean);
			req.setAttribute("user", userBean);
			//��ת��index.jsp
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
