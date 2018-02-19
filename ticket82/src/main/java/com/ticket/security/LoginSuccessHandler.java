package com.ticket.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.ticket.domain.MemberVO;
import com.ticket.service.MemberService;
import com.ticket.service.UserService;
import com.ticket.service.impl.UserServiceImpl;

public class LoginSuccessHandler extends 
			 SavedRequestAwareAuthenticationSuccessHandler{
	
	
	private String loginFormPath;
	
	public void setLoginFormPath(String loginFormPath){
		this.loginFormPath=loginFormPath;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		
		HttpSession session=request.getSession();		
		String loginUser=authentication.getName();
		session.setAttribute("loginUser2",loginUser);	
		
		String accept=request.getHeader("accept");
		String retUrl=request.getParameter("returl");
		
		if(StringUtils.indexOf(accept,"html")>-1){
			//super.onAuthenticationSuccess(request, response, authentication);
			if(retUrl!=null && !retUrl.isEmpty()){
				response.sendRedirect(retUrl);
			}else{
				response.sendRedirect(request.getContextPath()+loginFormPath);
			}
		}else if(StringUtils.indexOf(accept, "json")>-1){
			
			response.setContentType("application/json;charset=utf-8");
			String data = StringUtils.join(new String[]{
					"{\"response\":{","\"error\":false,",
					"\"message\" : \"로그인하였습니다.\",",
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
