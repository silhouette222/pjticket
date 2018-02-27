package com.ticket.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.model.PlacesSearchResult;
import com.ticket.domain.BoardVO;
import com.ticket.domain.MemberVO;
import com.ticket.domain.PayVO;
import com.ticket.domain.ResVO;
import com.ticket.domain.SearchCriteria;
import com.ticket.domain.Seatinfo;
import com.ticket.service.BoardService;
import com.ticket.service.ResService;
import com.ticket.service.UserService;

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
		String url="mboard/etc";
		cri.setTtr_cat("etc");
		List<BoardVO> boardList=bs.searchBoardList(cri);
		for(BoardVO b:boardList){
			if(b.getTtr_content().length()>30){
				b.setTtr_content(b.getTtr_content().substring(0, 30));
			}
		}
		model.addAttribute("list",boardList);
		return url;
	}
	
	@RequestMapping(value="/etc/etcdetail",method=RequestMethod.GET)
	public String etcdetail(@RequestParam("ttr_no")int ttr_no,Model model) throws Exception{
		String url="mboard/etc/etcdetail";
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
	
	@RequestMapping(value = "/gal", method = RequestMethod.GET)
	public String readgal(@ModelAttribute("cri")SearchCriteria cri,Model model,HttpSession session) throws Exception{
		String url="mboard/gal";
		cri.setTtr_cat("gal");
		List<BoardVO> boardList=bs.searchBoardList(cri);
		for(BoardVO b:boardList){
			if(b.getTtr_content().length()>30){
				b.setTtr_content(b.getTtr_content().substring(0, 30));
			}
		}
		model.addAttribute("list",boardList);
		return url;
	}
	
	@RequestMapping(value="/gal/galdetail",method=RequestMethod.GET)
	public String galdetail(@RequestParam("ttr_no")int ttr_no,Model model) throws Exception{
		String url="mboard/gal/galdetail";
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
	public String reserve(@RequestParam("rescheck")String[] rescheck,BoardVO board,HttpSession session,Model model) throws Exception{
		System.out.println("asd");
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
			System.out.println(rescheck);
			if(rescheck==null){
				rs.insertres(res);
			}else if(rescheck.getStatus()==1){
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
	
	@RequestMapping(value="resch",method=RequestMethod.GET)
	public void resa(@RequestParam("ttr_no")int ttr_no,Model model) throws Exception{
		List<ResVO> rl=rs.selectresbyttr_no(ttr_no);
		List<MemberVO> ml= new ArrayList<MemberVO>();
		List<Seatinfo> sl=bs.readseatbyttr_no(ttr_no);
		if(rl.size()!=0){
		for(ResVO res:rl){
			ml.add(us.selectmembyid(res.getMem_id()));
		}
		
		int seatall=0;
		int seatend=0;
		int man=0;
		int woman=0;
		int age10=0;
		int age20=0;
		int age30=0;
		int age40=0;
		int age50=0;
		int age60=0;
		
		Date d=new Date();
		int dy=d.getYear()+1900;
		for(Seatinfo seat:sl){
			seatall+=seat.getSeat_no();
		}
		seatend=rl.size();
		for(MemberVO m:ml){
			if(m.getMem_gender().equals("남자")){
				man++;
			}
			if(m.getMem_gender().equals("여자")){
				woman++;
			}
			if(dy-(m.getMem_birth().getYear()+1900)<20){
				age10++;
			}
			if(dy-(m.getMem_birth().getYear()+1900)>=20&&dy-(m.getMem_birth().getYear()+1900)<30){
				age20++;
			}
			if(dy-(m.getMem_birth().getYear()+1900)>=30&&dy-(m.getMem_birth().getYear()+1900)<40){
				age30++;
			}
			if(dy-(m.getMem_birth().getYear()+1900)>=40&&dy-(m.getMem_birth().getYear()+1900)<50){
				age40++;
			}
			if(dy-(m.getMem_birth().getYear()+1900)>=50&&dy-(m.getMem_birth().getYear()+1900)<60){
				age50++;
			}
			if(dy-(m.getMem_birth().getYear()+1900)>=60){
				age60++;
			}
		}
		int seat=seatend*100/seatall;
		model.addAttribute("seat",seat);
		int man1=man*100/(man+woman);
		model.addAttribute("man",man1);
		int woman1=woman*100/(man+woman);
		model.addAttribute("woman",woman1);
		int age101=age10*100/seatall;
		model.addAttribute("age10",age101);
		int age201=age20*100/seatall;
		model.addAttribute("age20",age201);
		int age301=age30*100/seatall;
		model.addAttribute("age30",age301);
		int age401=age40*100/seatall;
		model.addAttribute("age40",age401);
		int age501=age50*100/seatall;
		model.addAttribute("age50",age501);
		int age601=age60*100/seatall;
		model.addAttribute("age60",age601);
		}
	}
}
