package login;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginRequiredFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        // フィルターの初期化処理
    	
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        // HttpServletRequestとHttpServletResponseにキャスト
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        // セッションを取得
        HttpSession session = request.getSession(false);

        if (session != null) {
            // セッションを無効化してログアウト
            session.invalidate();

            // ログアウト後のリダイレクト先を設定
            response.sendRedirect("login.jsp"); // ログインページにリダイレクト
        } else {
            // セッションが無効の場合、次のフィルターに進む
            chain.doFilter(req, res);
        }
    }

    public void destroy() {
        // フィルターの破棄処理
    }
}
