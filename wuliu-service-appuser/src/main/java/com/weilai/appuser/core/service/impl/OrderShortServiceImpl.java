package com.weilai.appuser.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weilai.appuser.core.dao.OrderShortDao;
import com.weilai.appuser.core.model.OrderShort;
import com.weilai.appuser.core.model.dto.OrderShortButionObj;
import com.weilai.appuser.core.service.OrderShortService;
import com.weilai.common.dao.BaseDao;
import com.weilai.common.service.impl.BaseServiceImpl;
import com.weilai.common.util.PageBean;

@Service("orderShortService")
public class OrderShortServiceImpl extends BaseServiceImpl<OrderShort> implements OrderShortService {

	@Autowired
	private OrderShortDao orderShortDaoImpl;

	@Override
	public BaseDao<OrderShort> getBaseDao() {
		return orderShortDaoImpl;
	}

	@Override
	public void orderTest() {
		// List<OrderShort> orderLsit = findBy("statusIn", 42 + "," + 47);
		int i = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("statusIn", 42 + "," + 47);
		PageBean<OrderShort> pageBean = new PageBean<OrderShort>(1, 10);
		find4Page(pageBean, map, true);
		List<OrderShort> orderList = pageBean.getObjList();

		for (OrderShort order : orderList) {
			OrderShortButionObj orderShortButionObj = new OrderShortButionObj();
			BeanUtils.copyProperties(order, orderShortButionObj);
			i++;
			Integer totalVloumn = order.getGoodsLength() * order.getGoodsWidth() * order.getGoodsHeight();
			System.out.println(totalVloumn + "\t" + i);
		}

	}
}
