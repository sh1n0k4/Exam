package subjects;

import java.io.IOException;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;
import tool.Util;

public class SubjectUpdateExecuteAction extends Action {
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		try {
			HttpSession session=request.getSession();
	
			String cd=request.getParameter("cd");
			String name=request.getParameter("name");
			
			Util u=new Util();
			Teacher t=u.getUser(request);
	
			SubjectDAO dao=new SubjectDAO();
			Subject s=new Subject();
			s.setCd(cd);
			s.setName(name);
			s.setSchool(t.getSchool());
			
			dao.save(s);
			
			if (s!=null) {
				session.setAttribute("subject", s);
				request.getRequestDispatcher("subject_create_done.jsp").forward(request, response);
			}
			
		}catch (IOException e) {
			System.out.println("このフィールドを入力してください。");
		}
	}
}