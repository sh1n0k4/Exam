package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDAO extends DAO {
	private String baseSql="select * from test inner join student on test.student_no = student.no where school_cd=?";
	public List<TestListSubject> postFilter(ResultSet rSet) {
		List<TestListSubject> list=new ArrayList<>();
		try {
			while (rSet.next()) {
				TestListSubject tls=new TestListSubject();
				tls.setEntYear(rSet.getInt("ent_year"));
				tls.setStudentNo(rSet.getString("student_no"));
				tls.setStudentName(rSet.getString("student.name"));
				tls.setClassNum(rSet.getString("class_num"));
				tls.putPoint(rSet.getInt("test.no"), rSet.getInt("point"));
				list.add(tls);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) throws Exception {
		List<TestListSubject> list=new ArrayList<>();
		Connection con=getConnection();
		
		PreparedStatement st=null;
		ResultSet rs=null;
		
		String condition=" and ent_year=? and class_num=? and subject_cd=?";
	
		String order=" order by student.no asc";

		try {
			st=con.prepareStatement(baseSql + condition + order);
			st.setString(1, school.getCd());
			st.setInt(2, entYear);
			st.setString(3, classNum);
			st.setString(4, subject.getCd());
			rs=st.executeQuery();
			
			list=postFilter(rs);
		} catch (Exception e) {
			throw e;
		} finally {
			if (st!=null) {
				try {
					st.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			if (con!=null) {
				try {
					con.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		return list;
	}
}