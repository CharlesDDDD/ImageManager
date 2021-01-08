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
 
public class DengluServlet extends HttpServlet {  //需要继承HttpServlet  并重写doGet  doPost方法
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);  //将信息使用doPost方法执行   对应jsp页面中的form表单中的method
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String name = request.getParameter("user"); //得到jsp页面传过来的参数
		String pwd = request.getParameter("pswd"); //得到jsp页面传过来的参数
		
		UserDao ud = new UserDaoImpl();
		HttpSession session = request.getSession();
		if(ud.login(name, pwd)){
			if(name.equals("root"))
			{
				UserDaoImpl userService = new UserDaoImpl();
				List<User> UserList = new ArrayList<User>();
	            UserList = userService.getUserAll();
	        request.setAttribute("UserList",UserList);				
			session.setAttribute("loginMsg","管理员登陆成功!");
			request.getRequestDispatcher("UserManagement.jsp").forward(request, response);//转发到成功页面
		     
			}else {
				    PhotoDaoImpl photoService = new PhotoDaoImpl();
				    List<Photo> PhotoList = new ArrayList<Photo>();
			        PhotoList = photoService.getAllPhotos();
			        request.setAttribute("PhotoList",PhotoList);
				session.setAttribute("loginMsg","普通用户登陆成功!");
				request.setAttribute("user", name);
				request.getRequestDispatcher("SatellitePic.jsp").forward(request, response);
			}
		}
			else{
			session.setAttribute("loginMsg","用户不存在或密码错误!");
			response.sendRedirect("login.jsp"); //重定向到首页
			}
		
	}
 
}