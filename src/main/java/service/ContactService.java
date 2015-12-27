package service;

import persistence.model.Contact;
import persistence.model.Phone;
import persistence.model.SearchCriteria;

import java.sql.Date;
import java.util.List;


public interface ContactService {
    List<Contact> getShowContacts(SearchCriteria criteria);

    void deleteContact(Long id);

    long setContact(Contact contact);

    void savePhones(long idContact, List<Phone> phones);

    Contact getContact(long id);

    List<Phone> getPhones(long id);

    long countContacts(SearchCriteria criteria);

    Date stringToDate(String stringDate);
}
