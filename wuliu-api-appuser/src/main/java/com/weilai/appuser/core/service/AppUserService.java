package com.weilai.appuser.core.service;

import java.util.List;

import com.weilai.appuser.core.model.AppUser;
import com.weilai.common.service.BaseService;

public interface AppUserService extends BaseService<AppUser> {
	public void updateAppUserName();

	public List<AppUser> findAllUser();

	public void shishiTry();
}
