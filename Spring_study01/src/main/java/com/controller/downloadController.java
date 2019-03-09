package com.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dao.DAO;

@Controller
public class downloadController {

	@Autowired
	DAO dao;
	
	//  ���� �ٿ�ε� �ϴ� �޼ҵ�
//	    @RequestMapping(value = "/test/fileDownload")
//	    public ModelAndView reDocumentDown(@RequestParam("bid") String bid) {
//	       
//	    	String path = dao.getPath(bid);
//	    	
//	    	//��ü ��θ� ���ڷ� �־� ���� ��ü�� ����
//	    	File downFile = new File("E:/code_WC/" + path);
//
//	        return new ModelAndView("downloadView", "downloadFile", downFile);
//	    }
	
	@RequestMapping(value = "/test/fileDownload")
    public ModelAndView reDocumentDown(@RequestParam("Fname") String Fname) {
       
		Fname = Fname.replaceAll("[.]{2}|/", "");
    	System.out.println(Fname);
    	//��ü ��θ� ���ڷ� �־� ���� ��ü�� ����
    	File downFile = new File("E:/code_WC/" + Fname);

        return new ModelAndView("downloadView", "downloadFile", downFile);
    }
	    
}
