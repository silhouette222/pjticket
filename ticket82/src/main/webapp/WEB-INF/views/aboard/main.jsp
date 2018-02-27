<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<div class="box">
		<div class="row">
			<div class="col-3">
				<div id="main1" class="file2 alert alert-info">
					<label>메인</label>
					<c:forEach items="${list}" var="mainVO">
						<c:choose>
						    <c:when test="${mainVO.main_id eq 'main1' }">
						    	<input id="ttr_no" type="text" value="${mainVO.ttr_no }">
    						</c:when>
						</c:choose>
					</c:forEach>
					<button>입력</button>
					<c:forEach items="${list}" var="mainVO">
						<c:choose>
						    <c:when test="${mainVO.main_id eq 'main1' }">
						    	<div data-src='${mainVO.main_path }'><img src='/displayFile?fileName=${mainVO.main_path }'/><small data-src='${mainVO.main_path }'>
						    	<button type='button'>X</button></small></div>
    						</c:when>
						</c:choose>
					</c:forEach>
				</div>
			</div>
			<div class="col-3">
				<div id="main2" class="file2 alert alert-info">
					<label>메인</label>
					<c:forEach items="${list}" var="mainVO">
						<c:choose>
						    <c:when test="${mainVO.main_id eq 'main2' }">
						    	<input id="ttr_no" type="text" value="${mainVO.ttr_no }">
    						</c:when>
						</c:choose>
					</c:forEach>
					<button>입력</button>
					<c:forEach items="${list}" var="mainVO">
						<c:choose>
						    <c:when test="${mainVO.main_id eq 'main2' }">
						    	<div data-src='${mainVO.main_path }'><img src='/displayFile?fileName=${mainVO.main_path }'/><small data-src='${mainVO.main_path }'>
						    	<button type='button'>X</button></small></div>
    						</c:when>
						</c:choose>
					</c:forEach>
				</div>
			</div>
			<div class="col-3">
				<div id="main3" class="file2 alert alert-info">
					<label>메인</label>
					<c:forEach items="${list}" var="mainVO">
						<c:choose>
						    <c:when test="${mainVO.main_id eq 'main3' }">
						    	<input id="ttr_no" type="text" value="${mainVO.ttr_no }">
    						</c:when>
						</c:choose>
					</c:forEach>
					<button>입력</button>
					<c:forEach items="${list}" var="mainVO">
						<c:choose>
						    <c:when test="${mainVO.main_id eq 'main3' }">
						    	<div data-src='${mainVO.main_path }'><img src='/displayFile?fileName=${mainVO.main_path }'/><small data-src='${mainVO.main_path }'>
						    	<button type='button'>X</button></small></div>
    						</c:when>
						</c:choose>
					</c:forEach>
				</div>
			</div>
			<div class="col-3">
				<div id="main4" class="file2 alert alert-info">
					<label>메인</label>
					<c:forEach items="${list}" var="mainVO">
						<c:choose>
						    <c:when test="${mainVO.main_id eq 'main4' }">
						    	<input id="ttr_no" type="text" value="${mainVO.ttr_no }">
    						</c:when>
						</c:choose>
					</c:forEach>
					<button>입력</button>
					<c:forEach items="${list}" var="mainVO">
						<c:choose>
						    <c:when test="${mainVO.main_id eq 'main4' }">
						    	<div data-src='${mainVO.main_path }'><img src='/displayFile?fileName=${mainVO.main_path }'/><small data-src='${mainVO.main_path }'>
						    	<button type='button'>X</button></small></div>
    						</c:when>
						</c:choose>
					</c:forEach>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-3">
				<div id="musical1" class="main2 alert alert-info">
					<label>뮤지컬</label>
					<c:forEach items="${list}" var="mainVO">
						<c:choose>
						    <c:when test="${mainVO.main_id eq 'musical1' }">
						    	<input id="ttr_no" type="text" value="${mainVO.ttr_no }">
    						</c:when>
						</c:choose>
					</c:forEach>
					<button>입력</button>
				</div>
			</div>
			<div class="col-3">
				<div id="musical2" class="main2 alert alert-info">
					<label>뮤지컬</label>
					<c:forEach items="${list}" var="mainVO">
						<c:choose>
						    <c:when test="${mainVO.main_id eq 'musical2' }">
						    	<input id="ttr_no" type="text" value="${mainVO.ttr_no }">
    						</c:when>
						</c:choose>
					</c:forEach>
					<button>입력</button>
				</div>
			</div>
			<div class="col-3">
				<div id="musical3" class="main2 alert alert-info">
					<label>뮤지컬</label>
					<c:forEach items="${list}" var="mainVO">
						<c:choose>
						    <c:when test="${mainVO.main_id eq 'musical3' }">
						    	<input id="ttr_no" type="text" value="${mainVO.ttr_no }">
    						</c:when>
						</c:choose>
					</c:forEach>
					<button>입력</button>
				</div>
			</div>
			<div class="col-3">
				<div id="musical4" class="main2 alert alert-info">
					<label>뮤지컬</label>
					<c:forEach items="${list}" var="mainVO">
						<c:choose>
						    <c:when test="${mainVO.main_id eq 'musical4' }">
						    	<input id="ttr_no" type="text" value="${mainVO.ttr_no }">
    						</c:when>
						</c:choose>
					</c:forEach>
					<button>입력</button>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-3">
				<div id="concert1" class="main2 alert alert-info">
					<label>콘서트</label>
					<c:forEach items="${list}" var="mainVO">
						<c:choose>
						    <c:when test="${mainVO.main_id eq 'concert1' }">
						    	<input id="ttr_no" type="text" value="${mainVO.ttr_no }">
    						</c:when>
						</c:choose>
					</c:forEach>
					<button>입력</button>
				</div>
			</div>
			<div class="col-3">
				<div id="concert2" class="main2 alert alert-info">
					<label>콘서트</label>
					<c:forEach items="${list}" var="mainVO">
						<c:choose>
						    <c:when test="${mainVO.main_id eq 'concert2' }">
						    	<input id="ttr_no" type="text" value="${mainVO.ttr_no }">
    						</c:when>
						</c:choose>
					</c:forEach>
					<button>입력</button>
				</div>
			</div>
			<div class="col-3">
				<div id="concert3" class="main2 alert alert-info">
					<label>콘서트</label>
					<c:forEach items="${list}" var="mainVO">
						<c:choose>
						    <c:when test="${mainVO.main_id eq 'concert3' }">
						    	<input id="ttr_no" type="text" value="${mainVO.ttr_no }">
    						</c:when>
						</c:choose>
					</c:forEach>
					<button>입력</button>
				</div>
			</div>
			<div class="col-3">
				<div id="concert4" class="main2 alert alert-info">
					<label>콘서트</label>
					<c:forEach items="${list}" var="mainVO">
						<c:choose>
						    <c:when test="${mainVO.main_id eq 'concert4' }">
						    	<input id="ttr_no" type="text" value="${mainVO.ttr_no }">
    						</c:when>
						</c:choose>
					</c:forEach>
					<button>입력</button>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-3">
				<div id="play1" class="main2 alert alert-info">
					<label>연극</label>
					<c:forEach items="${list}" var="mainVO">
						<c:choose>
						    <c:when test="${mainVO.main_id eq 'play1' }">
						    	<input id="ttr_no" type="text" value="${mainVO.ttr_no }">
    						</c:when>
						</c:choose>
					</c:forEach>
					<button>입력</button>
				</div>
			</div>
			<div class="col-3">
				<div id="play2" class="main2 alert alert-info">
					<label>연극</label>
					<c:forEach items="${list}" var="mainVO">
						<c:choose>
						    <c:when test="${mainVO.main_id eq 'play2' }">
						    	<input id="ttr_no" type="text" value="${mainVO.ttr_no }">
    						</c:when>
						</c:choose>
					</c:forEach>
					<button>입력</button>
				</div>
			</div>
			<div class="col-3">
				<div id="play3" class="main2 alert alert-info">
					<label>연극</label>
					<c:forEach items="${list}" var="mainVO">
						<c:choose>
						    <c:when test="${mainVO.main_id eq 'play3' }">
						    	<input id="ttr_no" type="text" value="${mainVO.ttr_no }">
    						</c:when>
						</c:choose>
					</c:forEach>
					<button>입력</button>
				</div>
			</div>
			<div class="col-3">
				<div id="play4" class="main2 alert alert-info">
					<label>연극</label>
					<c:forEach items="${list}" var="mainVO">
						<c:choose>
						    <c:when test="${mainVO.main_id eq 'play4' }">
						    	<input id="ttr_no" type="text" value="${mainVO.ttr_no }">
    						</c:when>
						</c:choose>
					</c:forEach>
					<button>입력</button>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-3">
				<div id="classic1" class="main2 alert alert-info">
					<label>클래식</label>
					<c:forEach items="${list}" var="mainVO">
						<c:choose>
						    <c:when test="${mainVO.main_id eq 'classic1' }">
						    	<input id="ttr_no" type="text" value="${mainVO.ttr_no }">
    						</c:when>
						</c:choose>
					</c:forEach>
					<button>입력</button>
				</div>
			</div>
			<div class="col-3">
				<div id="classic2" class="main2 alert alert-info">
					<label>클래식</label>
					<c:forEach items="${list}" var="mainVO">
						<c:choose>
						    <c:when test="${mainVO.main_id eq 'classic2' }">
						    	<input id="ttr_no" type="text" value="${mainVO.ttr_no }">
    						</c:when>
						</c:choose>
					</c:forEach>
					<button>입력</button>
				</div>
			</div>
			<div class="col-3">
				<div id="classic3" class="main2 alert alert-info">
					<label>클래식</label>
					<c:forEach items="${list}" var="mainVO">
						<c:choose>
						    <c:when test="${mainVO.main_id eq 'classic3' }">
						    	<input id="ttr_no" type="text" value="${mainVO.ttr_no }">
    						</c:when>
						</c:choose>
					</c:forEach>
					<button>입력</button>
				</div>
			</div>
			<div class="col-3">
				<div id="classic4" class="main2 alert alert-info">
					<label>클래식</label>
					<c:forEach items="${list}" var="mainVO">
						<c:choose>
						    <c:when test="${mainVO.main_id eq 'classic4' }">
						    	<input id="ttr_no" type="text" value="${mainVO.ttr_no }">
    						</c:when>
						</c:choose>
					</c:forEach>
					<button>입력</button>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-3">
				<div id="gal1" class="main2 alert alert-info">
					<label>갤러리</label>
					<c:forEach items="${list}" var="mainVO">
						<c:choose>
						    <c:when test="${mainVO.main_id eq 'gal1' }">
						    	<input id="ttr_no" type="text" value="${mainVO.ttr_no }">
    						</c:when>
						</c:choose>
					</c:forEach>
					<button>입력</button>
				</div>
			</div>
			<div class="col-3">
				<div id="gal2" class="main2 alert alert-info">
					<label>갤러리</label>
					<c:forEach items="${list}" var="mainVO">
						<c:choose>
						    <c:when test="${mainVO.main_id eq 'gal2' }">
						    	<input id="ttr_no" type="text" value="${mainVO.ttr_no }">
    						</c:when>
						</c:choose>
					</c:forEach>
					<button>입력</button>
				</div>
			</div>
			<div class="col-3">
				<div id="gal3" class="main2 alert alert-info">
					<label>갤러리</label>
					<c:forEach items="${list}" var="mainVO">
						<c:choose>
						    <c:when test="${mainVO.main_id eq 'gal3' }">
						    	<input id="ttr_no" type="text" value="${mainVO.ttr_no }">
    						</c:when>
						</c:choose>
					</c:forEach>
					<button>입력</button>
				</div>
			</div>
			<div class="col-3">
				<div id="gal4" class="main2 alert alert-info">
					<label>갤러리</label>
					<c:forEach items="${list}" var="mainVO">
						<c:choose>
						    <c:when test="${mainVO.main_id eq 'gal4' }">
						    	<input id="ttr_no" type="text" value="${mainVO.ttr_no }">
    						</c:when>
						</c:choose>
					</c:forEach>
					<button>입력</button>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-3">
				<div id="etc1" class="main2 alert alert-info">
					<label>기타</label>
					<c:forEach items="${list}" var="mainVO">
						<c:choose>
						    <c:when test="${mainVO.main_id eq 'etc1' }">
						    	<input id="ttr_no" type="text" value="${mainVO.ttr_no }">
    						</c:when>
						</c:choose>
					</c:forEach>
					<button>입력</button>
				</div>
			</div>
			<div class="col-3">
				<div id="etc2" class="main2 alert alert-info">
					<label>기타</label>
					<c:forEach items="${list}" var="mainVO">
						<c:choose>
						    <c:when test="${mainVO.main_id eq 'etc2' }">
						    	<input id="ttr_no" type="text" value="${mainVO.ttr_no }">
    						</c:when>
						</c:choose>
					</c:forEach>
					<button>입력</button>
				</div>
			</div>
			<div class="col-3">
				<div id="etc3" class="main2 alert alert-info">
					<label>기타</label>
					<c:forEach items="${list}" var="mainVO">
						<c:choose>
						    <c:when test="${mainVO.main_id eq 'etc3' }">
						    	<input id="ttr_no" type="text" value="${mainVO.ttr_no }">
    						</c:when>
						</c:choose>
					</c:forEach>
					<button>입력</button>
				</div>
			</div>
			<div class="col-3">
				<div id="etc4" class="main2 alert alert-info">
					<label>기타</label>
					<c:forEach items="${list}" var="mainVO">
						<c:choose>
						    <c:when test="${mainVO.main_id eq 'etc4' }">
						    	<input id="ttr_no" type="text" value="${mainVO.ttr_no }">
    						</c:when>
						</c:choose>
					</c:forEach>
					<button>입력</button>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<div id="new">
					<input type="text" id="new_id">
					<input type="text" id="new_no">
					<button>asd</button>
				</div>
			</div>
		</div>
	</div>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="/resources/js/upload.js"></script>
