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
import com.dao.UserDaoImpl;
import com.entity.Photo;
 
public class DeleteServlet extends HttpServlet {  //��Ҫ�̳�HttpServlet  ����дdoGet  doPost����
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);  //����Ϣʹ��doPost����ִ��   ��Ӧjspҳ���е�form���е�method
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String name = request.getParameter("sat_name"); //�õ�jspҳ�洫�����Ĳ���
		String user = request.getParameter("username");
		System.out.println(name);
		PhotoDaoImpl tempDaoImpl = new PhotoDaoImpl();
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
			PhotoDaoImpl photoService = new PhotoDaoImpl();
		    List<Photo> PhotoList = new ArrayList<Photo>();
	        PhotoList = photoService.getAllPhotos();
	        request.setAttribute("PhotoList",PhotoList);
	        request.setAttribute("user",user);
			session.setAttribute("operationMsg","ɾ���ɹ�!");
			request.getRequestDispatcher("SatellitePic.jsp").forward(request, response);
		}
		else {
			PhotoDaoImpl photoService = new PhotoDaoImpl();
		    List<Photo> PhotoList = new ArrayList<Photo>();
	        PhotoList = photoService.getAllPhotos();
	        request.setAttribute("PhotoList",PhotoList);
	        request.setAttribute("user", user);
			session.setAttribute("operationMsg","ɾ��ʧ�ܣ���������ȷ������!");
			request.getRequestDispatcher("SatellitePic.jsp").forward(request, response);
		}
		
	}
 
}