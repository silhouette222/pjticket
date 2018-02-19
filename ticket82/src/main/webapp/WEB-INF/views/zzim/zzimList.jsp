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
      <h1 class="mt-4 mb-3">Sidebar Page
        <small>Subheading</small>
      </h1>

      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="index.html">Home</a>
        </li>
        <li class="breadcrumb-item active">About</li>
      </ol>

      <!-- Content Row -->
      <div class="row">
        <!-- Sidebar Column -->
        <div class="col-lg-3 mb-4">
          <div class="list-group">
            <a href="index.html" class="list-group-item">예약확인</a>
            <a href="about.html" class="list-group-item">회원정보</a>
            <a href="contact.html" class="list-group-item">최근목록</a>
            <a href="/zzim/zzimList" class="list-group-item active">찜목록</a>
          </div>
        </div>
        <!-- Content Column -->
        
        <!-- 찜목록 -->
        <div class="col-lg-9 mb-4">
        <h2>Section Heading</h2>
          
        <h2>찜 확인</h2>
    	<c:choose>
        	<c:when test="${map.count == 0}">
            	찜목록이 비어있습니다.
        	</c:when>
        	<c:otherwise>
        		<%-- <form name="form1" id="form1" method="post" action="${path}/member/zzim/delete"> --%>
        		<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">LIST PAGING</h3>
				</div>
				<div class="box-body">
					<table class="table table-bordered">
						<tr>
							<th style="width: 10px">BNO</th>
							<th>공연명</th>
							<th>등록일</th>
							<th>취소</th>
						</tr>
					</table>
				</div>
            	<table border="1">
                	<tr>
                    	<th>상품명</th>
                    	<th>등록일</th>
                    	<th>취소</th>
                	</tr>
                	<!-- <script>console.log(${map.list })</script> -->
                	<c:forEach var="row" items="${map.list}" varStatus="i">
                	<tr>
                    	<td>
                        	<a href="">${row.ttr_title}</a>
                    	</td>
                    	<td>
                        	<fmt:formatDate pattern="yyyy-MM-dd"
							value="${row.zzim_date}"/>
                    	</td>
                    	<td>
                        	<a href="${path}/member/zzim/delete?zzim_no=${row.zzim_no}">삭제</a>
                        	<input type="hidden" name="ttr_no" value="${row.ttr_no}">
                    	</td>
                	</tr>
                	</c:forEach>
            	</table>
            	<input type="hidden" name="count" value="${map.count}">
        	<!-- </form> -->
        	</c:otherwise>
    	</c:choose>
    	<button type="button" id="btnList">상품목록</button>
    	<script>
    		$(document).ready(function(){
        		// 리스트 페이지로 이동
        		$("#btnList").click(function(){
          	  		location.href="/zzim/zzimList";
        		});
    		});
		</script>      
      	</div>
      </div>
        
      <!-- /.row -->

    </div>
    <!-- /.container -->


    <!-- Bootstrap core JavaScript -->
    <script src="/resources/main/vendor/jquery/jquery.min.js"></script>
    <script src="/resources/main/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  </body>

</html>
