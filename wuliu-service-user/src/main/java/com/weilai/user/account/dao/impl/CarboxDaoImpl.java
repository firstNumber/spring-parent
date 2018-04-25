package com.weilai.user.account.dao.impl;

import org.springframework.stereotype.Repository;

import com.weilai.common.dao.impl.BaseDaoImpl;
import com.weilai.user.account.dao.CarboxDao;
import com.weilai.user.account.model.Carbox;

@Repository("carboxDaoImpl")
public class CarboxDaoImpl extends BaseDaoImpl<Carbox> implements CarboxDao {

}
