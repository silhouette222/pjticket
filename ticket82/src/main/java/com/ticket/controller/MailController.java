package com.ticket.controller;

import java.io.PrintWriter;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ticket.domain.MemberVO;

@Controller
public class MailController {
	
	@Autowired
	JavaMailSender mailSender;// goole mailsender 빈
	
	//mailSending  코드
	@RequestMapping(value="/sendpass")
	public String mailSending(Model model, HttpServletResponse response){
		Map<String, Object> map = model.asMap();
		MemberVO member = (MemberVO)map.get("result");
		
		System.out.println(member.getMem_mail());
		System.out.println(member.getMem_id());
		String setfrom = "rkgkldklwelk@gmail.com";
		String tomail = member.getMem_mail(); //받는사람 이메일
		String title = member.getMem_name() + "님의 아이디와 비밀번호 입니다."; // 메일제목
		String content = member.getMem_name() + "님의 아이디는 "
				+ member.getMem_id()
				+ " 비밀번호는 "
				+ member.getMem_pw()
				+ " 입니다."; // 메일 내용

		try {
			MimeMessage message = mailSender.createMimeMessage();
	        MimeMessageHelper messageHelper  = new MimeMessageHelper(message, true, "UTF-8"); //두번째 인자 true여야 파일첨부 가능.
	     
	        messageHelper.setFrom(setfrom);  // 보내는사람 생략하거나 하면 정상작동을 안함
	        messageHelper.setTo(tomail);     // 받는사람 이메일
	        messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
	        messageHelper.setText(content);  // 메일 내용
	         
	        mailSender.send(message);
	          
	        response.setContentType("text/html; charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        out.println("<script>alert('성공적으로 메일을 발송했습니다.');history.go(-1);</script>");
	        out.flush();


			
		}catch(Exception e){
			System.out.println(e);
	    }
	       
	    return "/member/login";


	}
}
