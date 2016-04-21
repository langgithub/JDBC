package com.lang.mysql;

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
 * 对于大量使用批处理操作还得使用statement
 * @param args
 */
public class demo2 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mysqlText();
	}
	/**
	 * 时间，长整形，字符串转换
	 * @return
	 */
	public Date formatToDate(){
		SimpleDateFormat formate=new SimpleDateFormat("yyyy-MM-DD");
		java.sql.Date time=null;
		//java.sql.Time
		//java.sql.Timestamp
		try {
		   time=(java.sql.Date) formate.parse(System.currentTimeMillis()+"");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//String currentTime=formate.format(new Date());
		return null;
	}
	public static void mysqlText(){
		Connection conn=null;
		Statement statement1=null;
		ResultSet result=null;        //都要关闭
		try {
			Class.forName("com.mysql.jdbc.Driver");
		    conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/school","root","123456");
			
		    conn.setAutoCommit(false);//设为手动提交,默认自动提交
			long start=new Date().getTime();
			statement1=conn.createStatement();
			for(int i=0;i<20000;i++){
				statement1.addBatch("insert into student values("+i+",'袁浪',12)");
			}
			statement1.executeBatch();//大量批处理还得使用statement
			conn.commit();
			long end=new Date().getTime();
			System.out.println(end-start);//用时100毫秒,建立连接比较耗时，实际开发中都是用连接池
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
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
