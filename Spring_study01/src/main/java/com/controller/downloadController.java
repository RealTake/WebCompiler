package com.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class downloadController {

	//  파일 다운로드 하는 메소드
	    @RequestMapping(value = "/test/fileDownload")
	    public ModelAndView reDocumentDown() {
	       
	    	//전체 경로를 인자로 넣어 파일 객체를 생성
	    	File downFile = new File("C:/agentlog.txt");

	        return new ModelAndView("downloadView", "downloadFile", downFile);
	    }
}
