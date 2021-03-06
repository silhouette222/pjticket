<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html >
<html>

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Ticket82</title>

    <!-- Bootstrap core CSS -->
    <link href="<%=request.getContextPath() %>/resources/main/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<%=request.getContextPath() %>/resources/main/css/modern-business.css" rel="stylesheet">
	
	<!-- 사이드메뉴 css -->
	<link href="//fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/collapzion.min.css">

	<style>
		/* 메뉴 선 */
		.nav-item{
			border-left:1px solid #dc6565;
		}
				
		/* 드롭다운 호버 */
		
		.dropdown:hover .dropdown-menu-right {
    		display: block;
		}
	</style>
	<decorator:head />
	
  </head>

  <body>
    <!-- Navigation -->
    <!-- 메뉴바 -->    
    <nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" href="/aboard/index">Ticket82</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
          	<sec:authorize access="hasAuthority('ROLE_ADMIN')">
            <li class="nav-item">
              <a class="nav-link" href="/aboard/main">메인수정</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/aboard/res">예약관리</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/aboard/mem">일반회원관리</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/aboard/com">기업회원관리</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/aboard/board">게시글 관리</a>
            </li>
            </sec:authorize>
            <li class="nav-item">
              <a class="nav-link" href="/event/eventList">이벤트</a>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownBlog" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                고객센터
              </a>
              <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownBlog">
                <a class="dropdown-item" href="faq.html">공지사항</a>
                <a class="dropdown-item" href="faq.html">Q&A</a>
                <a class="dropdown-item" href="faq.html">FAQ</a>
              </div>
            </li>            
            <sec:authorize access="!isAuthenticated()">
            <li class="nav-item">
              <a class="nav-link" href="/aboard/login">로그인</a>
            </li>
            
            </sec:authorize>
            <%-- <sec:authorize access="hasAuthority('ROLE_ADMIN')">
            	<li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownBlog" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
               	마이페이지
              </a>
              <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownBlog">
                <a class="dropdown-item" href="/aboard/res">예약관리</a>
                <a class="dropdown-item" href="/aboard/mem">일반회원관리</a>
                <a class="dropdown-item" href="/aboard/com">기업회원관리</a>
                <a class="dropdown-item" href="/aboard/board">게시글 관리</a>
              </div>
            </li>
            </sec:authorize> --%>
            <sec:authorize access="isAuthenticated()">
            		<li class="nav-item">
              			<a class="nav-link" href="#" onclick="javascript:location.href=
							'<%=request.getContextPath() %>/aboard/logout'">로그아웃</a>
            		</li>
            </sec:authorize>
          </ul>
        </div>
      </div>
    </nav>
    

	
	<!-- 바디시작 -->
    <decorator:body />
    
    <!-- 바디끝 -->
    
    
   	
    <!-- /.container -->

    <!-- Footer -->
    <footer class="py-5 bg-dark">
      <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Your Website 2018</p>
      </div>
      <!-- /.container -->
    </footer>

    <!-- Bootstrap core JavaScript -->
    <script src="<%=request.getContextPath() %>/resources/main/vendor/jquery/jquery.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/main/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    
    <!-- 사이드바 jquery -->

	<script src="<%=request.getContextPath() %>/resources/js/collapzion.min.js"></script>
    
  </body>

</html>
