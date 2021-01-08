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
import com.entity.Photo2;
import com.util.DBconn;
 
public class UpdateServlet2 extends HttpServlet {  //��Ҫ�̳�HttpServlet  ����дdoGet  doPost����
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);  //����Ϣʹ��doPost����ִ��   ��Ӧjspҳ���е�form���е�method
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String name = request.getParameter("fig_name");
		String figname = request.getParameter("fig_figname");
		String country = request.getParameter("fig_country");
		String resolution = request.getParameter("fig_resolution");
		String actime = request.getParameter("fig_actime");//�õ�jspҳ�洫�����Ĳ���
		String user = request.getParameter("username");
		System.out.println(name);
		Photo2DaoImpl photoDaoImpl = new Photo2DaoImpl();
		boolean a = photoDaoImpl.update(name, figname, country, resolution, actime);
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
        	
			Photo2DaoImpl photoService = new Photo2DaoImpl();
		    List<Photo2> PhotoList = new ArrayList<Photo2>();
	        PhotoList = photoService.getAllPhotos();
	        request.setAttribute("user",user);
	        request.setAttribute("PhotoList",PhotoList);
			session.setAttribute("operationMsg","ͼ����Ϣ���³ɹ�!");
			request.getRequestDispatcher("FigurePic.jsp").forward(request, response);
			}
		else {
			Photo2DaoImpl photoService = new Photo2DaoImpl();
		    List<Photo2> PhotoList = new ArrayList<Photo2>();
	        PhotoList = photoService.getAllPhotos();
	        request.setAttribute("user",user);
	        request.setAttribute("PhotoList",PhotoList);
			session.setAttribute("operationMsg","ͼ����Ϣ����ʧ�ܣ�����ȷ����ͼƬ��!" );
			request.getRequestDispatcher("FigurePic.jsp").forward(request, response);
		}
		DBconn.closeConn();
		
	}
 
}