package com.ticket.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class LoginFailureHandler 
		extends SimpleUrlAuthenticationFailureHandler{
	
	private String loginFormPath;
	public void setLoginFormPath(String loginFormPath){
		this.loginFormPath=loginFormPath;
	}
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		
		String accept=request.getHeader("accept");
		String retUrl=request.getParameter("returl");
		
		
		if(StringUtils.indexOf(accept, "html")>-1){
			response.sendRedirect(request.getContextPath()+loginFormPath
					+"&returl="+retUrl);
		}else if(StringUtils.indexOf(accept,"json")>-1){
			response.setContentType("application/json;charset=utf-8");
			String data = StringUtils.join(new String[]{
					"{\"response\":{","\"error\":true,",
					"\"message\" : \"로그인 실패했습니다.\",",
					" \"returl\" : \"",retUrl,"\"",
					"}}"
					});
			
			PrintWriter out=response.getWriter();
			out.print(data);
			out.flush();
			out.close();
			
		}		
	}
	
	
	
}
