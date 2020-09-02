package com.dysy.demo.util;

import com.dysy.demo.common.exception.FileException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;

/**
 * @author: Dai Junfeng
 * @create: 2020-08-31
 **/
@Component
public class FileUtil {

    /**
     * 加载文件
     * @param url 文件路径
     * @return 文件
     */
    public Resource loadUrlAsResource(String url) {
        try {
            Resource resource = new UrlResource(url);
            if(resource.exists()) {
                return resource;
            } else {
                throw new FileException("File not found " + url);
            }
        } catch (MalformedURLException ex) {
            throw new FileException("File not found " + url, ex);
        }
    }
}
