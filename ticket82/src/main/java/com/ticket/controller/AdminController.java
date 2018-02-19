package com.ticket.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ticket.domain.AdminVO;
import com.ticket.domain.CompanyVO;
import com.ticket.domain.PageMaker;
import com.ticket.domain.SearchCriteria;
import com.ticket.service.AdminService;
import com.ticket.service.CompanyService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService service;
	
	@Autowired
	private CompanyService cs;
	
	//메인화면
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public void index()throws Exception{
		
	}
	
	//기업회원정보
	
	//기업회원 리스트
	@RequestMapping(value="/companyList",method=RequestMethod.GET)
	public String listpage(@ModelAttribute("cri")SearchCriteria cri, Model model)throws Exception{
		
		List<CompanyVO> companyList=cs.readSearchMemberList(cri);
		
		PageMaker pageMaker=new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(cs.readSearchCompanyListCount(cri));
		
		model.addAttribute("list", companyList);
		model.addAttribute("pageMaker", pageMaker);
		
		return "/admin/mypage/companyList";
	}
	
	//기업회원 상세페이지
	@RequestMapping(value="/cread",method=RequestMethod.GET)
	public String read(@ModelAttribute("cri")SearchCriteria cri,
								  @RequestParam("com_id")String com_id,
								  Model model) throws Exception{
		model.addAttribute(cs.getCompanyById(com_id));
		
		return "/admin/mypage/cread";
	}
	
	//기업회원 수정
	@RequestMapping(value="/cmodifyPage",method=RequestMethod.GET)
	public String modifyPageForm(@ModelAttribute("cri")SearchCriteria cri,
													String com_id, Model model) throws Exception{
		model.addAttribute(cs.getCompanyById(com_id));
		return "/admin/mypage/cmodifyPage";
	}
	
	@RequestMapping(value="/cmodify",method=RequestMethod.POST)
	public String modifyPage(CompanyVO company, SearchCriteria cri,
							RedirectAttributes rttr) throws Exception{
		System.out.println(company);
		System.out.println(cri);
		cs.updateCompany(company);
		
		rttr.addAttribute("page",cri.getPage());
		rttr.addAttribute("perPageNum",cri.getPerPageNum());
		rttr.addAttribute("searchType",cri.getSearchType());
		rttr.addAttribute("keyword",cri.getKeyword());
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/admin/companyList";
	}
	
	//기업회원 삭제
	@RequestMapping(value="/cremove",method=RequestMethod.POST)
	public String remove(@RequestParam("com_id")String com_id, SearchCriteria cri,
						RedirectAttributes rttr) throws Exception{
		
		cs.deleteCompany(com_id);
		
		rttr.addAttribute("page",cri.getPage());
		rttr.addAttribute("perPageNum",cri.getPerPageNum());
		rttr.addAttribute("searchType",cri.getSearchType());
		rttr.addAttribute("keyword",cri.getKeyword());
		
		
		return "redirect:/admin/mypage/companyList";
	}
	
	//로그인
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public void login()throws Exception{}
	
	//로그인체크
	@ResponseBody
	@RequestMapping(value="/loginPost",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	public String loginPost(LoginRequest loginReq, HttpSession session, HttpServletRequest request)throws Exception{
		
		String msg="";
		
		AdminVO admin=service.login(loginReq.getId());
		
		//아이디가 null이 아닐경우
		if(admin!=null){
			if(loginReq.getPw().equals(admin.getAm_pw())){
				//로그인 성공
				session.setAttribute("loginUser", admin);
				msg="alert('로그인 성공했습니다.');"
					+"location.href='"+request.getContextPath()+"/admin/index'";
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
		
		return "redirect:/admin/index";
	}
	

}
