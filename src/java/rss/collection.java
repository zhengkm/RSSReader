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

/**
 *
 * @author zhengkaiming
 */
public class collection extends HttpServlet {

    rssModel rss= null; 
        
    @Override
    public void init() {
        rss = new rssModel();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //get the value of "username"
        String username=request.getParameter("username");
        StringBuilder html = new StringBuilder();
        
        String sql="select rss from Web_Info where username='"+username+"'";      
        String url =  "jdbc:derby://localhost:1527/KaimingZheng";//"jdbc:mysql://localhost:8889/web";  // 数据库只能小写！！！
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;

            try
            {  
                /**
                 * start the database and get the information stored in it
                 */
                con = DriverManager.getConnection(url, "IS2560", "IS2560");
                stat = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,
                          ResultSet.CONCUR_READ_ONLY);
                rs = stat.executeQuery(sql);
                
                html.append(rss.GetHtml(rs));//the information should be used to show in the web
                rs.close();
                stat.close();
                con.close();
            }
            catch (SQLException ex){
                while (ex != null){
                        html.append(ex.toString());
                        ex = ex.getNextException();
                }
            }
            
            String result=html.toString();
            response.getWriter().print(result);//send the result to front-end
      }
    
        //set the method GetHtml
       

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
