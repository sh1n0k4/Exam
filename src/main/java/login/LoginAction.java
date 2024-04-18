package login;

import bean.Teacher;
import dao.TeacherDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class LoginAction extends Action {
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {

		HttpSession session=request.getSession();

		String id=request.getParameter("id");
		String password=request.getParameter("password");

		TeacherDAO dao=new TeacherDAO();
		Teacher teacher=dao.login(id, password);
		
		if (teacher!=null) {
			session.setAttribute("teacher", teacher);
	        request.getRequestDispatcher("/login-out.jsp").forward(request, response);
		}
		
		else {
			request.getRequestDispatcher("/login-error.jsp").forward(request, response);
		}
	}
}