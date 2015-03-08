/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rss;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author kaimingzheng
 */
public class rssServlet extends HttpServlet {
    
    private Logger logger = Logger.getLogger(this.getClass());
	private RequestDispatcher homeJsp;
        private RequestDispatcher hJsp;
        
        @Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext context = config.getServletContext();
		homeJsp = context.getRequestDispatcher("/browse.jsp");
                hJsp = context.getRequestDispatcher("/index.jsp");
	}

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, IllegalArgumentException, FeedException {
        logger.debug("Retrieving yahoo news feed");
        String rs;
        
        rs = request.getParameter("rs");//get the string of rss feed from front-end
        
        //if user not input the feed, it will refresh the web
        if(rs==""){
             homeJsp.forward(request, response);
        }
        
        else
        {
        
        URL url = new URL(rs);//transfer to URL
        SyndFeedInput syndFeedInput = new SyndFeedInput();
        SyndFeed syndFeed = null;
        
        /**
         *  get the content of the feed(xml)
         */
        XmlReader xmlReader = new XmlReader(url);
        syndFeed = syndFeedInput.build(xmlReader);
        logger.debug("Forwarding to home.jsp");
        request.setAttribute("title", syndFeed.getTitle());
        request.setAttribute("entries", syndFeed.getEntries());
        request.setAttribute("date",syndFeed.getPublishedDate());
        request.getSession().setAttribute("rssfeed", rs);
        request.setAttribute("image", syndFeed.getImage());
        homeJsp.forward(request, response);
    }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (IllegalArgumentException ex) {
            java.util.logging.Logger.getLogger(rssServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FeedException ex) {
            java.util.logging.Logger.getLogger(rssServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        response.sendRedirect("browse.jsp");
      
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (IllegalArgumentException ex) {
            java.util.logging.Logger.getLogger(rssServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FeedException ex) {
            java.util.logging.Logger.getLogger(rssServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
