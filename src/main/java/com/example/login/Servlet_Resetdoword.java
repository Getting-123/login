package com.example.login;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Servlet_Resetdoword", value = "/Servlet_Resetdoword")
public class Servlet_Resetdoword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session=request.getSession();
        String a= (String) session.getAttribute("randomreset");
        String b=request.getParameter("random");
        if(a.equals(b)){
            response.sendRedirect("RetrievePassword.jsp");
        }else {
            response.sendRedirect("Reset_failure.jsp");
        }


    }
}
