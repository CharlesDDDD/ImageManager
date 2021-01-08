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
 
public class DeleteUserServlet extends HttpServlet {  //需要继承HttpServlet  并重写doGet  doPost方法
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);  //将信息使用doPost方法执行   对应jsp页面中的form表单中的method
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String name = request.getParameter("name"); //得到jsp页面传过来的参数
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
			session.setAttribute("loginMsg","删除成功!");
			request.getRequestDispatcher("UserManagement.jsp").forward(request, response);
		}
		else {
			UserDaoImpl userService = new UserDaoImpl();
			List<User> UserList = new ArrayList<User>();
            UserList = userService.getUserAll();
        request.setAttribute("UserList",UserList);	
			session.setAttribute("loginMsg","删除失败!");
			request.getRequestDispatcher("UserManagement.jsp").forward(request, response);
		}
		
	}
 
}