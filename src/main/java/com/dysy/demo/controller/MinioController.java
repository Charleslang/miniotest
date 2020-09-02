package com.dysy.demo.controller;

import com.dysy.demo.common.Result;
import com.dysy.demo.common.config.FileProperties;
import com.dysy.demo.pojo.FileUploadResult;
import com.dysy.demo.util.FileUtil;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import java.util.UUID;

/**
 * @author: Dai Junfeng
 * @create: 2020-08-31
 **/
@RestController
@RequestMapping("/upload")
public class MinioController {

    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private FileProperties fileProperties;

    @Autowired
    private MinioClient minioClient;

    @PostMapping("/test1")
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.getSize() > 0) {
            try {
                //创建一个MinIO的Java客户端
                MinioClient minioClient = new MinioClient("http://192.168.2.160", 9000,"admin", "admin123456");
                boolean isExist = minioClient.bucketExists("testone");
                if (isExist) {
                    System.out.println("桶名已经存在");;
                } else {
                    //创建存储桶并设置只读权限
                    minioClient.makeBucket("testone");
//                minioClient.setBucketPolicy(BUCKET_NAME, "*.*", PolicyType.READ_ONLY);
                }
                String fileName = file.getOriginalFilename();
                String suffixName = fileName.substring(fileName.lastIndexOf("."));
                String objectName = UUID.randomUUID().toString() + suffixName;
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//            // 设置存储对象名称
//            String objectName = sdf.format(new Date()) + "/" + filename;
                // 使用putObject上传一个文件到存储桶中
                minioClient.putObject("testone", objectName, file.getInputStream(), file.getContentType());
                System.out.println("upload successfully");
                return "http://192.168.2.160:9000/testone/" + objectName;
            } catch (Exception e) {
                System.out.println("上传发生错误: " + e.getMessage());
            }
        }
        return "fail";
    }

    @PostMapping("/test2")
    public Result upload2(@RequestParam("file") MultipartFile file) {
        if (file.getSize() > 0) {
            try {
                //创建一个MinIO的Java客户端
                MinioClient minioClient = new MinioClient("http://192.168.2.160", 9000,"admin", "admin123456");
                boolean isExist = minioClient.bucketExists("testone");
                if (isExist) {
                    System.out.println("桶名已经存在");;
                } else {
                    //创建存储桶并设置只读权限
                    minioClient.makeBucket("testone");
                }
                String fileName = file.getOriginalFilename();
                String suffixName = fileName.substring(fileName.lastIndexOf("."));
                String objectName = UUID.randomUUID().toString() + suffixName;
                String fileType = file.getContentType();
                // 使用putObject上传一个文件到存储桶中
//                minioClient.putObject("testone", objectName, file.getInputStream(), fileType);
                // 使用 application/octet-stream 上传时, 浏览器打开链接会默认进行下载而不是在浏览器中加载文件
                minioClient.putObject("testone", objectName, file.getInputStream(), "application/octet-stream");
                System.out.println("upload successfully");
//                String imageUrl = "http://192.168.2.160:9000/testone/" + objectName;
                String imageUrl = minioClient.getObjectUrl("testone", objectName);
                String downloadUri = minioClient.presignedGetObject("testone", objectName, 60 * 60 * 24);
                System.out.println(downloadUri);
                return Result.success(new FileUploadResult(fileName, file.getSize(), fileType, objectName, imageUrl, downloadUri));
            } catch (Exception e) {
                System.out.println("上传发生错误: " + e.getMessage());
            }
        }
        return null;
    }

    @PostMapping("/test3")
    public Result uploadFile(@RequestParam("file") MultipartFile file){
        if (file.getSize() > 0) {
            try {
                //创建一个MinIO的Java客户端
                MinioClient minioClient = new MinioClient("http://192.168.2.160", 9000,"admin", "admin123456");
                boolean isExist = minioClient.bucketExists("testone");
                if (isExist) {
                    System.out.println("桶名已经存在");;
                } else {
                    //创建存储桶并设置只读权限
                    minioClient.makeBucket("testone");
                }
                String fileName = file.getOriginalFilename();
                String suffixName = fileName.substring(fileName.lastIndexOf("."));
                String objectName = UUID.randomUUID().toString() + suffixName;
                String fileType = file.getContentType();
                // 使用putObject上传一个文件到存储桶中
                minioClient.putObject("testone", objectName, file.getInputStream(), fileType);
                System.out.println("upload successfully");
                String imageUrl = minioClient.getObjectUrl("testone", objectName);
                String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/upload/downloadFile/")
                        .path(objectName)
                        .toUriString();
                return Result.success(new FileUploadResult(fileName, file.getSize(), fileType, objectName, imageUrl, downloadUri));
            } catch (Exception e) {
                System.out.println("上传发生错误: " + e.getMessage());
            }
        }
        return null;
    }

    @PostMapping("/test6")
    public Result upload6(@RequestParam("file") MultipartFile file) throws Exception{
        if (file.getSize() > 0) {
            //创建一个MinIO的Java客户端
//            MinioClient minioClient = new MinioClient("http://192.168.2.160", 9000,"admin", "admin123456");
            String bucketName = fileProperties.getBucketName();
            boolean isExist = minioClient.bucketExists(bucketName);
            if (!isExist) {
                minioClient.makeBucket(bucketName);
            }
            // 原文件名
            String fileName = file.getOriginalFilename();
            // 文件类型
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            // 生成新文件名，确保唯一性
            String objectName = UUID.randomUUID().toString() + suffixName;
            // 文件类型
            String fileType = file.getContentType();
            // 使用putObject上传一个文件到存储桶中
            minioClient.putObject(bucketName, objectName, file.getInputStream(), fileType);
            // 得到文件 url
            String imageUrl = minioClient.getObjectUrl(bucketName, objectName);
            // 生成下载
            String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/upload/downloadFile/")
                    .path(objectName)
                    .toUriString();
            return Result.success(new FileUploadResult(fileName, file.getSize(), fileType, objectName, imageUrl, downloadUri));
        }
        return Result.error("未选择任何文件，上传失败");
    }

    // 正则表达式，然后结果存在filename 这个变量里
    // 比如 /downloadFile/123q, 那么 fileName 就是 123q
    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws Exception{
        // Load file as Resource
//        Resource resource = new UrlResource("http://192.168.2.160:9000/testone/" + fileName);
        String url = fileProperties.getServerPath() + "/" + fileProperties.getBucketName() + "/" + fileName;
        Resource resource = fileUtil.loadUrlAsResource(url);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
