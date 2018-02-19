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

    <title>Modern Business - Start Bootstrap Template</title>

    <!-- Bootstrap core CSS -->
    <link href="/resources/main/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/resources/main/css/modern-business.css" rel="stylesheet">

  </head>

  <body>

    <!-- Navigation -->
    <nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" href="/member/index">Start Bootstrap</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item">
              <a class="nav-link" href="about.html">About</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="services.html">Services</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="contact.html">Contact</a>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownPortfolio" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Portfolio
              </a>
              <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownPortfolio">
                <a class="dropdown-item" href="portfolio-1-col.html">1 Column Portfolio</a>
                <a class="dropdown-item" href="portfolio-2-col.html">2 Column Portfolio</a>
                <a class="dropdown-item" href="portfolio-3-col.html">3 Column Portfolio</a>
                <a class="dropdown-item" href="portfolio-4-col.html">4 Column Portfolio</a>
                <a class="dropdown-item" href="portfolio-item.html">Single Portfolio Item</a>
              </div>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownBlog" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Blog
              </a>
              <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownBlog">
                <a class="dropdown-item" href="blog-home-1.html">Blog Home 1</a>
                <a class="dropdown-item" href="blog-home-2.html">Blog Home 2</a>
                <a class="dropdown-item" href="blog-post.html">Blog Post</a>
              </div>
            </li>
            <li class="nav-item active dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownBlog" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Other Pages
              </a>
              <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownBlog">
                <a class="dropdown-item" href="full-width.html">Full Width Page</a>
                <a class="dropdown-item active" href="sidebar.html">Sidebar Page</a>
                <a class="dropdown-item" href="faq.html">FAQ</a>
                <a class="dropdown-item" href="404.html">404</a>
                <a class="dropdown-item" href="pricing.html">Pricing Table</a>
              </div>
            </li>
          </ul>
        </div>
      </div>
    </nav>

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
            <a href="index.html" class="list-group-item">Home</a>
            <a href="about.html" class="list-group-item">About</a>
            <a href="services.html" class="list-group-item">Services</a>
            <a href="contact.html" class="list-group-item">Contact</a>
            <a href="portfolio-1-col.html" class="list-group-item">1 Column Portfolio</a>
            <a href="portfolio-2-col.html" class="list-group-item">2 Column Portfolio</a>
            <a href="portfolio-3-col.html" class="list-group-item">3 Column Portfolio</a>
            <a href="portfolio-4-col.html" class="list-group-item">4 Column Portfolio</a>
            <a href="portfolio-item.html" class="list-group-item">Single Portfolio Item</a>
            <a href="blog-home-1.html" class="list-group-item">Blog Home 1</a>
            <a href="blog-home-2.html" class="list-group-item">Blog Home 2</a>
            <a href="blog-post.html" class="list-group-item">Blog Post</a>
            <a href="full-width.html" class="list-group-item">Full Width Page</a>
            <a href="sidebar.html" class="list-group-item active">Sidebar Page</a>
            <a href="faq.html" class="list-group-item">FAQ</a>
            <a href="404.html" class="list-group-item">404</a>
            <a href="pricing.html" class="list-group-item">Pricing Table</a>
          </div>
        </div>
        <!-- Content Column -->
        
        <!-- 기업회원 목록 -->
        
        <!-- Main content -->
		<section class="content">
		<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">회원수정</h3>
				</div>
				<!-- /.box-header -->

				<form role="form" action="cmodify" method="post">
					<input type='hidden' name='page' value="${cri.page}"> 
					<input type='hidden' name='perPageNum' value="${cri.perPageNum}">
					<input type='hidden' name='searchType' value="${cri.searchType}">
					<input type='hidden' name='keyword' value="${cri.keyword}">
				

				<div class="box-body">
				<div class="form-group">
					<label for="exampleInputEmail1">아이디</label>
						<input type="text" name='com_id' class="form-control" 
		       			value="${companyVO.com_id}" readonly="readonly">
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">패스워드</label>
						<input type="text" name='com_pw' class="form-control" 
		       			value="${companyVO.com_pw}">
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">사업자번호</label>
					<input type="text" name='com_no' class="form-control" 
		       		value="${companyVO.com_no}">
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">기업명</label>
					<input type="text" name="com_name" class="form-control"
					 value="${companyVO.com_name}">
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">대표자</label>
					<input type="text" name="com_dname" class="form-control"
					 value="${companyVO.com_dname}">
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">이메일</label>
					<input type="text" name="com_mail" class="form-control"
					 value="${companyVO.com_mail}">
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">회원상태</label>
					<input type="text" name="enabled" class="form-control"
					 value="${companyVO.enabled}">
				</div>
				</div>
			<!-- /.box-body -->
		</form>
			<div class="box-footer">
				<button type="submit" class="btn btn-primary">저장</button>
				<button type="submit" class="btn btn-warning">취소</button>
			</div>
		</div>
		<!-- /.box -->
		</div>
		<!--/.col (left) -->
		</div>
		<!-- /.row -->
		</section>
		<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
        
    </div>
    <!-- /.container -->

    <!-- Footer -->
    <footer class="py-5 bg-dark">
      <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Your Website 2018</p>
      </div>
      <!-- /.container -->
    </footer>

    <!-- Bootstrap core JavaScript -->
    <script src="/resources/main/vendor/jquery/jquery.min.js"></script>
    <script src="/resources/main/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	
	<script>
		$(document).ready(function() {
			var formObj = $("form[role='form']");
			console.log(formObj);
			$(".btn-warning").on("click",function() {
				self.location = "/admin/companyList?page=${cri.page}&perPageNum=${cri.perPageNum}"
							+ "&searchType=${cri.searchType}&keyword=${cri.keyword}";
			});

			$(".btn-primary").on("click",function(){
				formObj.submit();
			});
		});
</script>
	

  </body>

</html>
