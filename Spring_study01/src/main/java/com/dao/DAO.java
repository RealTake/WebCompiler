package com.dao;

import java.util.ArrayList;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.dto.DTO;

@Component
public class DAO {
	
	@Autowired
	MongoTemplate mongoTemplate;
	

	DAO()
	{
		
	}
	
	//글쓰기
	public String write(DTO dto)
	{
		System.out.println("글쓰기 메소드 실행");
		Document doc = new Document()
				.append("board_header", dto.getTitle())
				.append("board_type", dto.getType())
				.append("board_code", dto.getCode())
				.append("board_contents", dto.getContents())
				.append("board_userID", dto.getId())
				.append("board_path", "D:/code_WC/" + dto.getId() + "-" + dto.getDate() + "-" + dto.getFname() + ".java")
				.append("board_date", dto.getDate())
				.append("board_like", 0)
				.append("board_dislike", 0)
				.append("comments", new ArrayList());
		
		mongoTemplate.insert(doc,"board");
		
		return (String) doc.get("_id");
	}
	
	
	public String getPath(String bid) {
		Query query = new Query(new Criteria("_id").is(new ObjectId(bid)));
		DTO dto = mongoTemplate.findOne(query, DTO.class);
		String path = dto.getFname();
		
		return path;
	}
	
	//
	
	// 디비에 접근합니다 가져옵니다
//	public DTO getData(Document temp, boolean mode)
//	{
//		System.out.println("디비에 접근합니다");
//		DTO dto = new DTO();
//		
//		dto.setbId(temp.getString("_id"));
//		dto.setTitle(temp.getString("board_header"));
//		if(mode == true)
//		{
//			dto.setType(temp.getString("board_type"));
//			dto.setFname(temp.getString("board_code"));
//			dto.setContents(temp.getString("board_contents"));
//			dto.setPath(temp.getString("board_path"));
//		}
//		dto.setId(temp.getString("board_userID"));
//		dto.setDate(temp.getString("board_date"));
//		
//		return dto;
//        
//	}
	
	//게시글 보기 내용 가져오기
//	public DTO viewContent(String bid)
//	{
//		System.out.println("게시글 가져오기");
//		ArrayList<DTO_C> commentss = new ArrayList<DTO_C>();
//		Query query = new Query(new Criteria("_id").all(new ObjectId(bid)));
//		Document it = mongoTemplate.find(query, DTO_C.class,"board");
//	
//		DTO dto = getData(it, true);
//		
//		List<Document> comments = (List<Document>) it.get("comments");
//		if(comments != null)
//		{
//			for (Document comment : comments) 
//			{
//				DTO_C dto_c = new DTO_C();
//				
//				dto_c.setName(comment.getString("name"));
//				dto_c.setContents((comment.getString("contents")));
//				
//				commentss.add(dto_c);
//			}
//			
//			dto.setComments(commentss);
//		}
//		
//		return dto;
//	}
	
}
