package persistence.dao;

import persistence.model.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public interface ContactDao {
    Contact getById(Long idContact);

    /**
     *
     * @return contact's id
     */
    long setContact(Contact contact);

    /**
     *
     * @return path of photo
     */
    String getPhoto(long idContact);

    void setPhoto(long idContact, String path);

    long countContacts(SearchCriteria criteria);

    void deleteContact(Long idContact);

    List<Contact> getToShowContacts(SearchCriteria criteria, ViewSettings settings);

    void insertPhone(Phone phone);

    void setPhones(long idContact, List<Phone> phones);

    List<Phone> getPhones(Long idContact);

    List<Attach> getAttaches(Long idContact);

    void insertAttach(Attach attach);

    void setAttaches(long idContact,Collection<Attach> attaches);

    List<Contact> forBirthdayContacts();
}