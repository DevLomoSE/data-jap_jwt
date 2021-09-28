package com.devlomose.springboot.data.jpa.app;

import com.devlomose.springboot.data.jpa.app.models.service.UploadFileServiceImplement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootDataJpaApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(SpringBootDataJpaApplication.class);

    @Autowired
    UploadFileServiceImplement uploadFileServiceImplement;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDataJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        uploadFileServiceImplement.deleteAll();
        uploadFileServiceImplement.init();
    }

}
