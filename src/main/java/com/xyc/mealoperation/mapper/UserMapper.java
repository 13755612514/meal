package com.xyc.mealoperation.mapper;



import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xyc.mealoperation.entity.meal.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
    User findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    User findByEmail(@Param("email") String email);

    int updateHeaderByEmail(@Param("email") String email,@Param("header") String header);

    int saveInfo(@Param("user") User user);

    int updateInfo(@Param("user") User user);
}