package com.back.backend.service.user.impl;

import com.back.backend.Mapper.UserMapper;
import com.back.backend.pojo.User;
import com.back.backend.service.user.GetUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetUsersServiceImpl implements GetUsersService {
    @Autowired
    UserMapper userMapper;
    @Override
    public List<User> getUsers() {
        return userMapper.getAll();
    }
}
