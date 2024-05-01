package subjects;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		try {
			HttpSession session=request.getSession();
	
			String cd=request.getParameter("cd");
			String name=request.getParameter("name");
			Teacher teacher=(Teacher)session.getAttribute("user");
			
			if (name == null || name.trim().isEmpty()) {
                request.setAttribute("error_msg", "このフィールドを入力してください。");
                request.getRequestDispatcher("subject_update.jsp").forward(request, response);
                return; // 以降の処理を中断
            }
	
			SubjectDAO dao=new SubjectDAO();
			Subject subject=new Subject();
			subject.setCd(cd);
			subject.setName(name);
			subject.setSchool(teacher.getSchool());
			
			boolean updated=dao.save(subject);
						
			if (updated) {
				request.getRequestDispatcher("subject_update_done.jsp").forward(request, response);
			}
			
		}catch (Exception e) {
			request.setAttribute("error_msg", "");
			request.getRequestDispatcher("subject_update.jsp").forward(request, response);
		}
	}
}