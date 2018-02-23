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
li{
list-style:none;
}
.popup_img{
	margin:auto;
}
</style>
<div class="container">

	<div class='box'>
		<div class="box-header">
			<h1 style="font-size: 50px" class="box-title"><strong>${boardVO.ttr_title}</strong></h1>
			<div>
				<label>평점 : </label><label id="scoreavg"></label>
			</div>
		</div>
		<div class="box-body text-right">
			<a href="/cboard/gal"><button class="btn btn-primary">목록</button></a>
			<c:if test="${boardVO.com_id eq loginUser.com_id }">
				<a href="/cboard/gal/galupdate?ttr_no=${boardVO.ttr_no }"><button class="btn btn-primary">수정</button></a>
				<a href="/cboard/gal/delete?ttr_no=${boardVO.ttr_no }"><button class="btn btn-primary">삭제</button></a>						
			</c:if>
		</div>
	</div>
	
	<div class="box">
		<form id="write" method="post">
			<input type="hidden" name="ttr_cat" value="${boardVO.ttr_cat}">
			<input type="hidden" name="com_id" value="${boardVO.com_id}">
			<div class="row">
				<div class="col-1"></div>
				<div class="col-3">
					<div class="alert alert-info">
					<label>썸네일</label>
					<div class="thumb"></div>
					</div>
				</div>
				<div class="col-7">
					<div class="alert alert-info">
						<label>예매시작시간</label>
						<label><fmt:formatDate pattern='yyyy-MM-dd' value='${boardVO.ttr_sdate}' /></label>
						&nbsp;&nbsp;&nbsp;
						<label><fmt:formatDate pattern='HH:mm' value='${boardVO.ttr_edate}' /></label>
					</div>
					<div class="alert alert-info">
						<label>장소</label>
						<label>${boardVO.ttr_place}</label>
					</div>
				</div>
				<div class="col-1"></div>
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
				<div class="col-10">
					<div class="alert alert-info">
						<label>좌석배치도</label>
						<div class="seatmap"></div>
					</div>
				</div>
				<div class="col-1"></div>
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
								</tr>
							</table>
						</div>
					</div>
				</div>
				<div class="col-1"></div>
			</div>
			<div class="row">
				<div class="col-1"></div>
				<div class="col-10">
					<div class="alert alert-info">
						<label>사진자료</label>
						<div class="file"></div>
					</div>
				</div>
				<div class="col-1"></div>
			</div>
		</form>
	</div>
	
	<div id="replyarea" class="box">
		<div class="row">
			<div class="col-1"></div>
			<div class="col-10">
				<div class="alert alert-info">
					<div>
						<label>평점</label>
						<table id="replylist" class="table table-striped">
						</table>
					</div>
				</div>
			</div>
			<div class="col-1"></div>
		</div>
		<div class="row">
			<div class="col-1"></div>
			<div class="col-10">
				<div class="alert alert-info">
					<div>
						<label>평점 작성</label>
						<table id="replywrite" class="table table-striped">
							<tr>
								<td>평점</td>
								<td class="">
									<div class="btn-group scorein">
										<button class="1 btn btn-primary" value="1">★</button>
										<button class="2 btn btn-primary" value="2">★</button>
										<button class="3 btn btn-primary" value="3">★</button>
										<button class="4 btn btn-primary" value="4">★</button>
										<button class="5 btn btn-primary" value="5">★</button>
									</div>
								</td>
								<td>
									<label class="scoreshow">&nbsp;&nbsp;</label>
								</td>
							</tr>
							<tr>
								<td colspan="3"><textarea id="reply_text" style="width: 600px; height: 200px;"></textarea></td>
							</tr>
						</table>
						<input id="rep_sub" type="button" class="btn btn-primary" value="작성">
					</div>
				</div>
			</div>
			<div class="col-1"></div>
		</div>
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
			"<td id='seat_info'>${boardVO.seat_grd[status.count-1]}</td>"+
			"<td>${boardVO.seat_no[status.count-1]}</td>"+
			"<td>${boardVO.seat_pri[status.count-1]}</td>"+
			"<td><fmt:formatDate pattern='yyyy-MM-dd' value='${boardVO.seat_date[status.count-1]}' /></td>"+
			"<td><fmt:formatDate pattern='HH:mm' value='${boardVO.seat_time[status.count-1]}' /></td>"+
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
					
					$('.popup').show('fast');
					imgTag.addClass('show');
				}
			});
			
			$('#popup_img').on('click',function(){
				$('.popup').hide('fast');
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
			str+="<tr><td colspan='4'><div id='pagearea' class='btn-group'></div></td></tr>"
			$('#replylist').html(str);
			
			var pageMaker=data.pageMaker;
			var str="";
			if(pageMaker.prev){
				str+="<li><button class='btn btn-primary'>"+(pageMaker.startPage-1)
					  +"'> << </button></li>";
			};
			for(var i=pageMaker.startPage,len=pageMaker.endPage;i<=len;i++){
				var strClass=pageMaker.cri.page==i?'class=active':'';
				str+="<li "+strClass+"><button class='reppage btn btn-primary'>"+i+"</button></li>";
			};
			if(pageMaker.next){
				str+="<li><button class='btn btn-primary'>"+(pageMaker.endPage+1)
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