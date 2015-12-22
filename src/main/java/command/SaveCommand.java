package command;

import persistence.model.Adds;
import persistence.model.Contact;
import persistence.model.Phone;
import service.ContactService;
import service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
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

    private Contact makeContact() {
        Contact contact = new Contact();
        String strID = request.getParameter("idContact");
        if(!"".equals(strID)) {
            contact.setId(Long.parseLong(strID));
        }
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

    private Date getDate() {
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
                System.out.println(paramName);
                System.out.println(paramName.substring(5));
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
        System.out.println(phones);
        contactService.savePhones(idContact, phones);
    }
}
