package com.spring.bizservice.user.feign;

import com.spring.core.dto.respones.ResultBean;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author liyongzhen
 * @create 2018-05-07 16:54
 **/
public interface UserFeignClient {
    public ResultBean<Map<String, Object>> getUserInfo(Long userId) ;
}
