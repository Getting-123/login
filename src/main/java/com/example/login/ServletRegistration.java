package com.example.login;

import Database.JDBCUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "ServletRegistration", value = "/ServletRegistration")
public class ServletRegistration extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");


        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String email=request.getParameter("email");

        if (Registration(username,password,email)){
           response.sendRedirect("login.jsp");
        }else {
            response.sendRedirect("Registration_failure.jsp");
        }
    }

    private boolean Registration(String username, String password, String email){
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        if (username == null || password == null ||email==null){
            return false;
        }

        try{
            conn = JDBCUtils.getConnection();
            //定义sql
            String sql = "INSERT INTO `user` (`name`, `password`, `email`) VALUES (?,?,?)";
            //获取sql对象
            pstmt = conn.prepareStatement(sql);
            //给？赋值
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            pstmt.setString(3,email);
            //执行sql查询
            int cout=pstmt.executeUpdate();

            if (cout>0)
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
