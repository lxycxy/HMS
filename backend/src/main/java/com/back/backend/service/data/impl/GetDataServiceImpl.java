package com.back.backend.service.data.impl;

import com.back.backend.Mapper.UserMapper;
import com.back.backend.pojo.User;
import com.back.backend.service.data.GetDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GetDataServiceImpl implements GetDataService {
    @Autowired
    UserMapper userMapper;

    @Override
    public Map<String, String> getData() {

        Map<String, String> resp = new HashMap<>();

        List<User> userList = userMapper.getAll();
        resp.put("userCount", String.valueOf(userList.size()));

        return resp;
    }
}
