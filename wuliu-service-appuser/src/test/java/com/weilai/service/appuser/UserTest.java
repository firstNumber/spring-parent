package com.weilai.service.appuser;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.weilai.appuser.core.model.AppUser;
import com.weilai.appuser.core.service.AppUserService;
import com.weilai.common.util.LockUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/application-context.xml" })
public class UserTest {
	@Autowired
	AppUserService appUserService;

	@Test
	public void appUserTest() {
		appUserService.updateAppUserName();
	}

	@Test
	public void lockTest() {
		String time = LockUtil.getCacheValue();
		System.out.println((int) ((Math.random() * 9 + 1) * 100000));
		System.out.println(time);
	}

	@Test
	public void findDBATest() {
		List<AppUser> userList = appUserService.findAllUser();
		for (AppUser appUser : userList) {
			System.out.println(appUser);
		}
	}

	@Test
	public void test() {
		appUserService.shishiTry();
	}
}
