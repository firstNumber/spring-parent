package com.spring.bizservice.user.controller;

import com.spring.core.dto.respones.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    @RequestMapping(value ="/getUserInfo/{userId}",method = RequestMethod.GET)
    public ResultBean<Map<String,Object>> getUserInfo(@PathVariable("userId") Long userId){
        return  new ResultBean<>(userId.intValue(),"获得成功");
    }
}
