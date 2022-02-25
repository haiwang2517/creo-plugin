package com.kenstudio.cos;

import com.kenstudio.framework.config.PropertiesConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class COSConfig {
    static Logger logger = LoggerFactory.getLogger(COSConfig.class);

    String secretId;

    String secretKey;

    String regionName;

    public COSConfig (){
        Properties props = PropertiesConfig.PROPERTIES_MAP.get("application.properties");
        this.secretId = props.getProperty("COS.SECRETID");
        this.secretKey = props.getProperty("COS.SECRETKEY");
        this.regionName = props.getProperty("COS.REGION_NAME");
    }

    public static void main(String[] args) {
        COSConfig config = new COSConfig();
        logger.info(config.getSecretId());
        logger.info(config.getSecretKey());
        logger.info(config.getRegionName());
    }

    public String getSecretId() {
        return secretId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getRegionName() {
        return regionName;
    }

    @Override
    public String toString() {
        return "COSConfig{" +
                "secretId='" + secretId + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", regionName='" + regionName + '\'' +
                '}';
    }
}
