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

@WebServlet(name = "Servletupdate", value = "/Servletupdate")
public class Servletupdate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session=request.getSession();
        String username= (String) session.getAttribute("ResetUsername");
        String password=request.getParameter("password");


        if ( update(password,username)){
            response.sendRedirect("login.jsp");
        }else {
            response.sendRedirect("Reset_failure.jsp");
        }



    }

    private boolean update(String password,String username){
        Connection conn = null;
        PreparedStatement pstmt = null;
        if (password == null){
            return false;
        }

        try {
            conn = JDBCUtils.getConnection();
            //定义sql
            String sql = "UPDATE `user` SET `password` = ? WHERE  user.name=? ";
            //获取sql对象
            pstmt = conn.prepareStatement(sql);
            //给？赋值
            pstmt.setString(1,password);
            pstmt.setString(2,username);
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


