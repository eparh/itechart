package command;


import persistence.model.Contact;
import service.ContactService;
import service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowCommand implements ActionCommand {
    private ContactService contactService = ServiceFactory.getContactService();
    @Override
    public String execute(HttpServletRequest request) {
        List<Contact> contacts= contactService.getContacts();
        request.setAttribute("contacts", contacts);
        return "/jsp/main.jsp";
    }
}
