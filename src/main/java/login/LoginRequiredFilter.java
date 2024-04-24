package login;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class LoginRequiredFilter {
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		chain.doFilter(req, res);
	}
	
	public void init(FilterConfig filterConfig) {
		
	}
	
	public void destroy() {}
}