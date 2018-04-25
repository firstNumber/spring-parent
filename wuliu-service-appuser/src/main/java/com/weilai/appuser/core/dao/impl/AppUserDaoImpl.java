package com.weilai.appuser.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.weilai.common.dao.impl.BaseDaoImpl;
import com.weilai.appuser.core.dao.AppUserDao;
import com.weilai.appuser.core.model.AppUser;

@Repository("appUserDaoImpl")
public class AppUserDaoImpl extends BaseDaoImpl<AppUser> implements AppUserDao {

}
