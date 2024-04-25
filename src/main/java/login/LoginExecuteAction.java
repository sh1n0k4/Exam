package login;

import java.io.IOException;

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
			
			if (teacher!=null) {
				session.setAttribute("user", teacher);
				request.getRequestDispatcher("../menu.jsp").forward(request, response);
			}
			else {
			    System.out.println("IDまたはパスワードが一致しませんでした。");
			}
		}catch (IOException e) {
		    System.out.println("このフィールドを入力してください。");
		}
	}
}