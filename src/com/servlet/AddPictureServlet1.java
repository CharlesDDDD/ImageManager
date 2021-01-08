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
        //ת�Ӹ����Ӧ�ĺ���ȥ����������ݡ�
        addDVDinfo(request, response);
        break;}
    
             } catch (SQLException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();}
    }
/**
     * <p>
     *  �������ʵ��������dvd�Ĺ���
     * <p>
     * @throws SQLException 
     * @throws ServletException 
     */
    private static void addDVDinfo(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        List<String> list=new ArrayList<String>();
        String filename=p1.getPhotoNewName();
        ServletContext servletContext=null;
        servletContext=request.getSession().getServletContext();
        //���ݿ��д洢��ʽ:/webTest/imgs/***.jpg
        //��һ��:��ȡҳ�����ϴ���ͼƬ��Դ
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
                //�жϺ�׺���Ƿ���jpg
                if(p1.isGif(item)) {
                    isLoadToSQL=p1.saveFile(item,filename);
                    System.out.println(isLoadToSQL);
                }else {
                    System.out.println("��׺��ʽ���󣬱����ļ�ʧ��");
                }
            }
            else { 
                /*��ȡ���еķ��ļ�ֵ
                ���еĿռ�nameֵ*/
                System.out.println("nameֵ:  "+item.getFieldName());
                /*��nameֵ�ռ��е�valueֵ*/
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
        String user = list.get(5);//��¼������ 
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
        	//������־
        	SimpleDateFormat sdf = new SimpleDateFormat();// ��ʽ��ʱ�� 
            sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// aΪam/pm�ı��  
            Date date = new Date();// ��ȡ��ǰʱ��
            String time = sdf.format(date).toString();
        	LogDaoImpl tempDaoImpl = new LogDaoImpl();
        	tempDaoImpl.add(user,"����", time, "�羰ͼƬ", name);
        	//�õ�����ͼƬ
        	Photo1DaoImpl photoService = new Photo1DaoImpl();
		    List<Photo1> PhotoList = new ArrayList<Photo1>();
	        PhotoList = photoService.getAllPhotos();
	        request.setAttribute("PhotoList",PhotoList);
	        request.setAttribute("user", user);
        	session.setAttribute("operationMsg", "ͼƬ���ӳɹ�!");
        	request.getRequestDispatcher("LandscapePic.jsp").forward(request, response);
        }
        else {
        	Photo1DaoImpl photoService = new Photo1DaoImpl();
		    List<Photo1> PhotoList = new ArrayList<Photo1>();
	        PhotoList = photoService.getAllPhotos();
	        request.setAttribute("PhotoList",PhotoList);
	        request.setAttribute("user", user);
        	session.setAttribute("operationMsg", "ͼƬ����ʧ�ܣ��Ѵ���ͬ��ͼƬ!");
        	request.getRequestDispatcher("LandscapePic.jsp").forward(request, response);
		}
    }
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}