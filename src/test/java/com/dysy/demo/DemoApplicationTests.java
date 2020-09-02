package com.dysy.demo;

import io.minio.MinioClient;
import io.minio.errors.MinioException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() throws Exception{
        try {
            // 使用MinIO服务的URL，端口，Access key和Secret key创建一个MinioClient对象
//            MinioClient minioClient = new MinioClient("http://192.168.2.160:9000", "admin", "admin123456");
            MinioClient minioClient = new MinioClient("http://192.168.2.160", 9000,"admin", "admin123456");

            // 检查存储桶是否已经存在
            boolean isExist = minioClient.bucketExists("testone");
            if(isExist) {
                System.out.println("Bucket already exists.");
            } else {
                // 创建一个名为asiatrip的存储桶，用于存储照片的zip文件。
                minioClient.makeBucket("testone");
            }

            // 使用putObject上传一个文件到存储桶中。
            // 参数分别是桶名、服务器端保存的文件名、文件的本地路径
            minioClient.putObject("testone","test1.jpg", "D:\\wallpapers\\p39.jpg");
            System.out.println("upload successfully");
        } catch(MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }

    @Test
    void test2() {

    }

}
