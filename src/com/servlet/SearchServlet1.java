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
 
public class SearchServlet1 extends HttpServlet {  //需要继承HttpServlet  并重写doGet  doPost方法
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);  //将信息使用doPost方法执行   对应jsp页面中的form表单中的method
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String name = request.getParameter("ls_name");
		String country = request.getParameter("ls_nationality");
		String location = request.getParameter("ls_location");
		String lalg = request.getParameter("ls_latitude_longtitude");
		String actime = request.getParameter("ls_actime");//得到jsp页面传过来的参数
		String user = request.getParameter("username");
		if(name==null)
			name = "";
		if(country==null)
			country = "";
		if(location==null)
			location = "";
		if(lalg==null)
			lalg = "";
		if(actime==null)
			actime = "";
		System.out.println(name);
		String sql ="select * from j2ee_landscape_pic where name like '%" + name + "%'" + " and"
				+ " country like '%" + country + "%' and"
				+ " location like '%" + location + "%' and"
				+ " latitude_longtitude like '%" + lalg + "%' and"
				+ " ac_time like '%" + actime + "%'";
		System.out.println(sql);
		List<Photo1> list = new ArrayList <Photo1>();
		Photo1DaoImpl photoDaoImpl = new Photo1DaoImpl();
		list = photoDaoImpl.search(sql);   	
    	
    	HttpSession session = request.getSession();
    	boolean flag = list.isEmpty();
    	System.out.println(list.isEmpty());
    	if(flag==false)
    	{
			/*
			 * for(Photo a:list) { System.out.println(a.getSrc());
			 * System.out.println(a.getName()); System.out.println(a.getCountry());
			 * System.out.println(a.getResolution());
			 * System.out.println(a.getLatitude_longitude());
			 * System.out.println(a.getAc_time()); }
			 */
    		//增加日志
        	SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间 
            sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// a为am/pm的标记  
            Date date = new Date();// 获取当前时间
            String time = sdf.format(date).toString();
        	LogDaoImpl logDaoImpl = new LogDaoImpl();
        	logDaoImpl.add(user,"查询", time, "风景图片", name);

 	        request.setAttribute("user",user);
    		request.setAttribute("PhotoList",list);
    		session.setAttribute("operationMsg", "查询成功!");
    		request.getRequestDispatcher("LandscapePic.jsp").forward(request, response);
    	}
    	else {
 	        request.setAttribute("user",user);
    		request.setAttribute("PhotoList",list);
    		session.setAttribute("operationMsg", "没有符合条件的图片!");
    		request.getRequestDispatcher("LandscapePic.jsp").forward(request, response);
		}
		  
		 
		
	}
 
}