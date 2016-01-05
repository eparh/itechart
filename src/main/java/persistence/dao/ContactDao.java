package persistence.dao;

import persistence.model.*;

import java.util.List;

public interface ContactDao {
    Contact getById(Long idContact);

    long setContact(Contact contact);

    long countContacts(SearchCriteria criteria);

    void deleteContact(Long idContact);

    List<Contact> getShowContacts(SearchCriteria criteria, ViewSettings settings);

    void insertPhone(Phone phone);

    void setPhones(long idContact, List<Phone> phones);

    List<Phone> getPhones(Long idContact);

    List<Attach> getAttach(Long idContact);
}