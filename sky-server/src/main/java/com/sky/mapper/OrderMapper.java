package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface OrderMapper {
    /**
     * 插入订单数据
     * @param orders
     */
    void insert(Orders orders);
    /**
     * 分页条件查询并按下单时间排序
     * @param ordersPageQueryDTO
     */
    Page<Orders> pageQuery(OrdersPageQueryDTO ordersPageQueryDTO);

    /**
     * 根据id查询订单信息
     * @param id
     */
    @Select("select * from orders where id=#{id}")
    Orders getById(Long id);

    /**
     * 删除订单
     * @param id
     */
    @Delete("delete from orders where id=#{id}")
    void delectorderById(Long id);

    /**
     * 用户取消订单后数据更新
     * @param orders
     */
    @Update("update orders set pay_status=#{payStatus},cancel_reason=#{cancelReason},status=#{status},cancel_time=#{cancelTime} where id=#{id}")
    void update1(Orders orders);

    /**
     * 根据状态统计订单数量
     * @param status
     */
    @Select("select count(id) from orders where status = #{status}")
    Integer countStatus(Integer status);

    /**
     * 管理端接单后数据更新
     * @param orders
     */
    @Update("update orders set status=#{status} where id =#{id}")
    void update2(Orders orders);

    /**
     * 拒单后状态更新
     * @param orders
     */
    @Update("update orders set status=#{status},rejection_reason=#{rejectionReason},cancel_time=#{cancelTime} where id =#{id}")
    void update3(Orders orders);

    /**
     * 商家取消订单后状态更新
     * @param orders
     */
    @Update("update orders set status=#{status},cancel_reason=#{cancelReason},cancel_time=#{cancelTime} where id =#{id}")
    void update4(Orders orders);

    /**
     * 派送订单或者完成状态更新
     * @param orders
     */
    @Update("update orders set status=#{status} where id =#{id}")
    void update5(Orders orders);
}
