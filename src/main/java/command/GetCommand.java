package command;

import persistence.model.Attach;
import persistence.model.Contact;
import persistence.model.Phone;
import service.ContactService;
import service.ServiceFactory;
import util.ContactUtil;
import util.GeneralUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GetCommand implements ActionCommand {
    private ContactService contactService = ServiceFactory.getContactService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        //Удаляем из сессии attachment-ы , если они были и чистим темповые папки
        session.removeAttribute("attaches");

        //Достаем idContact
        String temp = request.getParameter("idContact");
        String[] chosen = request.getParameterValues("marked");
        Long idContact = ContactUtil.findOutIdContact(temp,chosen);

        Contact contact = contactService.getContact(idContact);

        List<Phone> phones = contactService.getPhones(idContact);
        HashMap<String,Attach> attaches = contactService.getAttaches(idContact);
        request.setAttribute("attaches",attaches);
        request.setAttribute("contact",contact);
        request.setAttribute("mode","edit");
        request.setAttribute("phones",phones);

        return "/jsp/contact.jsp";
    }
}
