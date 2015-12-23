package persistence.dao;



import persistence.model.Attach;
import persistence.model.Contact;
import persistence.model.Phone;
import java.util.List;

public interface ContactDao {
    Contact getById(Long idContact);

    long setContact(Contact contact);

    long countContacts();

    void deleteContact(Long idContact);

    List<Contact> getShowContacts(long start, long count);

    void insertPhone(Phone phone);

    void setPhones(long idContact, List<Phone> phones);

    List<Phone> getPhones(Long idContact);

    List<Attach> getAttach(Long idContact);
}