package com.servlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDaoImpl;
import com.entity.User;
public class GetUserServlet extends HttpServlet {

    private UserDaoImpl userService = new UserDaoImpl();

    @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<User> UserList = new ArrayList<User>();
            UserList = userService.getUserAll();
        request.setAttribute("UserList",UserList);
        request.getRequestDispatcher("UserManagement.jsp").forward(request, response);
      //  response.sendRedirect("/page/MainContent.jsp");
   }

   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       doGet(request,response);
   }
}