package command;


import persistence.model.Contact;
import service.ContactService;
import service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowCommand implements ActionCommand {
    private ContactService contactService = ServiceFactory.getContactService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        String requestCount = request.getParameter("countRecords");

        Long pageNumber, count;
        //Смотрим, проводил ли пользователь действия на текущей странице
        if (requestCount == null) {
            Long sessionCount = (Long) session.getAttribute("countRecords");
            //Если нет, то тут два варианта: первичный приход, либо переход с другой страницы сайта.
            if (sessionCount == null) {
                count = (long)10;
                pageNumber = (long)1;
            } else {
                count = sessionCount;
                pageNumber = (Long) session.getAttribute("pageNumber");
            }
        } else {
            count = Long.parseLong(requestCount);
            pageNumber = Long.parseLong(request.getParameter("pageNumber"));
        }

        long start = (pageNumber - 1) * count;
        List<Contact> contacts= contactService.getShowContacts(start,count);
        long total = contactService.countContacts();
        long pages = total / count;
        if(total % count != 0) {
            pages ++;
        }

        session.setAttribute("countRecords", count);
        session.setAttribute("pageNumber", pageNumber);
        session.setAttribute("pages", pages);
        request.setAttribute("contacts", contacts);
        return "/jsp/main.jsp";
    }
}
