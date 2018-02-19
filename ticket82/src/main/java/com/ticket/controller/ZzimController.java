package com.ticket.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ticket.domain.BoardVO;
import com.ticket.domain.MemberVO;
import com.ticket.domain.ZzimVO;
import com.ticket.service.BoardService;
import com.ticket.service.ZzimService;

@Controller
@RequestMapping("/zzim")
public class ZzimController {

	@Autowired
	ZzimService zs;
	
	@Autowired
	BoardService bs;
	
	
	@RequestMapping(value="insert",method=RequestMethod.GET)
	public void insert(){}
	
	@RequestMapping(value="insert",method=RequestMethod.POST)
	public String insert(@ModelAttribute ZzimVO zzim, HttpSession session)throws Exception{
		MemberVO mem=(MemberVO) session.getAttribute("loginUser");
		String mem_id =mem.getMem_id();
		zzim.setMem_id(mem_id);
		String msg="";
		int count = zs.countZzim(zzim.getTtr_no(), mem_id);
		if(count == 0){
			zs.insertZzim(zzim);
		}else{
			
		}
		
		return "redirect:/zzim/theaterList";
	}
	
	@RequestMapping(value="/zzimList",method=RequestMethod.GET)
	public ModelAndView list(HttpSession session, ModelAndView mav)throws Exception{
		MemberVO mem=(MemberVO) session.getAttribute("loginUser");
		String mem_id =mem.getMem_id();
		Map<String, Object> map = new HashMap<String , Object>();
		List<ZzimVO> list = zs.listZzim(mem_id);
		System.out.println(list);
		for(ZzimVO a:list){
			String t=bs.readBoardByNo(a.getTtr_no()).getTtr_title();
			a.setTtr_title(t);
		}
		System.out.println(list);
		map.put("list",	list);
		map.put("count", list.size());
		mav.setViewName("/zzim/zzimList");
		mav.addObject("map", map);
		return mav;
	}
	
	@RequestMapping(value="/delete")
	public String delete(@RequestParam int zzim_no)throws Exception{
		zs.deleteZzzim(zzim_no);
		return "redirect:/zzim/zzimList";
	}
	
	
}
