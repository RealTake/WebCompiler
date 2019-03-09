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
		System.out.println("파일쓰기");
        FileOutputStream outF = new FileOutputStream(rootPath + Fname + type);
        outF.write(code.getBytes("UTF-8"));
        outF.close();
    }
	
	public void compile(String Fname,String type) 
	{
		System.out.println("컴파일");
		String path = rootPath + Fname + type;
		Process process = null;
		String[] cmd = new String[] {"javac", path};
		
		System.out.println(cmd[0]+cmd[1]);
		String str = null;

		try {

		    // 프로세스 빌더를 통하여 외부 프로그램 실행
		    process = new ProcessBuilder(cmd).start();

		    // 외부 프로그램의 표준출력 상태 버퍼에 저장
		    BufferedReader stdOut = new BufferedReader( new InputStreamReader(process.getInputStream()) );
		    
		    // 표준출력 상태를 출력
		    while( (str = stdOut.readLine()) != null ) 
		    {
		        System.out.println("결과 출력" + str);
		    }
		    
			} catch (IOException e) 
			{
			    e.printStackTrace();
	
			}
	}
	
	
}
