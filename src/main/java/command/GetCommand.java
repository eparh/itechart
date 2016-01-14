package command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import persistence.model.Attach;
import persistence.model.Contact;
import persistence.model.Phone;
import service.ContactService;
import service.ServiceFactory;
import command.util.ContactUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetCommand implements ActionCommand {
    private ContactService contactService = ServiceFactory.getContactService();

    private Logger logger = LogManager.getLogger(GetCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        //Deleting attachments from session if it existed.
        session.removeAttribute("attaches");

        //Getting idContact
        String temp = request.getParameter("idContact");
        String[] chosen = request.getParameterValues("marked");
        Long idContact = ContactUtil.findOutIdContact(temp,chosen);

        Contact contact = contactService.getContact(idContact);

        List<Phone> phones = contactService.getPhones(idContact);
        List<Attach> attaches = contactService.getAttaches(idContact);
        request.setAttribute("attaches",attaches);
        request.setAttribute("contact",contact);
        request.setAttribute("mode","edit");
        request.setAttribute("phones",phones);
        logger.info("Getting of contact");
        return "/jsp/contact.jsp";
    }
}
