package com.ticket.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ticket.domain.BoardVO;
import com.ticket.domain.MemberVO;
import com.ticket.domain.PayVO;
import com.ticket.domain.ResVO;
import com.ticket.domain.Seatinfo;
import com.ticket.service.BoardService;
import com.ticket.service.ResService;
import com.ticket.service.UserService;

@Controller
public class PayController {
	
	@Autowired
	private BoardService bs;
	
	@Autowired
	private ResService rs;
	
	@Autowired
	private UserService us;
	
	@RequestMapping(value="/respay",method=RequestMethod.GET)
	public void reserveform(@RequestParam("rescheck")String[] rescheck,HttpSession session,BoardVO board,Model model) throws Exception{
		String[] seat_id=new String[rescheck.length];
		int[] res_nom=new int[rescheck.length];
		//�뀒�뒪�듃�슜
		session.setAttribute("loginUser",us.selectmembyid("asd"));
		MemberVO mem=(MemberVO) session.getAttribute("loginUser");
		List<ResVO> reslist=new ArrayList<ResVO>();
		List<Seatinfo> seatlist=new ArrayList<Seatinfo>();
		int pri=0;
		for(int i=0;i<rescheck.length;i++){
			seat_id[i]=rescheck[i].split("_")[0];
			res_nom[i]=Integer.parseInt(rescheck[i].split("_")[1]);
		}
		for(int i=0;i<rescheck.length;i++){
			ResVO res=new ResVO(null,null,res_nom[i],seat_id[i],"asd",board.getTtr_no(),null);
			reslist.add(res);
		}
		for(ResVO res:reslist){
			seatlist.add(bs.readseatbyseat_id(res.getSeat_id()));
		}
		for(Seatinfo seat:seatlist){
			pri+=seat.getSeat_pri();
		}
		PayVO pay=new PayVO(board.getTtr_title(),pri,mem.getMem_mail(),mem.getMem_name(),mem.getMem_mobile(),mem.getMem_addr(),null);
		model.addAttribute("reslist",reslist);
		model.addAttribute("seatlist",seatlist);
		model.addAttribute("pay",pay);
	}
	
	@RequestMapping(value="/respay",method=RequestMethod.POST)
	public void reservesubmit(@RequestParam("rescheck")String[] rescheck,BoardVO board,Model model) throws Exception{
		String[] seat_id=new String[rescheck.length];
		int[] res_nom=new int[rescheck.length];
		List<ResVO> reslist=null;
		for(int i=0;i<rescheck.length;i++){
			seat_id[i]=rescheck[i].split("_")[0];
			res_nom[i]=Integer.parseInt(rescheck[i].split("_")[1]);
		}
		for(int i=0;i<rescheck.length;i++){
			ResVO res=new ResVO(null,null,res_nom[i],seat_id[i],"asd",board.getTtr_no(),null);
			reslist.add(res);
		}
	}
}
