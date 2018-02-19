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
<a href="/cboard/gal"><button>목록</button></a>
<c:if test="${boardVO.com_id eq loginUser.com_id }">
<a href="/cboard/gal/galupdate?ttr_no=${boardVO.ttr_no }"><button>수정</button></a>
<a href="/cboard/gal/delete?ttr_no=${boardVO.ttr_no }"><button>삭제</button></a>
</c:if>
<label>평점 :</label><label id="scoreavg"></label>
	<form id="write" method="post">
		<input type="hidden" name="ttr_cat" value="${boardVO.ttr_cat}">
		<input type="hidden" name="com_id" value="${boardVO.com_id}">
		<label>썸네일</label>
		<div class="thumb"
			style="width: 200px; height: 200px; background-color: blue;"></div>
		<label>좌석배치도</label>
		<div class="seatmap"
			style="width: 200px; height: 200px; background-color: blue;"></div>
		<div>
			<label>제목</label> <input type="text" name="ttr_title"
				value="${boardVO.ttr_title}" readonly>
		</div>
		<div>
			<label>시작일</label>
			<input type="date" value="<fmt:formatDate pattern='yyyy-MM-dd'
				value='${boardVO.ttr_sdate}' />" readonly>
		</div>
		<div>
			<label>종료일</label>
			<input type="date" value="<fmt:formatDate pattern='yyyy-MM-dd'
				value='${boardVO.ttr_edate}' />" readonly>
		</div>
		<div>
			<label>장소</label> <input type="text" name="ttr_place"
				value="${boardVO.ttr_place}" readonly>
		</div>
		<div>
			<label>시간</label> <input type="text" name="ttr_time"
				value="${boardVO.ttr_time}" readonly>
		</div>
		<div>
			<label>알림</label> <input type="text" name="ttr_alert"
				value="${boardVO.ttr_alert}" readonly>
		</div>
		<div>
			<label>세부내용</label> <input type="text" name="ttr_content"
				value="${boardVO.ttr_content}" readonly>
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
		<label>사진자료</label>
		<div class="file"
			style="width: 800px;"></div>
	</form>
	
	<div id="replyarea">
		<table id="replylist">
		</table>
	</div>
	
	<div class="popup back" style="display: none;"></div>
	<div id="popup_front" class='popup front' style="display: none;">
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
			"<td id='seat_info'><input type='text' name='seat_grd' value='${boardVO.seat_grd[status.count-1]}' readonly></td>"+
			"<td><input type='int' name='seat_no' value='${boardVO.seat_no[status.count-1]}' readonly></td>"+
			"<td><input type='int' name='seat_pri' value='${boardVO.seat_pri[status.count-1]}' readonly></td>"+
			"<td><input type='date' name='seat_date' value='<fmt:formatDate pattern='yyyy-MM-dd' value='${boardVO.seat_date[status.count-1]}' />' readonly></td>"+
			"<td><input type='time' name='seat_time' value='<fmt:formatDate pattern='HH:mm' value='${boardVO.seat_time[status.count-1]}' />' readonly></td>"+
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
			$.getJSON("/cboard/getFiles/"+ttr_no,function(list){
				$(list).each(function(){
					var file;
					file="<img src='"+getOri(this)+"'>";
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
			$.ajax({
				type:'get',
				url:"/mboard/scoreavg/${boardVO.ttr_no}",
				success:function(data){
					$('#scoreavg').text(data)
				}
			});
		})
</script>
<script>
	var replyPage=1;
	var upttr_score;
	var uptr_rno;
	var uptr_content;
	function date(as){
		a=new Date(as)
		return a.getFullYear()+"-"+a.getMonth()+"-"+a.getDate()+" "+a.getHours()+":"+a.getMinutes()
	}
	function getPage(pageInfo){
		$.getJSON(pageInfo,function(data){
			console.log(data)
			var str="<tr><td>작성자</td><td>평점</td><td>내용</td><td>작성일</td></tr>";
			var rep=data.list;
			for(i in rep){
				var tr_date=new Date(rep[i].tr_date);
			str+="<tr><td class='mem_id'>"+rep[i].mem_id+"</td><td class='ttr_score'>"+rep[i].ttr_score+"</td><td class='ttr_content' style='width: 600px;'>"+rep[i].tr_content+"</td><td class='tr_date'>"+date(rep[i].tr_date)+"</td>"+
			"<td rno="+rep[i].tr_rno+">";
			str+="</td></tr>"
			}
			str+="<tr><td id='pagearea' colspan='4'></td></tr>"
			$('#replylist').html(str);
			
			var pageMaker=data.pageMaker;
			var str="";
			if(pageMaker.prev){
				str+="<li><button>"+(pageMaker.startPage-1)
					  +"'> << </button></li>";
			};
			for(var i=pageMaker.startPage,len=pageMaker.endPage;i<=len;i++){
				var strClass=pageMaker.cri.page==i?'class=active':'';
				str+="<li "+strClass+"><button class='reppage'>"+i+"</button></li>";
			};
			if(pageMaker.next){
				str+="<li><button>"+(pageMaker.endPage+1)
				  +"'> >> </button></li>";
			};
			$('#pagearea').append(str);
		});
		
	};
	$('#replylist').on('click','tr td li .reppage',function(index){
		reppage=$(this).text();
		getPage("/replies/"+ttr_no+"/"+reppage)
	})
	getPage("/replies/"+ttr_no+"/1");
</script>
</body>
</html>