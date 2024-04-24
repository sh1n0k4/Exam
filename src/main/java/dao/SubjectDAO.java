package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDAO extends DAO {
	// 
	public Subject get(String cd, School school) throws Exception {
		Connection con=getConnection();
		
		PreparedStatement st=con.prepareStatement("select * from subject where cd=? and school_cd=?");
		st.setString(1, cd);
		st.setString(2, school.getCd());
		ResultSet rs=st.executeQuery();
		
		Subject sj=new Subject();
		if (rs.next()) {
			sj.setCd(rs.getString("cd"));
			sj.setName(rs.getString("name"));
			sj.setSchool(school);
		}
		
		st.close();
		con.close();
		return sj;
	}
	
	// 一覧
	public List<Subject> filter(School school) throws Exception {
		List<Subject> list=new ArrayList<>();
		
		Connection con =getConnection();
		
		PreparedStatement st=con.prepareStatement("select cd, name from subject where school_cd=?");
		st.setString(1, school.getCd());
		ResultSet rs=st.executeQuery();
		
		while (rs.next()) {
			Subject sj=new Subject();
			sj.setCd(rs.getString("cd"));
			sj.setName(rs.getString("name"));
			sj.setSchool(school);
			list.add(sj);
		}
		
		st.close();
		con.close();
		
		return list;
	}
	
	// 保存
	public boolean save(Subject subject) throws Exception {
		Connection con=getConnection();
		
		PreparedStatement st=con.prepareStatement("update subject set name=? where cd=? and school_cd=?;"
				+ "insert into subject (cd, name, school_cd) select ?, ?, ? where not exsists(select 1 from subject where cd=? and school_cd=?;");
		st.setString(1, subject.getName());
		st.setString(2, subject.getCd());
		st.setString(3, subject.getSchool().getCd());
		st.setString(4, subject.getCd());
		st.setString(5, subject.getName());
		st.setString(6, subject.getSchool().getCd());
		st.setString(7, subject.getCd());
		st.setString(8, subject.getSchool().getCd());
		int line=st.executeUpdate();
		
		st.close();
		con.close();
		return line>0;
	}
	
	// 削除
	public boolean delete(Subject subject) throws Exception {
		Connection con=getConnection();
		
		PreparedStatement st=con.prepareStatement("delete * from subject where cd =? and school_cd=?");
		st.setString(1, subject.getCd());
		st.setString(2, subject.getSchool().getCd());
		int line=st.executeUpdate();
		
		st.close();
		con.close();
		return line>0;
	}
}