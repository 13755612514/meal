package com.xyc.mealoperation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@SpringBootApplication
public class MealOperationApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(MealOperationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
