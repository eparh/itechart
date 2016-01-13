package service;

import persistence.model.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Date;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ContactService {
    List<Contact> getShowContacts(SearchCriteria criteria, ViewSettings settings);

    void deleteContact(Long id);

    /**
     *
     * @param contact
     * @return
     */
    long setContact(Contact contact);

    void savePhones(long idContact, List<Phone> phones);

    Contact getContact(long id);

    List<Phone> getPhones(long id);

    long countContacts(SearchCriteria criteria);

    void setPhoto(long idContact, String path);

    String getPhoto(long idContact);

    /**
     *
     * @param idContact
     * @return
     */

    List<Attach> getAttaches(long idContact);

    /**
     *
     * @param idContact
     * @return
     * @throws IOException
     */
    void saveAttaches(long idContact, List<Attach> attachesList) throws IOException;

    List<Contact> getBirthdayContacts();
}
