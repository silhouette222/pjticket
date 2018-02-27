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
      <h1 class="mt-4 mb-3">${loginUser.com_name}님의
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
            <a href="info" class="list-group-item">회원정보</a>
            <a href="/" class="list-group-item  active">게시판목록</a>
          </div>
        </div>
        <!-- Content Column -->
        
        <!-- 예약정보 -->
    	<div class="box">
			<div class="box-header with-border">
				<h3 class="box-title">예약정보</h3>
			</div>
			<div class="box-body">
				<table id="list" class="table table-bordered">
					<tr>
						<th>글번호</th>
						<th>카테고리</th>
						<th>제목</th>
						<th>시작일</th>
						<th>종료일</th>
						<th>상태</th>
					</tr>
					
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
	var page=1
	var pagenum=10
	function makelist(){
		$.getJSON("/cboard/my/board2/"+page+"/"+pagenum,function(data){
			var str="<tr><th>글번호</th><th>카테고리</th><th>제목</th><th>시작일</th><th>종료일</th>"+
				"<th>상태</th></tr>";
				var list=data.list;
				for(i in list){
					var sdate=new Date(list[i].ttr_sdate)
					var edate=new Date(list[i].ttr_edate)
					var sdate2=sdate.getFullYear()+"-"+(sdate.getMonth()+1)+"-"+sdate.getDate()
					var edate2=edate.getFullYear()+"-"+(edate.getMonth()+1)+"-"+edate.getDate()
					str+="<tr><td id='pk'>"+list[i].ttr_no+"</td>"+
					"<td>"+list[i].ttr_cat+"</td>"+
					"<td>"+list[i].ttr_title+"</td>"+
					"<td>"+sdate2+"</td>"+
					"<td>"+edate2+"</td>"+
					"<td>"+list[i].status+"</td>"+
					"</tr>"
				}
				console.log(list[i].ttr_title)
				str+="<tr><td id='pagearea' colspan='7'></td></tr>"
					$('#list').html(str);
					var pageMaker=data.pageMaker;
					var str="";
					if(pageMaker.prev){
						str+="<li><button class='btn btn-primary'>"+(pageMaker.startPage-1)
							  +"'> << </button></li>";
					};
					for(var i=pageMaker.startPage,len=pageMaker.endPage;i<=len;i++){
						var strClass=pageMaker.cri.page==i?'class=active':'';
						str+="<li "+strClass+"><button class='reppage btn btn-primary'>"+i+"</button></li>";
					};
					if(pageMaker.next){
						str+="<li><button class='btn btn-primary'>"+(pageMaker.endPage+1)
						  +"'> >> </button></li>";
					};
					$('#pagearea').append(str);
		})
	}
	$('#list').on('click','tr td li .reppage',function(index){
		page=$(this).text();
		makelist()
	})
	$('#pagenum').change(function(){
		pagenum=$(this).children("option:selected").val();
		makelist();
	})
	makelist()
	</script>
	
<script>
$(document).ready(function(){
	makelist()
})

</script>
  </body>

</html>
