package com.dysy.demo.common.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Dai Junfeng
 * @create: 2020-08-31
 **/
@Configuration
public class BeanConfig {

    @Autowired
    private FileProperties fileProperties;

    @Bean
    public MinioClient minioClient() throws Exception{
        MinioClient minioClient = new MinioClient(fileProperties.getServerPath(),
                fileProperties.getAccessKey(), fileProperties.getSecureKey());
        return minioClient;
    }
}
