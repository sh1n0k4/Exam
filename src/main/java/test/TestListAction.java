package test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.TestListStudent;
import bean.TestListSubject;
import dao.ClassNumDAO;
import dao.StudentDAO;
import dao.SubjectDAO;
import dao.TestListStudentDAO;
import dao.TestListSubjectDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListAction extends Action {
    public void execute(HttpServletRequest request, HttpServletResponse response
    ) throws Exception {
    	HttpSession session = request.getSession();
   		Teacher teacher = (Teacher)session.getAttribute("user");
   		
    	String action = request.getParameter("action");
    	
    	// 現在の年を取得
    	LocalDate todaysDate=LocalDate.now();
    	int year=todaysDate.getYear();
    	ClassNumDAO cDAO=new ClassNumDAO();
    	SubjectDAO sbDAO=new SubjectDAO();
    	
    	
		// ログインユーザの学校コードをもとにクラス番号の一覧を取得
		List<String> list=cDAO.filter(teacher.getSchool());
				
		List<Subject> subjectSet=sbDAO.filter(teacher.getSchool());
    	
    	List<Integer> entYearSet = new ArrayList<>();
		// 10年前から1年後まで年をリストに追加
		for (int i = year - 10; i < year + 1; i++) {
			entYearSet.add(i);
		}
		
		request.setAttribute("ent_year_set", entYearSet);
		request.setAttribute("class_num_set", list);
		request.setAttribute("subject_set", subjectSet);

    			
        if (action != null) {
            if (action.equals("setSubject")) {
                setTestListSubject(request, response);
            } else if (action.equals("setStudent")) {
                setTestListStudent(request, response);
            } else {
                // デフォルトの処理
                response.sendRedirect("test_list.jsp");
            }
        } else {
            // デフォルトの処理
            response.sendRedirect("test_list.jsp");
        }
    }

    public void setTestListSubject(HttpServletRequest request, HttpServletResponse response
    ) throws Exception {
    	// セッションからユーザー情報を取得
   		HttpSession session = request.getSession();
   		Teacher teacher = (Teacher)session.getAttribute("user");
   		School school=teacher.getSchool();
   		
   		String entYearStr="";
   		String classNum="";
   		String subjectCd="";
   		int entYear=0;
   		
   		List<TestListSubject> tlsb=null;
   		
   		entYearStr=request.getParameter("ent_year");
   		classNum=request.getParameter("class_num");
   		subjectCd=request.getParameter("subjectCd");
   		
   		// エラーメッセージのマップ
   		Map<String, String> errors=new HashMap<>();
   		
   		TestListSubjectDAO tDAO=new TestListSubjectDAO();
   		SubjectDAO sDAO=new SubjectDAO();
   		Subject subject=sDAO.get(subjectCd, school);
   		
   		if (entYearStr != null) {
   			entYear=Integer.parseInt(entYearStr);
   		}
   		
   		if (entYear != 0 && !classNum.equals("0") && subject != null && school != null) {
   			tlsb=tDAO.filter(entYear, classNum, subject, school);
   		} else {
   			errors.put("tlsb_e", "入学年度と科目とクラスを選択してください");
            response.sendRedirect("test_list.jsp");
   		}
   		
		request.setAttribute("year", entYear);
		request.setAttribute("class_num", classNum);
		request.setAttribute("subject", subjectCd);
		
		request.setAttribute("tlsb", tlsb);

		
		request.getRequestDispatcher("test_list_subject.jsp").forward(request, response);
    }

    public void setTestListStudent(HttpServletRequest request, HttpServletResponse response
    ) throws Exception {
    	// セッションからユーザー情報を取得
   		HttpSession session = request.getSession();
   		Teacher teacher = (Teacher)session.getAttribute("user");
   		School school=teacher.getSchool();
   		
   		String studentNo="";
   		
   		List<TestListStudent> tlst=null;
   		
   		studentNo=request.getParameter("student_no");
   		
   		TestListStudentDAO tDAO=new TestListStudentDAO();
   		StudentDAO sDAO=new StudentDAO();
   		Student student=sDAO.get(studentNo);
   		
   		if ( student != null ) {
   			tlst=tDAO.filter(student);
   		}
   		
		request.setAttribute("student_no", studentNo);
		
		request.setAttribute("student", student);
		
		request.setAttribute("tlst", tlst);

		request.getRequestDispatcher("test_list_student.jsp").forward(request, response);
    }
}
