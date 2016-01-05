package service;

import persistence.dao.ContactDao;
import persistence.dao.DaoFactory;
import persistence.model.Contact;
import persistence.model.Phone;
import persistence.model.SearchCriteria;
import persistence.model.ViewSettings;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

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
    public Date stringToDate(String stringDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        if(stringDate != null && !stringDate.equals("")) {
            try {
                java.util.Date utilDate = formatter.parse(stringDate);
                date = new Date(utilDate.getTime());
            } catch (ParseException e) {
                throw new RuntimeException();
            }
        }
        return date;
    }
}
