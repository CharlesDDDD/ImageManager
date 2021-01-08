package com.servlet;
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.PhotoDaoImpl;
import com.dao.UserDao;
import com.dao.UserDaoImpl;
import com.entity.Photo;
import com.entity.User;
 
public class DengluServlet extends HttpServlet {  //��Ҫ�̳�HttpServlet  ����дdoGet  doPost����
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);  //����Ϣʹ��doPost����ִ��   ��Ӧjspҳ���е�form���е�method
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String name = request.getParameter("user"); //�õ�jspҳ�洫�����Ĳ���
		String pwd = request.getParameter("pswd"); //�õ�jspҳ�洫�����Ĳ���
		
		UserDao ud = new UserDaoImpl();
		HttpSession session = request.getSession();
		if(ud.login(name, pwd)){
			if(name.equals("root"))
			{
				UserDaoImpl userService = new UserDaoImpl();
				List<User> UserList = new ArrayList<User>();
	            UserList = userService.getUserAll();
	        request.setAttribute("UserList",UserList);				
			session.setAttribute("loginMsg","����Ա��½�ɹ�!");
			request.getRequestDispatcher("UserManagement.jsp").forward(request, response);//ת�����ɹ�ҳ��
		     
			}else {
				    PhotoDaoImpl photoService = new PhotoDaoImpl();
				    List<Photo> PhotoList = new ArrayList<Photo>();
			        PhotoList = photoService.getAllPhotos();
			        request.setAttribute("PhotoList",PhotoList);
				session.setAttribute("loginMsg","��ͨ�û���½�ɹ�!");
				request.setAttribute("user", name);
				request.getRequestDispatcher("SatellitePic.jsp").forward(request, response);
			}
		}
			else{
			session.setAttribute("loginMsg","�û������ڻ��������!");
			response.sendRedirect("login.jsp"); //�ض�����ҳ
			}
		
	}
 
}