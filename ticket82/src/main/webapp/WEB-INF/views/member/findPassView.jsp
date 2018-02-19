<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>ID/PW 찾기</title>

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
          <h1>ID/PW 찾기</h1>
        </div>
        <div class="col-md-6 col-md-offset-3">
		<form:form id="signupform" class="form-horizontal" role="form" commandName = "member" action="/member/findPass" method="post">
        	<div class="form-group">
            	<label for="email" class="col-md-2 control-label">이메일</label>
                <div class="col-md-10">
                	<input type="text" class="form-control" name="mem_mail" value="${memberVO.mem_mail}" placeholder="이메일을 작성해주세요">
                </div>
                                    
            	<div class="col-md-offset-2 col-md-10">
            		<div style="color:red ; margin-top:2px" >
                		<form:errors path="mem_mail"/>
                    	<form:errors/>
                	</div>
            	</div>                 
                                    
            </div>
                                    
            <div class="form-group" style="margin-left:82%">
            	<!-- Button -->
                                                  
                <div class="col-md-offset-10 col-md-9">
                	<button id="btn-signup" type="submit" class="btn btn-warning">확인</button>
                </div>
            </div>
                                
            <div class="form-group">
            	<div class="col-md-12 control">
                	<div style="border-top: 1px solid#888; padding-top:15px; font-size:85%" >
                     	가입하신 이메일로 아이디와 비밀번호를 전송해드리겠습니다.
                    </div>
                </div>
            </div> 
            
          </form:form>
        </div>
    </article>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=request.getContextPath() %>/resources/bootstrap/js/bootstrap.min.js"></script>

  </body>
</html>
