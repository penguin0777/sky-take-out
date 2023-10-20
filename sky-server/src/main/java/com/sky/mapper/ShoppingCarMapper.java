package com.sky.mapper;

import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ShoppingCarMapper {
    /**
     * 动态条件查询购物车
     * @param shoppingCart
     * @return
     */

    List<ShoppingCart> list(ShoppingCart shoppingCart);
    /**
     * 更新数据
     */
    @Update("update  shopping_cart set  number=#{number} where id=#{id}")
    void  updateNumberbyId(ShoppingCart shoppingCart);

    /**
     * 插入数据
     * @param shoppingCart
     */
    @Insert("insert into shopping_cart ( name, image, user_id, dish_id, setmeal_id, dish_flavor, number, amount, create_time) " +
            "values (#{name},#{image},#{userId},#{dishId},#{setmealId},#{dishFlavor},#{number},#{amount},#{createTime})")
    void insert(ShoppingCart shoppingCart);

    /**
     * 清空购物车
     * @param userid
     */
    @Delete("delete from shopping_cart where user_id=#{userid}")
    void delectByUserId(Long userid);

    /**
     * 删除特定购物车一条数据
     * @param cart
     */
    @Delete("delete from shopping_cart where id=#{id}")
    void deletebyId(ShoppingCart cart);

    /**
     * 批量插入
     * @param shoppingCartList
     */
    void insertBatch(List<ShoppingCart> shoppingCartList);
}
