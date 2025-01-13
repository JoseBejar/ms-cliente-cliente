package com.pruebatecnica.jose;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MsUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsUserApplication.class, args);
    }

}
