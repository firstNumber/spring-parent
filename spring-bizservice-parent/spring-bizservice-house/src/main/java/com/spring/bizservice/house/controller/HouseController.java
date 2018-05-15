package com.spring.bizservice.house.controller;

import com.spring.bizservice.house.feign.UserFeignClient;
import com.spring.core.dto.respones.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.sound.midi.Soundbank;
import java.util.Map;

/**
 * @author liyongzhen
 * @create 2018-05-07 16:38
 **/
@RestController
@RequestMapping("/hourse")
public class HouseController {

    @Autowired
    public UserFeignClient userFeignClient;
    @RequestMapping(value = "/getHourse/{hourseId}", method = RequestMethod.GET)
    public ResultBean<Map<String, Object>> getHouseByUid(@PathVariable("hourseId") Long hourseId){
        ResultBean<Map<String, Object>>  resuklt=userFeignClient.getHouseByUid(hourseId);
        System.out.println(resuklt.toString());
        System.out.println("***");
        return resuklt;
    }

    @RequestMapping(value = "/getUser/{hourseId}", method = RequestMethod.GET)
    public ResultBean<Map<String, Object>> getUser(@PathVariable("hourseId") Long hourseId){
        System.out.println("啦啦啦啦啦啦");
        ResultBean<Map<String, Object>>  resuklt=userFeignClient.getUser(hourseId);
        System.out.println(resuklt.toString());
        System.out.println("***");
        return resuklt;
    }
}
