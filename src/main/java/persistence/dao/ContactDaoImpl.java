package persistence.dao;


import persistence.DbUtil;
import persistence.model.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDaoImpl implements ContactDao {
    public static final ContactDao INSTANCE = new ContactDaoImpl();
    private DataSource source = DbUtil.getMySQLDataSource();
    private String query;
    private List<String> paramStrList;
    private List<Date> paramDateList;

    private ContactDaoImpl() {

    }

    @Override
    public Contact getById(Long idContact) {
        Contact contact = new Contact();
        try (Connection connection = source.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Contact AS c LEFT JOIN Address AS a " +
                     "ON c.idAddress = a.idAddress WHERE idContact = ?")) {
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
                    Adds adds = new Adds();
                    adds.setIdAddress(set.getLong("idAddress"));
                    adds.setCountry(set.getString("country"));
                    adds.setCity(set.getString("city"));
                    adds.setAddress(set.getString("address"));
                    adds.setIndex(set.getString("index"));
                    contact.setAddress(adds);
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
    public String getPhoto(long idContact) {
        String path = null;
        try (Connection connection = source.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT photo FROM Contact WHERE idContact = ?")) {
            statement.setLong(1, idContact);
            try (ResultSet set = statement.executeQuery()) {
                if (set.next()) {
                   path = set.getString("photo");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return path;
    }

    @Override
    public void setPhoto(long idContact, String path) {
        try (Connection connection = source.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("UPDATE Contact SET `photo`= ? WHERE `idContact`= ?")) {
                statement.setString(1, path);
                statement.setLong(2,idContact);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long countContacts(SearchCriteria criteria) {
        long total = 0;
        paramStrList = new ArrayList<>();
        paramDateList = new ArrayList<>();
        query = "SELECT COUNT(*) AS total from Contact AS c LEFT JOIN Address AS a ON c.idAddress = a.idAddress";
        query = makeSelectQuery(query,criteria);
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
            throw new RuntimeException(e);
        }
        return total;
    }

    @Override
    public List<Attach> getAttach(Long idContact) {
        List<Attach> list = new ArrayList<Attach>();
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
    public List<Contact> getShowContacts(SearchCriteria criteria, ViewSettings settings) {
        paramStrList = new ArrayList<>();
        paramDateList = new ArrayList<>();
        List<Contact> list = new ArrayList<>();
        query = "SELECT idContact, `name`, surname, middName, birthday, company, country, city, address," +
                " `index` from Contact AS c LEFT JOIN Address AS a ON c.idAddress = a.idAddress";
        query = makeSelectQuery(query,criteria);
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
            statement.setLong(++index,settings.getCount());
            try (ResultSet set = statement.executeQuery()) {
                while (set.next()) {
                    Contact contact = new Contact();
                    contact.setId(set.getLong("idContact"));
                    contact.setSurname(set.getString("surname"));
                    contact.setName(set.getString("name"));
                    contact.setMidName(set.getString("middName"));
                    contact.setCompany(set.getString("company"));
                    contact.setBirthday(set.getDate("birthday"));

                    Adds adds = new Adds();
                    adds.setCountry(set.getString("country"));
                    adds.setCity(set.getString("city"));
                    adds.setAddress(set.getString("address"));
                    adds.setIndex(set.getString("index"));
                    contact.setAddress(adds);

                    list.add(contact);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
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
                    " gender,maritStatus,`national`, website, company, idAddress ) VALUES (?,?,?,?,?,?,?,?,?,?,? )", Statement.RETURN_GENERATED_KEYS)) {
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

    private String makeSelectQuery(String query,SearchCriteria criteria) {
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
        if (criteria.getBirthday_from() != null && criteria.getBirthday_to() != null) {
            query += word + "birthday BETWEEN ? AND ?";
            paramDateList.add(criteria.getBirthday_from());
            paramDateList.add(criteria.getBirthday_to());
        } else {
            if (criteria.getBirthday_from() != null) {
                query += word + "birthday >= ?";
                paramDateList.add(criteria.getBirthday_from());
            }
            if (criteria.getBirthday_to() != null) {
                query += word + "birthday <= ?";
                paramDateList.add(criteria.getBirthday_to());
            }
        }
        return query;
    }
}