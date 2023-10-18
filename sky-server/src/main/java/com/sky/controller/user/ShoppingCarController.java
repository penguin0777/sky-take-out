package com.sky.controller.user;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;
import com.sky.result.Result;
import com.sky.service.ShoppingCarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/shoppingCart")
@Slf4j
@Api(tags = "C端购物车相关接口")
public class ShoppingCarController {
    @Autowired
    private ShoppingCarService shoppingCarService;
    /**
     * 添加购物车
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("添加购物车")
    public Result add(@RequestBody ShoppingCartDTO shoppingCartDTO){
        log.info("添加到购物车{}",shoppingCartDTO);
        shoppingCarService.addShoppingCar(shoppingCartDTO);
        return  Result.success();
    }
    /**
     * 查看购物车
     */
    @GetMapping("/list")
    @ApiOperation("查看购物车")
    public  Result<List<ShoppingCart>> getShoppingCar(){
      List<ShoppingCart> shoppingCart =  shoppingCarService.getShoppingCar();
        return  Result.success(shoppingCart);
    }
    @DeleteMapping("/clean")
    @ApiOperation("清空购物车")
    public  Result cleanShoppingCar(){
        shoppingCarService.cleanShoppingCar();
        return  Result.success();

    }

}
