package com.servlet;
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDaoImpl;
import com.entity.User;
 
public class DeleteUserServlet extends HttpServlet {  //��Ҫ�̳�HttpServlet  ����дdoGet  doPost����
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);  //����Ϣʹ��doPost����ִ��   ��Ӧjspҳ���е�form���е�method
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String name = request.getParameter("name"); //�õ�jspҳ�洫�����Ĳ���
		System.out.println(name);
		UserDaoImpl tempDaoImpl = new UserDaoImpl();
		boolean i = tempDaoImpl.delete(name);
		System.out.print(i);
		if(i==true)
		{
			UserDaoImpl userService = new UserDaoImpl();
			List<User> UserList = new ArrayList<User>();
            UserList = userService.getUserAll();
        request.setAttribute("UserList",UserList);	
			session.setAttribute("loginMsg","ɾ���ɹ�!");
			request.getRequestDispatcher("UserManagement.jsp").forward(request, response);
		}
		else {
			UserDaoImpl userService = new UserDaoImpl();
			List<User> UserList = new ArrayList<User>();
            UserList = userService.getUserAll();
        request.setAttribute("UserList",UserList);	
			session.setAttribute("loginMsg","ɾ��ʧ��!");
			request.getRequestDispatcher("UserManagement.jsp").forward(request, response);
		}
		
	}
 
}