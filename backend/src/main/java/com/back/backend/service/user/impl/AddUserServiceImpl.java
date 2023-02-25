package com.back.backend.service.user.impl;

import com.back.backend.Mapper.UserMapper;
import com.back.backend.pojo.User;
import com.back.backend.service.user.AddUserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AddUserServiceImpl implements AddUserService {


    @Autowired
    UserMapper userMapper;

    @Override
    public Map<String, String> addUser(Map<String, String> map) {

        String name, password, role, tel, sex, address;
        Integer age;

        Map<String, String> resp = new HashMap<>();

        try {
            name = map.get("name");
            password = map.get("password");
            role = map.get("role");
            tel = map.get("tel");
            sex = map.get("sex");
            address = map.get("address");

            age = Integer.parseInt(map.get("age"));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date time = sdf.parse(map.get("time"));

            userMapper.addUser(new User(null, name, password, role, tel, age, sex, address, time));

        } catch (Exception e) {
            resp.put("info", "error");
            return resp;
        }



        resp.put("info", "success");

        return resp;
    }
}
