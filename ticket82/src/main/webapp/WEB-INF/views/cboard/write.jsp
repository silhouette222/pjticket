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
<a href="/main"><button>목록</button></a>
	<form id="write" method="post">
		<input type="hidden" name="ttr_cat" value="${ttr_cat }"> 
		<input type="hidden" name="com_id" value="${loginUser.com_id }"> 
		<label>썸네일</label>
		<div class="thumb"
			style="width: 200px; height: 200px; background-color: blue;">
		</div>
		<label>좌석배치도</label>
		<div class="seatmap"
			style="width: 200px; height: 200px; background-color: blue;">
		</div>
		<div>
		<label>제목</label> <input type="text" name="ttr_title"> 
		</div>
		<div>
		<label>시작일</label>
		<input type="date" name="ttr_sdate"></div> 
		<div>
		<label>종료일</label> <input type="date" name="ttr_edate">
		</div> 
		<div>	
			<label>장소</label> <input type="text" name="ttr_place">
		</div> 
		<div>
		<label>시간</label> <input type="text" name="ttr_time">
		</div> 
		<div><label>알림</label> <input type="text" name="ttr_alert">
		</div> 
		<div><label>세부내용</label> <input type="text" name="ttr_content">
		</div> 
		<div><label>좌석정보</label><button id="addseat" type="button">+</button>
			<table id="seat_table">
				<tr>
					<td>등급</td>
					<td>좌석수</td>
					<td>가격</td>
					<td>날짜</td>
					<td>시간</td>
				</tr>
			</table>
		</div>
		<label>사진자료</label>
		<div class="file"
			style="width: 800px; height: 200px; background-color: blue;"></div>
		<input type="submit" value="작성">
	</form>
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script src="/resources/js/upload.js"></script>
</body>
</html>