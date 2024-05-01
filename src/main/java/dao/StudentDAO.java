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
		
		PreparedStatement st=con.prepareStatement("select * from student inner join school student.school_cd=school.cd where no=?");
		st.setString(1, no);
		ResultSet rs=st.executeQuery();
		
		Student sd=new Student();
		School sc=new School();
		sd.setNo(rs.getString("no"));
		sd.setName(rs.getString("student.name"));
		sd.setEntYear(rs.getInt("ent_year"));
		sd.setClassNum(rs.getString("class_num"));
		sd.setIsAttend(rs.getBoolean("is_attend"));
		sc.setCd(rs.getString("school.cd"));
		sc.setName(rs.getString("school.name"));
		sd.setSchool(sc);
		
		st.close();
		con.close();
		return sd;
	}
	
	public List<Student> postFilter(ResultSet rSet, School school) throws Exception {
		List<Student> list=new ArrayList<>();
		
		Connection con=getConnection();
		
		PreparedStatement st=con.prepareStatement("select * from student where school_cd=?");
		st.setString(1, school.getCd());
		ResultSet rs=st.executeQuery();
		
		while (rs.next()) {
			Student sd=new Student();
			sd.setNo(rs.getString("no"));
			sd.setName(rs.getString("student.name"));
			sd.setEntYear(rs.getInt("ent_year"));
			sd.setClassNum(rs.getString("class_num"));
			sd.setIsAttend(rs.getBoolean("is_attend"));
			sd.setSchool(school);
			list.add(sd);
		}
		
		st.close();
		con.close();
		return list;
	}
	
	public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {
		List<Student> list=new ArrayList<>();
		
		Connection con=getConnection();
		
		PreparedStatement st=con.prepareStatement("select * from student where school_cd=? and ent_year=? and is_attend=?");
		st.setString(1, school.getCd());
		st.setInt(2, entYear);
		st.setBoolean(3, isAttend);
		ResultSet rs=st.executeQuery();
		
		while (rs.next()) {
			Student sd=new Student();
			sd.setNo(rs.getString("no"));
			sd.setName(rs.getString("student.name"));
			sd.setEntYear(rs.getInt("ent_year"));
			sd.setClassNum(rs.getString("class_num"));
			sd.setIsAttend(rs.getBoolean("is_attend"));
			sd.setSchool(school);
			list.add(sd);
		}
		
		st.close();
		con.close();
		return list;
	}
	
	public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {
		List<Student> list=new ArrayList<>();
		
		Connection con=getConnection();
		
		PreparedStatement st=con.prepareStatement("select * from student where school_cd=? and ent_year=? and is_attend=?");
		st.setString(1, school.getCd());
		st.setInt(2, entYear);
		st.setBoolean(3, isAttend);
		ResultSet rs=st.executeQuery();
		
		while (rs.next()) {
			Student sd=new Student();
			sd.setNo(rs.getString("no"));
			sd.setName(rs.getString("student.name"));
			sd.setEntYear(rs.getInt("ent_year"));
			sd.setClassNum(rs.getString("class_num"));
			sd.setIsAttend(rs.getBoolean("is_attend"));
			sd.setSchool(school);
			list.add(sd);
		}
		
		st.close();
		con.close();
		return list;
	}
	public List<Student> filter(School school, boolean isAttend) throws Exception {
		List<Student> list=new ArrayList<>();
		
		Connection con=getConnection();
		
		PreparedStatement st=con.prepareStatement("select * from student where school_cd=? and is_attend=?");
		st.setString(1, school.getCd());
		st.setBoolean(2, isAttend);
		ResultSet rs=st.executeQuery();
		
		while (rs.next()) {
			Student sd=new Student();
			sd.setNo(rs.getString("no"));
			sd.setName(rs.getString("student.name"));
			sd.setEntYear(rs.getInt("ent_year"));
			sd.setClassNum(rs.getString("class_num"));
			sd.setIsAttend(rs.getBoolean("is_attend"));
			sd.setSchool(school);
			list.add(sd);
		}
		
		st.close();
		con.close();
		return list;
	}
	
	public boolean save(Student student) throws Exception {
		Connection con=getConnection();
		
		PreparedStatement st=null;
		Student old=get(student.getNo());
		
		if (old==null) {
			st=con.prepareStatement("insert into student(no, name, ent_year, class_num, is_attend, school_cd) vlaues(?, ?, ?, ?, ?, ?)");
			st.setString(1, student.getNo());
			st.setString(2, student.getName());
			st.setInt(3, student.getEntYear());
			st.setString(4, student.getClassNum());
			st.setBoolean(5, student.getIsAttend());
			st.setString(5, student.getSchool().getCd());
		} else {
			st=con.prepareStatement("update student set name=?, is_Attend=?, class_Num=? where no=?");
			st.setString(1, student.getName());
			st.setBoolean(2, student.getIsAttend());
			st.setString(3, student.getClassNum());
			st.setString(4, student.getNo());
		}
		
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
