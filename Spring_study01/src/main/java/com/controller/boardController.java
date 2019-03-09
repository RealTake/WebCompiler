package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dto.DTO;
import com.service.Service_Interface;

@Controller
public class boardController {
	
	@Autowired
	Service_Interface w;
	
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public String home(DTO dto) {
		
		
		return "home";
	}
	@RequestMapping(value = "/write", method=RequestMethod.POST)
	public String write(DTO dto) {
		w.excute(dto);
		
		return "home";
	}
   
}
