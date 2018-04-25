package com.weilai.appuser.core.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weilai.appuser.core.dao.AppUserDao;
import com.weilai.appuser.core.model.AppUser;
import com.weilai.appuser.core.service.AppUserService;
import com.weilai.common.dao.BaseDao;
import com.weilai.common.exception.BusiException;
import com.weilai.common.service.impl.BaseServiceImpl;
import com.weilai.common.util.LockUtil;
import com.weilai.common.util.ThreadUtil;
import com.weilai.common.util.TimeHelper;

@Service("appUserService")
public class AppUserServiceImpl extends BaseServiceImpl<AppUser> implements AppUserService {

	@Autowired
	private AppUserDao appUserDaoImpl;

	@Override
	public BaseDao<AppUser> getBaseDao() {
		return appUserDaoImpl;
	}

	@Override
	public void updateAppUserName() {
		List<AppUser> userList = findAll();
		for (int i = 0; i < 2; i++) {
			try {
				update2(userList, i);
			} catch (Exception e) {
				throw e;
			}
		}
	}

	@SuppressWarnings("finally")
	private void update2(List<AppUser> userList, Integer num) {
		String phone = getBatchNum();
		boolean flag = false;
		try {
			if (num == 0) {
				userList.get(0).setNickName("杨幂");
				userList.get(1).setNickName("刘诗诗");
				update(userList.get(0));
				update(userList.get(1));
			}
			if (num == 1) {
				userList.get(2).setNickName("唐嫣");
				update(userList.get(2));
				// userList.get(3).setNickName("景甜是个大美女,每个人都喜欢");
				userList.get(3).setNickName("景甜");
				update(userList.get(3));
			}

		} catch (Exception e) {
			flag = true;
			throw e;
		} finally {
			System.out.println(flag);
			if (flag) {
				final AppUser a = new AppUser();
				a.setNickName("范冰冰");
				a.setPhone(phone);
				a.setToken("21321321321321321");
				// create(a);
				ThreadUtil.execute(new Runnable() {
					@Override
					public void run() {
						// throw new BusiException("线程");
						// create(a);
					}
				});
			}
			throw new BusiException("线程");
		}
		// System.out.println("我照样走.");

	}

	private String getBatchNum() {
		return "B" + String.valueOf(Math.random() * 10000 + 10000).substring(0, 5)
				+ TimeHelper.date2String(new Date(), "MMddHHmmssSSS");
	}

	private void redislock() {
		LockUtil.lock("yongzhen", LockUtil.getCacheValue(), 5);
		LockUtil.tryLock("yongzhen", LockUtil.getCacheValue(), 1000, 1000);
		LockUtil.unlock("yongzhen", LockUtil.getCacheValue());
	}

	@Override
	public List<AppUser> findAllUser() {
		return findBy("userId", 20);
	}

	@Override
	public void shishiTry() {
		List<String> a = new ArrayList<>();
		try {
			a.get(0);
		} catch (Exception e) {
			throw e;
		} finally {
			System.out.println("************************");
		}

		System.out.println("==============================");
		try {
			System.out.println("888");
		} catch (Exception e) {

		} finally {
			System.out.println("999");
		}

	}
}
