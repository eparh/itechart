package service;

import persistence.model.Contact;
import persistence.model.Phone;

import java.util.List;


public interface ContactService {
    List<Contact> getContacts();

    void deleteContact(Long id);

    long insertContact(Contact contact);

    void addPhone(Phone phone);

    Contact getContact(long id);

    List<Phone> getPhones(long id);
}
