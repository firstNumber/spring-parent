package com.spring.bizservice.user.mapper;

import com.spring.bean.entity.user.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;


@Repository
public interface UserMapper extends Mapper<User> {
}
