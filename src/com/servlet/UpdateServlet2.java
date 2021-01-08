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
 
public class UpdateServlet2 extends HttpServlet {  //需要继承HttpServlet  并重写doGet  doPost方法
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);  //将信息使用doPost方法执行   对应jsp页面中的form表单中的method
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String name = request.getParameter("fig_name");
		String figname = request.getParameter("fig_figname");
		String country = request.getParameter("fig_country");
		String resolution = request.getParameter("fig_resolution");
		String actime = request.getParameter("fig_actime");//得到jsp页面传过来的参数
		String user = request.getParameter("username");
		System.out.println(name);
		Photo2DaoImpl photoDaoImpl = new Photo2DaoImpl();
		boolean a = photoDaoImpl.update(name, figname, country, resolution, actime);
		HttpSession session = request.getSession();	
		if(a==true)
		{
			//增加日志
        	SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间 
            sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// a为am/pm的标记  
            Date date = new Date();// 获取当前时间
            String time = sdf.format(date).toString();
        	LogDaoImpl logDaoImpl = new LogDaoImpl();
        	logDaoImpl.add(user,"更新", time, "人物图片", name);
        	
			Photo2DaoImpl photoService = new Photo2DaoImpl();
		    List<Photo2> PhotoList = new ArrayList<Photo2>();
	        PhotoList = photoService.getAllPhotos();
	        request.setAttribute("user",user);
	        request.setAttribute("PhotoList",PhotoList);
			session.setAttribute("operationMsg","图像信息更新成功!");
			request.getRequestDispatcher("FigurePic.jsp").forward(request, response);
			}
		else {
			Photo2DaoImpl photoService = new Photo2DaoImpl();
		    List<Photo2> PhotoList = new ArrayList<Photo2>();
	        PhotoList = photoService.getAllPhotos();
	        request.setAttribute("user",user);
	        request.setAttribute("PhotoList",PhotoList);
			session.setAttribute("operationMsg","图像信息更新失败，请正确输入图片名!" );
			request.getRequestDispatcher("FigurePic.jsp").forward(request, response);
		}
		DBconn.closeConn();
		
	}
 
}