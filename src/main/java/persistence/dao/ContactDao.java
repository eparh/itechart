package persistence.dao;



import persistence.model.Attach;
import persistence.model.Contact;
import persistence.model.Phone;
import java.util.List;

public interface ContactDao {
    Contact getById(Long idContact);

    void insert(Contact contact);

    void deleteContact(Long idContact);

    List<Contact> getAll();
    List<Phone> getPhones(Long idContact);
    List<Attach> getAttach(Long idContact);
}