package command;

import persistence.model.Adds;
import persistence.model.Contact;
import persistence.model.Phone;
import service.ContactService;
import service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SaveCommand implements ActionCommand {
    private ContactService contactService = ServiceFactory.getContactService();
    private HttpServletRequest request;

    @Override
    public String execute(HttpServletRequest request) {
        this.request = request;
        Contact contact = makeContact();
        long idContact = contactService.setContact(contact);
        savePhones(idContact);

        return "/controller?command=show";
    }

    private String getAvatar()  {
        String SAVE_DIR = "images";
        String PARAM_FILE = "avatar";
        // gets absolute path of the web application
        String appPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String savePath = appPath + SAVE_DIR;
        String path = null;
        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        try {
            Part filePart = request.getPart(PARAM_FILE);
            String fileName = extractFileName(filePart);
            if(filePart.getSize()>0){
                path = savePath + File.separator + fileName;
                filePart.write(path);
                File avatar = new File(path);
                System.out.println(avatar.exists());
                System.out.println(avatar.canRead());
                System.out.println(avatar.canExecute());
                System.out.println(avatar.canWrite());

                System.out.println(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }

        return path;
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }

    private Contact makeContact() {
        Contact contact = new Contact();
        String strID = request.getParameter("idContact");
        if(!"".equals(strID)) {
            contact.setId(Long.parseLong(strID));
        }
        contact.setName(request.getParameter("name"));
        contact.setSurname(request.getParameter("surname"));
        contact.setMidName(request.getParameter("middname"));
        contact.setBirthday(contactService.stringToDate(request.getParameter("birthday")));
        contact.setEmail(request.getParameter("email"));
        contact.setGender(request.getParameter("gender"));
        contact.setMaritStatus(request.getParameter("maritStatus"));
        contact.setNationality(request.getParameter("national"));
        contact.setPhoto(request.getParameter("photo"));
        contact.setSite(request.getParameter("site"));
        contact.setCompany(request.getParameter("company"));
        contact.setAddress(getAdds());

        contact.setPhoto(getAvatar());

        return contact;
    }

    private Adds getAdds() {
        Adds adds = new Adds();
        String strID = request.getParameter("idAddress");

        if(!"".equals(strID)) {
            adds.setIdAddress(Long.parseLong(strID));
        }

        adds.setCountry(request.getParameter("country"));
        adds.setAddress(request.getParameter("address"));
        adds.setCity(request.getParameter("city"));
        adds.setIndex(request.getParameter("index"));
        return adds;
    }

    private List<Phone> getPhones(long idContact) {
        List<Phone> phones = new ArrayList<>();
        Enumeration<String> paramNames = request.getParameterNames();
        Pattern pattern = Pattern.compile("phone\\d+");

        while(paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            Matcher matcher = pattern.matcher(paramName);
            if( matcher.matches()) {
                long i = Long.parseLong(paramName.substring(5));
                Phone phone = new Phone();
                phone.setCountryCode(request.getParameter("countryCode" + i));
                phone.setOperatorCode(request.getParameter("operatorCode" + i));
                phone.setNumber(request.getParameter("phone" + i));
                phone.setKind(request.getParameter("kind" + i));
                phone.setComment(request.getParameter("comment" + i));
                phone.setIdContact(idContact);
                phones.add(phone);
            }
        }
        return phones;
    }

    private void savePhones(long idContact) {
        List<Phone> phones = getPhones(idContact);
        contactService.savePhones(idContact, phones);
    }
}
