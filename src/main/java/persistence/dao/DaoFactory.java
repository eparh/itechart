package persistence.dao;

public class DaoFactory {
    public static ContactDao getContactDao(){
        return ContactDaoImpl.INSTANCE;
    }
}