<script>
$(".file2").on("dragenter dragover", function(event) {
	event.preventDefault();
});
$(".file2").on(
		"drop",
		function(event) {
			var that=$(this)
			event.preventDefault();
			
				
			
			var files = event.originalEvent.dataTransfer.files;
			var file = files[0];

			var formData = new FormData();
			formData.append("file", file);

			$.ajax({
				url : '/upload',
				type : 'post',
				data : formData,
				contentType : false,
				processData : false,
				success : function(data) {

					var str = "";
					if (checkImageType(data)) {
						str = "<div data-src='" + data+"'><img src='/displayFile?fileName=" + data
								+ "'/><small data-src='" + data
								+ "'><button type='button'>X</button></small></div>"
					}

					that.append(str);
				}

			});
			
		});
$('.file2').on('click', 'small ', function(event) {
	event.preventDefault();
	var that = $(this);

	$.ajax({
		url : "/deleteFile",
		type : "post",
		dataType : "text",
		data : {
			fileName : $(this).attr("data-src")
		},
		success : function(result) {
			if (result == 'deleted') {
				that.parent("div").remove();
			}
		}
	});

});
$('.main2').on('click','button',function(){
	var ttr_no=$(this).parent().children('input').val();
	var main_id=$(this).parent().attr('id');
	$.getJSON("/aboard/main2/"+main_id+"/"+ttr_no)
})
$('.file2').on('click','button',function(){
	var ttr_no=$(this).parent().children('input[id=ttr_no]').val();
	var main_id=$(this).parent().attr('id');
	var main_path=$(this).parent().children('div').attr('data-src')
	$.getJSON("/aboard/main3/"+main_id+"/"+ttr_no+"?main_path="+main_path)
})
$('#new').on('click','button',function(){
	var id=$('#new_id').val();
	var no=$('#new_no').val();
	$.getJSON("/aboard/main2/"+id+"/"+no)
	
})
</script>
</body>
</html>