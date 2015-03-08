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
 * @author zhengkaiming
 */
public class rssServlet2 extends HttpServlet {

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
        
            /**
             * action of the "favourites" buttom
             */
            logger.debug("Retrieving yahoo news feed");
            String rss;
            rss = request.getParameter("rssreader");
            System.out.println(rss);
            if("0".equals(rss)){
                 homeJsp.forward(request, response);
            }
            else if(rss.equals("null")||"null".equals(rss)){
                homeJsp.forward(request, response);
            }
            
            else{

                rss=""+rss+"";

                //System.out.println(rss);
                URL url = new URL(rss);
                SyndFeedInput syndFeedInput = new SyndFeedInput();
                SyndFeed syndFeed = null;
                XmlReader xmlReader = new XmlReader(url);
                syndFeed = syndFeedInput.build(xmlReader);

                logger.debug("Forwarding to home.jsp");
                request.setAttribute("title", syndFeed.getTitle());
                request.setAttribute("entries", syndFeed.getEntries());
                request.setAttribute("date",syndFeed.getPublishedDate());
                request.setAttribute("image", syndFeed.getImage());
                request.getSession().setAttribute("rssfeed", rss);
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
            homeJsp.forward(request, response);
        } catch (FeedException | NullPointerException ex) {
            homeJsp.forward(request, response);
            java.util.logging.Logger.getLogger(rssServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
