package students;

import java.util.List;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDAO;
import dao.StudentDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentUpdateAction extends Action {
	@Override
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		
		String no=request.getParameter("no");
		
		StudentDAO dao=new StudentDAO();
		Student student=dao.get(no);
		
		ClassNumDAO cDAO=new ClassNumDAO();
		// ログインユーザの学校コードをもとにクラス番号の一覧を取得
		List<String> list=cDAO.filter(teacher.getSchool());
		
		if (student!=null) {
			request.setAttribute("class_num_set", list);
			request.setAttribute("student", student);
			request.getRequestDispatcher("student_update.jsp").forward(request, response);
		}
	} 
}
