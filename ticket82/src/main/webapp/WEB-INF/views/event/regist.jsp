<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<head>
	<style type="text/css">
		.fileDrop{
			width:80%;
			height:100px;
			border:1px dotted gray;
			background-color:lightslategray;
			margin:auto;
		}
	</style>
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
					<h3 class="box-title">REGISTER EVENT</h3>
				</div>
				<!-- /.box-header -->

				<form id="registerForm" action="/event/regist" role="form" method="post">
					<div class="box-body">
						<div class="form-group">
							<label>제목</label>
							<input type="text" name='et_title' class="form-control" placeholder="이벤트 제목">
						</div>
						<div class="form-group">
							<label for="writer">작성자</label> 
							<input type="text" id="writer" name="am_id" value="${loginUser}" readonly
								name="writer" class="form-control" placeholder="Enter Writer">
						</div>
						<div class="form-group">
							<label>내용</label>
							<textarea class="form-control" name="et_content" rows="5"
								placeholder="Enter ..."></textarea>
						</div>
					</div>
					<!-- /.box-body -->

					<div class="box-footer">						
						<div>
							<hr />
						</div>
							<ul class="mailbox-attachments clearfix uploadedList">
						</ul>
						<button type="submit" class="btn btn-primary">등록</button>
						<button type="button" class="btn btn-warning" onclick="javascript:history.back(-1)">작성취소<i class="fa fa-times spaceLeft"></i></button>
					</div>
				</form>


			</div>
			<!-- /.box -->
		</div>
		<!--/.col (left) -->

	</div>
	<!-- /.row -->
</section>
<!-- /.content -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<script type="text/javascript" src="/resources/js/upload.js"></script>

</body>
