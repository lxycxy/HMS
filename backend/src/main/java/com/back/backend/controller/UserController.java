package com.back.backend.controller;

import com.back.backend.pojo.User;
import com.back.backend.service.user.AddUserService;
import com.back.backend.service.user.DeleteUserService;
import com.back.backend.service.user.GetUsersService;
import com.back.backend.service.user.UpdateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {


    @Autowired
    GetUsersService getUsersService;

    @Autowired
    DeleteUserService deleteUserService;

    @Autowired
    AddUserService addUserService;

    @Autowired
    UpdateUserService updateUserService;

    @GetMapping("/api/getusers/")
    public List<User> getUsers() {
        return getUsersService.getUsers();
    }

    @PostMapping("/api/deleteuser/")
    public Map<String, String> deleteUser(@RequestParam Map<String, String> map) {


        Map<String, String> resp = new HashMap<>();

        Integer userId;
        try {
            userId = Integer.parseInt(map.get("id"));
        } catch (Exception e) {
            resp.put("info", "error");
            return resp;
        }

        return deleteUserService.deleteUser(userId);
    }

    @PostMapping("/api/updateuser/")
    public Map<String, String> updateUser(@RequestParam Map<String, String> map) {

        return updateUserService.updateUser(map);
    }

    @GetMapping("/api/adduser")

    public Map<String, String> addUser(@RequestParam Map<String, String> map) {

        return addUserService.addUser(map);
    }
}
