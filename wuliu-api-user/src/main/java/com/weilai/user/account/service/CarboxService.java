package com.weilai.user.account.service;

import com.weilai.common.service.BaseService;
import com.weilai.user.account.model.Carbox;

public interface CarboxService extends BaseService<Carbox> {
	public Carbox queryCar();
}
