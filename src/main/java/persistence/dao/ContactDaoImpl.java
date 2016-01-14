package persistence.dao;

import persistence.dao.exception.DaoException;
import util.DbUtil;
import persistence.model.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
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
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Contact AS c LEFT JOIN" +
                     " Address AS a ON c.idAddress = a.idAddress WHERE idContact = ?")) {
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

                    //Getting address
                    Address address = new Address();
                    address.setIdAddress(set.getLong("idAddress"));
                    address.setCountry(set.getString("country"));
                    address.setCity(set.getString("city"));
                    address.setAddress(set.getString("address"));
                    address.setIndex(set.getString("index"));
                    contact.setAddress(address);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error while getting contacts by ID", e);
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
            throw new DaoException("Error while getting phones of contact", e);
        }
        return list;
    }

    @Override
    public void insertPhone(Phone phone){
        try (Connection connection = source.getConnection()){
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Telephone(countryCode," +
                    "operatorCode,`number`,kind,comment,idContact ) VALUES (?,?,?,?,?,?)")) {
                statement.setString(1,phone.getCountryCode());
                statement.setString(2,phone.getOperatorCode());
                statement.setString(3,phone.getNumber());
                statement.setString(4,phone.getKind());
                statement.setString(5,phone.getComment());
                statement.setLong(6,phone.getIdContact());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("Error while inserting phone of contact", e);
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
    public String getPhoto(long idContact) {
        String path = null;
        try (Connection connection = source.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT photo FROM Contact" +
                     " WHERE idContact = ?")) {
            statement.setLong(1, idContact);
            try (ResultSet set = statement.executeQuery()) {
                if (set.next()) {
                   path = set.getString("photo");
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error while getting photo of contact", e);
        }
        return path;
    }

    @Override
    public void setPhoto(long idContact, String path) {
        try (Connection connection = source.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("UPDATE Contact SET photo = ? " +
                    "WHERE idContact = ?")) {
                statement.setString(1, path);
                statement.setLong(2,idContact);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("Error while setting photo of contact", e);
        }
    }

    @Override
    public long countContacts(SearchCriteria criteria) {
        long total = 0;
        List<String> paramStrList = new ArrayList<>();
        List<Date> paramDateList = new ArrayList<>();
        String query = "SELECT COUNT(*) AS total from Contact AS c LEFT JOIN Address AS a ON c.idAddress = a.idAddress";
        query = makeSelectQuery(query,criteria, paramStrList, paramDateList);
        System.out.println("totalQuery:"+query);
        try (Connection connection = source.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            int index = 1;
            for(String param: paramStrList){
                statement.setString(index, param);
                index ++;
            }
            for(Date date: paramDateList){
                statement.setDate(index, date);
                index ++;
            }
            try (ResultSet set = statement.executeQuery()) {
                while (set.next()) {
                    total = set.getLong("total");
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error while counting contacts", e);
        }
        return total;
    }

    @Override
    public List<Attach> getAttaches(Long idContact) {
        List<Attach> attaches = new ArrayList<>();
        try (Connection connection = source.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Attachment " +
                     "WHERE idContact = ?")) {
            statement.setLong(1, idContact);
            try (ResultSet set = statement.executeQuery()) {
                while (set.next()) {
                    Attach attach = new Attach();
                    attach.setIdContact(set.getLong("idContact"));
                    attach.setComment(set.getString("comment"));
                    attach.setName(set.getString("name"));
                    attach.setDate(set.getDate("date"));
                    attach.setPath(set.getString("path"));
                    attach.setIdAttach(set.getLong("idAttach"));
                    attaches.add(attach);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error while getting attachment of contact", e);
        }
        return attaches;
    }

    @Override
    public void insertAttach(Attach attach) {
        try (Connection connection = source.getConnection()){
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Attachment(path,`name`," +
                    "`date`,comment,idContact ) VALUES (?,?,?,?,?)")) {
                statement.setString(1, attach.getPath());
                statement.setString(2, attach.getName());
                statement.setDate(3, attach.getDate());
                statement.setString(4, attach.getComment());
                statement.setLong(5,attach.getIdContact());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("Error while inserting contact's attachment", e);
        }
    }

    @Override
    public void setAttaches(long idContact, Collection<Attach> attaches) {
        deleteAttaches(idContact);
        attaches.forEach(this::insertAttach);
    }

    @Override
    public List<Contact> forBirthdayContacts() {
        List<Contact> list = new ArrayList<>();
        try (Connection connection = source.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT `name`, surname, middName, birthday," +
                     " FROM Contact")) {
            try (ResultSet set = statement.executeQuery()) {
                while (set.next()) {
                    Contact contact = new Contact();
                    contact.setSurname(set.getString("surname"));
                    contact.setName(set.getString("name"));
                    contact.setMidName(set.getString("middName"));
                    contact.setBirthday(set.getDate("birthday"));

                    list.add(contact);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error while getting birthday contacts", e);
        }
        return list;
    }

    @Override
    public List<Contact> getToShowContacts(SearchCriteria criteria, ViewSettings settings) {
        List<String> paramStrList = new ArrayList<>();
        List<Date> paramDateList = new ArrayList<>();
        List<Contact> list = new ArrayList<>();
        String query = "SELECT idContact, `name`, surname, middName, birthday, company, country, city, address," +
                " email,`index` from Contact AS c LEFT JOIN Address AS a ON c.idAddress = a.idAddress";
        query = makeSelectQuery(query,criteria, paramStrList, paramDateList);
        query +=" LIMIT ? , ?";
        try (Connection connection = source.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            int index = 1;
            for(String param: paramStrList){
                statement.setString(index, param);
                index ++;
            }
            for(Date date: paramDateList){
                statement.setDate(index, date);
                index ++;
            }
            statement.setLong(index,settings.getStart());
            statement.setLong(index + 1,settings.getCount());
            try (ResultSet set = statement.executeQuery()) {
                while (set.next()) {
                    Contact contact = new Contact();
                    contact.setId(set.getLong("idContact"));
                    contact.setSurname(set.getString("surname"));
                    contact.setName(set.getString("name"));
                    contact.setMidName(set.getString("middName"));
                    contact.setCompany(set.getString("company"));
                    contact.setBirthday(set.getDate("birthday"));
                    contact.setEmail(set.getString("email"));

                    Address address = new Address();
                    address.setCountry(set.getString("country"));
                    address.setCity(set.getString("city"));
                    address.setAddress(set.getString("address"));
                    address.setIndex(set.getString("index"));
                    contact.setAddress(address);

                    list.add(contact);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error while getting contacts for showing", e);
        }
        return list;
    }

    private long setAdds(Contact contact) {
        Address address = contact.getAddress();
        Long idAddress = address.getIdAddress();
        if(idAddress == null) {
            try (Connection connection = source.getConnection();
                 PreparedStatement statement = connection.prepareStatement("INSERT INTO Address " +
                         "(country, city, address, `index`) VALUES (?, ?, ?, ?)",
                         Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, address.getCountry());
                statement.setString(2, address.getCity());
                statement.setString(3, address.getAddress());
                statement.setString(4, address.getIndex());

                statement.executeUpdate();

                ResultSet generatedKeys = statement.getGeneratedKeys();
                generatedKeys.next();
                return generatedKeys.getLong(1);
            } catch (SQLException e) {
                throw new DaoException("Error while inserting address of contact", e);
            }
        } else {
            try (Connection connection = source.getConnection();
                 PreparedStatement statement = connection.prepareStatement("UPDATE Address " +
                         "SET country = ? , city = ?, address = ?, `index` = ?" +
                         " WHERE idAddress = ?")) {
                statement.setString(1, address.getCountry());
                statement.setString(2, address.getCity());
                statement.setString(3, address.getAddress());
                statement.setString(4, address.getIndex());
                statement.setLong(5, idAddress);

                statement.executeUpdate();

                return idAddress;
            } catch (SQLException e) {
                throw new DaoException("Error while updating address of contact", e);
            }
        }
    }

    private void deletePhones(long idContact) {
        deleteOnStatement("Delete from Telephone where idContact = ?", idContact);
    }

    private void deleteAttaches(long idContact) {
        deleteOnStatement("Delete from Attachment where idContact = ?", idContact);
    }

    private long insertContact(Contact contact) {
        try (Connection connection = source.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Contact(`name`,surname," +
                    "middName,birthday,email," +
                    " gender,maritStatus,`national`, website, company, idAddress ) VALUES (?,?,?,?,?,?,?,?,?,?,? )",
                    Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, contact.getName());
                statement.setString(2, contact.getSurname());
                statement.setString(3, contact.getMidName());
                statement.setDate(4, contact.getBirthday());
                statement.setString(5, contact.getEmail());
                statement.setString(6, contact.getGender());
                statement.setString(7, contact.getMaritStatus());
                statement.setString(8, contact.getNationality());
                statement.setString(9, contact.getSite());
                statement.setString(10, contact.getCompany());

                long id = setAdds(contact);
                statement.setLong(11, id);

                statement.executeUpdate();
                ResultSet generatedKeys = statement.getGeneratedKeys();

                generatedKeys.next();
                return generatedKeys.getLong(1);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while inserting contact", e);
        }
    }

    private void updateContact(Contact contact) {
        try (Connection connection = source.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("UPDATE Contact  SET surname = ?, " +
                    "`name`= ?, middName = ?," +
                    " birthday = ?,  email = ?, gender = ? , maritStatus = ?, `national`= ?, " +
                    "website = ?, company = ?, idAddress = ? WHERE idContact = ?")) {
                statement.setString(1,contact.getSurname());
                statement.setString(2,contact.getName());
                statement.setString(3,contact.getMidName());
                statement.setDate(4,contact.getBirthday());
                statement.setString(5,contact.getEmail());
                statement.setString(6,contact.getGender());
                statement.setString(7,contact.getMaritStatus());
                statement.setString(8,contact.getNationality());
                statement.setString(9,contact.getSite());
                statement.setString(10,contact.getCompany());

                long addressId = setAdds(contact);
                statement.setLong(11, addressId);

                statement.setLong(12, contact.getId());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("Error while updating contact", e);
        }
    }

    private void deleteOnStatement(String string, long idContact){
        try (Connection connection = source.getConnection()){
            PreparedStatement statement = connection.prepareStatement(string);
            statement.setLong(1, idContact);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Error while deleting of phones or attaches", e);
        }
    }

    private String makeSelectQuery(String query,SearchCriteria criteria,
                                   List<String> paramStrList, List<Date> paramDateList) {
        String word = " WHERE ";
        if (criteria.getName() != null && ! "".equals(criteria.getName())) {
            query += word +"`name` = ?";
            paramStrList.add(criteria.getName());
            word = " AND ";
        }
        if (criteria.getSurname() != null && ! "".equals(criteria.getSurname())) {
            query += word +"surname = ?";
            paramStrList.add(criteria.getSurname());
            word = " AND ";
        }
        if (criteria.getMidName() != null && ! "".equals(criteria.getMidName())) {
            query += word +"middname = ?";
            paramStrList.add(criteria.getMidName());
            word = " AND ";
        }
        if (criteria.getGender() != null && ! "".equals(criteria.getGender())) {
            query += word +"gender = ?";
            paramStrList.add(criteria.getGender());
            word = " AND ";
        }
        if (criteria.getNationality() != null && ! "".equals(criteria.getNationality())) {
            query += word +"national = ?";
            paramStrList.add(criteria.getNationality());
            word = " AND ";
        }
        if (criteria.getMaritStatus() != null && ! "".equals(criteria.getMaritStatus())) {
            query += word+ "maritStatus = ?";
            paramStrList.add(criteria.getMaritStatus());
            word = " AND ";
        }
        if (criteria.getCountry() != null && ! "".equals(criteria.getCountry())) {
            query += word+ "country = ?";
            paramStrList.add(criteria.getCountry());
            word = " AND ";
        }
        if (criteria.getCity() != null && ! "".equals(criteria.getCity())) {
            query += word+ "city = ?";
            paramStrList.add(criteria.getCity());
            word = " AND ";
        }
        if (criteria.getAddress() != null && ! "".equals(criteria.getAddress())) {
            query += word+ "address = ?";
            paramStrList.add(criteria.getAddress());
            word = " AND ";
        }
        if (criteria.getIndex() != null && ! "".equals(criteria.getIndex())) {
            query += word+ "`index` = ?";
            paramStrList.add(criteria.getIndex());
            word = " AND ";
        }
        if (criteria.getBirthdayFrom() != null && criteria.getBirthdayTo() != null) {
            query += word + "birthday BETWEEN ? AND ?";
            paramDateList.add(criteria.getBirthdayFrom());
            paramDateList.add(criteria.getBirthdayTo());
        } else {
            if (criteria.getBirthdayFrom() != null) {
                query += word + "birthday >= ?";
                paramDateList.add(criteria.getBirthdayFrom());
            }
            if (criteria.getBirthdayTo() != null) {
                query += word + "birthday <= ?";
                paramDateList.add(criteria.getBirthdayTo());
            }
        }
        return query;
    }
}