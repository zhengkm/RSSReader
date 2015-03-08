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
public class newuser extends HttpServlet {

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     * create a new user account
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /**
         * create a new user account
         */
        StringBuilder html = new StringBuilder();
        String getid="select max(user_id) from User_Info";//get the latest id 
        String sql1="";
        String sql2="";
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String city=request.getParameter("city");
        String gender=request.getParameter("gender");

        String hasexisted="select * from User_Detail where username='"+username+"'";

        String url  = "jdbc:derby://localhost:1527/KaimingZheng";
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;

            try
            {
                con = DriverManager.getConnection(url, "IS2560", "IS2560");
                stat = con.createStatement();
                rs=stat.executeQuery(hasexisted);
                
                if(rs.next()==true)
                {
                    html.append("You have registered! ");
                }
                else
                {
                    String id="";
                    rs=stat.executeQuery(getid);

                    if(rs.next()){
                    int user_id=Integer.valueOf(rs.getString(1))+1;
                    id = String.valueOf(user_id);
                    }
                    else{
                        int user_id = 1;
                        id=String.valueOf(user_id);
                    }

                    sql1="insert into User_Detail VALUES('"+username+"','"+password+"','"+city+"','"+gender+"')";
                    sql2 = "insert into User_Info VALUES('"+id+"','"+username+"','"+password+"')";

                    //insert the new user information into "User_Detail" and "User_Info"
                    int updateresult1=stat.executeUpdate(sql1);
                    int updateresult2=stat.executeUpdate(sql2);                
                    if(updateresult1>0 && updateresult2>0)
                    {
                        html.append("You have became our new USER! ");
                        
                    }
                    else
                    { // if failed , may be some information you input is wrong
                        html.append("Sorry, there are something wrong!");
                    }
                    
                    rs.close();
                    stat.close();
                    con.close();
                }
            }
            catch (SQLException ex){
                while (ex != null){
                        html.append("1");
                        System.out.println(ex);
                        ex = ex.getNextException();
                }
            }
            
                String result=html.toString();
                response.getWriter().print(result);
        
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
