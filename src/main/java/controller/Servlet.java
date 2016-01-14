package controller;

import command.ActionCommand;
import command.ActionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.CheckBirthdayUtil;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Servlet",urlPatterns = {"/controller"})
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
                 maxFileSize=1024*1024*10,      // 10MB
                 maxRequestSize=1024*1024*50)   // 50MB
public class Servlet extends HttpServlet {
    private Logger logger = LogManager.getLogger(Servlet.class);

    public void init() throws ServletException{
        try {
            logger.info("Init servlet");
            //CheckBirthdayUtil.getInstance().startService();
        } catch (Exception e) {
            logger.error("Error while init", e);
        }

    }

    @Override
    public void destroy() {
        try {
           // CheckBirthdayUtil.getInstance().stopService();
        } catch (Exception e) {
            logger.error("Error while destroy servlet", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    private void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            // Determination command from JSP
            ActionFactory client = new ActionFactory();
            ActionCommand command = client.defineCommand(request);
            String page = command.execute(request,response);
            if (page != null) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                dispatcher.forward(request, response);
            } else {
                // Do nothing, command returns response manually
            }
         } catch (Exception e) {
            logger.error("Error while process request", e);
            response.sendRedirect(request.getContextPath() + "/jsp/error.jsp");
        }

    }


}
