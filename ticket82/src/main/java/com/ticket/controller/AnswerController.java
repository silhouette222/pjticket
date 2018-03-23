package com.ticket.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ticket.domain.AnswerVO;
import com.ticket.domain.Criteria;
import com.ticket.domain.PageMaker;
import com.ticket.service.AnswerService;

@RestController
@RequestMapping("/answer")
public class AnswerController {

	@Autowired
	private AnswerService as;
	
	@RequestMapping(value="",method=RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody AnswerVO answer){
		System.out.println(answer);
		ResponseEntity<String> entity = null;
		try{
			as.addAnswer(answer);
			entity=new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}catch(SQLException e){
			e.printStackTrace();
			entity=new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value="/all/{qna_no}",method=RequestMethod.GET)
	public ResponseEntity<List<AnswerVO>> list(@PathVariable("qna_no") int qna_no){
		
		ResponseEntity<List<AnswerVO>> entity = null;
		
		try{
			entity = new ResponseEntity<List<AnswerVO>>(as.getAnswerList(qna_no),HttpStatus.OK);
		}catch(SQLException e){
			e.printStackTrace();
			entity= new ResponseEntity<List<AnswerVO>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value="/{ano}", method= {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> update(@PathVariable("ano") int ano,@RequestBody AnswerVO answer){
		
		ResponseEntity<String> entity=null;
		
		try{
			answer.setAno(ano);
			as.modifyAnswer(answer);
			entity= new ResponseEntity<String>(HttpStatus.OK);
		}catch(SQLException e){
			e.printStackTrace();
			entity= new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value="/{ano}/{page}",method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listPage(@PathVariable("qna_no")int qna_no, @PathVariable("page") int page){
		
		ResponseEntity<Map<String, Object>> entity =null;
		
		Criteria cri = new Criteria();
		cri.setPage(page);
		cri.setPerPageNum(5);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		
		try{
			Map<String, Object>map = new HashMap<String, Object>();
			List<AnswerVO> answerList = as.getAnswerListPage(qna_no, cri);
			
			map.put("list", answerList);
			
			int answerCount=as.countAnswer(qna_no);
			pageMaker.setTotalCount(answerCount);
			
			map.put("pageMaker", pageMaker);
			
			entity= new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}catch(SQLException e){
			
			e.printStackTrace();
			entity= new ResponseEntity<Map<String,Object>>(HttpStatus.BAD_REQUEST);
			
		}
		return entity;
	}
	
	@RequestMapping(value="/{ano}",method=RequestMethod.DELETE)
	public ResponseEntity<String> remove(@PathVariable("ano")int ano){
		
		ResponseEntity<String> entity=null;
		
		try{
			as.removeAnswer(ano);
			entity=new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		}catch(SQLException e){
			e.printStackTrace();
			entity=new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
}
