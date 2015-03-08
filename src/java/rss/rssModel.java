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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author zhengkaiming
 */
public class rssModel  {
   
   
   /**
    * use to output the favourite rss feeds of the user in the web page
    * @param rs
    * @return 
    */
    public String GetHtml(ResultSet rs)
        {
            StringBuilder html = new StringBuilder();
            try
            {
                if(rs.next()==false){
                    html.append("<optgroup label=\"RSS Feed\">");
                    html.append("<option value='0'>");
                    html.append("None");
                    html.append("</option>");
                }

                else
                {
                    html.append("<optgroup label=\"RSS Feed\" >");
                    html.append("RSS Feed");
                    html.append("<option value='0'>");
                    html.append("Choose Your Favourite Feed");
                    html.append("</option>");



                   do
                    {
                          html.append("<option value=").append(rs.getString(1)).append(">");
                          html.append(rs.getString(1));
                          html.append("</option>");             
                    }while(rs.next());

                }

            }
            catch(SQLException ex)
            {
                   while (ex != null)
                   {
                        html.append(ex.toString());
                        ex = ex.getNextException();     
                    }
            }


            return html.toString(); 
        }
}
