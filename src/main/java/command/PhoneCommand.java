package command;

import persistence.model.Phone;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class PhoneCommand implements ActionCommand {
    private static List<Phone> phones;
    @Override
    public String execute(HttpServletRequest request) {
        String mode = request.getParameter("mode");
        switch (mode){
            case "add": addPhone(request); break;
            case "delete": break;
            case "edit": break;
        }

        return "/jsp/create.jsp";
    }

    private void addPhone(HttpServletRequest request){
        Object temp = request.getAttribute("phones");


        if(temp!= null){
            phones = (ArrayList<Phone>)temp;
        } else{
            phones = new ArrayList<Phone>();
        }
        Phone phone = new Phone();
        phone.setCountryCode(request.getParameter("countryCode"));
        phone.setOperatorCode(request.getParameter("operatorCode"));
        phone.setNumber(request.getParameter("phone"));
        phone.setKind(request.getParameter("kind"));
        phone.setComment(request.getParameter("comment"));
        phones.add(phone);
        request.setAttribute("phones",phones);
        System.out.println(phones);
    }
}
