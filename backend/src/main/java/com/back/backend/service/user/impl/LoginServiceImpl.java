package com.back.backend.service.user.impl;

import com.back.backend.Mapper.UserMapper;
import com.back.backend.pojo.User;
import com.back.backend.service.user.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserMapper userMapper;



    @Override
    public Map<String, String> login(String name, String password) {

        Map<String, String> resp = new HashMap<>();

        User user = userMapper.getUserByName(name);

        if(user != null && user.getPassword().equals(password) && "管理员".equals(user.getRole())) {
            resp.put("info", "success");

        } else {
            resp.put("info", "error");
        }

        return resp;
    }
}
