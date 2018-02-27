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
            <a href="reslist" class="list-group-item active">예약확인</a>
            <a href="info" class="list-group-item">회원정보</a>
            <a href="/zzim/zzimList" class="list-group-item">찜목록</a>
          </div>
        </div>
        <!-- Content Column -->
        
        <!-- 예약정보 -->
    	<div class="box">
			<div class="box-header with-border">
				<h3 class="box-title">예약정보</h3>
			</div>
			<div class="box-body">
				<table class="table table-bordered">
					<tr>
						<th>제목</th>
						<th>좌석</th>
						<th>날짜</th>
						<th>시간</th>
						<th>장소</th>
						<th>예약상태</th>
						<th>예약취소</th>
					</tr>

					<c:forEach items="${reslist}" var="reslist">

						<tr>
							<td>${reslist.title }</td>
							<td>${reslist.seat}</td>
							<td>
								<fmt:formatDate pattern="yyyy-MM-dd"
								value="${reslist.date}" />
							</td>
							<td>
								<fmt:formatDate pattern="HH:mm"
								value="${reslist.time}" />
							</td>
							<td>${reslist.place}</td>
							<td>
								<c:choose>
									<c:when test="${reslist.status==1}">예약완료</c:when>
									<c:otherwise>예약취소</c:otherwise>
								</c:choose>
							</td>									
							<td>
								<button class="resdel" pk="${reslist.res_id }" uid="${reslist.imp_uid }">예약취소</button>
							</td>
						</tr>

					</c:forEach>

				</table>
			</div>
        	<!-- col-sm-12 -->
    	</div>
    	<!-- row -->
	</div>
	<!-- container end-->
        
      <!-- /.row -->

    </div>
    <!-- /.container -->


    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=request.getContextPath() %>/resources/bootstrap/js/bootstrap.min.js"></script>
	
	<script>
	$('.resdel').on('click',function(){
		var pk=$(this).attr('pk')
		var uid=$(this).attr('uid')
		$.ajax({
			url : "https://cors-anywhere.herokuapp.com/api.iamport.kr/users/getToken",
			type : "post",
			dataType : 'json',
			data: {
	    		imp_key:"imp_apikey",
	    		imp_secret:"ekKoeW8RyKuT0zgaZsUtXXTLQ4AhPFW3ZGseDA6bkA5lamv9OqDMnxyeB9wqOsuO9W3Mx9YSJ4dTqJ3f"
    		},
    	complete:function(result){
    		var token=result.responseJSON.response.access_token
    		$.ajax({
				url : "https://cors-anywhere.herokuapp.com/api.iamport.kr/payments/cancel?_token="+token,
				type : "post",
				dataType : 'json',
				data: {
		    		imp_uid : uid
	    		}
			});
    		$.getJSON("/aboard/resdel/"+pk)
    		alert("예약이 취소되었습니다");
    		location.href="/member/my/reslist";
    	}
		})
	})
</script>
  </body>

</html>
