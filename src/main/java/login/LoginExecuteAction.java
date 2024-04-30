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
			
			// 入力値の空欄チェック
            if (id == null || id.trim().isEmpty() || password == null || password.trim().isEmpty()) {
                request.setAttribute("error_msg", "このフィールドを入力してください。");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return; // 以降の処理を中断
            }
			
			if (teacher!=null) {
				session.setAttribute("user", teacher);
				request.getRequestDispatcher("../menu.jsp").forward(request, response);
			}
			else {
				request.setAttribute("error_msg", "IDまたはパスワードが一致しませんでした。");
				request.getRequestDispatcher("login.jsp").forward(request, response);

			}
		}catch (IOException e) {
			request.setAttribute("error_msg", "このフィールドを入力してください。1");
			request.getRequestDispatcher("login.jsp").forward(request, response);

		}
	}
}