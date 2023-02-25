package com.back.backend;

import com.back.backend.service.user.AddUserService;
import com.back.backend.service.user.UpdateUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class BackendApplicationTests {

    @Autowired
    UpdateUserService updateUserService;
    @Test
    void contextLoads() {
        Map<String, String> map = new HashMap<>();

        map.put("id", "1");
        map.put("name", "李四");
        map.put("password", "123456");
        map.put("role", "病人");
        map.put("tel", "110");
        map.put("age", "34");
        map.put("sex", "男");

        map.put("address", "成华大道");
        map.put("time", "2023-02-25");
        updateUserService.updateUser(map);
    }

}
