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
	String rootPath = "E:/code_WC/";// ������ ����Ǵ� ������ ��ġ�� ����
	
	public void excute(DTO dto) 
	{
		String date = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date()).toString();// �����̸��� ����� �ð��� ������
		String Fname = dto.getId() + "_" + date + "_" + dto.getFname();// ������ ����� ������ �̸��� ��ġ
		String code = dto.getCode().replaceFirst("##", Fname);// Java�� class�̸��� ġȯ���ش�
		
		dto.setFname(Fname);
		dto.setCode(code);
		dto.setDate(date);
		
		dao.write(dto);// DB�� ���� ����
		
		try 
		{
			WriteFile(dto);//���Ͼ���
			compile(dto);//�������ϱ�
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public void WriteFile(DTO dto) throws IOException {// ���Ͼ��� �޼ҵ�
		System.out.println("<���Ͼ���>");
		
        FileOutputStream outF = new FileOutputStream(rootPath + dto.getFname() + dto.getType());//������ ��ġ�� ����.
        outF.write(dto.getCode().getBytes("UTF-8"));
        outF.close();
    }
	
	public void compile(DTO dto) //������ �ϴ� �޼ҵ�
	{
		System.out.println("<������>");
		
		Process process = null;		// ���μ��� �����ϱ� ���� ���۷��� ����
		int i = 0;					// ������ ��ɾ ����� �迭�� �ε����� ������ ����
		String resultOut = null;	// �������� ��°���� ���� �޼ҵ�
		String filePath_onlyName = rootPath + dto.getFname();// ���� ������ ��ġ�� �̸������� �����ִ� ����
		String[][] cmd = new String[][] {{"javac", filePath_onlyName + dto.getType()}, {"gcc", "-o", filePath_onlyName, filePath_onlyName + dto.getType()}};//�����Ͽ� �ʿ��� ��ɾ� ����
		
		switch (dto.getType()) {// ���� Ȯ���ڿ� �°� ����
		case ".java":
			i = 0;
			dto.setType(".class");// �ٿ�ε� ��� �����̷�Ʈ �Ҷ� �����ϵ� ������ Ȯ���ڸ� �Ķ���ͷ� �ֱ�����
			break;				  // ���ϵ���

		case ".c":
			i = 1;
			dto.setType(".exe");
			break;
			
		default:
			System.out.println("���������ʴ� ��� �Դϴ�.");
			break;
		}
		
		System.out.println(Arrays.toString(cmd[i]));// ���� ��ɾ� Ȯ��

		try {

		    // ���μ��� ������ ���Ͽ� �ܺ� ���α׷� ����
		    process = new ProcessBuilder(cmd[i]).start();

		    // �ܺ� ���α׷��� ǥ����� ���� ���ۿ� ����
		    BufferedReader stdOut = new BufferedReader( new InputStreamReader(process.getInputStream()) );
		    
		    // ǥ����� ���¸� ���
		    while( (resultOut = stdOut.readLine()) != null ) 
		    {
		        System.out.println("��� ���" + resultOut);
		    }
		    
			} 
			catch (IOException e) 
			{
			    e.printStackTrace();
	
			}
	}
	
	
}
