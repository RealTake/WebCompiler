package com.service;

import java.io.BufferedReader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.DAO;
import com.dto.DTO;

@Service
public class Write_S implements Service_Interface{
	
	@Autowired
	DAO dao;
	String rootPath = "E:/code_WC/";// 파일이 저장되는 폴더의 위치를 저장
	
	public void excute(DTO dto) 
	{
		String date = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date()).toString();// 파일이름에 저장될 시간을 저장함
		String Fname = dto.getId() + "_" + date + "_" + dto.getFname();// 실제로 저장될 파일의 이름과 위치
		String code = dto.getCode().replaceFirst("##", Fname);// Java의 class이름을 치환해준다
		
		dto.setFname(Fname);
		dto.setCode(code);
		dto.setDate(date);
		
		dao.write(dto);// DB에 내용 저장
		
		try 
		{
			WriteFile(dto);//파일쓰기
			compile(dto);//컴파일하기
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public void WriteFile(DTO dto) throws IOException {// 파일쓰는 메소드
		System.out.println("<파일쓰기>");
		
        FileOutputStream outF = new FileOutputStream(rootPath + dto.getFname() + dto.getType());//파일을 위치에 쓴다.
        outF.write(dto.getCode().getBytes("UTF-8"));
        outF.close();
    }
	
	public void compile(DTO dto) //컴파일 하는 메소드
	{
		System.out.println("<컴파일>");
		
		Process process = null;		// 프로세스 실행하기 위한 레퍼런스 변수
		int i = 0;					// 컴파일 명령어가 저장된 배열의 인덱스를 지정할 변수
		String resultOut = null;	// 컴파일후 출력결과를 담을 메소드
		String filePath_onlyName = rootPath + dto.getFname();// 원시 파일의 위치와 이름까지만 적혀있는 변수
		String[][] cmd = new String[][] {{"javac", filePath_onlyName + dto.getType()}, {"gcc", "-o", filePath_onlyName, filePath_onlyName + dto.getType()}};//컴파일에 필요한 명령어 모음
		
		switch (dto.getType()) {// 파일 확장자에 맞게 저장
		case ".java":
			i = 0;
			dto.setType(".class");// 다운로드 뷰로 리다이렉트 할때 컴파일된 파일의 확장자를 파라메터로 주기위해
			break;				  // 이하동문

		case ".c":
			i = 1;
			dto.setType(".exe");
			break;
			
		default:
			System.out.println("지원하지않는 언어 입니다.");
			break;
		}
		
		System.out.println(Arrays.toString(cmd[i]));// 사용된 명령어 확인

		try {

		    // 프로세스 빌더를 통하여 외부 프로그램 실행
		    process = new ProcessBuilder(cmd[i]).start();

		    // 외부 프로그램의 표준출력 상태 버퍼에 저장
		    BufferedReader stdOut = new BufferedReader( new InputStreamReader(process.getInputStream()) );
		    
		    // 표준출력 상태를 출력
		    while( (resultOut = stdOut.readLine()) != null ) 
		    {
		        System.out.println("결과 출력" + resultOut);
		    }
		    
			} 
			catch (IOException e) 
			{
			    e.printStackTrace();
	
			}
	}
	
	
}
