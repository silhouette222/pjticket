package com.ticket.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ticket.domain.BoardVO;
import com.ticket.domain.EventVO;
import com.ticket.domain.MemberVO;
import com.ticket.domain.ResVO;
import com.ticket.service.BoardService;
import com.ticket.service.EventService;
import com.ticket.service.MemberService;
import com.ticket.service.ResService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BoardService bs;
	
	@Autowired
	private EventService es;
	
	@Autowired
	private ResService rs;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
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
	
	@RequestMapping(value="/my/info",method=RequestMethod.GET)
	public String myinfo(HttpSession session,Model model)throws Exception{
		MemberVO mem=(MemberVO) session.getAttribute("loginUser");
		String mem_id =mem.getMem_id();
		MemberVO member = memberService.getMemberById(mem_id);
		
		model.addAttribute(member);
		
		return "/member/my/info";
	}
	
	@RequestMapping(value="/my/infoMody",method=RequestMethod.GET)
	public String myinfoMody(HttpSession session,Model model)throws Exception{
		MemberVO mem=(MemberVO) session.getAttribute("loginUser");
		String mem_id =mem.getMem_id();
		MemberVO member = memberService.getMemberById(mem_id);
		
		model.addAttribute(member);
		
		return "/member/my/infoMody";
	}
	
	@RequestMapping(value="/my/infoMody",method=RequestMethod.POST)
	public String infoMody(MemberVO member)throws Exception{
		
		memberService.updateMember(member);
		
		return "redirect:/member/my/info";
	}
	
	@RequestMapping(value="/my/delinfo",method=RequestMethod.POST)
	public String delinfo(MemberVO member)throws Exception{
		member.setEnabled(0);
		System.out.println("1");
		memberService.updateMember(member);
		memberService.deleteMemberAuthority(member.getMem_id());
		return "redirect:/member/my/info";
	}
	
	@RequestMapping(value="/my/reslist",method=RequestMethod.GET)
	public void reslist(Model model,HttpSession session)throws Exception{
		MemberVO mem=(MemberVO) session.getAttribute("loginUser");
		String mem_id =mem.getMem_id();
		List<ResVO> list= rs.selectresbymem_id(mem_id);
		
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
