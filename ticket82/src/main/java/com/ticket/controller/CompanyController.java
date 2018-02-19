package com.ticket.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ticket.domain.CompanyVO;
import com.ticket.service.CompanyService;

@Controller
@RequestMapping("/company")
public class CompanyController {
	
	@Autowired
	private CompanyService service;
	
	//메인화면
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public void index()throws Exception{
		
	}
	
	@RequestMapping(value="/regist",method=RequestMethod.GET)
	public void registCompany()throws Exception{
	}
	
	@RequestMapping(value="/regist",method=RequestMethod.POST)
	public String registPost(CompanyVO company)throws Exception{
		service.insertCompany(company);
		service.insertCompanyAuthority(company);
		return "redirect:/company/login";
	}
	
	@ResponseBody
	@RequestMapping(value="/idcheck",method=RequestMethod.POST)
	public String duplication(HttpServletRequest request) throws Exception{
		String id = request.getParameter("id");
		int rowcount=service.checkId(id);
		return String.valueOf(rowcount);
	}
	
	//로그인
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public void login()throws Exception{}
	
	//로그인체크
	@ResponseBody
	@RequestMapping(value="/loginPost",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	public String loginPost(LoginRequest loginReq, HttpSession session, HttpServletRequest request)throws Exception{
		
		String msg="";
		
		CompanyVO company=service.login(loginReq.getId());
		
		//아이디가 null이 아닐경우
		if(company!=null){
			if(loginReq.getPw().equals(company.getCom_pw())){
				//로그인 성공
				session.setAttribute("loginUser", company);
				msg="alert('로그인 성공했습니다.');"
					+"location.href='"+request.getContextPath()+"/company/index'";
			}else{// 패스워드 불일치
				msg="alert('패스워드가 일치하지 않습니다.');"
					+"history.go(-1);";
			}
		}else{//아이디 부재...
			msg="alert('아이디가 존재하지 않습니다.');"
				 +"history.go(-1);";
		}
		return "<script>"+msg+"</script>";
	}
	
	//로그아웃
	@RequestMapping(value="logout",method=RequestMethod.GET)
	public String logout(HttpSession session)throws Exception{
		
		session.invalidate();
		
		return "redirect:/company/index";
	}
	
}
