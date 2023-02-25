package com.back.backend.service.user.impl;

import com.back.backend.Mapper.UserMapper;
import com.back.backend.pojo.User;
import com.back.backend.service.user.UpdateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UpdateUserServiceImpl implements UpdateUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Map<String, String> updateUser(Map<String, String> map) {
        String name, password, role, tel, sex, address;
        Integer id, age;

        Map<String, String> resp = new HashMap<>();

        try {
            name = map.get("name");
            password = map.get("password");
            role = map.get("role");
            tel = map.get("tel");
            sex = map.get("sex");
            address = map.get("address");

            age = Integer.parseInt(map.get("age"));
            id = Integer.parseInt(map.get("id"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date time = sdf.parse(map.get("time"));

            userMapper.updateUser(new User(id, name, password, role, tel, age, sex, address, time));

        } catch (Exception e) {
            resp.put("info", "error");
            return resp;
        }

        resp.put("info", "success");

        return resp;
    }
}
