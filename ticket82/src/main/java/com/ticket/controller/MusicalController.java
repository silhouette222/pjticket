package com.ticket.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ticket.domain.MusicalVO;
import com.ticket.domain.MusicalVO2;
import com.ticket.domain.SearchCriteria;
import com.ticket.service.MusicalService;
import com.ticket.service.ReservationService;


@Controller
public class MusicalController {
	
	@Autowired
	private MusicalService musicalService;
	
	@Autowired
	private ReservationService rs;
	
	
	@InitBinder public void initBinder(WebDataBinder binder) 
	{ 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");binder.registerCustomEditor(Date.class,"seat_date", new CustomDateEditor(dateFormat, true));
		SimpleDateFormat time = new SimpleDateFormat("hh:mm"); binder.registerCustomEditor(Date.class,"seat_time", new CustomDateEditor(time, true));
	}
	
	@RequestMapping(value="/mclistA",method=RequestMethod.GET)
	public String mclist(@ModelAttribute("cri")SearchCriteria cri,Model model)throws Exception{
		String url="/mc/mclistA";
		cri.setTtr_cat("뮤지컬");
		List<MusicalVO> mcList=musicalService.readMcList();
		List<MusicalVO> mList=musicalService.searchMusicalList(cri);
		model.addAttribute("list",mcList);
		model.addAttribute("mlist",mList);
		return url;
	}
	
		
	@RequestMapping(value="/removeMusical",method=RequestMethod.GET)
	public String removeMusical(int ttr_no)throws Exception{
		String url="redirect:/mclistA";
		musicalService.deleteMc(ttr_no);
		return url;
	}
	
	@RequestMapping(value="/createMusical",method=RequestMethod.GET)
	public String createMusical()throws Exception{
		String url="/mc/createMcForm";
		return url;
	}
	
	@RequestMapping(value="/createMusical",method=RequestMethod.POST)
	public String createMusical(MusicalVO2 mc2)throws Exception{
		MusicalVO mc=mc2.toMusicalVO();
		musicalService.createMc(mc);
		return "redirect:/mclistA";
	}
	@RequestMapping(value="/readMcPage",method=RequestMethod.GET)
	public String readPage(@RequestParam("ttr_no") int ttr_no,Model model)throws Exception{
		
		String url="/mc/readMcPage";
		
		MusicalVO mc=musicalService.readMusicalBymcno(ttr_no);
		model.addAttribute(mc);
		
		return url;
	}
	@RequestMapping(value="/modifyPageForm", method=RequestMethod.GET)
	public String modifyPageForm(@RequestParam("ttr_no") int ttr_no, Model model)throws Exception{
		
		String url="/mc/modifyPage";
		MusicalVO mc=musicalService.readMusicalBymcno(ttr_no);
		model.addAttribute(mc);
		
		return url;
	}
	
	@RequestMapping(value="/modifyPage",method=RequestMethod.POST)
	public String modifyPage(MusicalVO2 mc2,RedirectAttributes rttr)throws Exception{
		System.out.println(mc2);
		MusicalVO musical=mc2.toMusicalVO();
		musicalService.updateMc(musical);
		
		return "redirect:/mclistA";
	}
	@RequestMapping("/getFiles/{ttr_no}")
	@ResponseBody
	public List<String> getFiles(@PathVariable("ttr_no") int ttr_no) throws Exception{
		return musicalService.getAttach(ttr_no);
	
	}
	@RequestMapping(value="/addzzim/{ttr_no}")/*(value="/addzzim/{mem_id}/{ttr_no}")*/
	public ResponseEntity<String> addzzim(@PathVariable("mem_id")String mem_id,@PathVariable("ttr_no")int ttr_no,HttpSession session){
		ResponseEntity<String> entity = null;
		
		try {
			musicalService.insertzzim(mem_id, ttr_no);
			entity = new ResponseEntity<String>("addzzim", HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}
	
	@RequestMapping(value="/delzzim/{ttr_no}")/*(value="/delzzim/{mem_id}/{ttr_no}")*/
	public ResponseEntity<String> delzzim(@PathVariable("mem_id")String mem_id,@PathVariable("ttr_no")int ttr_no){
		ResponseEntity<String> entity = null;
		try {
			musicalService.deletezzim(mem_id, ttr_no);
			entity = new ResponseEntity<String>("delzzim", HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}
	@RequestMapping(value="/selzzim/{ttr_no}")/*(value="/selzzim/{mem_id}/{ttr_no}")*/
	public ResponseEntity<String> selzzim(@PathVariable("mem_id")String mem_id,@PathVariable("ttr_no")int ttr_no){
		ResponseEntity<String> entity = null;
		try {
			if(musicalService.selectzzin(mem_id, ttr_no)!=null){
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
			double avg=musicalService.scoreavg(ttr_no);
			double b = Math.round(avg*100d)/100d;
			entity = new ResponseEntity<Double>(b, HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}
	@RequestMapping(value="/cclistA",method=RequestMethod.GET)
	public String cclist(@ModelAttribute("cri")SearchCriteria cri,Model model)throws Exception{
		String url="/cc/cclistA";
		cri.setTtr_cat("콘서트");
		List<MusicalVO> ccList=musicalService.readMcList();
		List<MusicalVO> cList=musicalService.searchMusicalList(cri);
		model.addAttribute("list",ccList);
		model.addAttribute("clist",cList);
		return url;
	}
	@RequestMapping(value="/removeConcert",method=RequestMethod.GET)
	public String removeConcert(int ttr_no)throws Exception{
		String url="redirect:/cclistA";
		musicalService.deleteMc(ttr_no);
		return url;
	}
	@RequestMapping(value="/createConcert",method=RequestMethod.GET)
	public String createConcert()throws Exception{
		String url="/cc/createCcForm";
		return url;
	}
	
	@RequestMapping(value="/createConcert",method=RequestMethod.POST)
	public String createConcert(MusicalVO2 mc2)throws Exception{
		MusicalVO mc=mc2.toMusicalVO();
		musicalService.createMc(mc);
		return "redirect:/cclistA";
		
	}
	@RequestMapping(value="/readCcPage",method=RequestMethod.GET)
	public String readCcPage(@RequestParam("ttr_no") int ttr_no,Model model)throws Exception{
		
		String url="/cc/readCcPage";
		
		MusicalVO mc=musicalService.readMusicalBymcno(ttr_no);
		model.addAttribute(mc);
		
		return url;
	}
	@RequestMapping(value="/modifyCcPageForm", method=RequestMethod.GET)
	public String modifyCcPageForm(@RequestParam("ttr_no") int ttr_no, Model model)throws Exception{
		
		String url="/cc/modifyCcPage";
		MusicalVO mc=musicalService.readMusicalBymcno(ttr_no);
		model.addAttribute(mc);
		
		return url;
	}
	
	@RequestMapping(value="/modifyCcPage",method=RequestMethod.POST)
	public String modifyCcPage(MusicalVO2 mc2,RedirectAttributes rttr)throws Exception{
		System.out.println(mc2);
		MusicalVO musical=mc2.toMusicalVO();
		musicalService.updateMc(musical);
		
		return "redirect:/cclistA";
	}
}

























