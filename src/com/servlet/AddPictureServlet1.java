package com.servlet;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;

import com.dao.LogDaoImpl;
import com.dao.Photo1Dao;
import com.dao.Photo1DaoImpl;
import com.dao.p1;
import com.entity.Photo1;


public class AddPictureServlet1 extends HttpServlet {
    public AddPictureServlet1() {
        super();
    }
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
 String method = request.getParameter("method");
try {
    switch (method) {
                        case "add":
        //转接给相对应的函数去处理相关数据。
        addDVDinfo(request, response);
        break;}
    
             } catch (SQLException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();}
    }
/**
     * <p>
     *  这个函数实现了增加dvd的功能
     * <p>
     * @throws SQLException 
     * @throws ServletException 
     */
    private static void addDVDinfo(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        List<String> list=new ArrayList<String>();
        String filename=p1.getPhotoNewName();
        ServletContext servletContext=null;
        servletContext=request.getSession().getServletContext();
        //数据库中存储格式:/webTest/imgs/***.jpg
        //第一步:获取页面上上传的图片资源
        List<FileItem> items=p1.getRequsetFileItems(request,servletContext);
        String isLoadToSQL;
        
		/*
		 * Date date = new Date(); DateFormat format = new
		 * SimpleDateFormat("yyyy-MM-dd"); String currentData = format.format(date);
		 * String name = request.getParameter("name");
		 */
        Photo1 photo = new Photo1();
        
        for(FileItem item:items) {
            if(!item.isFormField()){
                //判断后缀名是否是jpg
                if(p1.isGif(item)) {
                    isLoadToSQL=p1.saveFile(item,filename);
                    System.out.println(isLoadToSQL);
                }else {
                    System.out.println("后缀格式有误，保存文件失败");
                }
            }
            else { 
                /*获取表单中的非文件值
                表单中的空间name值*/
                System.out.println("name值:  "+item.getFieldName());
                /*该name值空间中的value值*/
                System.out.println(item.getString("UTF-8"));
                list.add(item.getString("UTF-8")); 
            }
        }
        String fileaddress1 = "pic/landscapepic/"+filename;
        String name = list.get(0);
        String country = list.get(1);
        String location = list.get(2);
        String lalg = list.get(3);
        String actime = list.get(4);
        String user = list.get(5);//记录操作者 
        photo.setSrc(fileaddress1);
        photo.setName(name);
        photo.setCountry(country);
        photo.setLocation(location);
        photo.setLatitude_longitude(lalg);
        photo.setAc_time(actime);
        Photo1Dao ph = new Photo1DaoImpl();
        HttpSession session = request.getSession();
        Boolean x = ph.add(photo);
        if(x==true) {
        	//增加日志
        	SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间 
            sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// a为am/pm的标记  
            Date date = new Date();// 获取当前时间
            String time = sdf.format(date).toString();
        	LogDaoImpl tempDaoImpl = new LogDaoImpl();
        	tempDaoImpl.add(user,"增加", time, "风景图片", name);
        	//得到所有图片
        	Photo1DaoImpl photoService = new Photo1DaoImpl();
		    List<Photo1> PhotoList = new ArrayList<Photo1>();
	        PhotoList = photoService.getAllPhotos();
	        request.setAttribute("PhotoList",PhotoList);
	        request.setAttribute("user", user);
        	session.setAttribute("operationMsg", "图片增加成功!");
        	request.getRequestDispatcher("LandscapePic.jsp").forward(request, response);
        }
        else {
        	Photo1DaoImpl photoService = new Photo1DaoImpl();
		    List<Photo1> PhotoList = new ArrayList<Photo1>();
	        PhotoList = photoService.getAllPhotos();
	        request.setAttribute("PhotoList",PhotoList);
	        request.setAttribute("user", user);
        	session.setAttribute("operationMsg", "图片增加失败，已存在同名图片!");
        	request.getRequestDispatcher("LandscapePic.jsp").forward(request, response);
		}
    }
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}