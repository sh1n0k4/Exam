package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDAO extends DAO {
	public Student get(String no) throws Exception {
		Connection con=getConnection();
		
		PreparedStatement st=con.prepareStatement("select * from student where no=?");
		st.setString(1, no);
		ResultSet rs=st.executeQuery();
		
		Student sd=new Student();
		sd.setNo(rs.getString("no"));
		
		st.close();
		con.close();
		return sd;
	}
	
	public List<Student> filter(ResultSet rSet, School school) throws Exception {
		List<Student> list=new ArrayList<>();
		
		Connection con=getConnection();
		
		PreparedStatement st=con.prepareStatement("select * from student where school_cd=?");
		st.setString(1, school.getCd());
		ResultSet rs=st.executeQuery();
		
		while (rs.next()) {
			Student sd=new Student();
			sd.setEntYear(rs.getInt("entYear"));
			sd.setClassNum(rs.getString("classNum"));
			sd.setIsAttend(rs.getBoolean("isAttend"));
			list.add(sd);
		}
		
		st.close();
		con.close();
		return list;
	}
	
	public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {
		List<Student> list=new ArrayList<>();
		
		Connection con=getConnection();
		
		PreparedStatement st=con.prepareStatement("select * from student where school_cd=?");
		st.setString(1, school.getCd());
		ResultSet rs=st.executeQuery();
		
		while (rs.next()) {
			Student sd=new Student();
			sd.setEntYear(rs.getInt("entYear"));
			sd.setIsAttend(rs.getBoolean("isAttend"));
			list.add(sd);
		}
		
		st.close();
		con.close();
		return list;
	}
	
	public List<Student> filter(School school, boolean isAttend) throws Exception {
		List<Student> list=new ArrayList<>();
		
		Connection con=getConnection();
		
		PreparedStatement st=con.prepareStatement("select * from student where school_cd=?");
		st.setString(1, school.getCd());
		ResultSet rs=st.executeQuery();
		
		while (rs.next()) {
			Student sd=new Student();
			sd.setIsAttend(rs.getBoolean("isAttend"));
			list.add(sd);
		}
		
		st.close();
		con.close();
		return list;
	}
	
	public boolean save(Student student) throws Exception {
		Connection con=getConnection();
		
		PreparedStatement st=con.prepareStatement("update student set name=?, is_Attend=?, class_Num=?, where no=?");
		st.setString(1, student.getName());
		st.setBoolean(2, student.getIsAttend());
		st.setString(3, student.getClassNum());
		st.setString(4, student.getNo());
		int line=st.executeUpdate();
		
		st.close();
		con.close();
		return line>0;
	}
	
	public boolean delete(Student student) throws Exception {
		Connection con=getConnection();
		
		PreparedStatement st=con.prepareStatement("delete student where no=?");
		st.setString(1, student.getNo());
		int line=st.executeUpdate();
		
		st.close();
		con.close();
		return line>0;
	}
}
