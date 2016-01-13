package command;


import util.GeneralUtil;
import service.ContactService;
import service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

public class DeleteCommand implements ActionCommand {
    private ContactService contactService = ServiceFactory.getContactService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String [] chosen = request.getParameterValues("marked");
        for(String item: chosen) {
            long idContact = Long.parseLong(item);
            String avatarPath = contactService.getPhoto(idContact);
            //add deleting of attachments
            if (avatarPath != null) {
                GeneralUtil.deleteOnPath(avatarPath);
            }
            contactService.deleteContact(idContact);
        }
        return "/controller?command=show";
    }
}
