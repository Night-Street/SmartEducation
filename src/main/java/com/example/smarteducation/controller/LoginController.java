package com.example.smarteducation.controller;

import com.example.smarteducation.dao.UserDao;
import com.example.smarteducation.entity.User;
import com.example.smarteducation.utils.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RestController
@RequestMapping("/login")
public class LoginController {
    private final UserDao userDao;

    public LoginController(UserDao userDao) {
        this.userDao = userDao;
    }

    @PostMapping("/")
//    private String login(
    private ModelAndView login(
            @RequestParam("id") String id
            , @RequestParam("password") String strPassword
            , @RequestParam("role") int role
//            , HttpServletRequest request
//            , HttpServletResponse response
    ) {
        int codePassword = strPassword.hashCode();
        id = userDao.queryIdByIdPwdRole(id, codePassword, role);
        if (id == null) {
            return new ModelAndView("login");
        }
        ModelAndView view = new ModelAndView();
        view.setViewName(Role.num2str(role));
        User user = null;
        switch (role) {
            case 0 -> {
                user = userDao.queryStudentById(id);
            }
            case 1 -> {
                user = userDao.queryTeacherById(id);
            }
            case 2 -> {
                user = userDao.queryAdminById(id);
            }
        }
        assert user != null;
        view.addObject("user", user.toString());
        return view;
    }
}
