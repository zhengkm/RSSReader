/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rss;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author zhengkaiming
 */
public class login extends HttpServlet {

    rssModel rss= null; 
        
    @Override
    public void init() {
        rss = new rssModel();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        StringBuilder html = new StringBuilder();
        String url = "jdbc:derby://localhost:1527/KaimingZheng";//"jdbc:mysql://localhost:8889/web";  // 数据库只能小写！！！
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        
        String sql = "select password from IS2560.USER_INFO where username='"+username+"'";
        
           try
            {
                
                con = DriverManager.getConnection(url, "IS2560", "IS2560");
                stat = con.createStatement();
                rs = stat.executeQuery(sql);
                
                if(!rs.next())
                    html.append("false");
                else if(!rs.getString(1).equals(password))
                    html.append("false");
                else
                {
                        session.setAttribute("username", username);
                        request.getSession().setAttribute("username", username);
                        //session.setAttribute("password", rs.getString(1));
                        System.out.println(request.getSession().getAttribute("username"));
                        html.append("0");

                }
                    
                    
                rs.close();
                stat.close();
                con.close();
            }
            catch (SQLException ex){
                while (ex != null){
                        html.append(ex);
                        ex = ex.getNextException();
                }
            }
            
            String result=html.toString();
            response.getWriter().print(result);
            //request.getRequestDispatcher("browse.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>


}
