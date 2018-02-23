<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>마이페이지</title>

    <!-- Bootstrap core CSS -->
    <!-- <link href="/resources/main/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet"> -->

    <!-- Custom styles for this template -->
    <link href="/resources/main/css/modern-business.css" rel="stylesheet">

  </head>

  <body>

    

    <!-- Page Content -->
    <div class="container">

      <!-- Page Heading/Breadcrumbs -->
      <h1 class="mt-4 mb-3">${loginUser.mem_name}님의
        <small>마이페이지</small>
      </h1>

      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="/member/index">Home</a>
      </ol>

      <!-- Content Row -->
      <div class="row">
        <!-- Sidebar Column -->
        <div class="col-lg-3 mb-4">
          <div class="list-group">
            <a href="index.html" class="list-group-item">예약확인</a>
            <a href="about.html" class="list-group-item active">회원정보</a>
            <a href="/zzim/zzimList" class="list-group-item">찜목록</a>
          </div>
        </div>
        <!-- Content Column -->
        
        	<!-- 내정보 -->
    		<div class="row">
        		<div class="col-sm-12">
            		<div class="col-sm-2"></div>
                	<div class="col-sm-9">
                    <h2 class="text-center">회원 정보 보기</h2>
    				<form action="/member/my/infoMody" method="post">
                    <table class="table table-striped">
                      	<tr>
                        	<td>아이디</td>
                        	<td>
                        		<input type="text" value="${memberVO.mem_id}" class="form-control" name="mem_id" id="mem_id">
                        	</td>
                      	</tr>
                       
                      	<tr>
                        	<td>이름</td>
							<td>
                        		<input type="text" value="${memberVO.mem_name}" class="form-control" name="mem_name" id="mem_name">
                        	</td>
                      	</tr>
                      	
                      	<tr>
                        	<td>비밀번호</td>
							<td>
                        		<input type="password" value="${memberVO.mem_pw}" class="form-control" name="mem_pw" id="pw">
                        	</td>
                      	</tr>
                       
                      	<tr>
                        	<td>휴대폰번호</td>
                        	<td>
                        		<input type="tel" value="${memberVO.mem_mobile}" class="form-control" name="mem_mobile" id="mem_mobile">
                        	</td>
                      	</tr>
                       
                      	<tr>
                        	<td>이메일</td>
                        	<td>
                        		<input type="email" value="${memberVO.mem_mail}" class="form-control" name="mem_mail" id="mem_mail">
                        	</td>
                      	</tr>
                       
                      	<tr>
                        	<td>주소</td>
                        	<td>
                        		<input type="text" value="${memberVO.mem_addr}" class="form-control" name="mem_addr" id="mem_addr">
                        	</td>
                      	</tr>
                       
                     	<tr>
                        	<td>생년월일</td>
                        	<td>
                        		<input type="date" value="${memberVO.mem_birth}" class="form-control" name="mem_birth" id="mem_birth">
                        	</td>
                      	</tr>
                       
                      	<tr>
                        	<td>성별</td>
                        	<td>
                        		<input type="radio" name='mem_gender'  value="남자" checked>남자
			  					<input type="radio" name="mem_gender"  value="여자">여자
                        	</td>
                      	</tr>
                       
                    	<tr>
                         	<td class="text-center" colspan="2">
								<button class="btn btn-success">회원수정</button>
								<button class="btn btn-danger">회원탈퇴</button>
                         	</td>    
                    	</tr> 
                    </table>
                </form>
                </div>
        	</div> <!-- col-sm-12 -->
    	</div><!-- row -->
	</div> <!-- container end-->
        
      <!-- /.row -->

    </div>
    <!-- /.container -->


    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=request.getContextPath() %>/resources/bootstrap/js/bootstrap.min.js"></script>
	<script>
	$(document).ready(function() {

		var formObj = $("form[role='form']");

		console.log(formObj);

		/* $(".btn-success").on("click", function(){
			formObj.attr("action", "/my/infoMody");
			formObj.submit();
		});
 */
		$(".btn-danger").on("click", function(){
			formObj.attr("action", "/my/delinfo");
			formObj.submit();
		});

	});
	</script>
  </body>

</html>
