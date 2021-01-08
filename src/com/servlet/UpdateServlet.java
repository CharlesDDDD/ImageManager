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
import com.dao.PhotoDaoImpl;
import com.entity.Photo;
import com.util.DBconn;
 
public class UpdateServlet extends HttpServlet {  //��Ҫ�̳�HttpServlet  ����дdoGet  doPost����
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);  //����Ϣʹ��doPost����ִ��   ��Ӧjspҳ���е�form���е�method
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String name = request.getParameter("sat_name");
		String country = request.getParameter("sat_nationality");
		String resolution = request.getParameter("sat_resolution");
		String lalg = request.getParameter("sat_latitude_longtitude");
		String actime = request.getParameter("sat_actime");//�õ�jspҳ�洫�����Ĳ���
		String user = request.getParameter("username");
		System.out.println(name);
		PhotoDaoImpl photoDaoImpl = new PhotoDaoImpl();
		boolean a = photoDaoImpl.update(name, country, resolution, lalg, actime);
		HttpSession session = request.getSession();	
		if(a==true)
		{
			//������־
        	SimpleDateFormat sdf = new SimpleDateFormat();// ��ʽ��ʱ�� 
            sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// aΪam/pm�ı��  
            Date date = new Date();// ��ȡ��ǰʱ��
            String time = sdf.format(date).toString();
        	LogDaoImpl logDaoImpl = new LogDaoImpl();
        	logDaoImpl.add(user,"����", time, "����ͼƬ", name);
        	
			PhotoDaoImpl photoService = new PhotoDaoImpl();
		    List<Photo> PhotoList = new ArrayList<Photo>();
	        PhotoList = photoService.getAllPhotos();
	        request.setAttribute("user",user);
	        request.setAttribute("PhotoList",PhotoList);
			session.setAttribute("operationMsg","ͼ����Ϣ���³ɹ�!");
			request.getRequestDispatcher("SatellitePic.jsp").forward(request, response);
			}
		else {
			PhotoDaoImpl photoService = new PhotoDaoImpl();
		    List<Photo> PhotoList = new ArrayList<Photo>();
	        PhotoList = photoService.getAllPhotos();
	        request.setAttribute("user",user);
	        request.setAttribute("PhotoList",PhotoList);
			session.setAttribute("operationMsg","ͼ����Ϣ����ʧ�ܣ�����ȷ����ͼƬ��!" );
			request.getRequestDispatcher("SatellitePic.jsp").forward(request, response);
		}
		DBconn.closeConn();
		
	}
 
}