package persistence.dao.exception;

import java.sql.SQLException;

/**
 * Created by zhenya on 14.01.16.
 */
public class DaoException extends RuntimeException {

    public DaoException(Exception e) {
        super(e);
    }

    public DaoException(String message, Exception e) {
        super(message, e);
    }
}
