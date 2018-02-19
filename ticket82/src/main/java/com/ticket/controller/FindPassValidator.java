package com.ticket.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ticket.domain.MemberVO;

public class FindPassValidator implements Validator {

	private Pattern pattern;
    private Matcher matcher;


	String regexp = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$"; //이메일 정규 표현식
    public FindPassValidator() {
    	pattern = pattern.compile(regexp);
    }


	@Override
	public boolean supports(Class<?> clazz) {
		return MemberVO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		MemberVO member = (MemberVO)target;
		//이메일이 올바른 형식인지 검사
		matcher = pattern.matcher(member.getMem_mail());
		if(member.getMem_mail() == null || member.getMem_mail().trim().isEmpty()){
			errors.rejectValue("mem_mail", "EmailRequired");
			System.out.println("이메일을 입력해주세요.");
		}else if(!matcher.matches()){ //사용자가 입력한 이메일이 정규표현식에 매치 되지않는다면
			errors.rejectValue("mem_mail", "bad");
			System.out.println("올바른 이메일 형식이 아닙니다.");
		}
	}

}
