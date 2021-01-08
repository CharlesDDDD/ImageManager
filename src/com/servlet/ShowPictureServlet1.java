package com.servlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.Photo1DaoImpl;
import com.entity.Photo1;
public class ShowPictureServlet1 extends HttpServlet {

    private Photo1DaoImpl photoService = new Photo1DaoImpl();

    @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String user = request.getParameter("username");
    	List<Photo1> PhotoList = new ArrayList<Photo1>();
        PhotoList = photoService.getAllPhotos();
        request.setAttribute("PhotoList",PhotoList);
        request.setAttribute("user",user);
        request.getRequestDispatcher("LandscapePic.jsp").forward(request, response);
      //  response.sendRedirect("/page/MainContent.jsp");
   }

   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       doGet(request,response);
   }
}