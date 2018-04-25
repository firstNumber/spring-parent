package com.weilai.cabox.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weilai.cabox.core.service.CarboxService;

@Controller
@RequestMapping("/carbox")
public class CarboxController {

	@Autowired
	private CarboxService carboxService;

}
