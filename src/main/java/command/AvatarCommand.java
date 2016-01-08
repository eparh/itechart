package command;

import service.ContactService;
import service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Properties;

public class AvatarCommand implements ActionCommand {
    private ContactService contactService = ServiceFactory.getContactService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //Определение ID контакта
        String temp = request.getParameter("idContact");
        Long idContact =(long) - 1;
        if(! "".equals(temp)) idContact = Long.parseLong(temp);
        else {
            String[] chosen = request.getParameterValues("marked");
            for (String item : chosen) {
                idContact = Long.parseLong(item);
                break;
            }
        }

        Properties properties = new Properties();
        try {
            properties.load(AvatarCommand.class.getResourceAsStream("/avatars.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String path = contactService.getPhoto(idContact);

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
