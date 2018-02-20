package com.ticket.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

import com.ticket.domain.SearchCriteria;
import com.ticket.domain.BoardVO;
import com.ticket.domain.MemberVO;
import com.ticket.domain.PayVO;
import com.ticket.domain.ResVO;
import com.ticket.domain.Seatinfo;
import com.ticket.service.BoardService;
import com.ticket.service.ResService;
import com.ticket.service.UserService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/mboard")
public class MBoardController {

	@Autowired
	private BoardService bs;
	
	@Autowired
	private ResService rs;
	
	@Autowired
	private UserService us;
	
	@InitBinder public void initBinder(WebDataBinder binder) 
	{ 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); binder.registerCustomEditor(Date.class,"ttr_sdate", new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Date.class,"ttr_edate", new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Date.class,"ttr_date", new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Date.class,"seat_date", new CustomDateEditor(dateFormat, true));
		SimpleDateFormat time = new SimpleDateFormat("hh:mm"); binder.registerCustomEditor(Date.class,"seat_time", new CustomDateEditor(time, true));
	}
	
	@RequestMapping(value = "/etc", method = RequestMethod.GET)
	public String readetc(@ModelAttribute("cri")SearchCriteria cri,Model model,HttpSession session) throws Exception{
		//테스트용
		System.out.println(session.getAttribute("loginUser"));
		String url="mboard/etc";
		cri.setTtr_cat("etc");
		List<BoardVO> boardList=bs.searchBoardList(cri);
		model.addAttribute("list",boardList);
		return url;
	}
	
	@RequestMapping(value="/etc/etcdetail",method=RequestMethod.GET)
	public String etcdetail(@RequestParam("ttr_no")int ttr_no,Model model) throws Exception{
		String url="mboard/etc/etcdetail";
		BoardVO board=bs.readBoardByNo(ttr_no);
		model.addAttribute(board);
		return url;
	}
	
	@RequestMapping(value = "/gal", method = RequestMethod.GET)
	public String readgal(@ModelAttribute("cri")SearchCriteria cri,Model model,HttpSession session) throws Exception{
		//테스트용
		session.getAttribute("loginUser");
		System.out.println(session.getAttribute("loginUser"));
		String url="mboard/gal";
		cri.setTtr_cat("gal");
		List<BoardVO> boardList=bs.searchBoardList(cri);
		model.addAttribute("list",boardList);
		return url;
	}
	
	@RequestMapping(value="/gal/galdetail",method=RequestMethod.GET)
	public String galdetail(@RequestParam("ttr_no")int ttr_no,Model model) throws Exception{
		String url="mboard/gal/galdetail";
		BoardVO board=bs.readBoardByNo(ttr_no);
		model.addAttribute(board);
		return url;
	}
	
	@RequestMapping(value="/reserve",method=RequestMethod.GET)
	public String reserve(@RequestParam("ttr_no")int ttr_no,Model model) throws Exception{
		String url="mboard/reserve";
		BoardVO board=bs.readBoardByNo(ttr_no);
		String[] seat_ids=board.getSeat_id();
		int[] seat_cnt=new int[seat_ids.length];
		for(int i=0;i<seat_ids.length;i++){
			seat_cnt[i]=rs.countresbyseat_id(seat_ids[i]);
		}
		model.addAttribute(board);
		model.addAttribute("seat_cnt",seat_cnt);
		return url;
	}

	
	@RequestMapping(value="/reserve",method=RequestMethod.POST)
	public String reserve(@RequestParam("rescheck")String[] rescheck,HttpSession session,BoardVO board,Model model) throws Exception{
		String url="mboard/respay";
		String[] seat_id=new String[rescheck.length];
		int[] res_nom=new int[rescheck.length];
		MemberVO mem=(MemberVO) session.getAttribute("loginUser");
		List<ResVO> reslist=new ArrayList<ResVO>();
		List<Seatinfo> seatlist=new ArrayList<Seatinfo>();
		int pri=0;
		for(int i=0;i<rescheck.length;i++){
			seat_id[i]=rescheck[i].split("_")[0];
			res_nom[i]=Integer.parseInt(rescheck[i].split("_")[1]);
		}
		for(int i=0;i<rescheck.length;i++){
			ResVO res=new ResVO(null,null,res_nom[i],seat_id[i],mem.getMem_id(),board.getTtr_no(),null,1);
			reslist.add(res);
		}
		for(ResVO res:reslist){
			seatlist.add(bs.readseatbyseat_id(res.getSeat_id()));
		}
		for(Seatinfo seat:seatlist){
			pri+=seat.getSeat_pri();
		}
		PayVO pay=new PayVO(board.getTtr_title(),pri,mem.getMem_mail(),mem.getMem_id(),mem.getMem_mobile(),mem.getMem_addr(),null);
		model.addAttribute("reslist",reslist);
		model.addAttribute("seatlist",seatlist);
		model.addAttribute("pay",pay);
		return url;
	}
	
	@RequestMapping(value="/respay",method=RequestMethod.POST)
	@ResponseBody
	public String respay(@RequestParam("reslist")String param,@RequestParam("imp_uid")String imp_uid) throws Exception{
		List<Map<String, Object>> maplist=new ArrayList<Map<String,Object>>();
		maplist=JSONArray.fromObject(param);
		for(Map<String, Object> map:maplist){
			ResVO res=new ResVO(null,null,(int)map.get("res_nom"),(String)map.get("seat_id"),(String)map.get("mem_id"),(int)map.get("ttr_no"),(String)map.get("imp_uid"),1);
			ResVO rescheck=rs.selectresbyres_nom((int)map.get("res_nom"),(String)map.get("seat_id"));
			if(rescheck.getStatus()==1){
				return "already reserved";
			}else{
				rs.insertres(res);
			}
		}
		return "everythings_fine";
	}
	
	@RequestMapping(value="/reserve/{seat_id}")
	@ResponseBody
	public int[] reservedseat(@PathVariable("seat_id") String seat_id) throws Exception{
		List<ResVO> reslist = rs.selectresbyseat_id(seat_id);
		int[] res_end=new int[reslist.size()];
		for(int i=0;i<reslist.size();i++){
			res_end[i]=reslist.get(i).getRes_nom();
		}
		return res_end;
	}
	
	@RequestMapping(value="/rescomplet",method=RequestMethod.GET)
	public void rescomplet(){}
	
	@RequestMapping("/getFiles/{ttr_no}")
	@ResponseBody
	public List<String> getFiles(@PathVariable("ttr_no") int ttr_no) throws Exception{
		return bs.getFiles(ttr_no);
	}
	
	@RequestMapping(value="/addzzim/{mem_id}/{ttr_no}")
	public ResponseEntity<String> addzzim(@PathVariable("mem_id")String mem_id,@PathVariable("ttr_no")int ttr_no){
		ResponseEntity<String> entity = null;
		try {
			bs.insertzzim(mem_id, ttr_no);
			entity = new ResponseEntity<String>("addzzim", HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}
	
	@RequestMapping(value="/delzzim/{mem_id}/{ttr_no}")
	public ResponseEntity<String> delzzim(@PathVariable("mem_id")String mem_id,@PathVariable("ttr_no")int ttr_no){
		ResponseEntity<String> entity = null;
		try {
			bs.deletezzim(mem_id, ttr_no);
			entity = new ResponseEntity<String>("delzzim", HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}
	@RequestMapping(value="/selzzim/{mem_id}/{ttr_no}")
	public ResponseEntity<String> selzzim(@PathVariable("mem_id")String mem_id,@PathVariable("ttr_no")int ttr_no){
		ResponseEntity<String> entity = null;
		try {
			if(bs.selectzzin(mem_id, ttr_no)!=null){
				entity = new ResponseEntity<String>("zzimexist", HttpStatus.OK);
			}else{
				entity = new ResponseEntity<String>("zzimnull", HttpStatus.OK);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
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
