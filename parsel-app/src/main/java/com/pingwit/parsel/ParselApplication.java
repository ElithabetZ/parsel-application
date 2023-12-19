package com.pingwit.parsel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(repositoryImplementationPostfix = "Imp")
@SpringBootApplication
public class ParselApplication {
    public static void main(String[] args) {SpringApplication.run(ParselApplication.class, args);}
}