package com.kevin.studyjpa;

import com.kevin.studyjpa.entity.User;
import com.kevin.studyjpa.repository.UserRepository;
import net.minidev.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class StudyJpaApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    void userTest() {
        User user = new User();
        user.setUserName("kevin");
        user.setAge(30);
        user.setPassword("123");
        userRepository.save(user);
        User item = userRepository.findByUserName("kevin");
        System.out.printf("user:", user);
    }

}
