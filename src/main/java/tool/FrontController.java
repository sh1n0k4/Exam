package tool;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns= {"*.action"})
public class FrontController extends HttpServlet {
	@Override
	protected void doGet(
		HttpServletRequest request, HttpServletResponse response
	) throws ServletException,IOException {
		try {
			String path=request.getServletPath().substring(1);
			String name=path.replace(".a", "A").replace('/', '.');
			Action action=(Action)Class.forName(name).getDeclaredConstructor().newInstance();
			action.execute(request,  response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}
	
	public void doPost(
		HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
		doGet(request, response);
	}
}