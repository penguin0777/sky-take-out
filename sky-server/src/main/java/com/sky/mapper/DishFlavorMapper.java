package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper {
    /**
     * 新增菜品
     * @param flavors
     */
    void insert(List<DishFlavor> flavors);

    /**
     * 根据菜品id删除菜品口味
     * @param ids
     */
    void delectByDishId(List<Long> ids);
}
