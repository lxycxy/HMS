package com.back.backend.service.user.impl;

import com.back.backend.Mapper.UserMapper;
import com.back.backend.service.user.DeleteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DeleteUserServiceImpl implements DeleteUserService {

    @Autowired
    UserMapper userMapper;
    @Override
    public Map<String, String> deleteUser(Integer userId) {

        Map<String, String> resp = new HashMap<>();
        try {
            userMapper.deleteUser(userId);
        } catch (Exception e) {
            resp.put("info", "error");
            return resp;
        }

        resp.put("info", "success");

        return resp;

    }
}
