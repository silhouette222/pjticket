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
</head>
<body>
<div class="container">
	<div class='box'>
		<div class="box-header">
			<h1 style="font-size: 50px" class="box-title"><strong>${boardVO.ttr_title}</strong></h1>
		</div>
		<div class="box-body">
			<a href="javascript:history.go(-1)"><button class="btn btn-primary">목록</button></a>
		</div>
	</div>
	<div class='box'>
	<form id="write" method="post">
		<input type="hidden" name="ttr_no" value="${boardVO.ttr_no}">
		<input type="hidden" name="ttr_cat" value="${boardVO.ttr_cat}">
		<input type="hidden" name="ttr_title" value="${boardVO.ttr_title}">
		<input type="hidden" name="com_id" value="${boardVO.com_id}">
		<div class="row">
			<div class="col-1"></div>
			<div class="col-3">
				<div class="alert alert-info">
					<label>썸네일</label>
					<div class="thumb"></div>
				</div>
			</div>
			<div class="col-8"></div>
		</div>
		<div class="row">
			<div class="col-1"></div>
			<div class="col-10">
				<div class="alert alert-info">
					<p><label>시간 정보</label><p>
					<label>${boardVO.ttr_time}</label>
				</div>
			</div>
			<div class="col-1"></div>
		</div>
		<div class="row">
			<div class="col-1"></div>
			<div class="col-10">
				<div class="alert alert-info">
					<p><label>알림 사항</label><p>
					<label>${boardVO.ttr_alert}</label>
				</div>
			</div>
			<div class="col-1"></div>
		</div>
		<div class="row">
			<div class="col-1"></div>
			<div class="col-10">
				<div class="alert alert-info">
					<p><label>세부 내용</label><p>
					<label>${boardVO.ttr_content}</label>
				</div>
			</div>
			<div class="col-1"></div>
		</div>
		<div class="row">
			<div class="col-1"></div>
			<div class="col-3">
				<div class="alert alert-info">
					<label>좌석배치도</label>
					<div class="seatmap"></div>
				</div>
			</div>
			<div class="col-8"></div>
		</div>
		<div class="row">
			<div class="col-1"></div>
			<div class="col-10">
				<div class="alert alert-info">
					<div>
						<label>좌석정보</label>
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
			<div class="col-1"></div>
		</div>
		<input type="submit" class="btn btn-primary" value="예약하기">
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

$(document).ready(function(){
	var seats=null;
	var str="";
	var cnt=null;
	<c:forEach var="seat" items="${boardVO.seat_grd}" varStatus="status">
		cnt='${boardVO.seat_no[status.count-1]}'-'${seat_cnt[status.count-1]}'
		str+="<tr seat_no='${boardVO.seat_no[status.count-1]}' seat_id='${boardVO.seat_id[status.count-1]}'>"+	
		"<td id='seat_info'>${boardVO.seat_grd[status.count-1]}</td>"+
		"<td>"+cnt+"</td>"+
		"<td>${boardVO.seat_pri[status.count-1]}</td>"+
		"<td><fmt:formatDate pattern='yyyy-MM-dd' value='${boardVO.seat_date[status.count-1]}' /></td>"+
		"<td><fmt:formatDate pattern='HH:mm' value='${boardVO.seat_time[status.count-1]}' /></td>"+
		"<td><input type='button' class='seat_btn btn btn-primary' value='좌석보기'></td>"+
	"</tr>"
	</c:forEach>
	$('#seat_table').append(str);
		var ttr_no=${boardVO.ttr_no};
			var thumb_name="${boardVO.thumb_name}"
			var thumb="<div class='min'><img src='"+getThumb(thumb_name)+"'><ori data_src='"+getOri(thumb_name)+"'></div>"
			var seatmap_name="${boardVO.seatmap_name}"
				var seatmap="<div class='min'><img src='"+getThumb(seatmap_name)+"'><ori data_src='"+getOri(seatmap_name)+"'></div>"
			$(".thumb").append(thumb);
			$(".seatmap").append(seatmap);
			$('.min').on('click',function(event){
				var fileLink=$(this).children('ori').attr('data_src')
				if(checkImageType(fileLink)){
					event.preventDefault();
					
					var imgTag=$('#popup_img');
					imgTag.attr('src',fileLink);
					
					$('.popup').show('fast');
					imgTag.addClass('show');
				}
			});
			$('#popup_img').on('click',function(){
				$('.popup').hide('fast');
			});
	$('#seat_table').on('click','tr td .seat_btn',function(event){
		var str="<tr><td colspan='6'><div style='width:500px;'>";
		var seat_no=$(this).parent('td').parent('tr').attr('seat_no');
		seat_no++
		var seat_id=$(this).parent('td').parent('tr').attr('seat_id');
		for(var i=1;i<seat_no;i++){
			str+="<label>"+i+"<input name='rescheck' value='"+seat_id+"_"+i+"' type='checkbox'></label>";
		}
		str+="<div><input seat_no="+seat_no+" seat_id="+seat_id+" class='refresh btn btn-primary' type='button' value='새로고침'></div></div></td></tr>";
		$(this).parent('td').parent('tr').after(str);
		$('.refresh').trigger('click');
	})
	var reserved = [];
	$('#seat_table').on('click','tr td div div .refresh',function(event){
		event.preventDefault();
		$(this).parent('div').parent('div').children('label').children("input[value='"+reserved[i]+"']").prop('checked', false);
		$(this).parent('div').parent('div').children('label').children("input[value='"+reserved[i]+"']").prop('disabled', false);
		var seat_id=$(this).attr('seat_id');
		$.getJSON("/mboard/reserve/"+seat_id,function(res){
			for(var i=0;i<res.length;i++){
				reserved[i]=seat_id+"_"+res[i];
			}
		})
		for(var i=0;i<reserved.length;i++){
			$(this).parent('div').parent('div').children('label').children("input[value='"+reserved[i]+"']").prop('checked', true);
			$(this).parent('div').parent('div').children('label').children("input[value='"+reserved[i]+"']").prop('disabled', true);
		}
		})
	})
</script>
</body>
</html>