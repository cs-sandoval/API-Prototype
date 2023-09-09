package com.csc340IA2.APIPrototype;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;

/**
 * @author Cindy Sandoval
 * Individual Assignment #2 - API Prototype
 * @since 09/08/2023
 * CSC 340-02
 */
@SpringBootApplication
public class ApiPrototypeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiPrototypeApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            String apiUrl = "https://www.themealdb.com/api/json/v1/1/categories.php";
            Map<String, List<Map<String, String>>> response = restTemplate.getForObject(apiUrl, Map.class);

            if (response != null && response.containsKey("categories")) {
                List<Map<String, String>> categories = response.get("categories");
                for (Map<String, String> category : categories) {
                    System.out.println("Category: " + category.get("strCategory"));
                }
            }
        };
    }
}
