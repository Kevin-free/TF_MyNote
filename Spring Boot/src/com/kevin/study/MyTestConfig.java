package com.kevin.study;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyTestConfig {
    @Bean
    public Driver driver(){
        Driver driver = new Driver();
        driver.setId(1);
        driver.setName("driver");
        driver.setCar(car());
        return driver;
    }

    @Bean
    public Car car(){
        Car car = new Car();
        car.setId(1);
        car.setName("car");
        return car;
    }
}
