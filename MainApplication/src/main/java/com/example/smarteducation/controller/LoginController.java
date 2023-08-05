package com.example.smarteducation.controller;

import com.example.smarteducation.entity.User;
import com.example.smarteducation.utils.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RestController
@RequestMapping("/login")
@Configuration
public class LoginController {


    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @PostMapping("/")
//    private String login(
    private ModelAndView login(
            @RequestParam("id") String id
            , @RequestParam("password") String strPassword
            , @RequestParam("role") int role
            , HttpServletRequest request
            , HttpServletResponse response
    ) {
        RestTemplate restTemplate = getRestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("id", id);
        requestBody.add("password", strPassword);
        requestBody.add("role", role);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(requestBody, httpHeaders);
        boolean valid = Boolean.TRUE.equals(
                restTemplate.postForObject(
                        "http://login:9000/validate"
                        , requestEntity
                        , Boolean.class
                )
        );
        if (!valid) {
            return new ModelAndView("login");
        }
        ModelAndView view = new ModelAndView();
        view.setViewName(Role.num2str(role));
        requestBody.clear();
        requestBody.add("id", id);
        requestBody.add("role", role);
        requestEntity = new HttpEntity<>(requestBody, httpHeaders);
        User user = getRestTemplate().postForObject(
                "http://login:9000/query"
                , requestEntity
                , User.class
        );
        assert user != null;
        view.addObject("user", user.toString());
        return view;
    }
}
