package com.spring.bizservice.user.controller;

import com.spring.bizservice.user.service.UserService;
import com.spring.core.dto.respones.ResultBean;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * @author liyongzhen
 * @create 2018-05-04 10:05
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserService userService;

    @ApiOperation(value = " 通过用户id获取用户信息", notes = " 通过用户id获取用户信息")
    @RequestMapping(value = "/getUserInfo/{userId}", method = RequestMethod.GET)
    public ResultBean<Map<String, Object>> getUserInfo(@PathVariable("userId") Long userId) {
        return userService.getUserInfoByUser(userId);
    }
}
