package com.spring.bizservice.user.service;

import com.spring.bean.entity.user.User;
import com.spring.bizservice.user.mapper.UserMapper;
import com.spring.core.dto.respones.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liyongzhen
 * @create 2018-05-04 16:44
 **/
@Service
public class UserService {
    @Autowired
    public UserMapper userMapper;

    public ResultBean<Map<String, Object>> getUserInfoByUser(Long userId) {
        User u  = new User();
        u.setId(userId);
        User u2 =userMapper.selectOne(u);
        Map<String,Object> map = new HashMap<>();
        map.put("user",u2);
        map.put("hourse","bigHourse");
        return  new ResultBean<>(map);
    }
}
