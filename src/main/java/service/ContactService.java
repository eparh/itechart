package service;

import persistence.model.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Date;
import java.util.Collection;
import java.util.HashMap;
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

    HashMap<String,Attach> getAttaches(long idContact);

    void saveAttaches(long idContact, HashMap<String,Attach> map_attaches) throws IOException;

    List<Contact> getBirthdayContacts();
}
