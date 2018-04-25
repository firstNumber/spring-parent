package com.weilai.cabox.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weilai.common.service.impl.BaseServiceImpl;
import com.weilai.common.dao.BaseDao;
import com.weilai.cabox.core.dao.CarboxDao;
import com.weilai.cabox.core.model.Carbox;
import com.weilai.cabox.core.service.CarboxService;


@Service("carboxService")
public class CarboxServiceImpl extends BaseServiceImpl<Carbox> implements CarboxService{

	@Autowired
	private CarboxDao carboxDaoImpl;
	
	@Override
	public BaseDao<Carbox> getBaseDao() {
		return carboxDaoImpl;
	}
}
