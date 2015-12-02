package persistence.dao;


import persistence.model.Contact;

import javax.management.InstanceAlreadyExistsException;
import java.util.List;

public interface ContactDao {
    Contact getById(Long contactId);

    void save(Contact contact) throws InstanceAlreadyExistsException;

    void insert(List<Contact> listContact);

    void delete(int id);

    List<Contact> gedfdf