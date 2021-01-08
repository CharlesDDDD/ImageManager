package com.servlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.Photo2DaoImpl;
import com.entity.Photo2;
public class ShowPictureServlet2 extends HttpServlet {

    private Photo2DaoImpl photoService = new Photo2DaoImpl();

    @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String user = request.getParameter("username");
    	List<Photo2> PhotoList = new ArrayList<Photo2>();
        PhotoList = photoService.getAllPhotos();
        request.setAttribute("PhotoList",PhotoList);
        request.setAttribute("user",user);
        request.getRequestDispatcher("FigurePic.jsp").forward(request, response);
      //  response.sendRedirect("/page/MainContent.jsp");
   }

   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       doGet(request,response);
   }
}