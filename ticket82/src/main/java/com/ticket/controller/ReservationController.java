package com.ticket.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ticket.domain.Criteria;
import com.ticket.domain.MemberVO;
import com.ticket.domain.MusicalVO;
import com.ticket.domain.PayVO;
import com.ticket.domain.ReservationVO;
import com.ticket.domain.Seatinfo;
import com.ticket.service.MusicalService;
import com.ticket.service.ReservationService;

@Controller
@RequestMapping("/res")
public class ReservationController {

	@Autowired
	private ReservationService rs;
	
	@Autowired
	private MusicalService ms;
	
	@InitBinder public void initBinder(WebDataBinder binder) 
	{ 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");binder.registerCustomEditor(Date.class,"seat_date", new CustomDateEditor(dateFormat, true));
		SimpleDateFormat time = new SimpleDateFormat("hh:mm"); binder.registerCustomEditor(Date.class,"seat_time", new CustomDateEditor(time, true));
	}
	
	@RequestMapping(value="/resList/{mem_id}",method=RequestMethod.GET)
	public String reslist(@PathVariable String mem_id, Model model)throws Exception{
		String url="/res/resList";
		
		List<ReservationVO> resList=rs.selectresbymem_id(mem_id);
		model.addAttribute("list",resList);
		return url;
	}
	
	@RequestMapping(value="/resList",method=RequestMethod.POST)
	public String reslist(MusicalVO mv,ReservationVO rv,Model model)throws Exception{
		String url="/res/resList";
		
		List<MusicalVO> mcList=new ArrayList<MusicalVO>();
		List<ReservationVO> resList=new ArrayList<ReservationVO>();
		List<Seatinfo> seatlist=new ArrayList<Seatinfo>();
		
		
		
		System.out.println(mv);
		System.out.println(rv);
		
		model.addAttribute("list",mcList);
		return url;
		
	}
	
	
	@RequestMapping(value="/removeRes",method=RequestMethod.POST)
	public String removeRes(int ttr_no)throws Exception{
		String url="redirect:/res/resList";
		rs.deletettrno(ttr_no);
		return url;
	}
	@RequestMapping(value="/createRes",method=RequestMethod.GET)
	public String createRes(@RequestParam("ttr_no") int ttr_no,Model model)throws Exception{
		String url="res/createRes";
		
		MusicalVO mv=ms.readMusicalBymcno(ttr_no);
		String[] seat_ids=mv.getSeat_id();
		int[] seat_cnt=new int[seat_ids.length];
		for(int i=0;i<seat_ids.length;i++){
			seat_cnt[i]=rs.countresbyseat_id(seat_ids[i]);
		}
		System.out.println(mv);
		model.addAttribute(mv);
		model.addAttribute("seat_cnt",seat_cnt);
		return url;
	}
	@RequestMapping(value="/createRes",method=RequestMethod.POST)
	public String createRes(@RequestParam("rescheck")String[] rescheck,HttpSession session,
			MusicalVO mv,Model model)throws Exception{
		String url="res/respay";
		String[] seat_id=new String[rescheck.length];
		int[] res_nom=new int[rescheck.length];
		
		MemberVO mem=(MemberVO) session.getAttribute("loginUser");
		
		List<ReservationVO> reslist=new ArrayList<ReservationVO>();
		List<Seatinfo> seatlist=new ArrayList<Seatinfo>();
		int pri=0;
		for(int i=0;i<rescheck.length;i++){
			
			seat_id[i]=rescheck[i].split("_")[0];
			res_nom[i]=Integer.parseInt(rescheck[i].split("_")[1]);
		}
		for(int i=0;i<rescheck.length;i++){
			ReservationVO res=new ReservationVO(null,null,res_nom[i],seat_id[i],"tj",mv.getTtr_no(),null);
			reslist.add(res);
		}
		for(ReservationVO res:reslist){
			seatlist.add(ms.readseatbyseat_id(res.getSeat_id()));
		}
		for(Seatinfo seat:seatlist){
			pri+=seat.getSeat_pri();
		}
		PayVO pay=new PayVO(mv.getTtr_title(),pri,mem.getMem_mail(),mem.getMem_name(),mem.getMem_mobile(),mem.getMem_addr(),null);
		model.addAttribute("reslist",reslist);
		model.addAttribute("seatlist",seatlist);
		model.addAttribute("pri",pri);
		model.addAttribute("pay",pay);
		return url;
	}
	@RequestMapping(value="/respay",method=RequestMethod.POST)
	@ResponseBody
	public String respay(@RequestParam("reslist")String param,@RequestParam("imp_uid")String imp_uid) throws Exception{
		ResponseEntity<String> entity=null;
		entity=new ResponseEntity<String>("everythings_fine", HttpStatus.OK);
		List<Map<String, Object>> maplist=new ArrayList<Map<String,Object>>();
		maplist=JSONArray.fromObject(param);
		for(Map<String, Object> map:maplist){
			ReservationVO res=new ReservationVO(null,null,(int)map.get("res_nom"),(String)map.get("seat_id"),(String)map.get("mem_id"),(int)map.get("ttr_no"),(String)map.get("imp_uid"));
			rs.insertres(res);
		}
		return "everythings_fine";
	}
	@RequestMapping(value="/createRes/{seat_id}")
	@ResponseBody
	public int[] reservedseat(@PathVariable("seat_id") String seat_id) throws Exception{
		List<ReservationVO> reslist = rs.selectresbyseat_id(seat_id);
		int[] res_end=new int[reslist.size()];
		for(int i=0;i<reslist.size();i++){
			res_end[i]=reslist.get(i).getRes_nom();
		}
		return res_end;
	}
	/*@RequestMapping(value="/listCri",method=RequestMethod.GET)
	public String listCri(Criteria cri,Model model) throws Exception{
		String url="res/reslist";
		List<ReservationVO> resList=rs.readResListCriteria(cri);
		model.addAttribute("list",resList);
		return url;
	}
	@RequestMapping(value="/listPage",method=RequestMethod.GET)
	public String listPage(@ModelAttribute("cri")Criteria cri,
			Model model)throws Exception{
		String url="res/listPage";
		List<ReservationVO> resList = rs.readResListCriteria(cri);
		
		PageMaker pageMaker=new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(rs.readResList().size());
		
		model.addAttribute("list",resList);
		model.addAttribute("pageMaker",pageMaker);
		
		return url;
	}
	@RequestMapping(value="/readRes",method=RequestMethod.GET)
	public String readRes(@ModelAttribute("cri")Criteria cri,
						   @RequestParam("ttr_no")int ttr_no,Model model)
						   		throws Exception{
		String url="/res/readRes";
		
		ReservationVO res=rs.readResByno(ttr_no);
		model.addAttribute(res);
		
		return url;
	}*/
	@RequestMapping(value="/removePage",method=RequestMethod.POST)
	public String removeRes(Criteria cri,int ttr_no,
							  RedirectAttributes rttr)throws Exception{
		String url="redirect:/res/resList";
		rs.deletettrno(ttr_no);
		rttr.addFlashAttribute("cri",cri);
		return url;
	}
	
}





























