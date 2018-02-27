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
	<style type="text/css">
    	#mtable{
    		width: 75%;
    	}
    </style>
  </head>

  <body>

    <!-- Page Content -->
    <div class="container">

      <!-- Page Heading/Breadcrumbs -->
      <h1 class="mt-4 mb-3">${loginUser.com_id}님의
        <small>마이페이지</small>
      </h1>

      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="/cboard/index">Home</a>
      </ol>

      <!-- Content Row -->
      <div class="row">
        <!-- Sidebar Column -->
        <div class="col-lg-3 mb-4">
          <div class="list-group">
            <a href="reslist" class="list-group-item">예약확인</a>
            <a href="info" class="list-group-item active">회원정보</a>
            <a href="#" class="list-group-item">게시판목록</a>
          </div>
        </div>
        <!-- Content Column -->
        
        <!-- 내정보 -->
    		<div class="row" id="mtable">
        		<div class="col-sm-12">
            		<div class="col-sm-2"></div>
                	<div class="col-sm-9">
                    <h2 class="text-center">회원 정보 보기</h2>
                    <table class="table table-striped">
                      	<tr>
                        	<td>아이디</td>
                        	<td>${companyVO.com_id}</td>
                        	<input type="hidden" value="${companyVO.com_id}" name="com_id" id="com_id">
                      	</tr>
                       
                      	<tr>
                        	<td>기업명</td>
                        	<td>${companyVO.com_name}</td>
                      	</tr>
                       
                      	<tr>
                        	<td>사업자번호</td>
                        	<td>${companyVO.com_no}</td>
                      	</tr>
                       
                      	<tr>
                        	<td>대표자</td>
                        	<td>${companyVO.com_dname}</td>
                      	</tr>
                       
                      	<tr>
                        	<td>기업주소</td>
                        	<td>${companyVO.com_addr}</td>
                      	</tr>
                       
                     	<tr>
                        	<td>기업전화번호</td>
                        	<td>${companyVO.com_mobile}</td>
                      	</tr>
                       
                      	<tr>
                        	<td>기업메일</td>
                        	<td>${companyVO.com_mail}</td>
                      	</tr>
                      	
                      	<tr>
                        	<td>가입일</td>
                        	<td>${companyVO.com_date}</td>
                      	</tr>
                       
                    	<tr>
                         	<td class="text-center" colspan="2">
								<button class="btn btn-success">회원수정</button>
								<button class="btn btn-danger">회원탈퇴</button>
                         	</td>    
                    	</tr> 
                    </table>
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

		$(".btn-success").on("click", function(){
			self.location = "infoMody";
		});

		$(".btn-danger").on("click", function(event) {
			self.location = "delinfo";
			alert("탈퇴되었습니다");
		});

	});
</script>
  </body>

</html>
