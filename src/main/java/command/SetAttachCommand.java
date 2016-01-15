package command;


import command.exception.CommandException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import persistence.model.Attach;
import persistence.model.Contact;
import persistence.model.Phone;
import service.ContactService;
import service.ServiceFactory;
import command.util.ContactUtil;
import util.MyFileUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class SetAttachCommand implements ActionCommand{
    private HttpServletRequest request;
    private ContactService contactService = ServiceFactory.getContactService();
    private  Properties properties = new Properties();

    private Logger logger = LogManager.getLogger(SetAttachCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)  {
        this.request = request;
        try {
            properties.load(AvatarCommand.class.getResourceAsStream("/temp.properties"));
        } catch (IOException e) {
            throw new CommandException("Error is occurred with temporary attachment",e);
        }
        HttpSession session = request.getSession();

        Contact contact = ContactUtil.makeContact(request);
        request.setAttribute("contact",contact);

        List<Attach> attachList = (List<Attach>) session.getAttribute("attaches");
        Map<String,Attach> attachMap = new HashMap<>();
        if (attachList == null) {
            attachList = new ArrayList<>();
            Long idContact = contact.getId();
            if(idContact != null) {
                System.out.println("start delete");
                System.out.println(contactService.getAttaches(idContact));
                for (Attach attach : contactService.getAttaches(idContact)) {
                    attachMap.put(attach.getName(), attach);

                }
            }
        }

        for (Attach attach: attachList) {
            attachMap.put(attach.getName(), attach);
        }
        System.out.println(attachMap);
        //Processing of buttons
        String attachMode =  request.getParameter("attachMode");
        switch(attachMode) {
            case "delete":
                String [] chosen_attaches =  request.getParameterValues("attaches");
                if(chosen_attaches != null) {
                    for (String item : chosen_attaches) {
                        System.out.println(item);
                        attachMap.remove(item);
                    }
                }
                break;

            case "add":
                Attach attach = makeAttach();
                attachMap.put(attach.getName(),attach);
                break;

            case "edit":
                Attach editAttach = prepareForEdit();
                attachMap.put(editAttach.getName(), editAttach);
                break;

            default:
                throw new CommandException("Invalid attachment's mode : " + attachMode);
        }

        //Saving form's parameters
        saveTempAvatar();

        List<Phone> phones = ContactUtil.getPhones(request, null);
        request.setAttribute("phones", phones);

        List<Attach> finalAttaches = new ArrayList<>();

        finalAttaches.addAll(attachMap.values());
        System.out.println(finalAttaches);
        session.setAttribute("attaches",finalAttaches);
        logger.info("File was attached");
        return "/jsp/contact.jsp";
    }

    private Attach makeAttach()  {
        Attach attach = new Attach();

        String savePath = properties.getProperty("TEMP_PATH");
        File fileSaveDir = new File(savePath);

        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }

        java.sql.Date date = new java.sql.Date(new Date().getTime());
        attach.setDate(date);

        attach.setComment(request.getParameter("attach_comment"));
        try {
            Part filePart = request.getPart("attach");
            if (filePart.getSize() > 0) {
                String fileName = MyFileUtil.extractFileName(filePart);
                Path saved = Files.createTempFile(Paths.get(savePath), "", fileName);
                filePart.write(saved.toAbsolutePath().toString());
                attach.setName(saved.getFileName().toString());
            }
        } catch (IOException | ServletException e) {
            throw new CommandException("Error is occurred with temporary attachment",e);
        }
        return attach;
    }

    private Attach prepareForEdit(){
        Attach attach = new Attach();
        attach.setName(request.getParameter("file_name"));
        java.sql.Date date = new java.sql.Date(new Date().getTime());
        attach.setDate(date);

        attach.setComment(request.getParameter("attach_comment"));
        return attach;
    }

    private void saveTempAvatar() {
        String savePath = properties.getProperty("TEMP_AVA");
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
       try {
           Part avaPart = request.getPart("avatar");
           if (avaPart.getSize() > 0) {
               String fileName = MyFileUtil.extractFileName(avaPart);
              // savePath += File.separator + fileName;
               Path saved = Files.createTempFile(Paths.get(savePath), "", fileName);
               String newPath = saved.toAbsolutePath().toString();
               avaPart.write(saved.toAbsolutePath().toString());

               //Adding path of temporary avatar to session
               HttpSession session = request.getSession();
               session.setAttribute("temp_path_avatar", newPath);
           }
       } catch ( IOException | ServletException e) {
           throw new CommandException("Error is occurred with temporary attachment",e);
       }
    }
}
