package students;

import bean.Student;
import bean.Teacher;
import dao.StudentDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		HttpSession session=request.getSession();
		
		String no=request.getParameter("no");
		String name=request.getParameter("name");
		String entYearStr=request.getParameter("ent_year");
		int entYear=Integer.parseInt(entYearStr);
		String classNum=request.getParameter("class_num");
		String isAttendStr=request.getParameter("is_attend");
		boolean isAttend=false;
		if (isAttendStr != null) {
			isAttend = true;
		}
		Teacher teacher=(Teacher)session.getAttribute("user");

		StudentDAO dao=new StudentDAO();
		Student student=new Student();
		student.setNo(no);
		student.setName(name);
		student.setEntYear(entYear);
		student.setClassNum(classNum);
		student.setAttend(isAttend);
		student.setSchool(teacher.getSchool());
		
		boolean updated=dao.save(student);
					
		if (updated) {
			request.getRequestDispatcher("student_update_done.jsp").forward(request, response);
		}
	}
}