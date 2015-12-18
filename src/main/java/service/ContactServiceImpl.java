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
    public List<Contact> getContacts() {
        return contactDao.getAll();
    }

    @Override
    public void deleteContact(Long id) {
        contactDao.deleteContact(id);
    }

    @Override
    public long insertContact(Contact contact) {
        return contactDao.insert(contact);
    }

    @Override
    public void addPhone(Phone phone) {
        contactDao.setPhone(phone);
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
