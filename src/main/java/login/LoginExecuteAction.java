package login;

import java.util.HashMap;
import java.util.Map;

import bean.Teacher;
import dao.TeacherDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class LoginExecuteAction extends Action {
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		try {
			HttpSession session=request.getSession();
	
			String id=request.getParameter("id");
			String password=request.getParameter("password");
	
			TeacherDAO dao=new TeacherDAO();
			Teacher teacher=dao.login(id, password);
			
			Map<String, String> errors=new HashMap<>();
			
			if (teacher!=null) {
				session.setAttribute("user", teacher);
				request.getRequestDispatcher("../tokutenkanri/menu.jsp").forward(request, response);
			}
			else {
				errors.put("login", "ログインに失敗しました。IDまたはパスワードが正しくありません。");
				request.setAttribute("errors", errors);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}catch (Exception e) {
			request.getRequestDispatcher("../error.jsp").forward(request, response);

		}
	}
}