<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

  <head>

    <!-- Custom styles for this template -->
    <link href="/resources/main/css/modern-business.css" rel="stylesheet">
	<style>
		.card h-100 a img{
			width: 120px;
		}
	</style>
  </head>

  <body>

    <!-- Page Content -->
    <div class="container">

      <!-- Page Heading/Breadcrumbs -->
      <h1 class="mt-4 mb-3">연주회
      </h1>

      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="/member/index">Home</a>
        </li>
        <li class="breadcrumb-item active">연주회</li>
      </ol>
	  
	  <div class="box">
			<div class="box-body text-right">
				<select name="searchType">
					<option value="n"<c:out value="${cri.searchType == null?'selected':''}"/>>---</option>
					<option value="t"<c:out value="${cri.searchType eq 't'?'selected':''}"/>>제목</option>
					<option value="c"<c:out value="${cri.searchType eq 'c'?'selected':''}"/>>내용</option>
					<option value="s"<c:out value="${cri.searchType eq 'p'?'selected':''}"/>>장소</option>
				</select>
				<input type="text" id="key">
				<input class="btn btn-primary" id="search" type="button" value="검색">
			</div>
		</div>
	  
      <div class="row">
      	<c:forEach items="${list }" var="boardVO">
        <div class="col-lg-4 col-sm-6 portfolio-item">
          <div class="card h-100">
            <a href="/mboard/classic/classicdetail?ttr_no=${boardVO.ttr_no}"><img class="card-img-top" src="/displayFile?fileName=${boardVO.thumb_name}" alt=""></a>
            <div class="card-body">
              <h4 class="card-title">
                <a href="/mboard/classic/classicdetail?ttr_no=${boardVO.ttr_no}">${boardVO.ttr_title }</a>
              </h4>
              <p class="card-text">${boardVO.ttr_content }</p>
              <p><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.ttr_date}" /></p>
            </div>
          </div>
        </div>
		</c:forEach>
    </div>

      <!-- Pagination -->
      <ul class="pagination justify-content-center">
        <li class="page-item">
          <a class="page-link" href="#" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
            <span class="sr-only">Previous</span>
          </a>
        </li>
        <li class="page-item">
          <a class="page-link" href="#">1</a>
        </li>
        <li class="page-item">
          <a class="page-link" href="#">2</a>
        </li>
        <li class="page-item">
          <a class="page-link" href="#">3</a>
        </li>
        <li class="page-item">
          <a class="page-link" href="#" aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
            <span class="sr-only">Next</span>
          </a>
        </li>
      </ul>

    </div>
    <!-- /.container -->
    
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script>
	$(document).ready(function(){
		$('#search').on('click',function(event){
			self.location = "etc"
				+ "?searchType="
				+ $("select option:selected").val()
				+ "&keyword=" + $('#key').val();
		})
	})
	</script>
  </body>

</html>
