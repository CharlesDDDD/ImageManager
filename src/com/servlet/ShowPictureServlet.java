package com.servlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.PhotoDaoImpl;
import com.entity.Photo;
import com.sun.net.httpserver.Authenticator.Result;
public class ShowPictureServlet extends HttpServlet {

    private PhotoDaoImpl photoService = new PhotoDaoImpl();

    @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String user = request.getParameter("username");
    	List<Photo> PhotoList = new ArrayList<Photo>();
        PhotoList = photoService.getAllPhotos();
        request.setAttribute("PhotoList",PhotoList);
        request.setAttribute("user",user);
        request.getRequestDispatcher("SatellitePic.jsp").forward(request, response);
      //  response.sendRedirect("/page/MainContent.jsp");
   }

   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       doGet(request,response);
   }
}