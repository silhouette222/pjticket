package com.ticket.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ticket.domain.BoardVO;
import com.ticket.domain.EventVO;
import com.ticket.domain.MemberVO;
import com.ticket.domain.PageMaker;
import com.ticket.domain.SearchCriteria;
import com.ticket.service.BoardService;
import com.ticket.service.EventService;
import com.ticket.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BoardService bs;
	
	@Autowired
	private EventService es;
	
	//메인화면
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public void index(HttpSession session)throws Exception{
		if(session.getAttribute("loginUser2")!=null){
		session.setAttribute("loginUser",memberService.getMemberById((String)session.getAttribute("loginUser2")));
		}
	}
	
	@RequestMapping(value="/indexModify",method=RequestMethod.GET)
	public void indexm(HttpSession session,Model model)throws Exception{
		if(session.getAttribute("loginUser2")!=null){
		session.setAttribute("loginUser",memberService.getMemberById((String)session.getAttribute("loginUser2")));
		}
		List<BoardVO> boardList=bs.readBoardList();
		model.addAttribute("list",boardList);
		List<EventVO> eventList=es.readEventList();
		model.addAttribute("elist",eventList);
	}
	
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public void listpage(@ModelAttribute("cri")SearchCriteria cri, Model model)throws Exception{
		List<MemberVO> memberList=memberService.readSearchMemberList(cri);
		
		PageMaker pageMaker=new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(memberService.readSearchMemberListCount(cri));
		
		model.addAttribute("list", memberList);
		model.addAttribute("pageMaker", pageMaker);
	}
	
	
	@RequestMapping(value="/regist",method=RequestMethod.GET)
	public void registMember()throws Exception{
	}
	
	@RequestMapping(value="/regist",method=RequestMethod.POST)
	public String registPost(MemberVO member)throws Exception{
		memberService.insertMember(member);
		memberService.insertMemberAuthority(member);
		return "redirect:/member/login";
	}
	
	@RequestMapping(value="/readPage",method=RequestMethod.GET)
	public void readMember(@ModelAttribute("cri")SearchCriteria cri, @RequestParam("mem_id")String mem_id,Model model)throws Exception{
		model.addAttribute(memberService.getMemberById(mem_id));
	}
	
	@RequestMapping(value="/modifyPageForm",method=RequestMethod.GET)
	public String modifyMember (@ModelAttribute("cri")SearchCriteria cri, String mem_id,Model model)throws Exception{
		model.addAttribute(memberService.getMemberById(mem_id));
		return "/member/modifyPage";
	}
	
	@RequestMapping(value="/modifyPage",method=RequestMethod.POST)
	public String modifyMember(MemberVO member, SearchCriteria cri, RedirectAttributes rttr)throws Exception{
		System.out.println(member);
		
		memberService.updateMember(member);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/member/list";
	}
	
	@RequestMapping(value="/removeMember",method=RequestMethod.POST)
	public String removeMember(@RequestParam("mem_id")String mem_id, SearchCriteria cri, RedirectAttributes rttr)throws Exception{
		memberService.deleteMember(mem_id);
		
		rttr.addAttribute("page",cri.getPage());
		rttr.addAttribute("perPageNum",cri.getPerPageNum());
		rttr.addAttribute("searchType",cri.getSearchType());
		rttr.addAttribute("keyword",cri.getKeyword());
		
		return "redirect:/member/list";
	}
	
	@ResponseBody
	@RequestMapping(value="/idcheck",method=RequestMethod.POST)
	public String duplication(HttpServletRequest request) throws Exception{
		String id = request.getParameter("id");
		int rowcount=memberService.checkId(id);
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
		
		MemberVO member=memberService.login(loginReq.getId());
		System.out.println(member);
		//아이디가 null이 아닐경우
		if(member!=null){
			if(loginReq.getPw().equals(member.getMem_pw())){
				//로그인 성공
				
				session.setAttribute("loginUser",member);
				msg="alert('로그인 성공했습니다.');"
					+"location.href='"+request.getContextPath()+"/member/index'";
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
		
		return "redirect:/member/index";
	}
	
	//아이디/비번 찾기
	@RequestMapping(value="/findPass", method = RequestMethod.GET)
    public String find(){
		return "/member/findPassView";
	}
	
	@RequestMapping(value="/findPass", method = RequestMethod.POST)
    public String find_pass(MemberVO member,RedirectAttributes redirectattr,Errors errors) {
        new FindPassValidator().validate(member, errors);
        
        if(errors.hasErrors())
            return "/member/findPassView";
        
        
        try {
            MemberVO result = memberService.getMemberByEmail(member.getMem_mail());
            redirectattr.addFlashAttribute("result",result); 
            return "redirect:/sendpass";
        }catch(Exception e)
        {
            errors.reject("EmailNotExist");
            return "/member/findPassView"; 
        }
        
    }


}
