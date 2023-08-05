package com.example.login.dao;

import com.example.login.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {
    @Select(
            "SELECT id FROM user WHERE id=#{id} AND pwd=#{pwd} AND role=#{role}"
    )
    String[] queryIdByIdPwdRole(String id, int pwd, int role);

    @Select(
            "SELECT id,name,sex FROM ${role} WHERE id=#{id}"
    )
    User[] queryNameByIdRole(String id, String role);
}
