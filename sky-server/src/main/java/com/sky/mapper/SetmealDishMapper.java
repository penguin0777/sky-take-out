package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealDishMapper {
    /**
     * 根据菜品id查询套餐id
     * @param dishIds
     * @return
     */

    public List<Long> getSetmealByDishid(List<Long> dishIds);

    /**
     * 新增套餐菜品信息
     * @param setmealDishes
     */
    void insert(List<SetmealDish> setmealDishes);
    @Select("select * from setmeal_dish where setmeal_id =#{id}")
    List<SetmealDish> getBySetmealId(Long id);

    /**
     * 删除套餐菜品关系
     * @param setmealId
     */
    @Delete("delete from setmeal_dish where setmeal_id = #{setmealId}")
    void deleteBySetmealId(Long setmealId);


}
