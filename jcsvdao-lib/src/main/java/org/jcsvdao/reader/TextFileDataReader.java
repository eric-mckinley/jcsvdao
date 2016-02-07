package org.jcsvdao.reader;

import org.jcsvdao.handler.TextHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ericmckinley on 07/02/2016.
 */
public class TextFileDataReader implements DataReader {

    private List<TextHandler> handlers = new ArrayList<TextHandler>();

    public void register(TextHandler handler) {
        this.handlers.add(handler);
    }

    public void read(InputStream is) throws DataReaderException {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                notifyHandlers(line);
            }
        } catch (IOException e) {
            throw new DataReaderException("Unable to read text file ", e);
        } finally {
            close(is);
        }
    }

    private void notifyHandlers(String line) {
        for (int i = 0; i < handlers.size(); i++) {
            TextHandler textHandler = handlers.get(i);
            textHandler.handleLine(line);
        }
    }

    private void close(InputStream inputStream) {
        try {
            inputStream.close();
        } catch (IOException e) {
            //
        }
    }
}

