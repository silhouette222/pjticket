package com.ticket.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
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

import com.ticket.domain.AdminCri;
import com.ticket.domain.AdminVO;
import com.ticket.domain.BoardVO;
import com.ticket.domain.CompanyVO;
import com.ticket.domain.MailRequest;
import com.ticket.domain.MemberVO;
import com.ticket.domain.PageMaker;
import com.ticket.domain.ResVO;
import com.ticket.service.AdminService;
import com.ticket.service.BoardService;
import com.ticket.service.ResService;
import com.ticket.service.UserService;

@Controller
@RequestMapping("/aboard")
public class ABoardController {
	
	@Autowired
	private BoardService bs;
	
	@Autowired
	private ResService rs;
	
	@Autowired
	private UserService us;
	
	@Autowired
	private MailSender sender;
	
	@Autowired
	private AdminService as;
	
	//메인화면
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public void index(HttpSession session)throws Exception{
		if(session.getAttribute("loginUser2")!=null){
		session.setAttribute("loginUser",as.getAdminById((String)session.getAttribute("loginUser2")));
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
		
		AdminVO admin=as.login(loginReq.getId());
		
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
	
	@RequestMapping(value="/main")
	public void main(){};
	
	@InitBinder public void initBinder(WebDataBinder binder) 
	{ 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat time = new SimpleDateFormat("hh:mm");
		SimpleDateFormat timestamp = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		binder.registerCustomEditor(Date.class,"ttr_sdate", new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Date.class,"ttr_edate", new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Date.class,"ttr_date", new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Date.class,"seat_date", new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Date.class,"seat_time", new CustomDateEditor(time, true));
		/*binder.registerCustomEditor(Date.class,"mem_birth", new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Date.class,"mem_date", new CustomDateEditor(dateFormat, true));*/
		binder.registerCustomEditor(Date.class,"res_date", new CustomDateEditor(dateFormat, true));
	}
	
	@RequestMapping(value = "/mem", method = RequestMethod.GET)
	public String readmem(@ModelAttribute("cri")AdminCri cri,Model model,HttpSession session) throws Exception{
		String url="aboard/memlist";
		List<MemberVO> list=us.searchsortmem(cri);
		model.addAttribute("list",list);
		return url;
	}
	@RequestMapping(value = "/mem", method = RequestMethod.POST)
	public String updatemem(MemberVO mem) throws Exception{
		String url="redirect:/aboard/mem";
		us.updatemem(mem);
		return url;
	}
	
	@RequestMapping(value = "/com", method = RequestMethod.GET)
	public String readcom(@ModelAttribute("cri")AdminCri cri,Model model,HttpSession session) throws Exception{
		String url="aboard/comlist";
		List<CompanyVO> list=us.searchsortcom(cri);
		model.addAttribute("list",list);
		return url;
	}
	@RequestMapping(value = "/com", method = RequestMethod.POST)
	public String updatecom(CompanyVO com) throws Exception{
		String url="redirect:/aboard/com";
		us.updatecom(com);
		return url;
	}
	
	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public String readboard(@ModelAttribute("cri")AdminCri cri,Model model,HttpSession session) throws Exception{
		String url="aboard/boardlist";
		List<BoardVO> list=us.searchsortboard(cri);
		model.addAttribute("list",list);
		return url;
	}
	@RequestMapping(value = "/updateboard", method = RequestMethod.GET)
	public String updateboard(@RequestParam("ttr_no")int ttr_no,Model model) throws Exception{
		String url="aboard/boardupdate";
		BoardVO board=bs.readBoardByNo(ttr_no);
		model.addAttribute(board);
		return url;
	}
	@RequestMapping(value="/updateboard",method=RequestMethod.POST)
	public String updateboard(BoardVO board,Model model) throws Exception{
		String url="redirect:/aboard/board";
		try{
			us.updateboard(board);
			model.addAttribute(board);
		}catch(SQLException e){
			throw e;
		}
		return url;
	}
	
	@RequestMapping(value = "/res", method = RequestMethod.GET)
	public String readres(@ModelAttribute("cri")AdminCri cri,Model model,HttpSession session) throws Exception{
		String url="aboard/reslist";
		List<ResVO> list=us.searchsortres(cri);
		model.addAttribute("list",list);
		return url;
	}
	
	@RequestMapping(value = "/resdel/{pk}", method = RequestMethod.GET)
	public void resdel(@PathVariable("pk")String pk) throws Exception{
		rs.deleteresbyres_id(pk);
	}
	
	@RequestMapping(value="/{type}/{pk}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> detail(@PathVariable("type")String type,@PathVariable("pk")String pk) {
		ResponseEntity<Map<String, Object>> entity = null;
	try {
		Map<String, Object> map = new HashMap<String, Object>();
		if(type.equals("board")) {
			BoardVO res=us.selectpkboard(Integer.parseInt(pk));
			map.put("res",res);
		}else if(type.equals("res")) {
			ResVO res=us.selectpkres(pk);
			map.put("res",res);
		}else if(type.equals("mem")) {
			MemberVO res=us.selectpkmem(pk);
			map.put("res",res);
		}else if(type.equals("com")) {
			CompanyVO res=us.selectpkcom(pk);
			map.put("res",res);
		}
		entity=new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}catch(Exception e){
		e.printStackTrace();
		entity=new ResponseEntity<Map<String,Object>>(HttpStatus.BAD_REQUEST);
	}
	return entity;
	}
	
	@RequestMapping(value="/delete/{type}/{pk}")
	public void delete(@PathVariable("type")String type,@PathVariable("pk")String pk) {
	try {
		if(type.equals("board")) {
			bs.deleteBoard(Integer.parseInt(pk));
		}else if(type.equals("res")) {
			rs.deleteresbyres_id(pk);
		}else if(type.equals("mem")) {
			us.deletemem(pk);
		}else if(type.equals("com")) {
			us.deletecom(pk);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	}
	
	@RequestMapping(value = "/{type}/{search}/{keyword}/{ord}/{page}/{pagenum}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> readboardpost2(@PathVariable("type")String type,@PathVariable("search")String search,@PathVariable("keyword")String keyword,@PathVariable("ord")String ord,@PathVariable("page")int page,@PathVariable("pagenum")int pagenum,Model model,HttpSession session) throws Exception{
		ResponseEntity<Map<String, Object>> entity = null;
		AdminCri cri=new AdminCri(search,keyword,ord);
		cri.setPage(page);
		cri.setPerPageNum(pagenum);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		System.out.println(cri);
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if(type.equals("board")) {
				List<BoardVO> list=us.searchsortboard(cri);
				model.addAttribute("list",list);
				map.put("list",list);
			}else if(type.equals("res")) {
				List<ResVO> list=us.searchsortres(cri);
				model.addAttribute("list",list);
				map.put("list",list);
			}else if(type.equals("mem")) {
				List<MemberVO> list=us.searchsortmem(cri);
				model.addAttribute("list",list);
				map.put("list",list);
			}else if(type.equals("com")) {
				List<CompanyVO> list=us.searchsortcom(cri);
				model.addAttribute("list",list);
				map.put("list",list);
			}
			int count=us.countboard();
			pageMaker.setTotalCount(count);
			map.put("pageMaker", pageMaker);
			entity=new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			entity=new ResponseEntity<Map<String,Object>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value = "/mail", method = RequestMethod.POST)
	public void mail(HttpServletRequest request,
			HttpServletResponse response, MailRequest mailReq) throws Exception {
		SimpleMailMessage message=new SimpleMailMessage(); //text message
		
		for(int i=0;i<mailReq.getTo().length;i++){
			message.setSubject(mailReq.getTitle());
			message.setFrom(mailReq.getFrom());
			message.setText(mailReq.getContent());
			message.setTo(mailReq.getTo()[i]);
			sender.send(message);
		}
	}
	@RequestMapping(value = "/mailall", method = RequestMethod.POST)
	public void mailall(HttpServletRequest request,
			HttpServletResponse response, MailRequest mailReq) throws Exception {
		SimpleMailMessage message=new SimpleMailMessage(); //text message
		List<MemberVO> all=us.selectmem();
		String[] toall=new String[all.size()];
		for(int i=0;i<all.size();i++){
			toall[i]=all.get(i).getMem_mail();
		}
		mailReq.setTo(toall);
		for(int i=0;i<mailReq.getTo().length;i++){
			message.setSubject(mailReq.getTitle());
			message.setFrom(mailReq.getFrom());
			message.setText(mailReq.getContent());
			message.setTo(mailReq.getTo()[i]);
			sender.send(message);
		}
	}
}
