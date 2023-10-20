package com.sky.service;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCarService {
    /**
     * 添加购物车
     */
    void  addShoppingCar(ShoppingCartDTO shoppingCartDTO);

    /**
     * 查看购物车
     * @return
     */
    List<ShoppingCart> getShoppingCar();

    /**
     * 清空购物车
     */
    void cleanShoppingCar();

    /**
     * 删除购物车的一个
     * @param shoppingCartDTO
     */
    void subShoppingCar(ShoppingCartDTO shoppingCartDTO);
}
