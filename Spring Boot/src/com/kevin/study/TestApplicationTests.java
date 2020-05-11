package com.kevin.study;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TestApplicationTests {
    @Autowired
    private Car car;

    @Autowired
    private Driver driver;

    @Test
    public void contextLoads() {
        boolean result = driver.getCar() == car;
        System.out.println(result ? "同一个car" : "不同的car");
    }
}
