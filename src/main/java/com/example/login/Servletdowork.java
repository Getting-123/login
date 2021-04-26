package com.example.login;

import Database.JDBCUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "Servletdowork", value = "/Servletdowork")
public class Servletdowork extends HttpServlet {
    Connection connection=null;

    ResultSet rs = null;

    PreparedStatement pstmt = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session=request.getSession();

        String urlrandom=request.getParameter("random");



        String username= (String) session.getAttribute("username");
        String password=(String) session.getAttribute("password");
        String random= (String) session.getAttribute("random");

        System.out.println(random.equals(urlrandom));


      if(login2(username,password) && random.equals(urlrandom)){
          response.sendRedirect("login_success.jsp");
      }else {
          response.sendRedirect("login_failure.jsp");
      }


    }

    public boolean login2(String username,String password){
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        if (username == null || password == null){
            return false;
        }

        try {
            conn = JDBCUtils.getConnection();
            //定义sql
            String sql = "select name,password from user where name = ? and password = ?";
            //获取sql对象
            pstmt = conn.prepareStatement(sql);
            //给？赋值
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            //执行sql查询
            rs = pstmt.executeQuery();
            System.out.println(username+password);

            if (rs.next())
                return true;
            else
                return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //释放资源
            JDBCUtils.Close(conn);
        }
        return false;
    }

}
