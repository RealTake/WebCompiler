package com.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class downloadController {
	
	//  파일 다운로드 하는 메소드
//	    @RequestMapping(value = "/test/fileDownload")
//	    public ModelAndView reDocumentDown(@RequestParam("bid") String bid) {
//	       
//	    	String path = dao.getPath(bid);
//	    	
//	    	//전체 경로를 인자로 넣어 파일 객체를 생성
//	    	File downFile = new File("E:/code_WC/" + path);
//
//	        return new ModelAndView("downloadView", "downloadFile", downFile);
//	    }
	
	@RequestMapping(value = "/fileDownload")
    public ModelAndView reDocumentDown(@RequestParam("Fname") String Fname) {
       
		Fname = Fname.replaceAll("[.]{2}|/", "");
    	System.out.println("다운로드할 파일 : " + Fname);
    	//전체 경로를 인자로 넣어 파일 객체를 생성
    	File downFile = new File("E:/code_WC/" + Fname);

        return new ModelAndView("downloadView", "downloadFile", downFile);
    }
	    
}
