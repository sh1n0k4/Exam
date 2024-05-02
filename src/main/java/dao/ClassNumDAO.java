package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;

public class ClassNumDAO extends DAO {
	public List<String> filter(School school) throws Exception {
		List<String> list=new ArrayList<>();
		
		Connection con=getConnection();
		
		PreparedStatement st=con.prepareStatement("select class_num from class_num where school_cd=?");
		st.setString(1, school.getCd());
		ResultSet rs=st.executeQuery();
		
		while (rs.next()) {
			list.add("class_num");
		}
		
		st.close();
		con.close();
		return list;
	}
}