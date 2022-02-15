package com.kenstudio;


import com.kenstudio.component.Button;
import com.kenstudio.component.UICommandFactory;
import com.kenstudio.config.LoggerConfig;
import com.kenstudio.listener.KenDemo2ButtonListener;
import com.kenstudio.listener.KenDemoButtonListener;
import com.ptc.cipjava.jxthrowable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoTestMain {

    static Logger logger = LoggerFactory.getLogger(DemoTestMain.class);
    static final String TEST_BUTTON_TXT = "msg_test_demo.txt";

    public static void start(){
        LoggerConfig.logbackConfiguration();
        logger.info("load logback configuration success");

        // load ui buttons configuration
        loadButtonsConfiguration();
        logger.info("load test button configuration success");

        // todo load other configuration
    }

    private static void loadButtonsConfiguration(){
        try {
            UICommandFactory.load(new Button.Builder()
                    .commandCode("KenDemo")
                    .actionListener(new KenDemoButtonListener())
                    .icon("key_icon32x32.png")
                    .label("KenDemo.label")
                    .help("KenDemo.text")
                    .messageFileName(TEST_BUTTON_TXT).build());

            UICommandFactory.load(new Button.Builder()
                    .commandCode("KenDemo@2")
                    .actionListener(new KenDemo2ButtonListener())
                    .icon("key_icon32x32.png")
                    .label("KenDemo@2.label")
                    .help("KenDemo@2.text")
                    .messageFileName(TEST_BUTTON_TXT).build());

        } catch (jxthrowable e) {
            e.printStackTrace();
            logger.error("start init error", e);
        }
    }

    
    public static void stop(){
        logger.info("stop DemoTest");
        System.out.println("stop DemoTest");
    }

}
