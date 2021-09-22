package com.devlomose.springboot.data.jpa.app.models.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * UploadFileService at: src/main/java/com/devlomose/springboot/data/jpa/app/models/service
 * Created by @DevLomoSE at 22/9/21 9:31.
 */
public interface UploadFileService {

    public Resource load(String filename) throws MalformedURLException;

    public String copy(MultipartFile file) throws IOException;

    public boolean delete(String filename);
}
