package by.boika.handler.parser;

import org.apache.log4j.Logger;

import java.io.*;

public class TextReader {
    private final String DEFAULT_FILE_NAME = "src\\main\\resources\\text\\text.txt";
    private static final Logger LOGGER = Logger.getLogger(TextReader.class);

    public TextReader() {
    }

    public String readText (String fileName) {
        if (fileName.isEmpty()) {
            fileName = DEFAULT_FILE_NAME;
        }
        StringBuilder text = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
            String temp;
            while ((temp = bufferedReader.readLine()) != null) {
                text.append(temp + "\n");
            }
        } catch (FileNotFoundException e) {
            LOGGER.error("File not found. " + e);
        } catch (IOException e) {
            LOGGER.error("Opening IO Exception. " + e);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    LOGGER.error("Closing IO Exception. " + e);
                }
            }
        }
        return text.toString();
    }
}
