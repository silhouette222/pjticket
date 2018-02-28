<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	

    <!-- Page Content -->
    <div class="container">

      <!-- Page Heading/Breadcrumbs -->
      <h1 class="mt-4 mb-3">ticket82
        <small>Event</small>
      </h1>
      
      <!-- 작성버튼 -->
      <sec:authorize access="hasAuthority('ROLE_ADMIN')">
      <button id="btnReg" type="button" class="btn btn-warning">작성<i class="fa fa-times spaceLeft"></i></button>
	  </sec:authorize>
      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="/aboard/index">Home</a>
        </li>
        <li class="breadcrumb-item active">Event</li>
      </ol>

      

      <!-- Marketing Icons Section -->
      <!-- 이벤트 리스트 -->
      <div class="row">
      <c:forEach items="${list}" var="eventVO" end="3">
        <div class="col-lg-4 mb-4">
          <div class="card h-100">
            <h4 class="card-header">${eventVO.et_title}</h4>
            <div class="card-body">
              <p class="card-text">${eventVO.et_content}</p>
            </div>
            <div class="card-footer">
              <a href="/event/read?et_no=${eventVO.et_no}" class="btn btn-danger">상세보기</a>
            </div>
          </div>
        </div>
      </c:forEach>
      </div> 
      <!-- /.row -->
    </div>
    <!-- /.container -->
    
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=request.getContextPath() %>/resources/bootstrap/js/bootstrap.min.js"></script>
	
    <script>
    $(document).ready(function() {
   		$('.btn-warning').on("click", function(){
		self.location = "/event/regist";

		});
    });
    </script>
</body>
</html>