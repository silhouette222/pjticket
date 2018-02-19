<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <meta charset="UTF-8">
    <title>회원가입</title>
<head>
<title></title>
</head>
<body>
<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">READ MEMBER</h3>
				</div>
				<!-- /.box-header -->

<form role="form" action="modifyPage" method="post">

	<input type='hidden' name='mem_id' value="${memberVO.mem_id}"> <input
		type='hidden' name='page' value="${cri.page}"> <input
		type='hidden' name='perPageNum' value="${cri.perPageNum}">
	<input type='hidden' name='searchType' value="${cri.searchType}">
	<input type='hidden' name='keyword' value="${cri.keyword}">

</form>

				<div class="box-body">
	<div class="form-group">
		<label for="exampleInputEmail1">아이디</label>
		<input type="text" name='mem_id' class="form-control" 
		       value="${memberVO.mem_id}" readonly="readonly">
	</div>
	<div class="form-group">
		<label for="exampleInputEmail1">패스워드</label>
		<input type="text" name='mem_pw' class="form-control" 
		       value="${memberVO.mem_pw}" readonly="readonly">
	</div>
	<div class="form-group">
		<label for="exampleInputEmail1">이름</label> <input type="text"
			name="mem_name" class="form-control" value="${memberVO.mem_name}"
			readonly="readonly">
	</div>
	<div class="form-group">
		<label for="exampleInputEmail1">이메일</label> <input type="text"
			name="mem_mail" class="form-control" value="${memberVO.mem_mail}"
			readonly="readonly">
	</div>
	<div class="form-group">
		<label for="exampleInputEmail1">가입날짜</label> <input type="text"
			name="mem_date" class="form-control" 
			value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm" 
					value="${memberVO.mem_date}" />"
			readonly="readonly">
	</div>
</div>
<!-- /.box-body -->

<div class="box-footer">
	<button type="submit" class="btn btn-warning">Modify</button>
	<button type="submit" class="btn btn-danger">REMOVE</button>
	<button type="submit" class="btn btn-primary">GO LIST</button>
</div>


<script>
	$(document).ready(function() {

		var formObj = $("form[role='form']");

		console.log(formObj);

		$(".btn-warning").on("click", function() {
			formObj.attr("action", "modifyPageForm");
			formObj.attr("method", "get");
			formObj.submit();
		});

		$(".btn-danger").on("click", function() {
			formObj.attr("action", "removePage");
			formObj.submit();
		});

		$(".btn-primary").on("click", function() {
			formObj.attr("method", "get");
			formObj.attr("action", "list");
			formObj.submit();
		});

	});
</script>




			</div>
			<!-- /.box -->
		</div>
		<!--/.col (left) -->

	</div>
	<!-- /.row -->
</section>
<!-- /.content -->
</body>
</html>
