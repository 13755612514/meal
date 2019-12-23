package com.xyc.mealoperation.mapper;


import com.xyc.mealoperation.entity.meal.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
    User findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    User findByEmail(@Param("email") String email);

    int updateHeaderByEmail(@Param("email") String email,@Param("header") String header);

    int saveInfo(@Param("user") User user);

    int updateInfo(@Param("user") User user);
}