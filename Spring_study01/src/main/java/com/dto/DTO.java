package com.dto;

import java.util.ArrayList;

public class DTO {
	private String bId;
	private String id;
	private String type;
	private String title;
	private String Fname;
	private String code;
	private String contents;
	private String date;
	private String like;
	private String disLike;
	private String path;
	private ArrayList<DTO_C> comments;
	
	public String getbId() {
		return bId;
	}
	public void setbId(String bId) {
		this.bId = bId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFname() {
		return Fname;
	}
	public void setFname(String fname) {
		Fname = fname;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getLike() {
		return like;
	}
	public void setLike(String like) {
		this.like = like;
	}
	public String getDisLike() {
		return disLike;
	}
	public void setDisLike(String disLike) {
		this.disLike = disLike;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public ArrayList<DTO_C> getComments() {
		return comments;
	}
	public void setComments(ArrayList<DTO_C> comments) {
		this.comments = comments;
	}
	
	

}
