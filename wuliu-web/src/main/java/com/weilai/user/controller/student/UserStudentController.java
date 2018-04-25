package com.weilai.user.controller.student;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weilai.common.cache.CacheMan;
import com.weilai.common.util.MapUtil;
import com.weilai.student.core.service.UserStudent;

@Controller
@RequestMapping("/userStudent")
public class UserStudentController {
	@Autowired
	UserStudent userStudent;
	@RequestMapping(value="queryStudent" ,method =RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> querUserStudent(){
		String lockkey = "userStudent.queryStudent";
		CacheMan.postLock(lockkey);
		userStudent.queryStudent();
		CacheMan.unLock(lockkey);
		return MapUtil.success200();
	}
}
