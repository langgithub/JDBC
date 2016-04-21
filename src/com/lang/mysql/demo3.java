package com.lang.mysql;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * 使用clob读取大数据，使用 blob读取二进制类的大数据(如图片)。
 * @author lang
 *
 */
public class demo3 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mysqlText();
	}
	public static void mysqlText(){
		Connection conn=null;
		PreparedStatement statement1=null;
		PreparedStatement statement2=null;
		ResultSet result=null;        //都要关闭
		try {
			Class.forName("com.mysql.jdbc.Driver");
		    conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/school","root","123456");
			
		   
		    FileReader fr=new FileReader(new File("D:/WordFile/java/workspace/JDBC/src/readme.txt"));
		    BufferedReader r=new BufferedReader(new InputStreamReader(new ByteArrayInputStream("sfdasfd".getBytes())));
		    FileInputStream fis=new FileInputStream("D:/WordFile/java/workspace/JDBC/src/4.png");
		    FileOutputStream fos=new FileOutputStream("D:/WordFile/java/workspace/JDBC/src/2.png");
		    
		    //存入数据
		    /*statement1=conn.prepareStatement("insert into student(stuId,text) values(?,?)");
			statement1.setInt(1, 1);
			statement1.setClob(2,fr);
			statement1.executeUpdate();*/
			
		    //读取数据
			/*statement2=conn.prepareStatement("select text from student where stuid=?");
			statement2.setInt(1, 1);
			result=statement2.executeQuery();
			while(result.next()){
				Clob c= result.getClob("text");
				StringReader ss=(StringReader) c.getCharacterStream();
				  int len=0;
				  while((len=ss.read())!=-1){
				    	System.out.print((char)len);
				  }
			}*/
			
		    //存入二进制图片
		   /* statement1=conn.prepareStatement("insert into student(stuId,img) values(?,?)");
			statement1.setInt(1, 1);
			statement1.setBlob(2, new FileInputStream("D:/WordFile/java/workspace/JDBC/src/4.png"));
			statement1.executeUpdate();*/
		
			statement2=conn.prepareStatement("select img from student where stuid=?");
			statement2.setInt(1, 1);
			result=statement2.executeQuery();
			while(result.next()){
				Blob b=result.getBlob("img");
				InputStream ss=  b.getBinaryStream();
				  int len=0;
				  while((len=ss.read())!=-1){
					  fos.write(len);
					//  System.out.print((char)len);
				  }
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(statement1!=null){
				try {
					statement1.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(result!=null){
				try {
					result.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}
}
