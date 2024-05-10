package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Teacher;

public class TeacherDAO extends DAO {
	public Teacher login(String id, String password)
		throws Exception {
		
		Teacher t = new Teacher();
		
		Connection con=getConnection();
		PreparedStatement st=null;
		
		try {
			st=con.prepareStatement(
				"select * from teacher where id = ? and password = ?");
			st.setString(1, id);
			st.setString(2, password);
			ResultSet rs=st.executeQuery();
			
			SchoolDAO sDAO=new SchoolDAO();

			if (rs.next()) {
				t.setId(rs.getString("id"));
				t.setPassword(rs.getString("password"));
				t.setName(rs.getString("name"));
				t.setSchool(sDAO.get(rs.getString("school_cd")));
			} else {
				t=null;
			}
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
		return t;
	}
}