package com.example.login.utils;

public class Encryption {
    public static int encrypt(String password) {
        return password.hashCode();
    }
}
