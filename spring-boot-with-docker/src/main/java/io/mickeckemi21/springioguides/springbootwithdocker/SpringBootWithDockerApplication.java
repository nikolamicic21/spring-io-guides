package io.mickeckemi21.springioguides.springbootwithdocker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBootWithDockerApplication {

    @GetMapping("/")
    public String home() {
        return "Hello Docker World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWithDockerApplication.class, args);
    }

}