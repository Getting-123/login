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

@WebServlet(name = "Servletlogin", value = "/Servletlogin")
public class Servletlogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String username=request.getParameter("username");
        String password=request.getParameter("password");

        HttpSession session=request.getSession();
        session.setAttribute("username",username);
        session.setAttribute("password",password);


        if(login2(username,password)){
            response.sendRedirect("random.jsp");
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
