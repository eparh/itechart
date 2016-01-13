package util;

import persistence.model.Contact;
import service.ContactService;
import service.ServiceFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class CheckBirthdayUtil {
    private ContactService contactService = ServiceFactory.getContactService();
    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);
    public static void check() {

    }

    private List<Contact> whoHave() {
        List<Contact> contacts = contactService.getBirthdayContacts();
        List<Contact> birtdayContacts = new ArrayList<>();
        java.sql.Date currDate = new java.sql.Date(new Date().getTime());
        for(Contact contact: contacts) {
            if ( contact.getBirthday() != null && contact.getBirthday() == currDate) {
                birtdayContacts.add(contact);
            }
        }
        return birtdayContacts;
    }

    private String makeMessage() {
        List<Contact> contacts = whoHave();
        String message = "";
        int count = 0;
        for (Contact contact: contacts) {
            count ++;
            message  += count + ". " + contact.getFullName() +", address:"+ contact.getAddress() + ", company" + contact.getCompany() +
                    ", email:" + contact.getEmail() + "\n";
        }
        return  message;
    }
}
