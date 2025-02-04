package org.foodics.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private Properties properties;

    public ConfigReader() {
        properties = new Properties();
        try {
            // Load the config.properties file
            String configFilePath = "src/test/resources/Config.properties";
            FileInputStream input = new FileInputStream(configFilePath);
            properties.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties file.");
        }
    }

    public String getProperty(String key) {

        return properties.getProperty(key);
    }
}