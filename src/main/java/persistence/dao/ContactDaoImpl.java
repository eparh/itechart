package persistence.dao;


import persistence.DbUtil;
import persistence.model.Adds;
import persistence.model.Attach;
import persistence.model.Contact;
import persistence.model.Phone;

import javax.sql.DataSource;
import java.sql.*;
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
                    contact.setGender(set.getString("gender"));
                    contact.setNationality(set.getString("national"));
                    contact.setSite(set.getString("website"));
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
        deleteOnStatement("delete from Contact where idContact = ?",idContact);
    }

    @Override
    public List<Phone> getPhones(Long idContact){
        List<Phone> list = new ArrayList<>();
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
    public void insertPhone(Phone phone){
        try (Connection connection = source.getConnection()){
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Telephone(countryCode,operatorCode,`number`,kind,comment," +
                    "idContact ) VALUES (?,?,?,?,?,?)")) {
                statement.setString(1,phone.getCountryCode());
                statement.setString(2,phone.getOperatorCode());
                statement.setString(3,phone.getNumber());
                statement.setString(4,phone.getKind());
                statement.setString(5,phone.getComment());
                statement.setLong(6,phone.getIdContact());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setPhones(long idContact, List<Phone> phones) {
        deletePhones(idContact);
        for(Phone phone : phones) {
            insertPhone(phone);
        }
    }

    @Override
    public long setContact(Contact contact) {
        Long idContact = contact.getId();
        if( idContact == null ) {
            return insertContact(contact);
        } else {
            updateContact(contact);
            return idContact;
        }
    }

    @Override
    public List<Attach> getAttach(Long idContact) {
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
    public List<Contact> getShowContacts() {
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

    private Adds getAdds(Long idAddress) {
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

    private long setAdds(Contact contact) {
        Adds adds = contact.getAddress();
        Long idAddress = adds.getIdAddress();
        if(idAddress == null) {
            try (Connection connection = source.getConnection();
                 PreparedStatement statement = connection.prepareStatement("INSERT INTO Address (country, city, address, `index`) VALUES (?, ?, ?, ?)",
                         Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, adds.getCountry());
                statement.setString(2, adds.getCity());
                statement.setString(3, adds.getAddress());
                statement.setString(4, adds.getIndex());

                statement.executeUpdate();

                ResultSet generatedKeys = statement.getGeneratedKeys();
                generatedKeys.next();
                return generatedKeys.getLong(1);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            try (Connection connection = source.getConnection();
                 PreparedStatement statement = connection.prepareStatement("UPDATE Address SET country = ? , city = ?, address = ?, `index` = ?" +
                         " WHERE idAddress = ?")) {
                statement.setString(1, adds.getCountry());
                statement.setString(2, adds.getCity());
                statement.setString(3, adds.getAddress());
                statement.setString(4, adds.getIndex());
                statement.setLong(5, idAddress);

                statement.executeUpdate();

                return idAddress;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void deletePhones(long idContact){
       deleteOnStatement("Delete from Telephone where idContact = ?", idContact);
    }

    private long insertContact(Contact contact) {
        try (Connection connection = source.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Contact(`name`,surname,middName,birthday,email," +
                    " gender,maritStatus,`national`, photo, website, company, idAddress ) VALUES (?,?,?,?,?,?,?,?,?,?,?,? )", Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, contact.getName());
                statement.setString(2, contact.getSurname());
                statement.setString(3, contact.getMidName());
                statement.setDate(4, contact.getBirthday());
                statement.setString(5, contact.getEmail());
                statement.setString(6, contact.getGender());
                statement.setString(7, contact.getMaritStatus());
                statement.setString(8, contact.getNationality());
                statement.setString(9, contact.getPhoto());
                statement.setString(10, contact.getSite());
                statement.setString(11, contact.getCompany());

                long id = setAdds(contact);
                statement.setLong(12, id);

                statement.executeUpdate();
                ResultSet generatedKeys = statement.getGeneratedKeys();
                generatedKeys.next();

                return generatedKeys.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateContact(Contact contact) {
        try (Connection connection = source.getConnection()) {

            try (PreparedStatement statement = connection.prepareStatement("UPDATE Contact  SET surname = ?, `name`= ?, middName = ?," +
                    " birthday = ?,  email = ?, gender = ? , maritStatus = ?, `national`= ?, " +
                    "photo = ? , website = ?, company = ?, idAddress = ? WHERE idContact = ?")) {
                statement.setString(1,contact.getSurname());
                statement.setString(2,contact.getName());
                statement.setString(3,contact.getMidName());
                statement.setDate(4,contact.getBirthday());
                statement.setString(5,contact.getEmail());
                statement.setString(6,contact.getGender());
                statement.setString(7,contact.getMaritStatus());
                statement.setString(8,contact.getNationality());
                statement.setString(9,contact.getPhoto());
                statement.setString(10,contact.getSite());
                statement.setString(11,contact.getCompany());

                long id = setAdds(contact);
                statement.setLong(12, id);

                statement.setLong(13, contact.getId());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteOnStatement(String string, long idContact){
        try (Connection connection = source.getConnection()){
            PreparedStatement statement = connection.prepareStatement(string);
            statement.setLong(1, idContact);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}