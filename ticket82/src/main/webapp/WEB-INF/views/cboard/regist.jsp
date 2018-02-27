<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>기업회원가입</title>

    <!-- Bootstrap -->
    <link href="<%=request.getContextPath() %>/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- font awesome -->
    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/css/font-awesome.min.css" media="screen" title="no title" charset="utf-8">
    <!-- Custom style -->
    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/css/style.css" media="screen" title="no title" charset="utf-8">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
      <article class="container">
        <div class="col-md-12">
        <div class="page-header">
    	    <h1>기업회원가입</h1>
        </div>
        <form class="form-horizontal" method="post" name="formm">
        	
        	<div class="form-group">
            	<label class="col-sm-3 control-label" for="inputId">아이디</label>
             	<div class="col-sm-6">
                	<div class="input-group">
                  		<input type="text" class="form-control" id="id" name="com_id" placeholder="아이디" />
                  		<input type="hidden" name="reid">
                  		<span class="input-group-btn">
                    		<button class="btn btn-success" id="checkbtn">중복확인</button>
                  		</span>
                	</div>
                	<div id="checkMsg"></div>
             	</div>
        	</div>
        	<div class="form-group">
         		<label class="col-sm-3 control-label" for="inputPassword">비밀번호</label>
        		<div class="col-sm-6">
          			<input type="password" class="form-control" name="com_pw" id="inputPassword" placeholder="비밀번호">
        			<p class="help-block">숫자, 특수문자 포함 8자 이상</p>
        		</div>
        	</div>
          	<div class="form-group">
            	<label class="col-sm-3 control-label" for="inputPasswordCheck">비밀번호 확인</label>
             	<div class="col-sm-6">
              		<input type="password" class="form-control" name="pwdCheck" id="inputPasswordCheck" placeholder="비밀번호 확인">
                	<p class="help-block">비밀번호를 한번 더 입력해주세요.</p>
             	</div>
          	</div>
          	<div class="form-group">
            	<label class="col-sm-3 control-label" for="inputName">회사명</label>
          		<div class="col-sm-6">
            		<input class="form-control" id="inputName" name="com_name" type="text" placeholder="회사명">
          		</div>
        	</div>
        	<div class="form-group">
          		<label class="col-sm-3 control-label" for="inputCNumber">사업자 번호</label>
        		<div class="col-sm-6">
          			<input class="form-control" id="inputCNumber" name="com_no" type="number" placeholder="- 없이 입력해 주세요">
        		</div>
        	</div>
        	<div class="form-group">
            	<label class="col-sm-3 control-label" for="inputDName">대표자</label>
          		<div class="col-sm-6">
            		<input class="form-control" id="inputDName" name="com_dname" type="text" placeholder="대표자">
          		</div>
        	</div>
        	<div class="form-group">
          		<label class="col-sm-3 control-label" for="inputAddr">회사주소</label>
        		<div class="col-sm-6">
          			<input class="form-control" id="inputAddr" name="com_addr" type="text" placeholder="회사주소">
        		</div>
        	</div>
        	<div class="form-group">
              <label for="InputEmail">이메일</label>
              <div class="input-group">
                <input type="email" class="form-control" name="com_mail" id="email" placeholder="이메일 주소">
                <span class="input-group-btn">
                  <button class="btn btn-success" id="authbtn">인증하기</button>
                </span>
              </div>
            </div>
            <div class="form-group">
              <label for="InputEmail">인증번호</label>
              <div class="input-group">
                <input type="text" class="form-control" id="user_authNum" name="user_authNum">
              	<input type="hidden" name="authNum" id="authNum">
                <span class="input-group-btn">
                  <button class="btn btn-success" id="numbtn">번호확인</button>
                </span>
              </div>
                <div id="lab1"></div>
            </div>
        	<div class="form-group">
          		<label class="col-sm-3 control-label" for="inputNumber">회사 전화번호</label>
        		<div class="col-sm-6">
          			<input class="form-control" id="inputNumber" name="com_mobile" type="tel" placeholder="- 없이 입력해 주세요">
        		</div>
        	</div>
          	<div class="form-group">
            	<label class="col-sm-3 control-label" for="inputAgree">약관 동의</label>
            	<div class="col-sm-6" data-toggle="buttons">
              		<label class="btn btn-warning active">
                		<input id="agree" type="checkbox" autocomplete="off" checked>
                  		<span class="fa fa-check"></span>
              		</label>
              		<a href="#">이용약관</a> 에 동의 합니다.
            	</div>
          	</div>
        	<div class="form-group">
          		<div class="col-sm-12 text-center">
            	<button type="button" class="btn btn-primary" onclick="go_save()">기업 회원가입<i class="fa fa-check spaceLeft"></i></button>
            	<button type="button" class="btn btn-danger" type="submit" onclick="javascript:history.back(-1)">가입취소<i class="fa fa-times spaceLeft"></i></button>
          	</div>
        </div>
		</form>
        <hr>
    	</div>
	</article>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=request.getContextPath() %>/resources/bootstrap/js/bootstrap.min.js"></script>
    <!-- jQuery 2.1.4 -->
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
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
					document.formm.reid.value=document.formm.com_id.value;
				}else{
					$('#checkMsg').html('<p style="color:blue">사용불가능</p>');
				}
			}
		});
	});
	
	var chk = -1;
    
	$("#authbtn").click(function (event) {
		event.preventDefault();
		if (document.formm.com_mail.value==""){
			alert("이메일을 입력해 주세요");
			document.formm.com_mail.focus();
		}else{
		var data = {"email": $("#email").val()};
		var authNum = "";
		$.ajax({
    		url : "/emailAuth.do",
    		data : data,
    		success : function (data) {
        		alert("인증번호 전송완료.");
        		document.formm.authNum.value= data;
    		}
		});
		}
	});
	
	$("#numbtn").click(function(event){
		event.preventDefault();
		authNum = document.formm.authNum.value;
		chk = checkNum(authNum);
		if( chk > 0){
    		alert("인증완료");
    		chk = 1;
    		$("#lab1").html("<label>인증완료</label>");
		}else{
    		alert("인증실패");
    		$("#lab1").html("<label>인증실패</label>");
		}
	});
	// 이메일 인증 버튼 end
});
	
