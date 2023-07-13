package com.example.smarteducation.dao;

import com.example.smarteducation.entity.Admin;
import com.example.smarteducation.entity.Student;
import com.example.smarteducation.entity.Teacher;
import com.example.smarteducation.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {
    @Select(
            "SELECT id FROM user WHERE id=#{id} AND password=#{password} AND role=#{role}"
    )
    String queryIdByIdPasswordRole(@Param("id") String id, @Param("password") int password, @Param("role") int role);

    @Select(
            "SELECT id, name, sex FROM student WHERE id=#{id}"
    )
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "sex", column = "sex")
    })
    Student queryStudentById(String id);

    @Select(
            "SELECT id, name, sex FROM teacher WHERE id=#{id}"
    )
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "sex", column = "sex")
    })
    Teacher queryTeacherById(String id);

    @Select(
            "SELECT id, name, sex FROM admin WHERE id=#{id}"
    )
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "sex", column = "sex")
    })
    Admin queryAdminById(String id);


}
