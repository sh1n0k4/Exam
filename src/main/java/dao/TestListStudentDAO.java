package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDAO extends DAO {
	public List<TestListStudent> postFilter(ResultSet rSet) {
		List<TestListStudent> list=new ArrayList<>();
		try {
			while (rSet.next()) {
				TestListStudent tls=new TestListStudent();
				tls.setSubjectName(rSet.getString("name"));
				tls.setSubjectCd(rSet.getString("subject_cd"));
				tls.setNum(rSet.getInt("no"));
				tls.setPoint(rSet.getInt("point"));
				list.add(tls);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<TestListStudent> filter(Student student) throws Exception {
		List<TestListStudent> list=new ArrayList<>();
		Connection con=getConnection();
		
		PreparedStatement st=null;
		ResultSet rs=null;

		try {
			st=con.prepareStatement("select test.subject_cd, test.no, test.point, subject.name from test "
					+ "left outer join subject on test.subject_cd=subject.cd "
					+ "where student_no=?;");
			st.setString(1, student.getNo());
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