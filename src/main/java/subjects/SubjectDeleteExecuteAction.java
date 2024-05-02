package subjects;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		HttpSession session=request.getSession();
		
		String cd=request.getParameter("cd");
		Teacher teacher=(Teacher)session.getAttribute("user");
		SubjectDAO dao=new SubjectDAO();
		Subject subject=dao.get(cd,  teacher.getSchool());

		boolean deleted=dao.delete(subject);
		
		if (deleted) {
			request.getRequestDispatcher("subject_delete_done.jsp").forward(request, response);
		}
	} 
}
