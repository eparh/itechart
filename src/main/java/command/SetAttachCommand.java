package command;


import persistence.model.Attach;
import persistence.model.Contact;
import persistence.model.Phone;
import service.ContactService;
import service.ServiceFactory;
import command.util.ContactUtil;
import util.GeneralUtil;

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

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.request = request;
        properties.load(AvatarCommand.class.getResourceAsStream("/temp.properties"));
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
        //Обрабока кнопок
        String attach_mode =  request.getParameter("attach_mode");
        switch(attach_mode) {
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
                Attach edit_attach = prepareForEdit();
                attachMap.put(edit_attach.getName(), edit_attach);
                break;

            default:
                throw new IllegalArgumentException("Invalid mode: " + attach_mode);
        }

        //Сохранение параметров формы
        saveTempAvatar();

        List<Phone> phones = ContactUtil.getPhones(request, null);
        request.setAttribute("phones", phones);

        List<Attach> finalAttaches = new ArrayList<>();

        finalAttaches.addAll(attachMap.values());
        System.out.println(finalAttaches);
        session.setAttribute("attaches",finalAttaches);

        return "/jsp/contact.jsp";
    }

    private Attach makeAttach() throws IOException, ServletException {
        Part filePart = request.getPart("attach");

        Attach attach = new Attach();

        String savePath = properties.getProperty("TEMP_PATH");
        File fileSaveDir = new File(savePath);

        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }

        java.sql.Date date = new java.sql.Date(new Date().getTime());
        attach.setDate(date);

        attach.setComment(request.getParameter("attach_comment"));


        if (filePart.getSize()>0) {
            String fileName = GeneralUtil.extractFileName(filePart);
            //savePath += File.separator + fileName;
            Path saved = Files.createTempFile(Paths.get(savePath),"",fileName);
            filePart.write(saved.toAbsolutePath().toString());
            attach.setName(saved.getFileName().toString());
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

    private void saveTempAvatar() throws IOException, ServletException{
        Part avaPart = request.getPart("avatar");
        String savePath = properties.getProperty("TEMP_AVA");
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        if (avaPart.getSize()>0) {
            String fileName = GeneralUtil.extractFileName(avaPart);
            savePath += File.separator + fileName;
            HttpSession session = request.getSession();
            session.setAttribute("temp_path_avatar", savePath);
            avaPart.write(savePath);
        }
    }
}
