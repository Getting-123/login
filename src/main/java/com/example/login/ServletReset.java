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

@WebServlet(name = "ServletReset", value = "/ServletReset")
public class ServletReset extends HttpServlet {

    String email=null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session=request.getSession();

        String username=request.getParameter("username");


        if(Reset(username)!=null){
            session.setAttribute("ResetUsername",username);
            response.sendRedirect("ResetUpdate.jsp");
        }else {
            response.sendRedirect("Reset_failure.jsp");
        }



    }

    private String Reset(String username){
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        if (username == null ){
            return null;
        }

        try {
            conn = JDBCUtils.getConnection();
            //定义sql
            String sql = "select name,email from user where name = ?";
            //获取sql对象
            pstmt = conn.prepareStatement(sql);
            //给？赋值
            pstmt.setString(1,username);
            //执行sql查询
            rs = pstmt.executeQuery();

            if(rs.next()){
                return rs.getString("email");
            }else {
                return null;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //释放资源
            JDBCUtils.Close(conn);
        }
       return null;
    }

}

