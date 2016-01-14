package service;

import org.apache.commons.io.FileUtils;
import persistence.dao.ContactDao;
import persistence.dao.DaoFactory;
import persistence.model.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ContactServiceImpl implements ContactService {
    private ContactDao contactDao;
    public ContactServiceImpl() {
        this.contactDao = DaoFactory.getContactDao();
    }

    @Override
    public List<Contact> getShowContacts(SearchCriteria criteria,ViewSettings settings) {
        return contactDao.getToShowContacts(criteria,settings);
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

    @Override
    public List<Attach> getAttaches(long idContact) {
        return contactDao.getAttaches(idContact);
    }

    @Override
    public void saveAttaches(long idContact, List<Attach> attachList) throws IOException {
        if(attachList == null) {
          return;
        }
        Properties properties = new Properties();
        properties.load(ContactServiceImpl.class.getResourceAsStream("/attach.properties"));

        String savePath = properties.getProperty("SAVE_ATTACH_PATH") + File.separator + idContact;
        File saveDir = new File(savePath);

        if(! saveDir.exists()) {
            saveDir.mkdirs();
        }

        List<String> fileNames = new ArrayList<>();
        for(Attach attach: attachList) {
            fileNames.add(attach.getName());
        }

        //Deleting files which don't included in save list from save folder
        String[] files = saveDir.list();
        for (int i=0; i<files.length; i++) {
            File file = new File(saveDir, files[i]);
            if(! fileNames.contains(file.getName()))  file.delete();
        }

        properties.load(ContactServiceImpl.class.getResourceAsStream("/temp.properties"));

        String tempPath = properties.getProperty("TEMP_PATH");
        File tempDir = new File(tempPath);
        if( tempDir != null) {
            String[] temp_files = tempDir.list();
            for (int i=0; i<temp_files.length; i++) {
                File file = new File(tempDir, temp_files[i]);
                //Check names of files in temp for accurately moving to save folder
                // without some service files(in Mac OS exists) trashes etc
                if(fileNames.contains(file.getName())) {
                    FileUtils.moveFileToDirectory(file,saveDir, true);
                }
            }
        }

        List<Attach> toSaveAttaches = new ArrayList<>();
        //Fill fields for new attachments поля

        for(Attach attach: attachList) {
            if (attach.getIdAttach() == null) {
                attach.setPath(savePath + File.separator + attach.getName());
                attach.setIdContact(idContact);
                toSaveAttaches.add(attach);
            } else {
                toSaveAttaches.add(attach);
            }
        }

        contactDao.setAttaches(idContact,toSaveAttaches);
    }

    @Override
    public List<Contact> getBirthdayContacts() {
        return contactDao.forBirthdayContacts();
    }
}
