package com.weilai.cabox.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.weilai.common.dao.impl.BaseDaoImpl;
import com.weilai.cabox.core.dao.CarboxDao;
import com.weilai.cabox.core.model.Carbox;

@Repository("carboxDaoImpl")
public class CarboxDaoImpl extends BaseDaoImpl<Carbox> implements CarboxDao {

}
