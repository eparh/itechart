package command;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import persistence.model.Attach;
import util.GeneralUtil;
import service.ContactService;
import service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class DeleteCommand implements ActionCommand {
    private ContactService contactService = ServiceFactory.getContactService();
    private Logger logger = LogManager.getLogger(DeleteCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String [] chosen = request.getParameterValues("marked");
        for(String item: chosen) {
            long idContact = Long.parseLong(item);
            deletePhoto(idContact);
            deleteAttaches(idContact);
            contactService.deleteContact(idContact);
        }
        logger.info("Deleting contact");
        return "/controller?command=show";
    }

    private void deletePhoto(long idContact) {
        String avatarPath = contactService.getPhoto(idContact);
        if (avatarPath != null) {
            GeneralUtil.deleteOnPath(avatarPath);
        }
    }

    private void deleteAttaches(long idContact) {
        List<Attach> attaches = contactService.getAttaches(idContact);
        for(Attach attach:attaches) {
            GeneralUtil.deleteOnPath(attach.getPath());
        }
    }
}
