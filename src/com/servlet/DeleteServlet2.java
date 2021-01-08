package com.servlet;
 
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.LogDaoImpl;
import com.dao.Photo2DaoImpl;
import com.dao.UserDaoImpl;
import com.entity.Photo2;
 
public class DeleteServlet2 extends HttpServlet {  //��Ҫ�̳�HttpServlet  ����дdoGet  doPost����
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);  //����Ϣʹ��doPost����ִ��   ��Ӧjspҳ���е�form���е�method
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String name = request.getParameter("fig_name"); //�õ�jspҳ�洫�����Ĳ���
		String user = request.getParameter("username");
		System.out.println(name);
		Photo2DaoImpl tempDaoImpl = new Photo2DaoImpl();
		boolean i = tempDaoImpl.delete(name);
		System.out.print(i);
		if(i==true)
		{
			//������־
        	SimpleDateFormat sdf = new SimpleDateFormat();// ��ʽ��ʱ�� 
            sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// aΪam/pm�ı��  
            Date date = new Date();// ��ȡ��ǰʱ��
            String time = sdf.format(date).toString();
        	LogDaoImpl logDaoImpl = new LogDaoImpl();
        	logDaoImpl.add(user,"ɾ��", time, "����ͼƬ", name);
        	
			UserDaoImpl userService = new UserDaoImpl();
			Photo2DaoImpl photoService = new Photo2DaoImpl();
		    List<Photo2> PhotoList = new ArrayList<Photo2>();
	        PhotoList = photoService.getAllPhotos();
	        request.setAttribute("PhotoList",PhotoList);
	        request.setAttribute("user",user);
			session.setAttribute("operationMsg","ɾ���ɹ�!");
			request.getRequestDispatcher("FigurePic.jsp").forward(request, response);
		}
		else {
			Photo2DaoImpl photoService = new Photo2DaoImpl();
		    List<Photo2> PhotoList = new ArrayList<Photo2>();
	        PhotoList = photoService.getAllPhotos();
	        request.setAttribute("PhotoList",PhotoList);
	        request.setAttribute("user", user);
			session.setAttribute("operationMsg","ɾ��ʧ�ܣ���������ȷ������!");
			request.getRequestDispatcher("FigruePic.jsp").forward(request, response);
		}
		
	}
 
}