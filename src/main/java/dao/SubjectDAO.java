package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDAO extends DAO {
	public Subject get(String cd, School school) throws Exception {
		// 科目インスタンスを初期化
		Subject sj=new Subject();
		// データベースのコネクションを確立
		Connection con=getConnection();
		PreparedStatement st=null;
		try {
			// PreparedStatementにSQL文をセット
			st=con.prepareStatement("select * from subject where cd=? and school_cd=?");
			// PreparedStatementに科目コード、学校コードをバインド
			st.setString(1, cd);
			st.setString(2, school.getCd());
			// PreparedStatementを実行
			ResultSet rs=st.executeQuery();
						
			if (rs.next()) {
				sj.setCd(rs.getString("cd"));
				sj.setName(rs.getString("name"));
				sj.setSchool(school);
			} else {
				sj=null;
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
		return sj;
	}
	
	// 一覧
	public List<Subject> filter(School school) throws Exception {
		List<Subject> list=new ArrayList<>();
		
		Connection con =getConnection();
		
		PreparedStatement st=null;
		
		try {
			st=con.prepareStatement("select * from subject where school_cd=?");
			st.setString(1, school.getCd());
			ResultSet rs=st.executeQuery();
			
			while (rs.next()) {
				Subject sj=new Subject();
				sj.setCd(rs.getString("cd"));
				sj.setName(rs.getString("name"));
				sj.setSchool(school);
				list.add(sj);
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
		return list;
	}
	
	// 保存
	public boolean save(Subject subject) throws Exception {
		// コネクションを確立
		Connection con=getConnection();
		// PreparedStatement
		PreparedStatement st=null;
		
		int line=0;
		
		// データベースから科目を取得
		Subject old=get(subject.getCd(), subject.getSchool());

		try {
			if (old==null) {
				// 科目が存在しなかった場合
				// PreparedStatementにinsert文をセット
				st=con.prepareStatement("insert into subject(cd, name, school_cd) values(?, ?, ?)");
				// prepareStatementに値をバインド
				st.setString(1, subject.getCd());
				st.setString(2, subject.getName());
				st.setString(3, subject.getSchool().getCd());
			} else {
				// 科目が存在した場合
				// prepareStatementにupdate文をセット
				st=con.prepareStatement("update subject set name=? where cd=? and school_cd=?");
				st.setString(1, subject.getName());
				st.setString(2, subject.getCd());
				st.setString(3, subject.getSchool().getCd());
			}
			
			line=st.executeUpdate();
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
		return line>0;
	}
	
	// 削除
	public boolean delete(Subject subject) throws Exception {
		Connection con=getConnection();
		PreparedStatement st=null;
		int line=0;
		
		try {
			st=con.prepareStatement("delete from subject where cd =? and school_cd=?");
			st.setString(1, subject.getCd());
			st.setString(2, subject.getSchool().getCd());
			line=st.executeUpdate();
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
		return line>0;
	}
}