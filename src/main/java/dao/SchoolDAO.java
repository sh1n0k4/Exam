package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.School;

public class SchoolDAO extends DAO {
	public School get(String school_cd) throws Exception {
		School sc=new School();
		Connection con=getConnection();
		PreparedStatement st=null;
		try {
			st=con.prepareStatement("select * from schoolwhere cd=?");
			st.setString(1, school_cd);
			ResultSet rs=st.executeQuery();
			
			if (rs.next()) {
				sc.setCd(rs.getString("school.cd"));
				sc.setName(rs.getString("school.name"));
			} else {
				sc=null;
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
		return sc;
	}
}