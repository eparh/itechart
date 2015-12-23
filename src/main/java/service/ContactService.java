package service;

import persistence.model.Contact;
import persistence.model.Phone;

import java.util.List;


public interface ContactService {
    List<Contact> getShowContacts(long start, long count);

    void deleteContact(Long id);

    long setContact(Contact contact);

    void savePhones(long idContact, List<Phone> phones);

    Contact getContact(long id);

    List<Phone> getPhones(long id);

    long countContacts();
}
