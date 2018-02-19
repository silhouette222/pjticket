package com.ticket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ticket.domain.BoardVO;
import com.ticket.domain.EventVO;
import com.ticket.service.EventService;

@Controller
@RequestMapping("/event")
public class EventController {
	
	@Autowired
	private EventService es;
	
	@RequestMapping(value="/eventList",method=RequestMethod.GET)
	public String readEvent(Model model)throws Exception{
		List<EventVO> eventList=es.readEventList();
		model.addAttribute("list",eventList);
		return "/event/eventList";
	}
	
	@RequestMapping(value="/regist",method=RequestMethod.GET)
	public void eventWriteForm()throws Exception{}
	
	@RequestMapping(value="/regist",method=RequestMethod.POST)
	public String eventWrite(EventVO event,Model model)throws Exception{
		es.insertEvent(event);
		model.addAttribute(event);
		
		return "redirect:/event/eventList";
	}
	
	@RequestMapping(value="/read",method=RequestMethod.GET)
	public String eventRead(@RequestParam("et_no")int et_no,Model model) throws Exception{
		String url="event/read";
		EventVO event=es.readEventByNo(et_no);
		model.addAttribute(event);
		return url;
	}
	
	
	

}
