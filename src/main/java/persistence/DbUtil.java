package persistence;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class DbUtil {
   // private DataSource dataSource = DbUtil.getDataSource();

    //public Contact get() {
      //  Connection connection = dataSource.getConnection();
   // }
    public static DataSource getMySQLDataSource() {
        Properties props = new Properties();
        FileInputStream fis = null;
        MysqlDataSource mysqlDS = null;
        try {
            fis = new FileInputStream("db.properties");
            props.load(fis);
            mysqlDS = new MysqlDataSource();
            mysqlDS.setURL(props.getProperty("MYSQL_DB_URL"));
            mysqlDS.setUser(props.getProperty("MYSQL_DB_USERNAME"));
            mysqlDS.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
            mysqlDS.setCharacterEncoding(props.getProperty("MYSQL_CHAR_ENCODING"));
            mysqlDS.setUseUnicode(Boolean.getBoolean(props.getProperty("MYSQL_USE_UNICODE")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mysqlDS;
    }
}
