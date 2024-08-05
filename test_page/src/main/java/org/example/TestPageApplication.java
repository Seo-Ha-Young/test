package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@SpringBootApplication
public class TestPageApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestPageApplication.class, args);
    }

}
