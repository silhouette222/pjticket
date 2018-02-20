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
<!-- <style>
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
</style> -->
</head>
<body>

<form id="res-write" method="post">
		<input type="hidden" name="ttr_no" value="${musicalVO.ttr_no}">
		<input type="hidden" name="ttr_cat" value="${musicalVO.ttr_cat}">
		
		<label>썸네일</label>
		<div class="thumb"
			style="width: 200px; height: 200px; background-color: white;"></div>
		<label>좌석배치도</label>
		<div class="seatmap"
			style="width: 200px; height: 200px; background-color: white;"></div>
		<div>
			<label>제목</label> <input type="text" name="ttr_title"
				value="${musicalVO.ttr_title}" readonly>
		</div>
		<div>
			<label>알림</label> <input type="text" name="ttr_alert"
				value="${musicalVO.ttr_alert}" readonly>
		</div>
		<div>
			<label>내용</label> <input type="text" name="ttr_content"
				value="${musicalVO.ttr_content}" readonly>
		</div>
		<div><label>좌석정보</label>
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
		<input type="submit" value="예약">
	</form>
		<div class="popup back" style="display: none;"></div>
	<div id="popup_front" class='popup front' style="display: none;">
		<img id="popup_img" />
	</div>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="/com/resources/js/upload.js"></script>
<script>
$(document).ready(function(){
	var seats=null;
	var str="";
	var cnt=null;
	<c:forEach var="seat" items="${musicalVO.seat_grd}" varStatus="status">
		cnt='${musicalVO.seat_no[status.count-1]}'-'${seat_cnt[status.count-1]}'
		str+="<tr seat_no='${musicalVO.seat_no[status.count-1]}' seat_id='${musicalVO.seat_id[status.count-1]}'>"+
		"<td id='seat_info'><input type='text' name='seat_grd' value='${musicalVO.seat_grd[status.count-1]}' readonly></td>"+
		"<td><input type='int' name='seat_no' value='"+cnt+"' readonly></td>"+
		"<td><input type='int' name='seat_pri' value='${musicalVO.seat_pri[status.count-1]}' readonly></td>"+
		"<td><input type='date' name='seat_date' value='<fmt:formatDate pattern='yyyy-MM-dd' value='${musicalVO.seat_date[status.count-1]}' />' readonly></td>"+
		"<td><input type='time' name='seat_time' value='<fmt:formatDate pattern='HH:mm' value='${musicalVO.seat_time[status.count-1]}' />' readonly></td>"+
		"<td><input type='button' class='seat_btn' value='좌석보기'></td>"+
	"</tr>"
	</c:forEach>
	$('#seat_table').append(str);
var ttr_no=${musicalVO.ttr_no};
			var thumb_name="${musicalVO.thumb_name}"
			var thumb="<div class='min'><img src='"+getThumb(thumb_name)+"'><ori data_src='"+getOri(thumb_name)+"'></div>"
			var seatmap_name="${musicalVO.seatmap_name}"
				var seatmap="<div class='min'><img src='"+getThumb(seatmap_name)+"'><ori data_src='"+getOri(seatmap_name)+"'></div>"
			$(".thumb").append(thumb);
			$(".seatmap").append(seatmap);
			$('.min').on('click',function(event){
				var fileLink=$(this).children('ori').attr('data_src')
				if(checkImageType(fileLink)){
					event.preventDefault();
					
					var imgTag=$('#popup_img');
					imgTag.attr('src',fileLink);
					
					$('.popup').show('slow');
					imgTag.addClass('show');
				}
			});
			$('#popup_img').on('click',function(){
				$('.popup').hide('slow');
			});
	$('#seat_table').on('click','tr td .seat_btn',function(event){
		var str="<tr><td colspan='6'><div style='width:500px;'>";
		var seat_no=$(this).parent('td').parent('tr').attr('seat_no');
		seat_no++
		var seat_id=$(this).parent('td').parent('tr').attr('seat_id');
		for(var i=1;i<seat_no;i++){
			str+="<label>"+i+"<input name='rescheck' value='"+seat_id+"_"+i+"' type='checkbox'></label>";
		}
		str+="<input seat_no="+seat_no+" seat_id="+seat_id+" class='refresh' type='button' value='새로고침'></div></td></tr>";
		$(this).parent('td').parent('tr').after(str);
		$('.refresh').trigger('click');
	})
	var reserved = [];
	$('#seat_table').on('click','tr td div .refresh',function(event){
		event.preventDefault();
		$(this).parent('div').children('label').children("input[value='"+reserved[i]+"']").prop('checked', false);
		$(this).parent('div').children('label').children("input[value='"+reserved[i]+"']").prop('disabled', false);
		var seat_id=$(this).attr('seat_id');
		$.getJSON("/com/createRes/"+seat_id,function(res){
			for(var i=0;i<res.length;i++){
				reserved[i]=seat_id+"_"+res[i];
			}
		})
		for(var i=0;i<reserved.length;i++){
			console.log('input[name='+reserved[i]+']');
			$(this).parent('div').children('label').children("input[value='"+reserved[i]+"']").prop('checked', true);
			$(this).parent('div').children('label').children("input[value='"+reserved[i]+"']").prop('disabled', true);
		}
		})
	})
		
</script>
</body>
</html>
<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<!-- <style>
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
</style> -->
</head>
<body>
<div>
	뮤지컬 예약페이지
</div>
<form id="write" method="post">
	<input type='text' name='ttr_no' value="${musicalVO.ttr_no}" readonly/>
	<input type='text' name='ttr_cat' value="${musicalVO.ttr_cat}" readonly/>
	

