package com.spring.core.base.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.condition.SelectByConditionMapper;

/**
 * @author liyongzhen
 * @create 2018-05-04 18:12
 **/
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T>, SelectByConditionMapper<T>, BatchLogicInsertMapper<T> {
	//FIXME 特别注意，该接口不能被扫描到，否则会出错
}

