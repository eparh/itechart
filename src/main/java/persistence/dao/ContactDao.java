package persistence.dao;



import persistence.model.Attach;
import persistence.model.Contact;
import persistence.model.Phone;
import java.util.List;

public interface ContactDao {
    Contact getById(Long idContact);

    long setContact(Contact contact);

    void deleteContact(Long idContact);

    void setPhone(Phone phone);

    List<Contact> getShowContacts();

    List<Phone> getPhones(Long idContact);

    List<Attach> getAttach(Long idContact);
}