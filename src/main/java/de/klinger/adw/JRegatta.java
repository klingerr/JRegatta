package de.klinger.adw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableJpaRepositories(basePackageClasses=RegattaRepository.class)
public class JRegatta {

    public static void main(String[] args) {
        SpringApplication.run(JRegatta.class, args);
    }
}
