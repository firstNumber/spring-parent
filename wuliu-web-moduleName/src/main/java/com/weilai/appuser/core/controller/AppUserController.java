package com.weilai.appuser.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weilai.appuser.core.service.AppUserService;

@Controller
@RequestMapping("/appUser")
public class AppUserController {

	@Autowired
	private AppUserService appUserService;

}
