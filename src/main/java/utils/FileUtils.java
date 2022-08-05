package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static utils.Constants.MESSAGES_PROPERTIES_PATH;


public class FileUtils {

    /**
     * Get the values of the properties from the properties
     *
     * @param key, Key of the property to get value for
     * @return propertyValue
     */
    public static synchronized String getPropertyValue(String filePath, String key) {

        String propertyValue = "message not found - ";
        try (InputStream input = new FileInputStream(filePath)) {
            Properties prop = new Properties();
            prop.load(input);
            propertyValue = prop.getProperty(key);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return propertyValue;
    }

    /**
     * Get the message value via Constants
     *
     * @param value, Constants to get value for
     * @return messageValue
     */
    public static synchronized String getMessage(String value) {
        return FileUtils.getPropertyValue(MESSAGES_PROPERTIES_PATH, value);
    }
}
