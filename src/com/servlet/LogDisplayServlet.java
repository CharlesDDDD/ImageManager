package com.servlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.LogDaoImpl;
import com.entity.Log;
public class LogDisplayServlet extends HttpServlet {

    private LogDaoImpl logservice = new LogDaoImpl();

    @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Log> LogList = new ArrayList<Log>();
            LogList = logservice.getLogAll();
        request.setAttribute("LogList",LogList);
        request.getRequestDispatcher("UserLog.jsp").forward(request, response);
      //  response.sendRedirect("/page/MainContent.jsp");
   }

   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       doGet(request,response);
   }
}