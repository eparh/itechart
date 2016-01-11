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
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Properties properties = new Properties();
        properties.load(DeleteCommand.class.getResourceAsStream("/temp.properties"));
        String appPath = request.getServletContext().getRealPath("");
        //Путь, где лежит дефолтный аватар, чтобы не удалить его случайно.
        String defaultPath = appPath + properties.getProperty("DEFAULT_AVATAR");

        String [] chosen = request.getParameterValues("marked");
        for(String item: chosen){
            long idContact = Long.parseLong(item);
            String avatarPath = contactService.getPhoto(idContact);
            if(avatarPath != null && avatarPath.equals(defaultPath)) {
                GeneralUtil.deleteOnPath(avatarPath);
            }
            contactService.deleteContact(idContact);
        }
        return "/controller?command=show";
    }
}
