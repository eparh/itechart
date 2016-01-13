package persistence.dao;

import persistence.model.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public interface ContactDao {
    Contact getById(Long idContact);

    long setContact(Contact contact);

    String getPhoto(long idContact);

    void setPhoto(long idContact, String path);

    long countContacts(SearchCriteria criteria);

    void deleteContact(Long idContact);

    List<Contact> getShowContacts(SearchCriteria criteria, ViewSettings settings);

    void insertPhone(Phone phone);

    void setPhones(long idContact, List<Phone> phones);

    List<Phone> getPhones(Long idContact);

    HashMap<String,Attach> getAttaches(Long idContact);

    void insertAttach(Attach attach);

    void setAttaches(long idContact,Collection<Attach> attaches);

    List<Contact> birthdayContacts();
}