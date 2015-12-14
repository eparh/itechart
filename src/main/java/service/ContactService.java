package service;

import persistence.model.Contact;

import java.util.List;

/**
 * Created by zhenya on 25.10.15.
 */
public interface ContactService {

    List<Contact> getContacts();
    void deleteContact(Long id);
    void insertContact(Contact contact);
}
