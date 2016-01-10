package service;

import persistence.model.Contact;
import persistence.model.Phone;
import persistence.model.SearchCriteria;
import persistence.model.ViewSettings;

import java.sql.Date;
import java.util.List;

public interface ContactService {
    List<Contact> getShowContacts(SearchCriteria criteria, ViewSettings settings);

    void deleteContact(Long id);

    long setContact(Contact contact);

    void savePhones(long idContact, List<Phone> phones);

    Contact getContact(long id);

    List<Phone> getPhones(long id);

    long countContacts(SearchCriteria criteria);

    void setPhoto(long idContact, String path);

    String getPhoto(long idContact);

    Date stringToDate(String stringDate);

    void deleteOnPath(String path);
}
