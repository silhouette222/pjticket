package com.ticket.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.model.PlacesSearchResult;
import com.ticket.domain.AdminCri;
import com.ticket.domain.BoardVO;
import com.ticket.domain.CompanyVO;
import com.ticket.domain.PageMaker;
import com.ticket.domain.ResVO;
import com.ticket.domain.SearchCriteria;
import com.ticket.service.BoardService;
import com.ticket.service.CompanyService;
import com.ticket.service.MusicalService;
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
	
	@Autowired
	private MusicalService ms;
	
	//메인화면
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public void index(HttpSession session)throws Exception{
		if(session.getAttribute("loginUser2")!=null){
		session.setAttribute("loginUser",cs.getCompanyById((String)session.getAttribute("loginUser2")));
		}
	}
	
	//회원정보
	@RequestMapping(value="/my/info",method=RequestMethod.GET)
	public String myinfo(HttpSession session,Model model)throws Exception{
		
		CompanyVO com=(CompanyVO) session.getAttribute("loginUser");
		String com_id=com.getCom_id();
		CompanyVO company = us.selectcombyid(com_id);
		
		model.addAttribute(company);
		
		return "/cboard/my/info";
	}
	
	//회원정보 수정폼
	@RequestMapping(value="/my/infoMody",method=RequestMethod.GET)
	public String myinfoMody(HttpSession session,Model model)throws Exception{
		
		CompanyVO com=(CompanyVO) session.getAttribute("loginUser");
		String com_id=com.getCom_id();
		CompanyVO company = us.selectcombyid(com_id);
		model.addAttribute(company);
		
		return "/cboard/my/infoMody";
	}
	
	//회원수정
	@RequestMapping(value="/my/infoMody",method=RequestMethod.POST)
	public String infoMody(CompanyVO company)throws Exception{
		
		us.updatecom(company);
		
		return "redirect:/cboard/my/info";
	}
	
	//회원탈퇴
	@RequestMapping(value="/my/delinfo",method=RequestMethod.POST)
	public String delinfo(CompanyVO company)throws Exception{
		company.setEnabled(0);
		us.updatecom(company);
		return "redirect:/cboard/index";
	}
	
	//예약확인
	@RequestMapping(value="/my/reslist",method=RequestMethod.GET)
	public void reslist(@ModelAttribute("cri")AdminCri cri,Model model,HttpSession session)throws Exception{
		CompanyVO com=(CompanyVO) session.getAttribute("loginUser");
		/*String com_id=com.getCom_id();
		List<BoardVO> comlist = bs.readBoardListByComId(com_id);
		System.out.println(comlist);
		for(BoardVO b:comlist){
			List<ResVO> list= rs.selectresbyttr_no(b.getTtr_no());
			System.out.println(list);
			List<Object> reslist = new ArrayList();
			for(ResVO a:list){
				Reservation res= new Reservation();
				String title=bs.readBoardByNo(a.getTtr_no()).getTtr_title();
				Date date=bs.readseatbyseat_id(a.getSeat_id()).getSeat_date();
				Date time=bs.readseatbyseat_id(a.getSeat_id()).getSeat_time();
				String place=bs.readBoardByNo(a.getTtr_no()).getTtr_place();
				int seat=a.getRes_nom();
				int status=a.getStatus();
				String res_id=a.getRes_id();
				String imp_uid=a.getImp_uid();
				
				res.setTitle(title);
				res.setDate(date);
				res.setTime(time);
				res.setPlace(place);
				res.setStatus(status);
				res.setSeat(seat);
				res.setRes_id(res_id);
				res.setImp_uid(imp_uid);
				
				reslist.add(res);
				System.out.println(res);
			}
			model.addAttribute("reslist", reslist);
			
			
		}*/
		String com_id=com.getCom_id();
		List<BoardVO> comlist = bs.readBoardListByComId(com_id);
		for(BoardVO b:comlist){
			cri.setSearch("no");
			cri.setKeyword(Integer.toString(b.getTtr_no()));
			cri.setOrd("no_asc");
			List<ResVO> list=us.searchsortres(cri);
			List<Object> reslist = new ArrayList();
			
			for(ResVO a:list){
				Reservation res= new Reservation();
				String title=bs.readBoardByNo(a.getTtr_no()).getTtr_title();
				Date date=bs.readseatbyseat_id(a.getSeat_id()).getSeat_date();
				Date time=bs.readseatbyseat_id(a.getSeat_id()).getSeat_time();
				String place=bs.readBoardByNo(a.getTtr_no()).getTtr_place();
				int seat=a.getRes_nom();
				int status=a.getStatus();
				String res_id=a.getRes_id();
				String imp_uid=a.getImp_uid();
				
				res.setTitle(title);
				res.setDate(date);
				res.setTime(time);
				res.setPlace(place);
				res.setStatus(status);
				res.setSeat(seat);
				res.setRes_id(res_id);
				res.setImp_uid(imp_uid);
				
				reslist.add(res);
				System.out.println(res);
			}
			model.addAttribute("reslist", reslist);
		}
	}
	
	@RequestMapping(value="/my/reslist2/{page}/{pagenum}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> reslist2(@ModelAttribute("cri")AdminCri cri,@PathVariable("page")int page,@PathVariable("pagenum")int pagenum,Model model,HttpSession session)throws Exception{
		CompanyVO com=(CompanyVO) session.getAttribute("loginUser");
		String com_id=com.getCom_id();
		List<BoardVO> comlist = bs.readBoardListByComId(com_id);
		ResponseEntity<Map<String, Object>> entity = null;
		List<Object> reslist = new ArrayList();
		Map<String, Object> map = new HashMap<String, Object>();
		PageMaker pageMaker = new PageMaker();
		cri.setPage(page);
		cri.setPerPageNum(pagenum);
		for(BoardVO b:comlist){
			cri.setSearch("no");
			cri.setKeyword(Integer.toString(b.getTtr_no()));
			cri.setOrd("no_asc");
			
			pageMaker.setCri(cri);
			
			List<ResVO> list=us.searchsortres(cri);
			
			
			for(ResVO a:list){
				Reservation res= new Reservation();
				String title=bs.readBoardByNo(a.getTtr_no()).getTtr_title();
				Date date=bs.readseatbyseat_id(a.getSeat_id()).getSeat_date();
				Date time=bs.readseatbyseat_id(a.getSeat_id()).getSeat_time();
				String place=bs.readBoardByNo(a.getTtr_no()).getTtr_place();
				int seat=a.getRes_nom();
				int status=a.getStatus();
				String res_id=a.getRes_id();
				String imp_uid=a.getImp_uid();
				
				res.setTitle(title);
				res.setDate(date);
				res.setTime(time);
				res.setPlace(place);
				res.setStatus(status);
				res.setSeat(seat);
				res.setRes_id(res_id);
				res.setImp_uid(imp_uid);
				
				reslist.add(res);
				System.out.println(res);
			}
		}
		map.put("reslist",reslist);
		model.addAttribute("reslist", reslist);
		int count=us.countboard();
		pageMaker.setTotalCount(count);
		map.put("pageMaker", pageMaker);
		entity=new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		return entity;
	}
	
	//게시글확인
	@RequestMapping(value = "/my/board", method = RequestMethod.GET)
	public String readboard(@ModelAttribute("cri")AdminCri cri,Model model,HttpSession session) throws Exception{
		CompanyVO com=(CompanyVO) session.getAttribute("loginUser");
		String com_id=com.getCom_id();
		cri.setSearch("id");
		cri.setKeyword(com_id);
		cri.setOrd("no_asc");
		List<BoardVO> list=us.searchsortboard(cri);
		model.addAttribute("list",list);
		return "/cboard/my/board";
	}
	@RequestMapping(value = "/my/board2/{page}/{pagenum}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> readboardpost(@ModelAttribute("cri")AdminCri cri,@PathVariable("page")int page,@PathVariable("pagenum")int pagenum,Model model,HttpSession session) throws Exception{
		ResponseEntity<Map<String, Object>> entity = null;
		CompanyVO com=(CompanyVO) session.getAttribute("loginUser");
		String com_id=com.getCom_id();
		cri.setSearch("id");
		cri.setKeyword(com_id);
		cri.setOrd("no_asc");
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		List<BoardVO> list=us.searchsortboard(cri);
		Map<String, Object> map = new HashMap<String, Object>();
		model.addAttribute("list",list);
		map.put("list",list);
		int count=us.countboard();
		pageMaker.setTotalCount(count);
		map.put("pageMaker", pageMaker);
		entity=new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		return entity;
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
		
		String key="AIzaSyBpdwpdpcgThSmCAME3OJ8esqYy_d2Tc5M";
		GeoApiContext context = new GeoApiContext.Builder().apiKey(key).build();
		PlacesSearchResult[] res2=PlacesApi.textSearchQuery(context,board.getTtr_place()).await().results;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String lat=gson.toJson(res2[0].geometry.location.lat);
		String lng=gson.toJson(res2[0].geometry.location.lng);
		model.addAttribute("lat", lat);
		model.addAttribute("lng", lng);
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
		
		String key="AIzaSyBpdwpdpcgThSmCAME3OJ8esqYy_d2Tc5M";
		GeoApiContext context = new GeoApiContext.Builder().apiKey(key).build();
		PlacesSearchResult[] res2=PlacesApi.textSearchQuery(context,board.getTtr_place()).await().results;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String lat=gson.toJson(res2[0].geometry.location.lat);
		String lng=gson.toJson(res2[0].geometry.location.lng);
		model.addAttribute("lat", lat);
		model.addAttribute("lng", lng);
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
