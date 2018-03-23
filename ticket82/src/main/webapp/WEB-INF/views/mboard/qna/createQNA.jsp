<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<body>
<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">Create QNA</h3>
				</div>
				<!-- /.box-header -->

				<form id="CreateForm" role="form" method="post">
					<div class="box-body">
						<div class="form-group">
							<label for="exampleInputEmail1">Title</label> 
							<input type="text" name='qna_title' class="form-control" placeholder="Enter Title">
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">Content</label>
							<textarea class="form-control" name="ans_content" rows="3"
								placeholder="Enter ..."></textarea>
						</div>
						<div class="form-group">
							<label for="writer">Writer</label> 
							<input type="text" id="mem_id" value="${loginUser}" readonly
								name="mem_id" class="form-control" placeholder="Enter Writer">
						</div>
						<div class="form-group">
							<label for="exampleInputPassword">Password</label>
							<input type="password" name='qna_content' class="form-control" placeholder="Enter Password">
						</div>
						
					</div>
					<!-- /.box-body -->

					<div class="box-footer">						
						<div>
							<hr />
						</div>
						<ul class="mailbox-attachments clearfix uploadedList">
						</ul>
						<button type="submit" class="btn btn-primary">Submit</button>
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