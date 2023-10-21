package com.sky.task;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class OrderTask {
    @Autowired
    private OrderMapper orderMapper;

    /**
     * 处理超时订单
     */
    @Scheduled(cron = "0 * * * * ?")
    public  void executeTask(){
      log.info("定时处理超时未支付订单");
        LocalDateTime time = LocalDateTime.now().plusMinutes(-15);

        List<Orders> orders = orderMapper.getBystatusandOrderTime(Orders.PENDING_PAYMENT, time);
        if(!orders.isEmpty()){
            for (Orders order : orders) {
                order.setStatus(Orders.CANCELLED);
                order.setCancelReason("订单超时，自动取消");
                order.setCancelTime(LocalDateTime.now());
                orderMapper.update4(order);


            }
        }


    }
    /**
     * 处理一直处于派送订单
     */
    @Scheduled(cron = "0 0 1 * * ?")//每天凌晨一点触发一次
    public  void deliverOrderTask(){
        log.info("定时处理一直派送订单");
        LocalDateTime time = LocalDateTime.now().plusMinutes(-60);

        List<Orders> orders = orderMapper.getBystatusandOrderTime(Orders.DELIVERY_IN_PROGRESS, time);
        if(!orders.isEmpty()){
            for (Orders order : orders) {
                order.setStatus(Orders.COMPLETED);

                orderMapper.update5(order);


            }
        }


    }
}
