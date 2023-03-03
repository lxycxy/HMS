package com.back.backend.controller;


import com.back.backend.service.user.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;


    @PostMapping("/api/login/")
    public Map<String, String> login(@RequestParam Map<String, String> map) {
        Map<String, String> resp = new HashMap<>();
        String name, password;
        try {
             name = map.get("name");
             password = map.get("password");
        } catch (Exception e) {

            resp.put("info", "error");

            return resp;
        }
        return loginService.login(name, password);
    }
}
