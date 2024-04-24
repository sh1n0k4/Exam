package login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class LogoutAction extends Action {
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {			
		try {
            request.getSession().invalidate();
    		request.getRequestDispatcher("logout.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}