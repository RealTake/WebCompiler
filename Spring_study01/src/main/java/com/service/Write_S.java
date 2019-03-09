package com.service;

import java.io.BufferedReader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.DAO;
import com.dto.DTO;

@Service
public class Write_S implements Service_Interface{
	
	@Autowired
	DAO dao;
	String rootPath = "E:/code_WC/";
	
	public void excute(DTO dto) 
	{
		String date = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date()).toString();
		String Fname = dto.getId() + "_" + date + "_" + dto.getFname();
		
		String code = dto.getCode().replaceFirst("##", Fname);
		
		dto.setFname(Fname);
		dto.setCode(code);
		dto.setDate(date);
		
		dao.write(dto);
		
		try {
			WriteFile(code, Fname, dto.getType());
			compile(Fname, dto.getType());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void WriteFile(String code, String Fname,String type) throws IOException {
		System.out.println("���Ͼ���");
        FileOutputStream outF = new FileOutputStream(rootPath + Fname + type);
        outF.write(code.getBytes("UTF-8"));
        outF.close();
    }
	
	public void compile(String Fname,String type) 
	{
		System.out.println("������");
		String path = rootPath + Fname + type;
		Process process = null;
		String[] cmd = new String[] {"javac", path};
		
		System.out.println(cmd[0]+cmd[1]);
		String str = null;

		try {

		    // ���μ��� ������ ���Ͽ� �ܺ� ���α׷� ����
		    process = new ProcessBuilder(cmd).start();

		    // �ܺ� ���α׷��� ǥ����� ���� ���ۿ� ����
		    BufferedReader stdOut = new BufferedReader( new InputStreamReader(process.getInputStream()) );
		    
		    // ǥ����� ���¸� ���
		    while( (str = stdOut.readLine()) != null ) 
		    {
		        System.out.println("��� ���" + str);
		    }
		    
			} catch (IOException e) 
			{
			    e.printStackTrace();
	
			}
	}
	
	
}
