package subjects;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		HttpSession session=request.getSession();
		
		String cd=request.getParameter("cd");
		String name=request.getParameter("name");
		Teacher teacher=(Teacher)session.getAttribute("user");
		

		SubjectDAO dao=new SubjectDAO();
		Subject subject=new Subject();
		subject.setCd(cd);
		subject.setName(name);
		subject.setSchool(teacher.getSchool());
		
		boolean created=dao.save(subject);
		
		if (created) {
			request.getRequestDispatcher("subject_create_done.jsp").forward(request, response);
		}
	}
}