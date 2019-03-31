package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dto.DTO;

@Controller
public class memberController {
	
	@RequestMapping
	public String login(DTO dto) {
		return null;
	}

}
