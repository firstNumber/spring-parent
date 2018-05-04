package com.spring.core.base.mapper;

import com.spring.core.base.mapper.provider.BatchLogicInsertMapperProvider;
import org.apache.ibatis.annotations.InsertProvider;

import java.util.List;

/**
 * @author liyongzhen
 * @create 2018-05-04 18:14
 **/
public interface BatchLogicInsertMapper<T> {

	@InsertProvider(type = BatchLogicInsertMapperProvider.class, method = "tanggoBatchInsertList")
	int tanggoBatchInsertList(List<T> arr);
}
