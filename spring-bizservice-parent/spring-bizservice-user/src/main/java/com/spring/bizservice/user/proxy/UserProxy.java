package com.spring.bizservice.user.proxy;

import com.spring.bizservice.user.feign.UserFeignClient;
import com.spring.core.dto.respones.ResultBean;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author liyongzhen
 * @create 2018-05-07 16:55
 **/
@Service
public class UserProxy implements UserFeignClient {
    @Override
    public ResultBean<Map<String, Object>> getUserInfo(Long userId) {
        return new ResultBean<>(1,"1234");
    }
}
