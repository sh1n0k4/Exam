package students;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Teacher;
import dao.ClassNumDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentCreateAction extends Action {
	@Override
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		
		ClassNumDAO cDAO=new ClassNumDAO();
		List<String> list=cDAO.filter(teacher.getSchool());
		
		LocalDate todaysDate=LocalDate.now();
		int year=todaysDate.getYear();
		List<Integer> entYearSet = new ArrayList<>();
		// 10年前から1年後まで年をリストに追加
		for (int i = year - 10; i < year + 1; i++) {
			entYearSet.add(i);
		}
		
		request.setAttribute("class_num_set", list);

		request.setAttribute("ent_year_set", entYearSet);

        request.getRequestDispatcher("student_create.jsp").forward(request, response);
	} 
}
