package com.lang.mysql;

import java.sql.Date;

public class Student {

	private int stuNumber;
	private String stuName;
	private int stuAge;
	private Date time; 
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getStuNumber() {
		return stuNumber;
	}
	public void setStuNumber(int stuNumber) {
		this.stuNumber = stuNumber;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public int getStuAge() {
		return stuAge;
	}
	public void setStuAge(int stuAge) {
		this.stuAge = stuAge;
	}
	
}
