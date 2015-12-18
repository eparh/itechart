package persistence;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class DbUtil {

    public static DataSource getMySQLDataSource() {
        Properties props = new Properties();
        FileInputStream fis = null;
        MysqlDataSource mysqlDS = null;
        // fis = new FileInputStream("classpath: /db.properties");
        // props.load(fis);
        mysqlDS = new MysqlDataSource();
        mysqlDS.setURL("jdbc:mysql://localhost:3306/mydb");
        mysqlDS.setUser("root");
        mysqlDS.setPassword("");
        mysqlDS.setCharacterEncoding("UTF-8");
        //mysqlDS.setUseUnicode(Boolean.getBoolean(props.getProperty("MYSQL_USE_UNICODE")));
        mysqlDS.setUseUnicode(true);
        return mysqlDS;
    }
}
