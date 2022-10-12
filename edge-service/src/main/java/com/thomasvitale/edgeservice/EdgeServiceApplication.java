package com.thomasvitale.edgeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class EdgeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EdgeServiceApplication.class, args);
    }

    @Bean
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
            .authorizeExchange(exchange -> exchange.anyExchange().authenticated())
            .oauth2Login(Customizer.withDefaults())
            .build();
    }

}

@RestController
class WelcomeController {

    record Welcome(String message){}

    @GetMapping("welcome")
    Mono<Welcome> getWelcome() {
        return Mono.just(new Welcome("Welcome to Polar Bookshop!"));
    }

}
