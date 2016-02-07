package org.jcsvdao.reader;

import java.io.InputStream;

/**
 * Created by ericmckinley on 07/02/2016.
 */
public interface DataReader {

    public void read(InputStream is) throws DataReaderException;
}
