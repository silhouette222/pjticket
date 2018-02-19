package com.ticket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ticket.domain.MusicalVO;
import com.ticket.domain.MusicalVO2;
import com.ticket.service.MusicalService;


@Controller
public class MusicalController {
	
	@Autowired
	private MusicalService musicalService;
	
	@RequestMapping(value="/mclistA",method=RequestMethod.GET)
	public String mclist(Model model)throws Exception{
		String url="/mc/mclistA";
		String ttr_cat="뮤지컬";
		List<MusicalVO> mcList=musicalService.readMcList();
		model.addAttribute("list",mcList);
		
		return url;
	}
	
	@RequestMapping("/readMcPage")
	public void readMc(int ttr_no,Model model)throws Exception{
		MusicalVO mc=musicalService.readMusicalBymcno(ttr_no);
		model.addAttribute(mc);
	}
	
	@RequestMapping(value="/removeMusical",method=RequestMethod.POST)
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
		/*String url="redirect:/mclistA";
		musicalService.createMc(mc);
		return url;*/
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
		MusicalVO musical=mc2.toMusicalVO();
		musicalService.updateMc(musical);
		
		return "redirect:/mclistA";
	}
	@RequestMapping("/getFiles/{ttr_no}")
	@ResponseBody
	public List<String> getFiles(@PathVariable("ttr_no") int ttr_no) throws Exception{
		return musicalService.getAttach(ttr_no);
	}
	/*@RequestMapping(value="/createPage",method=RequestMethod.GET)
	public String createPage()throws Exception{
		String url="mc/createMcForm";
		return url;
	}
	@RequestMapping(value="/createPage",method=RequestMethod.POST)
	public String createPage(MusicalVO mc)throws Exception{
		String url="redirect:/mc/listPage";
		musicalService.createMc(mc);
		return url;
	}*/
	/*@RequestMapping(value="/removePage",method=RequestMethod.POST)
	public String removeMusical(int ttr_no,RedirectAttributes rttr)throws Exception{
		String url="redirect:/mc/mclistA";
		musicalService.deleteMc(ttr_no);
		return url;
	}*/
	/*@RequestMapping(value="/modifyMusical",method=RequestMethod.GET)
	public String modifyMusical(int ttr_no,Model model)throws Exception{
		String url="/modifyPage";
		MusicalVO mc=musicalService.readMusicalBymcno(ttr_no);
		model.addAttribute(musicalService.readMusicalBymcno(ttr_no));
		
		return url;
	}
	
	@RequestMapping(value="/modifyMusical",method=RequestMethod.POST)
	public String modifyMusical(MusicalVO mc,Model model)throws Exception{
		
		String url="redirect:/mc/mclistA";
		musicalService.updateMc(mc);
		return url;
	}*/
}

























