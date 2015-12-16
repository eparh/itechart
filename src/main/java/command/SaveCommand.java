package command;


import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import persistence.model.Adds;
import persistence.model.Contact;
import service.ContactService;
import service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;

public class SaveCommand implements ActionCommand{

    private ContactService contactService = ServiceFactory.getContactService();
    private HttpServletRequest request;

    @Override
    public String execute(HttpServletRequest request) {

        this.request = request;

        Contact contact = makeContact();
        contactService.insertContact(contact);
        return "/controller?command=show";
    }

    private Contact makeContact(){

        System.out.println(request.getParameter("hi"));
        Contact contact = new Contact();
        contact.setName(request.getParameter("name"));
        contact.setSurname(request.getParameter("surname"));
        contact.setMidName(request.getParameter("middname"));

        contact.setBirthday(getDate());

        contact.setEmail(request.getParameter("email"));
        contact.setGender(request.getParameter("gender"));
        contact.setMaritStatus(request.getParameter("maritStatus"));
        contact.setNationality(request.getParameter("national"));
        contact.setPhoto(request.getParameter("photo"));
        contact.setSite(request.getParameter("site"));
        contact.setCompany(request.getParameter("company"));

        contact.setAddress(getAdds());

        return contact;
   }

    private Date getDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        String stringDate = request.getParameter("birthday");
        if(stringDate != null && !stringDate.equals("")) {
            try {
                java.util.Date utilDate = formatter.parse(stringDate);
                date = new Date(utilDate.getTime());
            } catch (ParseException e) {
                throw new RuntimeException();
            }
        }
        return date;
    }

    private Adds getAdds(){
        Adds adds = new Adds();
        adds.setCountry(request.getParameter("country"));
        adds.setAddress(request.getParameter("address"));
        adds.setCity(request.getParameter("city"));
        adds.setIndex(request.getParameter("index"));
        return adds;
    }
}
