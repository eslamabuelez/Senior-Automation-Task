package org.foodics.api.apiRequests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class APIConfigReader {
    private Properties properties;

    // ✅ Corrected Constructor Name to match class name
    public APIConfigReader() {
        properties = new Properties();
        try {
            // Load the config.properties file
            String configFilePath = "src/main/resources/APIConfig.properities";
            FileInputStream input = new FileInputStream(configFilePath);
            properties.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties file.");
        }
    }

    // Method to fetch the property value by key
    public String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property " + key + " not found in config file.");
        }
        return value;
    }
}
