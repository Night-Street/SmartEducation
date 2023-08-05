package com.example.login.controller;

import com.example.login.dao.UserDao;
import com.example.login.entity.User;
import com.example.login.utils.Encryption;
import com.example.login.utils.Role;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final UserDao userDao;

    public LoginController(UserDao userDao) {
        this.userDao = userDao;
    }

    @PostMapping("/validate")
    public boolean validate(String id, String password, int role) {
        int pwd = Encryption.encrypt(password);
        String[] queryResults = userDao.queryIdByIdPwdRole(id, pwd, role);
        return queryResults.length != 0;
    }

    @PostMapping("/query")
    public User query(String id, int role) {
        return userDao.queryNameByIdRole(id, Role.num2str(role))[0];
    }
}
