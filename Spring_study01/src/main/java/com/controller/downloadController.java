package com.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class downloadController {

	//  ���� �ٿ�ε� �ϴ� �޼ҵ�
	    @RequestMapping(value = "/test/fileDownload")
	    public ModelAndView reDocumentDown() {
	       
	    	//��ü ��θ� ���ڷ� �־� ���� ��ü�� ����
	    	File downFile = new File("C:/agentlog.txt");

	        return new ModelAndView("downloadView", "downloadFile", downFile);
	    }
}
