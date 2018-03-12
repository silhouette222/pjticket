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
        <a class="navbar-brand" href="/member/index">Ticket82</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item">
              <a class="nav-link" href="/mboard/mc">뮤지컬</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/mboard/cc">콘서트</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/mboard/play">연극</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/mboard/classic">연주회</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/mboard/gal">전시회</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/mboard/etc">기타</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/mboard/eventList">이벤트</a>
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
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownBlog" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                로그인
              </a>
              <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownBlog">
                <a class="dropdown-item" href="/member/login">로그인</a>
                <a class="dropdown-item" href="/member/regist">회원가입</a>
              </div>
            </li>
            </sec:authorize>
            <sec:authorize access="hasAuthority('ROLE_USER')">
            	<li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownBlog" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
               	<b>${loginUser.mem_name }</b>님의 마이페이지
              </a>
              <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownBlog">
                <a class="dropdown-item" href="/member/my/reslist">예약확인</a>
                <a class="dropdown-item" href="/member/my/info">회원정보</a>
                <a class="dropdown-item" href="/zzim/zzimList">찜목록</a>
              </div>
            </li>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
            		<li class="nav-item">
              			<a class="nav-link" href="#" onclick="javascript:location.href=
							'<%=request.getContextPath() %>/member/logout'">로그아웃</a>
            		</li>
            </sec:authorize>
          </ul>
        </div>
      </div>
    </nav>
    
    <%-- <!-- 사이드바 -->
    <sec:authorize access="isAuthenticated()">
    	<div id="btncollapzion" class=" btn_collapzion">
    	</div>
	</sec:authorize>
	 --%>
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
    
    <script>
		$('#btncollapzion').Collapzion({
            _child_attribute:[
              	{
                  'label':'찜목록',
                  'url':'/zzim/zzimList',
                  'icon':'&#xE873;'
              	},
              	{
	              'label':'Edit Item',
	              'url':'#',
	              'icon':'&#xE150;'
              	},
              	{
	              'label':'내정보',
	              'url':'/member/my/info',
	              'icon':'&#xE7FD;'
              	},
          	],
          	_main_btn_color:'#009688;',
          	_child_btn_color:'#f4645f;',
			
    	});
	
    </script>
  </body>

</html>
