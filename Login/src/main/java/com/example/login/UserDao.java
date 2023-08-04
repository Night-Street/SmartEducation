package com.example.login;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {
    @Select(
            "SELECT id FROM user WHERE id=#{id} AND password=#{pwd}"
    )
    String[] validateIdPwd(String id, int pwd);
}