function go_save(){
	if(document.formm.com_id.value==""){
		alert("아이디를 입력해 주세요");
		document.formm.com_id.focus();
	}else if (document.formm.com_id.value != document.formm.reid.value){
		alert("중복확인을 클릭해 주세요");
		document.formm.com_id.focus();
	}else if (document.formm.com_pw.value==""){
		alert("패스워드를 입력해 주세요");
		document.formm.com_pw.focus();
	}else if ((document.formm.com_pw.value !=document.formm.pwdCheck.value)){
		alert("패스워드가 일치하지 않습니다.");
		document.formm.com_pwdCheck.focus();
		return;
	}else if (document.formm.com_name.value==""){
		alert("회사명을 입력해 주세요");
		document.formm.com_name.focus();
	}else if (document.formm.com_no.value==""){
		alert("사업자 번호를 입력해 주세요");
		document.formm.com_no.focus();
	}else if (document.formm.com_dname.value==""){
		alert("대표자명을 입력해 주세요");
		document.formm.com_dname.focus();
	}else if (document.formm.com_addr.value==""){
		alert("회사주소를 입력해 주세요");
		document.formm.com_addr.focus();
	}else if (document.formm.com_mail.value==""){
		alert("이메일을 입력해 주세요");
		document.formm.com_mail.focus();
	}else if ((document.formm.user_authNum.value !=document.formm.authNum.value)){
		alert("인증번호가 일치하지 않습니다");
		document.formm.user_authNum.focus();
	}else if (document.formm.com_mobile.value==""){
		alert("회사 전화번호를 입력해 주세요");
		document.formm.com_mobile.focus();
	}else{
		document.formm.submit();
		alert("가입되었습니다");
	}
}

</script>
<script type="text/javascript">
			function checkNum(authNum) {
	        	var chk = 0;
	        	var user_authNum = document.formm.user_authNum.value;
	        	// 인증번호 비교
	        	if (authNum == user_authNum) {
	            	chk = 1;
	            	$("#user_authNum").val(user_authNum);
	        	} else {
	            	chk = -1;
	        	}
	        	return chk;
	    	};
	</script>
    
  </body>
</html>
