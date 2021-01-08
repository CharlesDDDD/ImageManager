package com.servlet;
 
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDao;
import com.dao.UserDaoImpl;
import com.entity.User;
 
public class ZhuceServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String name = request.getParameter("register_user"); //��ȡjspҳ�洫�����Ĳ���
		String pwd = request.getParameter("register_pswd");
		
		User user = new User(); //ʵ����һ��������װ����
		user.setName(name);
		user.setPwd(pwd);		
		UserDao ud = new UserDaoImpl();
		
		if(ud.register(user)){
			request.setAttribute("username", name);  //��request���з��ò���
			//request.setAttribute("xiaoxi", "ע��ɹ�");
			request.getRequestDispatcher("login.jsp").forward(request, response);  //ת������¼ҳ��
		}else{
			
			response.sendRedirect("login.jsp");//�ض�����ҳ
		}
	}
}