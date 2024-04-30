package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;
import bean.Teacher;

public class TeacherDAO extends DAO {
	public Teacher login(String id, String password)
		throws Exception {
		
		School s = new School();
		Teacher t = new Teacher();
		
		Connection con=getConnection();

		PreparedStatement st;
		st=con.prepareStatement(
			"select * from teacher inner join school on teacher.school_cd = school.cd where id = ? and password = ?");
		st.setString(1, id);
		st.setString(2, password);
		ResultSet rs=st.executeQuery();

		if (rs.next()) {
			t.setId(rs.getString("id"));
			t.setPassword(rs.getString("password"));
			t.setName(rs.getString("school.name"));
			s.setCd(rs.getString("school.cd"));
			s.setName(rs.getString("school.name"));
			t.setSchool(s);
			
			st.close();
			con.close();
			return t;
		}

		st.close();
		con.close();
		return t;
	}
}