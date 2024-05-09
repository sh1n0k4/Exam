package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDAO extends DAO {
	private String baseSql="select * from student where school_cd=?";
	public Student get(String no) throws Exception {
		Student sd=new Student();
		Connection con=getConnection();
		PreparedStatement st=null;
		
		try {
			st=con.prepareStatement("select * from student where no=?");
			st.setString(1, no);
			ResultSet rs=st.executeQuery();
			
			SchoolDAO sDAO=new SchoolDAO();
			
			if (rs.next()) {
				sd.setNo(rs.getString("no"));
				sd.setName(rs.getString("student.name"));
				sd.setEntYear(rs.getInt("ent_year"));
				sd.setClassNum(rs.getString("class_num"));
				sd.setAttend(rs.getBoolean("is_attend"));
				sd.setSchool(sDAO.get(rs.getString("school_cd")));
			} else {
				sd=null;
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
		return sd;
	}
	
	public List<Student> postFilter(ResultSet rSet, School school) throws Exception {
		List<Student> list=new ArrayList<>();
		try {
			while (rSet.next()) {
				Student sd=new Student();
				sd.setNo(rSet.getString("no"));
				sd.setName(rSet.getString("student.name"));
				sd.setEntYear(rSet.getInt("ent_year"));
				sd.setClassNum(rSet.getString("class_num"));
				sd.setAttend(rSet.getBoolean("is_attend"));
				sd.setSchool(school);
				list.add(sd);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {
		List<Student> list=new ArrayList<>();
		
		Connection con=getConnection();
		
		PreparedStatement st=null;
		ResultSet rs=null;
		// SQLの条件
		String condition=" and ent_year=? and class_num=?";
		// SQL文のソート
		String order=" order by no asc";
		// 在学フラグ
		String conditionIsAttend="";
		if (isAttend) {
			conditionIsAttend=" and is_attend=true";
		}
		
		try {
			st=con.prepareStatement(baseSql + condition + conditionIsAttend + order);
			st.setString(1, school.getCd());
			st.setInt(2, entYear);
			st.setString(3, classNum);
			rs=st.executeQuery();
			
			list=postFilter(rs, school);
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
	
	public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {
List<Student> list=new ArrayList<>();
		
		Connection con=getConnection();
		
		PreparedStatement st=null;
		ResultSet rs=null;
		// SQLの条件
		String condition=" and ent_year=?";
		// SQL文のソート
		String order=" order by no asc";
		// 在学フラグ
		String conditionIsAttend="";
		if (isAttend) {
			conditionIsAttend=" and is_attend=true";
		}
		
		try {
			st=con.prepareStatement(baseSql + condition + conditionIsAttend + order);
			st.setString(1, school.getCd());
			st.setInt(2, entYear);
			rs=st.executeQuery();
			
			list=postFilter(rs, school);
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
	public List<Student> filter(School school, boolean isAttend) throws Exception {
List<Student> list=new ArrayList<>();
		
		Connection con=getConnection();
		
		PreparedStatement st=null;
		ResultSet rs=null;
		// SQL文のソート
		String order=" order by no asc";
		// 在学フラグ
		String conditionIsAttend="";
		if (isAttend) {
			conditionIsAttend=" and is_attend=true";
		}
		
		try {
			st=con.prepareStatement(baseSql + conditionIsAttend + order);
			st.setString(1, school.getCd());
			rs=st.executeQuery();
			
			list=postFilter(rs, school);
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
	
	public boolean save(Student student) throws Exception {
		Connection con=getConnection();
		
		PreparedStatement st=null;
		Student old=get(student.getNo());
		
		int line=0;
		try {
			if (old==null) {
				st=con.prepareStatement("insert into student(no, name, ent_year, class_num, is_attend, school_cd) values(?, ?, ?, ?, true, ?)");
				st.setString(1, student.getNo());
				st.setString(2, student.getName());
				st.setInt(3, student.getEntYear());
				st.setString(4, student.getClassNum());
				st.setString(5, student.getSchool().getCd());
			} else {
				st=con.prepareStatement("update student set name=?, is_Attend=?, class_Num=? where no=?");
				st.setString(1, student.getName());
				st.setBoolean(2, student.isAttend());
				st.setString(3, student.getClassNum());
				st.setString(4, student.getNo());
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
	
	public boolean delete(Student student) throws Exception {
		Connection con=getConnection();
		PreparedStatement st=null;
		int line=0;

		try {

			st=con.prepareStatement("delete student where no=?");
			st.setString(1, student.getNo());
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
