package com.kenstudio.framework.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class PropertiesConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesConfig.class);

    public static Map<String, Properties> PROPERTIES_MAP;

    static {
        PROPERTIES_MAP = new HashMap<>();
        PROPERTIES_MAP.put("application.properties", loadPropertiesFromFile("/application.properties"));
    }

    private static Properties loadPropertiesFromFile(String filename){
        Properties props = new Properties();
        try(InputStream stream = PropertiesConfig.class.getResourceAsStream(filename)) {
            if(null == stream){
                throw new RuntimeException("Not Found "+ filename);
            }
            props.load(new InputStreamReader(stream, StandardCharsets.UTF_8));
            return props;
        } catch (Exception e) {
            LOGGER.error("Load "+ filename +" Fail.", e);
            return props;
        }
    }
}
