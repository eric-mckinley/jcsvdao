package org.jcsvdao.reader;

/**
 * Created by ericmckinley on 07/02/2016.
 */
public class DataReaderException extends Exception {

    public DataReaderException(String message) {
        super(message);
    }

    public DataReaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
