package subjects;

import bean.Teacher;
import dao.SubjectDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;
import tool.Util;

public class SubjectUpdateAction extends Action {
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {		
		String cd=request.getParameter("cd");
		Util u=new Util();
		Teacher t=u.getUser(request);

		SubjectDAO dao=new SubjectDAO();
		dao.get(cd, t.getSchool());
		
        request.getRequestDispatcher("subject_update.jsp").forward(request, response);
	} 
}
