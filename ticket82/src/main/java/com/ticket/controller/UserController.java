package com.ticket.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ticket.domain.AdminVO;
import com.ticket.domain.CompanyVO;
import com.ticket.domain.MemberVO;
import com.ticket.service.AdminService;
import com.ticket.service.CompanyService;
import com.ticket.service.MemberService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private AdminService adminService;
	
	//일반 로그인화면
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public void login()throws Exception{}
	
	//일반 로그인체크
	@ResponseBody
	@RequestMapping(value="/loginPost",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	public String loginPost(LoginRequest loginReq, HttpSession session, HttpServletRequest request)throws Exception{
		
		String msg="";
		
		MemberVO member=memberService.login(loginReq.getId());
		
		//아이디가 null이 아닐경우
		if(member!=null){
			if(loginReq.getPw().equals(member.getMem_pw())){
				//로그인 성공
				session.setAttribute("loginUser", member);
				msg="alert('로그인 성공했습니다.');"
					+"location.href='"+request.getContextPath()+"/index'";
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
	
	//기업 로그인화면
	@RequestMapping(value="clogin",method=RequestMethod.GET)
	public void clogin()throws Exception{
	}
	
	//기업 로그인 체크
	@ResponseBody
	@RequestMapping(value="/cloginPost",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	public String cloginPost(LoginRequest loginReq, HttpSession session, HttpServletRequest request)throws Exception{
		
		String msg="";
		
		CompanyVO company=companyService.login(loginReq.getId());
		
		//아이디가 null이 아닐경우
		if(company!=null){
			if(loginReq.getPw().equals(company.getCom_pw())){
				//로그인 성공
				session.setAttribute("loginCompany", company);
				msg="alert('로그인 성공했습니다.');"
				+"location.href='"+request.getContextPath()+"/index'";
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
	
	//관리자 로그인 화면
	@RequestMapping(value="alogin",method=RequestMethod.GET)
	public void alogin()throws Exception{
	}
	
	//관리자 로그인 체크
	@ResponseBody
	@RequestMapping(value="/aloginPost",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	public String aloginPost(LoginRequest loginReq, HttpSession session, HttpServletRequest request)throws Exception{
		
		String msg="";
		
		AdminVO admin=adminService.login(loginReq.getId());
		
		//아이디가 null이 아닐경우
		if(admin!=null){
			if(loginReq.getPw().equals(admin.getAm_pw())){
				//로그인 성공
				session.setAttribute("loginAdmin", admin);
				msg="alert('로그인 성공했습니다.');"
				+"location.href='"+request.getContextPath()+"/index'";
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
		
		return "redirect:/index";
	}
	

}
