package com.xyc.mealoperation.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xyc.mealoperation.entity.meal.Dynamic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Author xiongyancong
 * @createTime 2019/12/20 11:41
 * @Description
 **/
@Repository
@Mapper
public interface DynamicMapper extends BaseMapper<Dynamic> {
    /**
     * 获取一段时间内的数据
     * @param start
     * @param end
     * @return
     */
    List<Dynamic> getAllByTimeBetween(@Param("start") Timestamp start,@Param("end") Timestamp end);

    /**
     * 分页查询
     * @param pageCount
     * @param number
     * @return
     */
    List<Dynamic> getByPage(@Param("pageCount") int pageCount,@Param("number") int number);

    /**
     * 分页+类型查询
     * @param pageCount
     * @param number
     * @param type
     * @return
     */
    List<Dynamic> getByPageAndType(@Param("pageCount") int pageCount,@Param("number") int number,@Param("type") int type);
}
