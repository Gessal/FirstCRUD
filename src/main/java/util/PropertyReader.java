package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    public static Properties getProperties(String fileName) throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = PropertyReader.class.getClassLoader().getResourceAsStream(fileName);
        properties.load(inputStream);
        return properties;
    }
}
