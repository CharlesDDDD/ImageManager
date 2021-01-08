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
import com.dao.PhotoDao;
import com.dao.PhotoDaoImpl;
import com.dao.p;
import com.entity.Photo;


public class AddPictureServlet extends HttpServlet {
    public AddPictureServlet() {
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
        String filename=p.getPhotoNewName();
        ServletContext servletContext=null;
        servletContext=request.getSession().getServletContext();
        //���ݿ��д洢��ʽ:/webTest/imgs/***.jpg
        //��һ��:��ȡҳ�����ϴ���ͼƬ��Դ
        List<FileItem> items=p.getRequsetFileItems(request,servletContext);
        String isLoadToSQL;
        
		/*
		 * Date date = new Date(); DateFormat format = new
		 * SimpleDateFormat("yyyy-MM-dd"); String currentData = format.format(date);
		 * String name = request.getParameter("name");
		 */
        Photo photo = new Photo();
        
        for(FileItem item:items) {
            if(!item.isFormField()){
                //�жϺ�׺���Ƿ���jpg
                if(p.isGif(item)) {
                    isLoadToSQL=p.saveFile(item,filename);
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
        String fileaddress1 = "pic/satellitepic/"+filename;
        String name = list.get(0);
        String country = list.get(1);
        String resolution = list.get(2);
        String lalg = list.get(3);
        String actime = list.get(4);
        String user = list.get(5);//��¼������ 
        photo.setSrc(fileaddress1);
        photo.setName(name);
        photo.setCountry(country);
        photo.setResolution(resolution);
        photo.setLatitude_longitude(lalg);
        photo.setAc_time(actime);
        PhotoDao ph = new PhotoDaoImpl();
        HttpSession session = request.getSession();
        Boolean x = ph.add(photo);
        if(x==true) {
        	//������־
        	SimpleDateFormat sdf = new SimpleDateFormat();// ��ʽ��ʱ�� 
            sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// aΪam/pm�ı��  
            Date date = new Date();// ��ȡ��ǰʱ��
            String time = sdf.format(date).toString();
        	LogDaoImpl tempDaoImpl = new LogDaoImpl();
        	tempDaoImpl.add(user,"����", time, "����ͼƬ", name);
        	//�õ�����ͼƬ
        	PhotoDaoImpl photoService = new PhotoDaoImpl();
		    List<Photo> PhotoList = new ArrayList<Photo>();
	        PhotoList = photoService.getAllPhotos();
	        request.setAttribute("PhotoList",PhotoList);
	        request.setAttribute("user", user);
        	session.setAttribute("operationMsg", "ͼƬ���ӳɹ�!");
        	request.getRequestDispatcher("SatellitePic.jsp").forward(request, response);
        }
        else {
        	PhotoDaoImpl photoService = new PhotoDaoImpl();
		    List<Photo> PhotoList = new ArrayList<Photo>();
	        PhotoList = photoService.getAllPhotos();
	        request.setAttribute("PhotoList",PhotoList);
	        request.setAttribute("user", user);
        	session.setAttribute("operationMsg", "ͼƬ����ʧ�ܣ��Ѵ���ͬ��ͼƬ!");
        	request.getRequestDispatcher("SatellitePic.jsp").forward(request, response);
		}
    }
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}