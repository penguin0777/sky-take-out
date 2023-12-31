package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {
    /**
     * 新增菜品
     * @param dishDTO
     */
    public  void  saveWithFlavor(DishDTO dishDTO);

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     * @return
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 菜品删除
     * @param ids
     */

    void delectBatch(List<Long> ids);

    /**
     * 根据分类id查询菜品
     * @param id
     * @return
     */
    DishVO getByIdWithFlavor(Long id);

    /**
     * 修改菜品
     * @param dishDTO
     */
    void updateWithFlavor(DishDTO dishDTO);

    /**
     * 根据分类id查询菜品(管理端）
     * @return
     */
    List<Dish> getDishBycategoryId(Long categoryId);
    /**
     * 根据分类id查询菜品（用户端）
     * @return
     */
    List<DishVO> listWithFlavor(Dish dish);

    /**
     * 菜品起售停售
     * @param id
     * @param status
     */
    void startostop(Long id, Integer status);
}
