package com.kenstudio.cos;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * COSClient 是线程安全的类，允许多线程访问同一实例。因为实例内部维持了一个连接池，
 * 创建多个实例可能导致程序资源耗尽，请确保程序生命周期内实例只有一个，并在不再需要使用时，
 * 调用 shutdown 方法将其关闭。如果需要新建实例，请先将之前的实例关闭。
 *
 */
public class COSFactory {
    static Logger logger = LoggerFactory.getLogger(COSFactory.class);

    private static COSClient client;

    /**
     * 获取COSClient 实例，保证只能有一个客户端。
     * @return COSClient
     */
    public static COSClient getInstance() {
        COSConfig config = new COSConfig();
        COSCredentials cred = new BasicCOSCredentials(config.getSecretId(), config.getSecretKey());
        Region region = new Region(config.getRegionName());
        ClientConfig clientConfig = new ClientConfig(region);
        return new COSClient(cred, clientConfig);
    }
}
