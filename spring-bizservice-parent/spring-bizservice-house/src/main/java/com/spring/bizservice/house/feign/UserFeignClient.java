package com.spring.bizservice.house.feign;

import com.spring.core.dto.respones.ResultBean;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * @author liyongzhen
 * @create 2018-05-07 16:28
 **/
@FeignClient(name = "user-biz-service")
public interface UserFeignClient {
    @RequestMapping(value = "/user/getUserInfo/{userId}", method = RequestMethod.GET, consumes = "application/json")
    public ResultBean<Map<String, Object>> getHouseByUid(@PathVariable("userId") Long uid);
}
