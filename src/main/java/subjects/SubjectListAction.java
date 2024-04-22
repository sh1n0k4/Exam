package subjects;

import java.util.List;

import bean.School;
import bean.Subject;
import dao.SubjectDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectListAction extends Action {
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		School school=new School();
		school.setCd(request.getParameter("school_cd"));
		
		SubjectDAO dao=new SubjectDAO();
		List<Subject> sb_list=dao.filter(school);
		
		request.setAttribute("sb_list", sb_list);
		
        request.getRequestDispatcher("subject_list.jsp").forward(request, response);
	} 
}
