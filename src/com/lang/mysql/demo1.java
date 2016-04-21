package com.lang.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * statement与preparedstatement使用
 * @param args
 */
public class demo1 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mysqlText();
	}
	public static void mysqlText(){
		Connection conn=null;
		PreparedStatement statement1=null;
		ResultSet result=null;        //都要关闭
		try {
			Class.forName("com.mysql.jdbc.Driver");
			long start=new Date().getTime();
		    conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/school","root","123456");
			long end=new Date().getTime();
			System.out.println(end-start);//用时100毫秒,建立连接比较耗时，实际开发中都是用连接池
			
			/*Statement statement=conn.createStatement();
			String sql="insert into student values(3,'李四',22)";
			String sql1="delete from student where stuid=3";
			String sql2="delete from student where stuid="+"2 or 1=1";//sql 注入
			statement.execute(sql2);*/
			

			/*String sql3="insert into student values(?,?,?)";
			PreparedStatement statement1=conn.prepareStatement(sql3);//防止sql 注入
			statement1.setInt(1, 2);
			statement1.setString(2, "王五");
			statement1.setInt(3, 22);
			statement1.execute();*/
			
		    String sql4="select * from student";
			statement1=conn.prepareStatement(sql4);//实际开发中对于大量的批处理，prepareStatement预编译空间有限，
			                                       //还得是用statement
			result=statement1.executeQuery();
			List<Student> list=new ArrayList<Student>();
			while(result.next()){
				Student stu=new Student();
				stu.setStuNumber(result.getInt("stuId"));
				stu.setStuName(result.getString("stuName"));
				stu.setStuAge(result.getInt("stuAge"));
				list.add(stu);
			}
			for (Student student : list) {
				System.out.println("学生姓名："+student.getStuName());
			}
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
