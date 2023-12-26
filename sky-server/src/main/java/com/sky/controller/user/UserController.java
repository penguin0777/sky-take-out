package com.sky.controller.user;

import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
import com.sky.properties.JwtProperties;
import com.sky.result.Result;
import com.sky.service.UserService;
import com.sky.utils.JwtUtil;
import com.sky.vo.UserLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user/user")
@Slf4j
@RequiredArgsConstructor
@Api(tags = "C端用户相关接口")
public class UserController {

    private final UserService userService;

    private final JwtProperties jwtProperties;
    /**
     * 用户登录
     * @param userLoginDTO
     * @return
     */
    @ApiOperation("微信登录")
   @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO){
        log.info("微信登录{}",userLoginDTO.getCode());
        User user = userService.wxLogin(userLoginDTO);
        //为微信用户生成JWt令牌
        Map<String,Object> claim = new HashMap<>();
        claim.put(JwtClaimsConstant.USER_ID,user.getId());

        String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claim);
        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .openid(user.getOpenid())
                .token(token)
                .build();

        return  Result.success(userLoginVO);
    }
}
