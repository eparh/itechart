package service;

import persistence.dao.ContactDao;
import persistence.dao.DaoFactory;
import persistence.model.Contact;

import java.util.List;


public class ContactServiceImpl implements ContactService{

    private ContactDao contactDao;

    public ContactServiceImpl() {
        this.contactDao = DaoFactory.getContactDao();
    }


    @Override
    public List<Contact> getContacts() {
        return contactDao.getAll();
    }

    @Override
    public void deleteContact(Long id) {
        contactDao.deleteContact(id);
    }
}
