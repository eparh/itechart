package command;

import persistence.model.Contact;
import persistence.model.Phone;
import service.ContactService;
import service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetCommand implements ActionCommand {
    private ContactService contactService = ServiceFactory.getContactService();

    @Override
    public String execute(HttpServletRequest request) {
        String temp = request.getParameter("idContact");
        Long idContact =(long) -1;
        if(! "".equals(temp)) {
            idContact = Long.parseLong(temp);
        } else {
            String[] chosen = request.getParameterValues("marked");

            if (chosen == null) {
                return "/controller?command=show";
            }

            for (String item : chosen) {
                idContact = Long.parseLong(item);
                break;
            }
        }

        Contact contact = contactService.getContact(idContact);
        List<Phone> phones = contactService.getPhones(idContact);

        request.setAttribute("contact",contact);
        request.setAttribute("title","Edit contact");
        request.setAttribute("phones",phones);

        return "/jsp/contact.jsp";
    }
}
