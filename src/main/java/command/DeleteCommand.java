package command;


import persistence.model.SearchCriteria;
import persistence.model.ViewSettings;
import service.ContactService;
import service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.List;

public class DeleteCommand implements ActionCommand {
    private ContactService contactService = ServiceFactory.getContactService();
    @Override
    public String execute(HttpServletRequest request) {
        String [] chosen = request.getParameterValues("marked");
        for(String item: chosen){
            contactService.deleteContact(Long.parseLong(item));
        }
        return "/controller?command=show";
    }
}
