package com.devlomose.springboot.data.jpa.app;

import com.devlomose.springboot.data.jpa.app.models.service.UploadFileServiceImplement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringBootDataJpaApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(SpringBootDataJpaApplication.class);

    @Autowired
    UploadFileServiceImplement uploadFileServiceImplement;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDataJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        uploadFileServiceImplement.deleteAll();
        uploadFileServiceImplement.init();

        String password = "password";
        for(int i=0; i<2; i++){
            String bcryptPassword = passwordEncoder.encode(password);
            logger.info("password generado '"+bcryptPassword+"'");
        }

        password = "sudo";
        String bcryptPassword = passwordEncoder.encode(password);
        logger.info("password generado '"+bcryptPassword+"'");
    }

}
