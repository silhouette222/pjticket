<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
<form method="post" name="formm">
	<div>
		<div class="form-group">
			<label>아이디</label> 
			<input type="text" name='mem_id' id="id" placeholder="아이디">
			<input type="hidden" name="reid">
			<div id="checkMsg"></div>
			<input type="button" id="checkbtn" value="중복확인">
		</div>
		<div class="form-group">
			<label>패스워드</label>
			<input type="password" name="mem_pw" rows="3" placeholder="패스워드"  />
			<label>패스워드 확인</label>
			<input type="password" name="pwdCheck" placeholder="패스워드 확인"></br>
		</div>
		
		<div class="form-group">
			<label>이름</label> 
			<input type="text" name="mem_name" placeholder="이름">
		</div>
		
		<div class="form-group">
			<label>이메일</label> 
			<input type="email" name="mem_mail"  placeholder="이메일">
		</div>
		<div class="form-group">
			<label>주소</label> 
			<input type="text" name='mem_addr' placeholder="주소">
		</div>
		<div class="form-group">
			<label>생년월일</label> 
			<input type="date" name='mem_birth'>
		</div>		
		<div class="form-group">
			<label>성별</label> 
			<input type="radio" name='mem_gender'  value="male" checked>남자
			<input type="radio" name="mem_gender"  value="female">여자
		</div>
		<div class="form-group">
			<label>휴대폰번호</label> 
			<input type="tel" name='mem_mobile' placeholder="-없이 입력하세요">
		</div>
		
	</div>

	<div class="box-footer">
		<input type="button" onclick="go_save()" value="회원가입">
	</div>
</form>
<!-- jQuery 2.1.4 -->
<script  src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
$(document).ready(function(){
	$('#checkbtn').on('click',function(event){
		event.preventDefault();
		$.ajax({
			type:'post',
			url:'idcheck',
			data:{"id":$('#id').val()}, 
			success:function(data){
				if($.trim(data)==0){
					$('#checkMsg').html('<p style="color:blue">사용가능</p>');
					document.formm.reid.value=document.formm.mem_id.value;
				}else{
					$('#checkMsg').html('<p style="color:blue">사용불가능</p>');
				}
			}
		});
	});
});
	
function go_save(){
	if(document.formm.mem_id.value==""){
		alert("아이디를 입력해 주세요");
		document.formm.mem_id.focus();
	}else if (document.formm.id.value != document.formm.reid.value){
		alert("중복확인을 클릭해 주세요");
		document.formm.mem_id.focus();
	}else if (document.formm.mem_pw.value==""){
		alert("패스워드를 입력해 주세요");
		document.formm.mem_pw.focus();
	}else if ((document.formm.mem_pw.value !=document.formm.pwdCheck.value)){
		alert("패스워드가 일치하지 않습니다.");
		document.formm.mem_pwd.focus();
		return;
	}else if (document.formm.mem_name.value==""){
		alert("이름을 입력해 주세요");
		document.formm.mem_name.focus();
	}else if (document.formm.mem_mail.value==""){
		alert("이메일을 입력해 주세요");
		document.formm.mem_mail.focus();
	}else if (document.formm.mem_birth.value==""){
		alert("생년월일을 입력해 주세요");
		document.formm.mem_birth.focus();
	}else if (document.formm.mem_mobile.value==""){
		alert("휴대폰 번호를 입력해 주세요");
		document.formm.mem_mobile.focus();
	}else{
		document.formm.submit();
	}
}

</script>
</body>
</html>