package service;

import persistence.dao.ContactDao;
import persistence.dao.DaoFactory;
import persistence.model.Contact;
import persistence.model.Phone;

import java.util.List;


public class ContactServiceImpl implements ContactService{
    private ContactDao contactDao;
    public ContactServiceImpl() {
        this.contactDao = DaoFactory.getContactDao();
    }

    @Override
    public List<Contact> getShowContacts() {
        return contactDao.getShowContacts();
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
}
