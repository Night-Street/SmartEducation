package com.example.login.utils;

import java.util.Arrays;

public class Role {
    private static final String[] roles = {
            "student"
            , "teacher"
            , "admin"
    };

    public static int str2num(String str) {
        return Arrays.stream(roles).toList().indexOf(str);
    }
    public static String num2str(int num){
        return roles[num];
    }
}
