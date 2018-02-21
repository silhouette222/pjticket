<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<div class="container">
	<div class='box'>
		<a href="javascript:history.go(-1)"><button class="btn btn-primary">목록</button></a>
	</div>
	<div class='box'>
	<form id="write" method="post">
		<input type="hidden" name="ttr_cat" value="${ttr_cat }"> 
		<input type="hidden" name="com_id" value="${loginUser.com_id }">
		<div class="row">
			<div class="col-4">
				<div class="thumb alert alert-info">
					<label>썸네일</label>
				</div>
			</div>
			<div class="col-8"></div>
		</div>
		<div class="row">
			<div class="col-12">
				<div class="alert alert-info">
					<label>제목</label>
					<div><input class="col-12" type="text" name="ttr_title"></div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<div class="alert alert-info">
					<label>예매 시작시간</label>
					<div>
						<input type="date" name="ttr_sdate">
						<input type="time" name="ttr_edate">
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<div class="alert alert-info">
					<label>장소</label>
					<div><input class="col-12" type="text" name="ttr_place"></div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<div class="alert alert-info">
					<label>시간 정보</label>
					<div><textarea name="ttr_time" style="width: 1050px; height: 200px;"></textarea></div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<div class="alert alert-info">
					<label>알림 사항</label>
					<div><textarea name="ttr_alert" style="width: 1050px; height: 200px;"></textarea></div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<div class="alert alert-info">
					<label>세부 내용</label>
					<div><textarea name="ttr_content" style="width: 1050px; height: 200px;"></textarea></div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-4">
				<div class="seatmap alert alert-info">
					<label>좌석배치도</label>
				</div>
			</div>
			<div class="col-8"></div>
		</div>
		<div class="row">
			<div class="col-12">
				<div class="alert alert-info">
					<label>좌석정보</label>
					<button class="btn btn-primary" id="addseat" type="button">+</button>
					<table id="seat_table" class="table table-striped">
						<tr>
							<td>등급</td>
							<td>좌석수</td>
							<td>가격</td>
							<td>날짜</td>
							<td>시간</td>
							<td></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<div class="file alert alert-info">
					<label>사진자료</label>
				</div>
			</div>
		</div>
		<input class="btn btn-primary" type="submit" value="작성">
	</form>
	</div>
</div>
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script src="/resources/js/upload.js"></script>
</body>
</html>