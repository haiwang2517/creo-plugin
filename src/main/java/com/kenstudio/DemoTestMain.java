package com.kenstudio;


import com.kenstudio.component.Button;
import com.kenstudio.component.UICommandFactory;
import com.kenstudio.cos.COSTransferManager;
import com.kenstudio.framework.config.LoggerConfig;
import com.kenstudio.listener.KenDemo2ButtonListener;
import com.kenstudio.listener.KenDemoButtonListener;
import com.kenstudio.listener.ScudSettingUIListener;
import com.kenstudio.listener.ScudTestUIListener;
import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSession.CreoCompatibility;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSession.pfcSession;
import com.qcloud.cos.model.GetObjectRequest;
import com.qcloud.cos.transfer.Download;
import com.qcloud.cos.transfer.TransferManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class DemoTestMain {

    static final String TEST_BUTTON_TXT = "msg_test_demo.txt";
    static Logger logger = LoggerFactory.getLogger(DemoTestMain.class);

    public static void start(){
        LoggerConfig.initialization();
        logger.info("load logback configuration success");

        // load ui buttons configuration
        loadButtonsConfiguration();
        logger.info("load test button configuration success");

        // todo load other configuration
    }

    private static void loadButtonsConfiguration(){
        try {
            Session session =
                    pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
            UICommandFactory.load(
                    session,
                    new Button.Builder()
                            .commandCode("ScudDemo")
                            .actionListener(new KenDemoButtonListener())
                            .icon("key_icon32x32.png")
                            .label("KenDemo.label")
                            .help("KenDemo.text")
                            .messageFileName(TEST_BUTTON_TXT)
                            .build());

            UICommandFactory.load(
                    session,
                    new Button.Builder()
                            .commandCode("ScudDemo@2")
                            .actionListener(new KenDemo2ButtonListener())
                            .icon("key_icon32x32.png")
                            .label("KenDemo@2.label")
                            .help("KenDemo@2.text")
                            .messageFileName(TEST_BUTTON_TXT)
                            .build());

            UICommandFactory.load(
                    session,
                    new Button.Builder()
                            .commandCode("ScudTestUI@1")
                            .actionListener(new ScudTestUIListener())
                            .icon("key_icon32x32.png")
                            .label("uiInfo.label")
                            .help("uiInfo.text")
                            .messageFileName(TEST_BUTTON_TXT)
                            .build());

            UICommandFactory.load(
                    session,
                    new Button.Builder()
                            .commandCode("Scud@SettingUI")
                            .actionListener(new ScudSettingUIListener())
                            .icon("key_icon32x32.png")
                            .label("Scud Setting Configuration")
                            .help("Scud Setting Configuration")
                            .messageFileName(TEST_BUTTON_TXT)
                            .build());

        } catch (jxthrowable e) {
            e.printStackTrace();
            logger.error("start init error", e);
        }
    }

    
    public static void stop(){
        logger.info("stop DemoTest");
        System.out.println("stop DemoTest");
    }

    public static void main(String[] args) {

        COSTransferManager cosTransferManager = new COSTransferManager();
        TransferManager transferManager = cosTransferManager.createTransferManager();

        GetObjectRequest getObjectRequest = new GetObjectRequest("scud-1256849561", "扫描01.prt.1");

        try {
            File downloadFile = new File("F:/tt/download/12.prt");
            Download download = transferManager.download(getObjectRequest, downloadFile);
            download.waitForCompletion();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cosTransferManager.shutdownTransferManager(transferManager);

        //        COSClient instance = COSFactory.getInstance();
        //        ListObjectsRequest listObjectsRequest = new ListObjectsRequest();
        //        listObjectsRequest.setBucketName("scud-1256849561");
        //        listObjectsRequest.setMaxKeys(100);
        //        ObjectListing objectListing = instance.listObjects(listObjectsRequest);
        //        List<COSObjectSummary> objectSummaries = objectListing.getObjectSummaries();
        //        for (COSObjectSummary object : objectSummaries) {
        //            logger.info("key: {}", object.getKey());
        //            logger.info("tags: {}", object.getETag());
        //            logger.info("size: {}", object.getSize());
        //        }
        //        GetObjectRequest getObjectRequest = new GetObjectRequest("scud-1256849561",
        // "扫描01.prt.1");
        //        COSObject object = instance.getObject(getObjectRequest);
        //        COSObjectInputStream objectContent = object.getObjectContent();
        //
        //        instance.shutdown();
    }
}
