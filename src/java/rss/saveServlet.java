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
public class saveServlet extends HttpServlet {

    rssModel rss= null; 
        
    @Override
    public void init() {
        rss = new rssModel();
    }
    
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username=request.getParameter("username");
        String rssfeed=request.getParameter("rssfeed");
        //String savedfeed=request.getParameter("savedfeed");
        StringBuilder html = new StringBuilder();
        String sql="INSERT INTO IS2560.WEB_INFO (USERNAME, RSS) VALUES ('"+username+"', '"+rssfeed+"')";
        String sql1="select rss from Web_Info where username='"+username+"'";
        String sql2="select rss from Web_Info where username='"+username+"' and rss='"+rssfeed+"' ";
        String sql3="delete from Web_Info where username='"+username+"' and rss='"+rssfeed+"'";
//        String sql4="delete from Web_Info where username='"+username+"' and rss='"+savedfeed+"'";
//        String sql5="INSERT INTO IS2560.WEB_INFO (USERNAME, RSS) VALUES ('"+username+"', '"+savedfeed+"')";
//        String sql6="select rss from Web_Info where username='"+username+"'and rss='"+savedfeed+"'";
        String url =  "jdbc:derby://localhost:1527/KaimingZheng";
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;

                
            String result=html.toString();
            response.getWriter().print(result);
    }
    
    
      
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>


}
