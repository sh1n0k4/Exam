package students;

import bean.Student;
import bean.Teacher;
import dao.StudentDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentCreateExecuteAction extends Action {
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		HttpSession session=request.getSession();
		
		String no=request.getParameter("no");
		String name=request.getParameter("name");
		String entYearStr=request.getParameter("ent_year");
		int entYear=Integer.parseInt(entYearStr);
		String classNum=request.getParameter("class_num");

		Teacher teacher=(Teacher)session.getAttribute("user");
		
		

		StudentDAO dao=new StudentDAO();
		Student student=new Student();
		student.setNo(no);
		student.setName(name);
		student.setEntYear(entYear);
		student.setClassNum(classNum);
		student.setSchool(teacher.getSchool());
		
		boolean created=dao.save(student);
		
		if (created) {
			request.getRequestDispatcher("student_create_done.jsp").forward(request, response);
		}
	}
}