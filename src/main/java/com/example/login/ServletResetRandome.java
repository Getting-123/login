package com.example.login;

import Database.JDBCUtils;
import method.Eamil;
import method.random;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "ServletResetRandome", value = "/ServletResetRandome")
public class ServletResetRandome extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session=request.getSession();
        random r=new random();
        String random=r.getRandomNumber();

        String username= (String) session.getAttribute("ResetUsername");

        session.setAttribute("randomreset",random);

        random(username,random);

        response.sendRedirect("ResetUpdate.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }
    public void random(String username,String random){
        Eamil eamil=new Eamil();


        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;


        conn= JDBCUtils.getConnection();
        try {
            pstmt= conn.prepareStatement("select email from user where name=?");
            pstmt.setString(1,username);

            rs = pstmt.executeQuery();

            while (rs.next()){

                System.out.println(rs.getString("email"));
                eamil.SendEamil(rs.getString("email"),"重置验证码","您的验证码为:"+random);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
