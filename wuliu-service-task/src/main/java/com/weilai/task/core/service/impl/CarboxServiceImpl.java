package com.weilai.task.core.service.impl;

import org.springframework.stereotype.Service;

import com.weilai.common.dao.BaseDao;
import com.weilai.common.service.impl.BaseServiceImpl;
import com.weilai.task.core.model.Carbox;
import com.weilai.task.core.service.CarboxService;


@Service("carboxService")
public class CarboxServiceImpl extends BaseServiceImpl<Carbox> implements CarboxService{

	@Override
	public BaseDao<Carbox> getBaseDao() {
		return null;
	}

	
}
