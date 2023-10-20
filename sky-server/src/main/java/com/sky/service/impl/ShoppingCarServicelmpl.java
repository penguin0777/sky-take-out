package com.sky.service.impl;

import com.sky.context.BaseContext;
import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.entity.ShoppingCart;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.mapper.ShoppingCarMapper;
import com.sky.service.ShoppingCarService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ShoppingCarServicelmpl implements ShoppingCarService {
    @Autowired
    private ShoppingCarMapper shoppingCarMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private SetmealMapper setmealMapper;
    /**
     * 添加购物车
     * @param shoppingCartDTO
     */
    @Override
    public void addShoppingCar(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO,shoppingCart);
        Long userid = BaseContext.getCurrentId();
        shoppingCart.setUserId(userid);
        List<ShoppingCart>  list =shoppingCarMapper.list(shoppingCart);
        //判断当前加入购物车的商品是否已经存在
       if(list!=null&& !list.isEmpty()){
           //如果存在，数量加一
           ShoppingCart cart = list.get(0);
           cart.setNumber(cart.getNumber()+1);//更新数据
           shoppingCarMapper.updateNumberbyId(cart);

       }else{
           //不存在，插入数据

           //判断本次添加到购物车的是菜品还是套餐
           Long dishId = shoppingCartDTO.getDishId();
           if(dishId!=null){
               //添加到本次购物车的是菜品
               Dish dish = dishMapper.getByid(dishId);
               shoppingCart.setName(dish.getName());
               shoppingCart.setImage(dish.getImage());
               shoppingCart.setAmount(dish.getPrice());


           }
           else{
               //本次插入套餐
               Long setmealId = shoppingCartDTO.getSetmealId();
               Setmeal setmeal= setmealMapper.getById(setmealId);
               shoppingCart.setName(setmeal.getName());
               shoppingCart.setImage(setmeal.getImage());
               shoppingCart.setAmount(setmeal.getPrice());

           }
           shoppingCart.setNumber(1);
           shoppingCart.setCreateTime(LocalDateTime.now());


           shoppingCarMapper.insert(shoppingCart);


       }





    }

    /**
     * 查看购物车
     * @return
     */

    @Override
    public List<ShoppingCart> getShoppingCar() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Long userid = BaseContext.getCurrentId();
        shoppingCart.setUserId(userid);
        List<ShoppingCart> list = shoppingCarMapper.list(shoppingCart);
        return list;
    }

    /**
     * 清空购物车
     */
    @Override
    public void cleanShoppingCar() {
        //获取用户id
        Long userid = BaseContext.getCurrentId();
        shoppingCarMapper.delectByUserId(userid);
    }

    /**
     * 删除购物车的一个
     * @param shoppingCartDTO
     */
    @Override
    public void subShoppingCar(ShoppingCartDTO shoppingCartDTO) {
    ShoppingCart shoppingCart = new ShoppingCart();
    BeanUtils.copyProperties(shoppingCartDTO,shoppingCart);
    Long userid = BaseContext.getCurrentId();
    shoppingCart.setUserId(userid);
    List<ShoppingCart>  list =shoppingCarMapper.list(shoppingCart);
    ShoppingCart cart = list.get(0);
    if(cart.getNumber()>1){
        cart.setNumber(cart.getNumber()-1);//更新数据
        shoppingCarMapper.updateNumberbyId(cart);
    }
    else{
        shoppingCarMapper.deletebyId(cart);
    }


    }
}
