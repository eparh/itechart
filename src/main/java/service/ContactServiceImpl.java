package service;

import util.GeneralUtil;
import persistence.dao.ContactDao;
import persistence.dao.DaoFactory;
import persistence.model.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactServiceImpl implements ContactService {
    private ContactDao contactDao;
    public ContactServiceImpl() {
        this.contactDao = DaoFactory.getContactDao();
    }

    @Override
    public List<Contact> getShowContacts(SearchCriteria criteria,ViewSettings settings) {
        return contactDao.getShowContacts(criteria,settings);
    }

    @Override
    public void deleteContact(Long id) {
        contactDao.deleteContact(id);
    }

    @Override
    public long setContact(Contact contact) {
        return contactDao.setContact(contact);
    }

    @Override
    public void savePhones(long idContact, List<Phone> phones) {
        contactDao.setPhones(idContact,phones);
    }

    @Override
    public Contact getContact(long id){
        return contactDao.getById(id);
    }

    @Override
    public List<Phone> getPhones(long id) {
        return contactDao.getPhones(id);
    }

    @Override
    public long countContacts(SearchCriteria criteria) {
        return contactDao.countContacts(criteria);
    }

    @Override
    public void setPhoto(long idContact, String path) {
        contactDao.setPhoto(idContact,path);
    }

    @Override
    public String getPhoto(long idContact) {
       return contactDao.getPhoto(idContact);
    }
}
