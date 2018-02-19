<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
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
					<h3 class="box-title">MODIFY MEMBER</h3>
				</div>
				<!-- /.box-header -->

<form role="form" action="modifyPage" method="post">

	<input type='hidden' name='page' value="${cri.page}"> <input
		type='hidden' name='perPageNum' value="${cri.perPageNum}">
	<input type='hidden' name='searchType' value="${cri.searchType}">
	<input type='hidden' name='keyword' value="${cri.keyword}">

	<div class="box-body">
		<div class="form-group">
			<label for="exampleInputEmail1">아이디</label> 
			<input type="text" name="userid" class="form-control" value="${memberVO.userid}" readonly="readonly" />
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">패스워드</label> 
			<input type="text" name='userpw' class="form-control" value="${memberVO.userpw}" />
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">이름</label> 
			<input type="text" name="username" class="form-control"	value="${memberVO.username}" />
		</div>
	 	<div class="form-group">
			<label for="exampleInputEmail1">이메일</label> 
			<input type="text" name="email" class="form-control" value="${memberVO.email}">
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">가입날짜</label> 
			<input type="text" class="form-control" value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm" 
						value="${memberVO.regdate}" />" readonly="readonly">
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">수정날짜</label> 
			<input type="text" class="form-control" value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm"
						value="${memberVO.updatedate}" />" 	readonly="readonly">
		</div>
	</div>
	<!-- /.box-body -->
</form>
	<div class="box-footer">
		<button type="submit" class="btn btn-primary">SAVE</button>
		<button type="submit" class="btn btn-warning">CANCEL</button>
	</div>

<script>
$(document).ready(
	function() {

		var formObj = $("form[role='form']");

		console.log(formObj);

		$(".btn-warning")
				.on("click",function() {
					self.location = "/smember/list?page=${cri.page}&perPageNum=${cri.perPageNum}"
							+ "&searchType=${cri.searchType}&keyword=${cri.keyword}";
				});

		$(".btn-primary").on("click",
				function() {
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
</div>
<!-- /.content-wrapper -->
</body>
</html>