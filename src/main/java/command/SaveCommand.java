package command;

import org.apache.commons.io.FileUtils;
import persistence.model.Attach;
import command.util.ContactUtil;
import util.GeneralUtil;
import persistence.model.Contact;
import persistence.model.Phone;
import service.ContactService;
import service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

import java.util.*;


public class SaveCommand implements ActionCommand {
    private ContactService contactService = ServiceFactory.getContactService();
    private HttpServletRequest request;
    private long idContact;
    private HttpSession session;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        this.request = request;
        session = request.getSession();
        Contact contact = ContactUtil.makeContact(request);
        idContact = contactService.setContact(contact);
        savePhones();
        saveAttaches();

        String path =  getAvatar();

        contactService.setPhoto(idContact,path);
        return "/controller?command=show";
    }

    private void savePhones() {
        List<Phone> phones = ContactUtil.getPhones(request, idContact);
        contactService.savePhones(idContact, phones);
    }

    private void saveAttaches() throws IOException {
        HashMap<String, Attach> attaches = (HashMap<String, Attach>) session.getAttribute("attaches");
        session.removeAttribute("attaches");
        contactService.saveAttaches(idContact, attaches);
    }

    private String getAvatar() throws IOException, ServletException {
        String savePath = getSavePath(idContact);
        File fileSaveDir = new File(savePath);

        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }

        Part filePart = request.getPart("avatar");
        if(filePart.getSize()>0) {
            savePath += File.separator + idContact + GeneralUtil.extractFileExtension(filePart);
            filePart.write(savePath);
        } else {
            String temp_path = (String)session.getAttribute("temp_path_avatar");
            if( temp_path != null) {
                session.removeAttribute("temp_path_avatar");
                File temp_avatar = new File(temp_path);
                temp_avatar = GeneralUtil.renameFile(idContact,temp_avatar);
                FileUtils.moveFileToDirectory(temp_avatar,fileSaveDir,true);
                savePath += File.separator + temp_avatar.getName();
            } else {
                return null;
            }
        }
        return savePath;

    }

    private String getSavePath(long idContact) throws IOException {
        Properties properties = new Properties();
        properties.load(AvatarCommand.class.getResourceAsStream("/avatars.properties"));
        String path = properties.getProperty("SAVE_AVATARS_PATH");
        while(idContact != 0) {
            path += File.separator + idContact % 10;
            idContact /= 10;
        }
        return  path;
    }
}
