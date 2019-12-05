package com.xyc.mealoperation.mapper;

import com.xyc.mealoperation.entity.Mealuser;
import com.xyc.mealoperation.entity.MealuserExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MealuserMapper {
    int countByExample(MealuserExample example);

    int deleteByExample(MealuserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Mealuser record);

    int insertSelective(Mealuser record);

    List<Mealuser> selectByExample(MealuserExample example);

    Mealuser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Mealuser record, @Param("example") MealuserExample example);

    int updateByExample(@Param("record") Mealuser record, @Param("example") MealuserExample example);

    int updateByPrimaryKeySelective(Mealuser record);

    int updateByPrimaryKey(Mealuser record);
}