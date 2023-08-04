package com.example.smarteducation;

import com.example.smarteducation.utils.Role;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SmartEducationApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(Role.str2num("admin"));
        System.out.println(Role.num2str(1));
        System.out.println(Role.num2str(1));
    }

}
