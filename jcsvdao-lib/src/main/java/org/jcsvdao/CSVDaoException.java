package org.jcsvdao;

/**
 * Created by ericmckinley on 07/02/2016.
 */
public class CSVDaoException extends RuntimeException {

    public CSVDaoException(String message) {
        super(message);
    }

    public CSVDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
