package persistence.dao;


import persistence.DbUtil;
import persistence.model.Adds;
import persistence.model.Attach;
import persistence.model.Contact;
import persistence.model.Phone;

import javax.management.InstanceAlreadyExistsException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactDaoImpl implements ContactDao {
    public static final ContactDao INSTANCE = new ContactDaoImpl();
    private DataSource source = DbUtil.getMySQLDataSource();

    private ContactDaoImpl() {

    }

    @Override
    public Contact getById(Long idContact) {
        Contact contact = new Contact();
        try (Connection connection = source.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Contact " +
                     "WHERE idContact = ?")) {
            statement.setString(1, Long.toString(idContact));
            try (ResultSet set = statement.executeQuery()) {
                while (set.next()) {
                    contact.setId(idContact);
                    contact.setSurname(set.getString("surname"));
                    contact.setName(set.getString("name"));
                    contact.setMidName(set.getString("middName"));
                    contact.setMaritStatus(set.getString("maritStatus"));
                    contact.setCompany(set.getString("company"));
                    contact.setBirthday(set.getDate("birthday"));
                    contact.setGender("gender");
                    contact.setNationality("national");
                    contact.setSite("website");
                    contact.setEmail(set.getString("email"));
                    contact.setPhoto(set.getString("photo"));

                    //Получаем адрес
                    Long idAddress = set.getLong("idAddress");
                    contact.setAddress(getAdds(idAddress));




                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contact;
    }



    @Override
    public void deleteContact(Long idContact) {
        System.out.println(idContact);
        try (Connection connection = source.getConnection()){
            PreparedStatement statement = connection.prepareStatement("delete from Contact where idContact = ?");
            statement.setLong(1, idContact);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    @Override
    public List<Phone> getPhones(Long idContact){
        List<Phone> list = new ArrayList<Phone>();
        try (Connection connection = source.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Telephone " +
                     "WHERE idContact = ?")) {
            statement.setString(1, Long.toString(idContact));
            try (ResultSet set = statement.executeQuery()) {
                while (set.next()) {
                    Phone phone = new Phone();
                    phone.setComment(set.getString("comment"));
                    phone.setNumber(set.getString("number"));
                    phone.setCountryCode(set.getString("countryCode"));
                    phone.setOperatorCode(set.getString("operatorCode"));
                    phone.setKind(set.getString("kind"));
                    phone.setIdContact(set.getLong("idContact"));
                    phone.setIdPhone(set.getLong("idPhone"));
                    list.add(phone);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }


    @Override
    public void insert(Contact contact) {

    }

    @Override
    public List<Attach> getAttach(Long idContact){
        List<Attach> list = new ArrayList<Attach>();
        try (Connection connection = source.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Attachment " +
                     "WHERE idContact = ?")) {
            statement.setString(1, Long.toString(idContact));
            try (ResultSet set = statement.executeQuery()) {
                while (set.next()) {
                    Attach attach = new Attach();
                    attach.setIdContact(set.getLong("idContact"));
                    attach.setComment(set.getString("comment"));
                    attach.setName(set.getString("name"));
                    attach.setDate(set.getDate("date"));
                    attach.setDirectory(set.getString("directory"));
                    attach.setIdAttach(set.getLong("idAttach"));
                    list.add(attach);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<Contact> getAll() {
        List<Contact> list = new ArrayList<Contact>();

        try (Connection connection = source.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT idContact, name, surname, middName," +
                     "birthday, company, idAddress from Contact")) {
            try (ResultSet set = statement.executeQuery()) {
                while (set.next()) {
                    Contact contact = new Contact();
                    contact.setId(set.getLong("idContact"));
                    contact.setSurname(set.getString("surname"));
                    contact.setName(set.getString("name"));
                    contact.setMidName(set.getString("middName"));
                    contact.setCompany(set.getString("company"));
                    contact.setBirthday(set.getDate("birthday"));
                    //contact.setPhoto(set.getString("photo"));

                    //Получаем адрес
                    Long idAddress = set.getLong("idAddress");
                    contact.setAddress(getAdds(idAddress));

                    list.add(contact);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private Adds getAdds(Long idAddress){
        Adds address = new Adds();
        try (Connection connection = source.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Address " +
                     "WHERE idAddress = ?")) {
            statement.setString(1, Long.toString(idAddress));
            try (ResultSet set = statement.executeQuery()) {
                while (set.next()) {
                    address.setCountry(set.getString("country"));
                    address.setAddress(set.getString("address"));
                    address.setCity(set.getString("city"));
                    address.setIndex(set.getString("index"));
                    address.setIdAddress(set.getLong("idAddress"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return address;
    }
}