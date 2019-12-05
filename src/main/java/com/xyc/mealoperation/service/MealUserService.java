package com.xyc.mealoperation.service;

import com.xyc.mealoperation.entity.Mealuser;
import com.xyc.mealoperation.entity.MealuserExample;
import com.xyc.mealoperation.mapper.MealuserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author xiongyancong
 * @createTime 2019/12/4 14:57
 * @Description
 **/
@Service
public class MealUserService {
    @Autowired
    MealuserMapper mealuserMapper;

    public List<Mealuser> findAllUser(){
        MealuserExample mealuserExample = new MealuserExample();
        MealuserExample.Criteria criteria = mealuserExample.createCriteria();
        criteria.getAllCriteria();
        return mealuserMapper.selectByExample(mealuserExample);
    }
}
