package com.sky.controller.user;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController("userShopController")
@RequestMapping("/user/shop")
@Slf4j
@RequiredArgsConstructor
@Api(tags ="店铺相关接口(用户端)")
public class ShopController {
    public static final  String KEY = "SHOP_STATUS";

    private final RedisTemplate redisTemplate;



    /**
     * 查询店铺营业状态
     * @return
     */
  @GetMapping("/status")
  @ApiOperation("查询店铺营业状态")
  public  Result<Integer>  getstatus(){

     Integer shopStatus = (Integer) redisTemplate.opsForValue().get(KEY);
      log.info("获取店铺营业状态为{}",shopStatus);
      return Result.success(shopStatus);
  }
}
