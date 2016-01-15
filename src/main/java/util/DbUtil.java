package util;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import persistence.dao.exception.DaoException;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

public class DbUtil {
    private static final DataSource dataSource = initDataSource();

    public static DataSource getDataSource() {
        return dataSource;
    }

    private static DataSource initDataSource() {

        Properties properties = new Properties();
        try {
            properties.load(DbUtil.class.getResourceAsStream("/db.properties"));
        } catch (IOException e) {
            throw new DaoException("Error while getting data source",e);
        }
        MysqlDataSource mysqlDS = new MysqlDataSource();
        mysqlDS.setURL(properties.getProperty("MYSQL_DB_URL"));
        mysqlDS.setUser(properties.getProperty("MYSQL_DB_USERNAME"));
        mysqlDS.setPassword(properties.getProperty("MYSQL_DB_PASSWORD"));
        mysqlDS.setCharacterEncoding(properties.getProperty("MYSQL_CHAR_ENCODING"));
        mysqlDS.setUseUnicode(Boolean.getBoolean(properties.getProperty("MYSQL_USE_UNICODE")));
        mysqlDS.setUseUnicode(true);
        return mysqlDS;
    }
}
