package com.ticket.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ticket.domain.BoardVO;
import com.ticket.domain.CompanyVO;
import com.ticket.domain.SearchCriteria;
import com.ticket.service.BoardService;
import com.ticket.service.CompanyService;
import com.ticket.service.ResService;
import com.ticket.service.UserService;

@Controller
@RequestMapping("/cboard")
public class CBoardController {

	@Autowired
	private BoardService bs;
	
	@Autowired
	private ResService rs;
	
	@Autowired
	private UserService us;
	
	@Autowired
	private CompanyService cs;
	
	//메인화면
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public void index(HttpSession session)throws Exception{
		if(session.getAttribute("loginUser2")!=null){
		session.setAttribute("loginUser",cs.getCompanyById((String)session.getAttribute("loginUser2")));
		}
	}
	
	//로그인
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public void login()throws Exception{}
	
	//로그인체크
	@ResponseBody
	@RequestMapping(value="/loginPost",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	public String loginPost(LoginRequest loginReq, HttpSession session, HttpServletRequest request)throws Exception{
		
		String msg="";
		
		CompanyVO company=cs.login(loginReq.getId());
		
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
		
		return "redirect:/cboard/index";
	}
	
	//회원가입
	@RequestMapping(value="/regist",method=RequestMethod.GET)
	public void registCompany()throws Exception{
	}
	
	@RequestMapping(value="/regist",method=RequestMethod.POST)
	public String registPost(CompanyVO company)throws Exception{
		cs.insertCompany(company);
		cs.insertCompanyAuthority(company);
		return "redirect:/cboard/login";
	}
	
	@ResponseBody
	@RequestMapping(value="/idcheck",method=RequestMethod.POST)
	public String duplication(HttpServletRequest request) throws Exception{
		String id = request.getParameter("id");
		int rowcount=cs.checkId(id);
		return String.valueOf(rowcount);
	}
	
	
	@InitBinder public void initBinder(WebDataBinder binder) 
	{ 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat time = new SimpleDateFormat("hh:mm");
		binder.registerCustomEditor(Date.class,"ttr_sdate", new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Date.class,"ttr_edate", new CustomDateEditor(time, true));
		binder.registerCustomEditor(Date.class,"ttr_date", new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Date.class,"seat_date", new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Date.class,"seat_time", new CustomDateEditor(time, true));
	}
	
	@RequestMapping(value = "/etc", method = RequestMethod.GET)
	public String readetc(@ModelAttribute("cri")SearchCriteria cri,Model model,HttpSession session) throws Exception{
		//테스트용
		String url="cboard/etc";
		cri.setTtr_cat("etc");
		List<BoardVO> boardList=bs.searchBoardList(cri);
		model.addAttribute("list",boardList);
		return url;
	}
	
	@RequestMapping(value="/etc/etcdetail",method=RequestMethod.GET)
	public String etcdetail(@RequestParam("ttr_no")int ttr_no,Model model) throws Exception{
		String url="cboard/etc/etcdetail";
		BoardVO board=bs.readBoardByNo(ttr_no);
		model.addAttribute(board);
		return url;
	}
	
	@RequestMapping(value="etc/etcupdate",method=RequestMethod.GET)
	public String etcupdate(@RequestParam("ttr_no")int ttr_no,Model model) throws Exception{
		String url="cboard/etc/etcupdate";
		BoardVO board=bs.readBoardByNo(ttr_no);
		model.addAttribute(board);
		return url;
	}
	
	@RequestMapping(value="etc/etcupdate",method=RequestMethod.POST)
	public String etcupdate(BoardVO board,Model model) throws Exception{
		String url="redirect:/cboard/etc";
		try{
			bs.updateBoard(board);
			model.addAttribute(board);
		}catch(SQLException e){
			throw e;
		}
		return url;
	}
	
	@RequestMapping(value="/etc/delete",method=RequestMethod.GET)
	public String etcdelete(int ttr_no) throws Exception{
		String url="redirect:/cboard/etc";
		bs.deleteBoard(ttr_no);
		return url;
	}
	
	@RequestMapping(value = "/gal", method = RequestMethod.GET)
	public String readgal(@ModelAttribute("cri")SearchCriteria cri,Model model,HttpSession session) throws Exception{
		//테스트용
		String url="cboard/gal";
		cri.setTtr_cat("gal");
		List<BoardVO> boardList=bs.searchBoardList(cri);
		model.addAttribute("list",boardList);
		return url;
	}
	
	@RequestMapping(value="/gal/galdetail",method=RequestMethod.GET)
	public String galdetail(@RequestParam("ttr_no")int ttr_no,Model model) throws Exception{
		String url="cboard/gal/galdetail";
		BoardVO board=bs.readBoardByNo(ttr_no);
		model.addAttribute(board);
		return url;
	}
	
	@RequestMapping(value="/gal/galupdate",method=RequestMethod.GET)
	public String galupdate(@RequestParam("ttr_no")int ttr_no,Model model) throws Exception{
		String url="cboard/gal/galupdate";
		BoardVO board=bs.readBoardByNo(ttr_no);
		model.addAttribute(board);
		return url;
	}
	
	@RequestMapping(value="/gal/galupdate",method=RequestMethod.POST)
	public String galupdate(BoardVO board,Model model) throws Exception{
		String url="redirect:/cboard/gal";
		try{
			bs.updateBoard(board);
			model.addAttribute(board);
		}catch(SQLException e){
			throw e;
		}
		return url;
	}
	
	@RequestMapping(value="/gal/delete",method=RequestMethod.GET)
	public String galdelete(int ttr_no) throws Exception{
		String url="redirect:/cboard/gal";
		bs.deleteBoard(ttr_no);
		return url;
	}
	
	@RequestMapping("/getFiles/{ttr_no}")
	@ResponseBody
	public List<String> getFiles(@PathVariable("ttr_no") int ttr_no) throws Exception{
		return bs.getFiles(ttr_no);
	}
	
	@RequestMapping(value="/write/{ttr_cat}",method=RequestMethod.GET)
	public String etcwriteForm(@PathVariable("ttr_cat")String ttr_cat,Model model) throws Exception{
		String url="cboard/write";
		model.addAttribute("ttr_cat",ttr_cat);
		return url;
	}
	
	@RequestMapping(value="/write/{ttr_cat}",method=RequestMethod.POST)
	public String etcwriteSubmit(BoardVO board,Model model) throws Exception{
		String url="redirect:/main";
		try{
			bs.insertBoard(board);
			model.addAttribute(board);
		}catch(SQLException e){
			throw e;
		}
		return url;
	}
	@RequestMapping(value="/scoreavg/{ttr_no}")
	public ResponseEntity<Double> scoreavg(@PathVariable("ttr_no")int ttr_no){
		ResponseEntity<Double> entity = null;
		try {
			double avg=bs.scoreavg(ttr_no);
			double b = Math.round(avg*100d)/100d;
			entity = new ResponseEntity<Double>(b, HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}
}
