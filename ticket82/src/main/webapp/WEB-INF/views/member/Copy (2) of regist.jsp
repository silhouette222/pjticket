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
    <title>회원가입</title>

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
        <div class="page-header">
          <h1>회원가입</h1>
        </div>
        <div class="col-md-6 col-md-offset-3">
          <form role="form" method="post" name="formm">
          	<div class="form-group">
              <label for="Id">아이디</label>
              <div class="input-group">
                <input type="text" class="form-control" id="id" name="mem_id" placeholder="아이디">
				<input type="hidden" name="reid">
                <span class="input-group-btn">
                  <button class="btn btn-success" id="checkbtn">중복확인</button>
                </span>
              </div>
                <div id="checkMsg"></div>
            </div>
            <div class="form-group">
              <label for="InputPassword1">비밀번호</label>
              <input type="password" class="form-control" name="mem_pw" id="InputPassword1" placeholder="비밀번호">
            </div>
            <div class="form-group">
              <label for="InputPassword2">비밀번호 확인</label>
              <input type="password" class="form-control" name="pwdCheck" id="InputPassword2" placeholder="비밀번호 확인">
              <p class="help-block">비밀번호 확인을 위해 다시한번 입력 해 주세요</p>
            </div> 
            <div class="form-group">
              <label for="username">이름</label>
              <input type="text" class="form-control" name="mem_name" id="username" placeholder="이름을 입력해 주세요">
            </div>
            <div class="form-group">
              <label for="InputEmail">이메일 주소</label>
              <div class="input-group">
                <input type="email" class="form-control" name="mem_mail" id="email" placeholder="이메일 주소">
                <span class="input-group-btn">
                  <button class="btn btn-success" id="authbtn">인증하기</button>
                </span>
              </div>
            </div>
            <div class="form-group">
              <label for="InputEmail">인증번호</label>
              <input type="text" class="form-control" id="user_authNum" name="user_authNum"><div id="lab1"></div>
              <input type="hidden" name="authNum" id="authNum">
            </div>
            <div class="form-group">
              <label for="addr">휴대폰 번호</label>
              <input type="tel" class="form-control" id="mobile" name="mem_mobile" placeholder="- 없이 입력해 주세요">
            </div>
            <div class="form-group">
              <label for="gender">성별</label>
              <div>
              <input type="radio" name='mem_gender'  value="남" checked>남자
			  <input type="radio" name="mem_gender"  value="여">여자
			  </div>
            </div>
            <div class="form-group">
              <label for="birth">생년월일</label>
              <div class="input-group">
                <input type="date" class="form-control" name="mem_birth" >
                <span class="input-group-btn">
                </span>
              </div>
            </div>
            <div class="form-group text-center">
              <button type="button" onclick="go_save()" class="btn btn-info">회원가입<i class="fa fa-check spaceLeft"></i></button>
              <button type="button" class="btn btn-warning" onclick="javascript:history.back(-1)">가입취소<i class="fa fa-times spaceLeft"></i></button>
            </div>
          </form>
        </div>
    </article>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=request.getContextPath() %>/resources/bootstrap/js/bootstrap.min.js"></script>
	<!-- <!-- jQuery 2.1.4 -->
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script> -->
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
	
			var chk = -1;
    
    		$("#authbtn").click(function (event) {
    			event.preventDefault();
    			if (document.formm.mem_mail.value==""){
					alert("이메일을 입력해 주세요");
					document.formm.mem_mail.focus();
    			}else{
        		var data = {"email": $("#email").val()};
        		var authNum = "";
        		$.ajax({
            		url : "/emailAuth.do",
            		data : data,
            		success : function (data) {
                		authNum = data;
                		alert("인증번호 전송완료.");
                		chk = checkNum(authNum);
                		if( chk > 0){
                    		alert("인증완료");
                    		chk = 1;
                    		$("#lab1").html("<label>인증완료</label>");
                		}else{
                    		alert("인증실패");
                    		$("#lab1").html("<label>인증실패</label>");
                		}
            		}
        		});
    			}
    		});// 이메일 인증 버튼 end
		});
	
			function go_save(){
	
				if(document.formm.mem_id.value==""){
					alert("아이디를 입력해 주세요");
					document.formm.mem_id.focus();
				}else if (document.formm.mem_id.value != document.formm.reid.value){
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
				}else if (document.formm.mem_addr.value==""){
					alert("주소를 입력해 주세요");
					document.formm.mem_addr.focus();
				}else if (document.formm.mem_mobile.value==""){
					alert("휴대폰 번호를 입력해 주세요");
					document.formm.mem_mobile.focus();
				}else if (document.formm.mem_birth.value==""){
					alert("생년월일을 입력해 주세요");
					document.formm.mem_birth.focus();
				}else{
					document.formm.submit();
				}
		
				if( chk > 0  ){
        			return true;
    			}else{
        			alert("이메일 인증을 완료하여 주세요.");
        			return false;
    			}

			}
		
	</script>
	<script type="text/javascript">
			function checkNum(authNum) {
	        	var chk = 0;
	        	var user_authNum = prompt("인증번호를 입력하세요.");
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
