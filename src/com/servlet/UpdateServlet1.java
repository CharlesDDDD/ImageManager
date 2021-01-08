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
import com.dao.Photo1DaoImpl;
import com.entity.Photo1;
import com.util.DBconn;
 
public class UpdateServlet1 extends HttpServlet {  //��Ҫ�̳�HttpServlet  ����дdoGet  doPost����
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);  //����Ϣʹ��doPost����ִ��   ��Ӧjspҳ���е�form���е�method
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String name = request.getParameter("ls_name");
		String country = request.getParameter("ls_nationality");
		String location = request.getParameter("ls_location");
		String lalg = request.getParameter("ls_latitude_longtitude");
		String actime = request.getParameter("ls_actime");//�õ�jspҳ�洫�����Ĳ���
		String user = request.getParameter("username");
		System.out.println(name);
		Photo1DaoImpl photoDaoImpl = new Photo1DaoImpl();
		boolean a = photoDaoImpl.update(name, country, location, lalg, actime);
		HttpSession session = request.getSession();	
		if(a==true)
		{
			//������־
        	SimpleDateFormat sdf = new SimpleDateFormat();// ��ʽ��ʱ�� 
            sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// aΪam/pm�ı��  
            Date date = new Date();// ��ȡ��ǰʱ��
            String time = sdf.format(date).toString();
        	LogDaoImpl logDaoImpl = new LogDaoImpl();
        	logDaoImpl.add(user,"����", time, "�羰ͼƬ", name);
        	
			Photo1DaoImpl photoService = new Photo1DaoImpl();
		    List<Photo1> PhotoList = new ArrayList<Photo1>();
	        PhotoList = photoService.getAllPhotos();
	        request.setAttribute("user",user);
	        request.setAttribute("PhotoList",PhotoList);
			session.setAttribute("operationMsg","ͼ����Ϣ���³ɹ�!");
			request.getRequestDispatcher("LandscapePic.jsp").forward(request, response);
			}
		else {
			Photo1DaoImpl photoService = new Photo1DaoImpl();
		    List<Photo1> PhotoList = new ArrayList<Photo1>();
	        PhotoList = photoService.getAllPhotos();
	        request.setAttribute("user",user);
	        request.setAttribute("PhotoList",PhotoList);
			session.setAttribute("operationMsg","ͼ����Ϣ����ʧ�ܣ�����ȷ����ͼƬ��!" );
			request.getRequestDispatcher("LandscapePic.jsp").forward(request, response);
		}
		DBconn.closeConn();
		
	}
 
}