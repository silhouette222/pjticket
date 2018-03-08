<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<style>
.popup {
	position: absolute;
	left: 0px;
	top: 0px;
}

.back {
	background-color: gray;
	opacity: 0.5;
	width: 100%;
	height: 100%;
	overflow: hidden;
	z-index: 1101;
}

.front {
	z-index: 1110;
	opacity: 1;
	border: 1px;
}

.show {
	position: relative;
	width: 50%;
	height: 50%;
	margin: 200px auto;
	overflow: hidden;
}
</style>
<div class="container">
	<div class='box'>
		<a href="/cboard/play"><button class="btn btn-primary">목록</button></a>
	</div>
	<div class='box'>
	<form id="write" method="post">
		<input type="hidden" name="ttr_cat" value="${ttr_cat }"> 
		<input type="hidden" name="com_id" value="${loginUser.com_id }">
		<div class="row">
			<div class="col-4">
				<div class="alert alert-info">
					<label>썸네일</label>
					<div class="thumb"></div>
				</div>
			</div>
			<div class="col-8"></div>
		</div>
		<div class="row">
			<div class="col-12">
				<div class="alert alert-info">
					<label>제목</label>
					<div><input class="col-12" type="text" name="ttr_title" value="${boardVO.ttr_title}"></div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<div class="alert alert-info">
					<label>예매 시작시간</label>
					<div>
						<input type="date" name="ttr_sdate" value="<fmt:formatDate pattern='yyyy-MM-dd' value='${boardVO.ttr_sdate}' />">
						<input type="time" name="ttr_edate" value="<fmt:formatDate pattern='HH:mm' value='${boardVO.ttr_edate}' />">
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<div class="alert alert-info">
					<label>장소</label>
					<div><input class="col-12" type="text" name="ttr_place" value="${boardVO.ttr_place}"></div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<div class="alert alert-info">
					<label>시간 정보</label>
					<div><textarea name="ttr_time" style="width: 1050px; height: 200px;">${boardVO.ttr_time}</textarea></div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<div class="alert alert-info">
					<label>알림 사항</label>
					<div><textarea name="ttr_alert" style="width: 1050px; height: 200px;">${boardVO.ttr_alert}</textarea></div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<div class="alert alert-info">
					<label>세부 내용</label>
					<div><textarea name="ttr_content" style="width: 1050px; height: 200px;">${boardVO.ttr_content}</textarea></div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-4">
				<div class="alert alert-info">
					<label>좌석배치도</label>
					<div class="seatmap"></div>
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
				<div class="alert alert-info">
					<label>사진자료</label>
					<div class="file"></div>
				</div>
			</div>
		</div>
		<input class="btn btn-primary" type="submit" value="작성">
	</form>
	</div>
</div>
	<div class="popup back" style="display: none;"></div>
	<div id="popup_front" class='popup front text-center' style="display: none;">
		<img id="popup_img" />
	</div>
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script src="/resources/js/upload.js"></script>
	<script>
	var ttr_no=${boardVO.ttr_no};
	var sdate=new Date('${boardVO.ttr_sdate}');
	$(document).ready(function(){
		var seats=null;
		var str=""
		<c:forEach var="seat" items="${boardVO.seat_grd}" varStatus="status">
			str+="<tr>"+
			"<input type='hidden' name='seat_id' value='${boardVO.seat_id[status.count-1]}'>"+
			"<td id='seat_info'><input type='text' name='seat_grd' value='${boardVO.seat_grd[status.count-1]}'></td>"+
			"<td><input type='int' name='seat_no' value='${boardVO.seat_no[status.count-1]}'></td>"+
			"<td><input type='int' name='seat_pri' value='${boardVO.seat_pri[status.count-1]}'></td>"+
			"<td><input type='date' name='seat_date' value='<fmt:formatDate pattern='yyyy-MM-dd' value='${boardVO.seat_date[status.count-1]}' />'></td>"+
			"<td><input type='time' name='seat_time' value='<fmt:formatDate pattern='HH:mm' value='${boardVO.seat_time[status.count-1]}' />'></td><td><input type='button' class='delseat' value='-'></td>"+
		"</tr>"
		</c:forEach>
		$('#seat_table').append(str);
			var ttr_no=${boardVO.ttr_no};
			var thumb_name="${boardVO.thumb_name}"
			var thumb="<div><img src='/displayFile?fileName=" + thumb_name
				+ "'/><small data-src='" + thumb_name
				+ "'><input type='hidden' name='thumb_name' value='"+thumb_name+"'><button type='button'>X</button></small></div>"
			var seatmap_name="${boardVO.seatmap_name}"
			var seatmap="<div><img src='/displayFile?fileName=" + seatmap_name
			+ "'/><small data-src='" + seatmap_name
			+ "'><input type='hidden' name='seatmap_name' value='"+seatmap_name+"'><button type='button'>X</button></small></div>"
			$(".thumb").append(thumb);
			$(".seatmap").append(seatmap);
			$.getJSON("/cboard/getFiles/"+ttr_no,function(list){
				$(list).each(function(){
					var file;
					file="<div style='display:inline;'><img src='/displayFile?fileName=" + this
					+ "'/><small class='file_submit' data-src='" + this
					+ "'><button type='button'>X</button></small></div>"
					$(".file").append(file);
				})
			})
			$('.min').on('click',function(event){
				var fileLink=$(this).children('ori').attr('data_src')
				if(checkImageType(fileLink)){
					event.preventDefault();
					
					var imgTag=$('#popup_img');
					imgTag.attr('src',fileLink);
					
					//console.log(imgTag.attr('src'));
					
					$('.popup').show('slow');
					imgTag.addClass('show');
				}
			});
			
			$('#popup_img').on('click',function(){
				$('.popup').hide('slow');
			});
		})
</script>
</body>
</html>