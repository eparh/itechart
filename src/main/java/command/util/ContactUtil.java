package command.util;

import persistence.model.Address;
import persistence.model.Contact;
import persistence.model.Phone;
import util.GeneralUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactUtil {
    public static Contact makeContact(HttpServletRequest request) {
        Contact contact = new Contact();
        String strID = request.getParameter("idContact");
        if(!"".equals(strID)) {
            contact.setId(Long.parseLong(strID));
        }
        contact.setName(request.getParameter("name"));
        contact.setSurname(request.getParameter("surname"));
        contact.setMidName(request.getParameter("middname"));
        contact.setBirthday(GeneralUtil.stringToDate(request.getParameter("birthday")));
        contact.setEmail(request.getParameter("email"));
        contact.setGender(request.getParameter("gender"));
        contact.setMaritStatus(request.getParameter("maritStatus"));
        contact.setNationality(request.getParameter("national"));
        contact.setPhoto(request.getParameter("photo"));
        contact.setSite(request.getParameter("site"));
        contact.setCompany(request.getParameter("company"));
        contact.setAddress(getAdds(request));

        return contact;
    }

    private static Address getAdds(HttpServletRequest request) {
        Address address = new Address();
        String strID = request.getParameter("idAddress");

        if(!"".equals(strID)) {
            address.setIdAddress(Long.parseLong(strID));
        }

        address.setCountry(request.getParameter("country"));
        address.setAddress(request.getParameter("address"));
        address.setCity(request.getParameter("city"));
        address.setIndex(request.getParameter("index"));
        return address;
    }

    public static List<Phone> getPhones(HttpServletRequest request, Long idContact) {
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
                if(idContact != null)
                    phone.setIdContact(idContact);
                phones.add(phone);
            }
        }
        return phones;
    }

    public static Long findOutIdContact(String temp , String [] chosen) {
        Long idContact =(long) - 1;
        if(! "".equals(temp)) idContact = Long.parseLong(temp);
        else {
            if (chosen != null) {
                for (String item : chosen) {
                    idContact = Long.parseLong(item);
                    break;
                }
            }
        }
        return  idContact;
    }
}
