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
	public List<TestListSubject> postFilter(ResultSet rSet) {
		List<TestListSubject> list=new ArrayList<>();
		try {
			while (rSet.next()) {
				TestListSubject tls=new TestListSubject();
				tls.setEntYear(rSet.getInt("ent_year"));
				tls.setStudentNo(rSet.getString("student.no"));
				tls.setStudentName(rSet.getString("student.name"));
				tls.setClassNum(rSet.getString("class_num"));

				if (rSet.getObject("a.no") != null) {
                    tls.putPoint(rSet.getInt("a.no"), rSet.getInt("a.point"));
                }
                if (rSet.getObject("b.no") != null) {
                    tls.putPoint(rSet.getInt("b.no"), rSet.getInt("b.point"));
                }
                
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

		try {
			st=con.prepareStatement("select student.no, student.name, student.ent_year, student.is_attend, subject.cd, subject.name subject_name, "
					+ "a.no as a_no, a.point as a_point , b.no as b_no, b.point as b_point "
					+ "from student inner join subject on student.school_cd = subject.school_cd "
					+ "left outer join "
					+ "test as a left join test as b on a.student_no = b.student_no and a.subject_cd = b.subject_cd and a.no <> b.no "
					+ "on student.no = a.student_no and subject.cd = a.subject_cd "
					+ "where (a.no = 1 or (a.no = 2 and b.no is null)) and student.school_cd =? "
					+ "and student.ent_year=? "
					+ "and student.class_num=? "
					+ "and subject.cd=?;");
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