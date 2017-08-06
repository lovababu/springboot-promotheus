package org.avol.promotheus;

import org.avol.promotheus.service.OrderService;
import org.avol.promotheus.service.impl.OrderServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Durga, Padala on 04/08/17.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"org.avol.promotheus"})
public class BootApp {

    public static void main(String[] args) {
        SpringApplication.run(BootApp.class, args);
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl();
    }
}
