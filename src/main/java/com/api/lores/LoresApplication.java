package com.api.lores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.api.lores.log.Logging.LOGGER;

@SpringBootApplication
public class LoresApplication {

    public static void main(String[] args) {
        LOGGER.info("Starting the Lores API");
        SpringApplication.run(LoresApplication.class, args);
        System.out.println(new BCryptPasswordEncoder().encode("senha123"));
    }

}

