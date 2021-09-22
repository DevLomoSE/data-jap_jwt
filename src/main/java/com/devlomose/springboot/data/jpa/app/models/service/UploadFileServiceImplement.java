package com.devlomose.springboot.data.jpa.app.models.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * UploadFileServiceImplement at: src/main/java/com/devlomose/springboot/data/jpa/app/models/service
 * Created by @DevLomoSE at 22/9/21 9:33.
 */
@Service
public class UploadFileServiceImplement implements UploadFileService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final String UPLOADS_DIR = "uploads";

    @Override
    public Resource load(String filename) throws MalformedURLException {
        Path pathPhoto = getPath(filename);
        logger.info("pathPhoto" + pathPhoto);

        Resource recurso = null;
        recurso = new UrlResource(pathPhoto.toUri());
        if(!recurso.exists() && !recurso.isReadable()){
            logger.error("Error el recurso "+pathPhoto.toString()+" no existe y/o no se puede leer");
            throw new RuntimeException("Error: no se puede cargar la imagen "+pathPhoto.toString());
        }

        return recurso;
    }

    @Override
    public String copy(MultipartFile file) throws IOException {
        String uniqueFileName = UUID.randomUUID().toString() +"_"+ file.getOriginalFilename();

        Path rootPath = Paths.get("uploads").resolve( uniqueFileName );

        logger.info("rootPath " + rootPath);
        Files.copy(file.getInputStream(), rootPath);

        return uniqueFileName;
    }

    @Override
    public boolean delete(String filename) {
        Path rootPath = getPath(filename);
        File foto = rootPath.toFile();
        if(foto.exists() && foto.canRead()){
            if(foto.delete()){
                return true;
            }
        }
        return false;
    }

    public Path getPath(String filename){
        return Paths.get(UPLOADS_DIR).resolve(filename).toAbsolutePath();
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(Paths.get(UPLOADS_DIR).toFile());
    }

    @Override
    public void init() throws IOException {
        Files.createDirectory(Paths.get(UPLOADS_DIR));
    }
}
