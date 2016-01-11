package command;

import persistence.model.Contact;
import persistence.model.Phone;
import service.ContactService;
import service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetCommand implements ActionCommand {
    private ContactService contactService = ServiceFactory.getContactService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String temp = request.getParameter("idContact");
        Long idContact =(long) -1;
        if(! "".equals(temp)) {
            idContact = Long.parseLong(temp);
        } else {
            String[] chosen = request.getParameterValues("marked");
            for (String item : chosen) {
                idContact = Long.parseLong(item);
                break;
            }
        }

        Contact contact = contactService.getContact(idContact);

        List<Phone> phones = contactService.getPhones(idContact);
        //System.out.println(contact.getPhoto());
        request.setAttribute("contact",contact);
        request.setAttribute("mode","edit");
        request.setAttribute("phones",phones);

        return "/jsp/contact.jsp";
    }
}
