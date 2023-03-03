package com.back.backend.service.user;

import java.util.Map;

public interface LoginService {
    Map<String, String> login(String name, String password);
}
