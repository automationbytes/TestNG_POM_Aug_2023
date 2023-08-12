package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    public static String readProperties(String key){
        Properties properties = new Properties();
        String value = null;
        try {
            FileInputStream file = new FileInputStream("src/test/resources/ApplicationConfig.properties");
            properties.load(file);
            value = properties.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }


}