<div>
	<label>썸네일</label>
	<div class="thumb"
		style="width: 200px; height: 200px; background-color: white;">
	</div>
	<label>좌석배치도</label>
	<div class="seatmap"
		style="width: 200px; height: 200px; background-color: white;">
	</div>	
	<div>
		<label>제목</label> 
		<input type="text" name='ttr_title' value="${musicalVO.ttr_title}" readonly="readonly">
	</div>
	
	
	<div>
		<label>알림</label> 
		<input type="text" name="ttr_alert" value="${musicalVO.ttr_alert}" readonly="readonly">
	</div>
	
	<div>
		<label>내용</label>
		<textarea name="ttr_content" rows="30" readonly="readonly">
			${musicalVO.ttr_content}
		</textarea>
	</div>
	<div>
		<label>시간정보</label> 
		<input type="text" name="ttr_time" value="${musicalVO.ttr_time}" readonly="readonly">
	</div>	
	<div>
		<label>장소</label> 
		<input type="text" name="ttr_place" value="${musicalVO.ttr_place}" readonly="readonly">
	</div>
	<div>
		<label>시작일</label>
		<input type="text" name="ttr_sdate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${musicalVO.ttr_sdate}" />" readonly="readonly">~
	</div>
	
	<div>
		<label>종료일</label>
		<input type="text" name="ttr_edate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${musicalVO.ttr_edate}" />" readonly="readonly">	
	</div>
	<div>
		<label>좌석정보</label>
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
	
</div>
	<div>
		<button type="submit">제출</button>
	</div>
	
</form>
	<div class="popup back" style="display: none;"></div>
	<div id="popup_front" class='popup front' style="display: none;">
		<img id="popup_img" />
	</div>

<a href="/com/res/resList"><button>목록</button></a>
	

<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="/com/resources/js/upload.js"></script>
<script>
$(document).ready(function(){
	var seats=null;
	var str="";
	var cnt=null;
	<c:forEach var="seat" items="${musicalVO.seat_grd}" varStatus="status">
		cnt='${musicalVO.seat_no[status.count-1]}'-'${seat_cnt[status.count-1]}'
		str+="<tr seat_no='${musicalVO.seat_no[status.count-1]}' seat_id='${musicalVO.seat_id[status.count-1]}'>"+
		"<td id='seat_info'><input type='text' name='seat_grd' value='${musicalVO.seat_grd[status.count-1]}' readonly></td>"+
		"<td><input type='int' name='seat_no' value='"+cnt+"' readonly></td>"+
		"<td><input type='int' name='seat_pri' value='${musicalVO.seat_pri[status.count-1]}' readonly></td>"+
		"<td><input type='date' name='seat_date' value='<fmt:formatDate pattern='yyyy-MM-dd' value='${musicalVO.seat_date[status.count-1]}' />' readonly></td>"+
		"<td><input type='time' name='seat_time' value='<fmt:formatDate pattern='HH:mm' value='${musicalVO.seat_time[status.count-1]}' />' readonly></td>"+
		"<td><input type='button' class='seat_btn' value='좌석보기'></td>"+
	"</tr>"
	</c:forEach>
	$('#seat_table').append(str);
		var ttr_no="${musicalVO.ttr_no}"
		var thumb_name="${musicalVO.thumb_name}"
		var thumb="<div class='min'><img src='"+getThumb(thumb_name)+"'><ori data_src='"+getOri(thumb_name)+"'></div>"
		var seatmap_name="${musicalVO.seatmap_name}"
			var seatmap="<div class='min'><img src='"+getThumb(seatmap_name)+"'><ori data_src='"+getOri(seatmap_name)+"'></div>"
		$(".thumb").append(thumb);
		$(".seatmap").append(seatmap);
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
			$('#seat_table').on('click','tr td .seat_btn',function(event){
				var str="<tr><td colspan='6'><div style='width:500px;'>";
				var seat_no=$(this).parent('td').parent('tr').attr('seat_no');
				seat_no++
				var seat_id=$(this).parent('td').parent('tr').attr('seat_id');
				for(var i=1;i<seat_no;i++){
					str+="<label>"+i+"<input name='rescheck' value='"+seat_id+"_"+i+"' type='checkbox'></label>";
				}
				str+="<input seat_no="+seat_no+" seat_id="+seat_id+" class='refresh' type='button' value='새로고침'></div></td></tr>";
				$(this).parent('td').parent('tr').after(str);
				$('.refresh').trigger('click');
			})
			var reserved = [];
			$('#seat_table').on('click','tr td div .refresh',function(event){
				event.preventDefault();
				$(this).parent('div').children('label').children("input[value='"+reserved[i]+"']").prop('checked', false);
				$(this).parent('div').children('label').children("input[value='"+reserved[i]+"']").prop('disabled', false);
				var seat_id=$(this).attr('seat_id');
				$.getJSON("/mboard/reserve/"+seat_id,function(res){
					for(var i=0;i<res.length;i++){
						reserved[i]=seat_id+"_"+res[i];
					}
				})
				for(var i=0;i<reserved.length;i++){
					console.log('input[name='+reserved[i]+']');
					$(this).parent('div').children('label').children("input[value='"+reserved[i]+"']").prop('checked', true);
					$(this).parent('div').children('label').children("input[value='"+reserved[i]+"']").prop('disabled', true);
				}
				})
			})
				
</script>
</body>
</html> --%>