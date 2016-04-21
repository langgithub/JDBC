package com.lang.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用容器保存数据
 * @param args
 */
public class demo4 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mysqlText();
	}
	public static void mysqlText(){
		Connection conn=null;
		PreparedStatement statement1=null;
		ResultSet result=null;        //都要关闭
		try {
		    conn=MySqlUtil.getConnection();
		    String sql4="select * from student";
			statement1=conn.prepareStatement(sql4);//实际开发中对于大量的批处理，prepareStatement预编译空间有限，
			                                       //还得是用statement
			result=statement1.executeQuery();
			/*List<Student> list=new ArrayList<Student>();
			while(result.next()){
				Student stu=new Student();
				stu.setStuNumber(result.getInt("stuId"));
				stu.setStuName(result.getString("stuName"));
				stu.setStuAge(result.getInt("stuAge"));
				stu.setTime(result.getDate("time"));
				list.add(stu);
			}
			for (Student student : list) {
				System.out.println("学生姓名："+student.getTime());
			}*/
			
			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
			while(result.next()){
				Map<String,Object> map=new HashMap<String, Object>();		
			    map.put("学号", result.getInt("stuId"));
			    map.put("姓名", result.getString("stuName"));
			    map.put("年龄", result.getInt("stuAge"));
			    map.put("时间", result.getDate("birthday"));
				list.add(map);
			}
			for (Map<String,Object> map : list) {
				System.out.println(map.get("学号")+"-----"+map.get("姓名")+"-----"+map.get("年龄")+"-----"+map.get("时间"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			MySqlUtil.close(result, conn, statement1);
		}
		
	}

}
