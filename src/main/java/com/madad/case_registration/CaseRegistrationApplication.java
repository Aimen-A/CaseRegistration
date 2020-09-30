package com.madad.case_registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CaseRegistrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaseRegistrationApplication.class, args);
    }
}
