package command;


import persistence.model.Attach;
import persistence.model.Contact;
import persistence.model.Phone;
import util.ContactUtil;
import util.GeneralUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class SetAttachCommand implements ActionCommand{
    private HttpServletRequest request;
    private  Properties properties = new Properties();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.request = request;
        properties.load(AvatarCommand.class.getResourceAsStream("/temp.properties"));
        HttpSession session = request.getSession();

        List<Attach> attaches = (List<Attach>) session.getAttribute("attaches");
        if( attaches == null ) {
            attaches = new ArrayList<>();
        }

        //Чтобы не сбивать title, храним его в атрибуте
        String title = request.getParameter("title");
        request.setAttribute("title", title);

        saveTempAvatar();

        Contact contact = ContactUtil.makeContact(request);
        request.setAttribute("contact",contact);
        // System.out.println(contact.getId());

        List<Phone> phones = ContactUtil.getPhones(request, contact.getId());
        request.setAttribute("phones", phones);

        Attach attach = makeAttach();
        attaches.add(attach);
        session.setAttribute("attaches",attaches);

        return "/jsp/contact.jsp";
    }

    private Attach makeAttach() throws IOException, ServletException {
        Part filePart = request.getPart("attach");

        Attach attach = new Attach();

        String savePath = properties.getProperty("TEMP_PATH");
        File fileSaveDir = new File(savePath);

        //Смотрю существует ли темповая папка, если есть то очищаю содержимое.
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        } else {
            //FileUtils.deleteDirectory(fileSaveDir);
            //fileSaveDir.mkdirs();
        }

        java.sql.Date date = new java.sql.Date(new Date().getTime());
        attach.setDate(date);

        attach.setComment(request.getParameter("comment"));


        if (filePart.getSize()>0) {
            String fileName = GeneralUtil.extractFileName(filePart);
            savePath += File.separator + fileName;
            filePart.write(savePath);
            attach.setName(fileName);
        }

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
