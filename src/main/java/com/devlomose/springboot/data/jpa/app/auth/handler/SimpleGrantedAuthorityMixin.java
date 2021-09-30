package com.devlomose.springboot.data.jpa.app.auth.handler;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * SimpleGrantedAuthorityMixin at: src/main/java/com/devlomose/springboot/data/jpa/app/auth/handler
 * Created by @DevLomoSE at 29/9/21 20:54.
 */
public abstract class SimpleGrantedAuthorityMixin {

    @JsonCreator
    public SimpleGrantedAuthorityMixin(@JsonProperty("authority") String role) {
    }
}
