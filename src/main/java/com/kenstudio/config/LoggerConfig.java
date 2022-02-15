package com.kenstudio.config;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import com.kenstudio.DemoTestMain;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class LoggerConfig {

    public static void logbackConfiguration() {
        try {
            LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
            context.reset();
            JoranConfigurator configurator = new JoranConfigurator();
            InputStream stream = DemoTestMain.class.getResourceAsStream("/logback.xml");
            configurator.setContext(context);
            configurator.doConfigure(stream);
            stream.close();
        } catch (JoranException | IOException e) {
            e.printStackTrace();
        }
    }
}
