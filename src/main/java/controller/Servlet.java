package controller;

import command.ActionCommand;
import command.ActionFactory;


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
    public void init() throws ServletException{

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    private void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        try {
            String page;
            request.setCharacterEncoding("UTF-8");
            // определение команды, пришедшей из JSP
            ActionFactory client = new ActionFactory();
            ActionCommand command = client.defineCommand(request);
            page = command.execute(request,response);
            if (page != null) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                dispatcher.forward(request, response);
            } else {
                //Файловая команда

            }
         } catch (Exception e) {
            //TODO
            System.err.println("Error, but I still work");
        }

    }
}
