package com.dysy.demo.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: Dai Junfeng
 * @create: 2020-08-31
 **/
@ConfigurationProperties(prefix = "file")
public class FileProperties {
    private String bucketName;
    private String serverPath;
    private Integer port;
    private String accessKey;
    private String secureKey;

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getServerPath() {
        return serverPath;
    }

    public void setServerPath(String serverPath) {
        this.serverPath = serverPath;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecureKey() {
        return secureKey;
    }

    public void setSecureKey(String secureKey) {
        this.secureKey = secureKey;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
