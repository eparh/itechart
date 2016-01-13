package command;

import service.ContactService;
import service.ServiceFactory;
import command.util.ContactUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Properties;

public class AvatarCommand implements ActionCommand {
    private ContactService contactService = ServiceFactory.getContactService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)  throws IOException {
        //Определение ID контакта
        String temp = request.getParameter("idContact");
        String[] chosen = request.getParameterValues("marked");

        Long idContact = ContactUtil.findOutIdContact(temp,chosen);

        Properties properties = new Properties();
        properties.load(AvatarCommand.class.getResourceAsStream("/avatars.properties"));

        String path;
        HttpSession session = request.getSession();
        String tempAvatar = (String) session.getAttribute("temp_path_avatar");
        if (tempAvatar != null && !"".equals(tempAvatar)) {
            path = tempAvatar;
        } else {
            path = contactService.getPhoto(idContact);
        }

        if (path == null) {
            String appPath = request.getServletContext().getRealPath("");
            path = appPath + properties.getProperty("DEFAULT_AVATAR");
        }

        File file = new File(path);

        int buffSize = Integer.parseInt(properties.getProperty("BUFFER_SIZE"));

        response.reset();
        response.setBufferSize(buffSize);
        response.setContentType(properties.getProperty("CONTENT_TYPE"));
        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setHeader("Content-Disposition", "avatar; filename=\"" + file.getName() + "\"");

        OutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(file);
        byte[] buffer = new byte[buffSize];
        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
        in.close();
        out.flush();

        return null;
    }

}
