<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<%@include file="header.jsp"%>
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
<h1>게시글 목록</h1>
<div>
	<select id="searchtype" name="search">
		<option value="n"<c:out value="${cri.search == null?'selected':''}"/>>---</option>
		<option value="no"<c:out value="${cri.search eq 'no'?'selected':''}"/>>글번호</option>
		<option value="id"<c:out value="${cri.search eq 'id'?'selected':''}"/>>회사아이디</option>
		<option value="cat"<c:out value="${cri.search eq 'cat'?'selected':''}"/>>카테고리</option>
		<option value="title"<c:out value="${cri.search eq 'title'?'selected':''}"/>>제목</option>
		<option value="date"<c:out value="${cri.search eq 'date'?'selected':''}"/>>작성일</option>
		<option value="sdate"<c:out value="${cri.search eq 'sdate'?'selected':''}"/>>시작일</option>
		<option value="edate"<c:out value="${cri.search eq 'edate'?'selected':''}"/>>종료일</option>
		<option value="place"<c:out value="${cri.search eq 'place'?'selected':''}"/>>장소</option>
		<option value="time"<c:out value="${cri.search eq 'time'?'selected':''}"/>>시간정보</option>
		<option value="alert"<c:out value="${cri.search eq 'alert'?'selected':''}"/>>알림사항</option>
		<option value="content"<c:out value="${cri.search eq 'content'?'selected':''}"/>>세부내용</option>
	</select>
	<input id="key" type="text" id="keyword">
	<input id="search" type="button" value="검색">
	<select id="pagenum">
		<option value="10"<c:out value="${cri.search eq '10'?'selected':''}"/>>10</option>
		<option value="30"<c:out value="${cri.search eq '30'?'selected':''}"/>>30</option>
		<option value="50"<c:out value="${cri.search eq '50'?'selected':''}"/>>50</option>
		<option value="999999999999999999999999"<c:out value="${cri.search eq '--'?'selected':''}"/>>--</option>
	</select>
</div>
<table border="1px" id="list">
	<tr>
		<th><label id="ttr_no">글번호</label></th>
		<th><label id="com_id">회사아이디</label></th>
		<th><label id="ttr_cat">카테고리</label></th>
		<th><label id="ttr_title">제목</label></th>
		<th><label id="ttr_date">작성일</label></th>
		<th><label id="ttr_sdate">시작일</label></th>
		<th><label id="ttr_edate">종료일</label></th>
	</tr>
</table>
<div class="popup back" style="display: none;"></div>
	<div id="popup_front" class='popup front' style="display: none;">
		<form id="updateform">
		</form>
	</div>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
var search;
var keyword;
var ord;
var orders=["no_asc","id_asc","cat_asc","title_asc","date_asc","sdate_asc","edate_asc","place_asc","time_asc","alert_asc","content_asc"]
var page=1
var pagenum=10
var pk;
function date(as){
	a=new Date(as)
	console.log(a)
	return a.getFullYear()+"-"+a.getMonth()+"-"+a.getDate()+" "+a.getHours()+":"+a.getMinutes()
}
Date.prototype.toDateInputValue = (function() {
    var local = new Date(this);
    local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
    return local.toJSON().slice(0,10);
});
function makelist(){
		$.getJSON("/aboard/board/"+search+"/"+keyword+"/"+ord+"/"+page+"/"+pagenum,function(data){
				var str="<tr>"+
					"<th><label id='ttr_no'>글번호</label></th>"+
					"<th><label id='com_id'>회사아이디</label></th>"+
					"<th><label id='ttr_cat'>카테고리</label></th>"+
					"<th><label id='ttr_title'>제목</label></th>"+
					"<th><label id='ttr_date'>작성일</label></th>"+
					"<th><label id='ttr_sdate'>시작일</label></th>"+
					"<th><label id='ttr_edate'>종료일</label></th>"+
				"</tr>";
				var list=data.list
				for(i in list){
					str+="<tr><td id='pk'>"+list[i].ttr_no+"</td>"+
					"<td>"+list[i].com_id+"</td>"+
					"<td>"+list[i].ttr_cat+"</td>"+
					"<td>"+list[i].ttr_title+"</td>"+
					"<td>"+date(list[i].ttr_date)+"</td>"+
					"<td>"+date(list[i].ttr_sdate)+"</td>"+
					"<td>"+date(list[i].ttr_edate)+"</td></tr>"
				}
				str+="<tr><td id='pagearea' colspan='7'></td></tr>"
				$('#list').html(str);
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
}
$(document).ready(function(){
	$('#list').on('click','tr th',function(index){
		if(orders[$(this).index()].indexOf('asc')>0){
			var a=orders[$(this).index()].replace('asc','desc')
			orders[$(this).index()]=a
			ord=a
		}else if(orders[$(this).index()].indexOf('desc')>0){
			var a=orders[$(this).index()].replace('desc','asc')
			orders[$(this).index()]=a
			ord=a
		}
		makelist()
	})
	$('#list').on('click','tr td li .reppage',function(index){
		page=$(this).text();
		makelist()
	})
	$('#search').on('click',function(event){
		search=$("#searchtype option:selected").val()
		keyword=$('#key').val()
		makelist();
	})
	$('#pagenum').change(function(){
		pagenum=$(this).children("option:selected").val();
		makelist();
	})
	makelist()
	$('#list').on('click','tr',function(event){
		pk=$(this).children('#pk').text()
		$.getJSON("/aboard/board/"+pk,function(data){
			data=data.res
			var str=""
			str+="<table><tr><td><input type='text' value="+data.ttr_no+" name='ttr_no' readonly></th></tr>"+
			"<tr><td><input type='text' value="+data.com_id+" name='com_id'></th></tr>"+
			"<tr><td><input type='text' value="+data.ttr_cat+" name='ttr_cat'></th></tr>"+
			"<tr><td><input type='text' value="+data.ttr_title+" name='ttr_title'></th></tr>"+
			"<tr><td><input type='date' value="+new Date(data.ttr_date).toDateInputValue()+" name='ttr_date'></th></tr>"+
			"<tr><td><input type='date' value="+new Date(data.ttr_sdate).toDateInputValue()+" name='ttr_sdate'></th></tr>"+
			"<tr><td><input type='date' value="+new Date(data.ttr_edate).toDateInputValue()+" name='ttr_edate'></th></tr>"+
			"<tr><td><input type='text' value="+data.ttr_place+" name='ttr_place'></th></tr>"+
			"<tr><td><input type='text' value="+data.ttr_time+" name='ttr_time'></th></tr>"+
			"<tr><td><input type='text' value="+data.ttr_alert+" name='ttr_alert'></th></tr>"+
			"<tr><td><input type='text' value="+data.ttr_content+" name='ttr_content'></th></tr>"+
			"<tr><td><input type='submit' id='update' value='수정'><input type='button' id='delete' value='삭제'></th></tr></table>";
			
			$('#updateform').html(str);
			$('.popup').show();
		})
	});

	
	$('.back').on('click',function(){
		$('.popup').hide();
	});
	$('#updateform').submit(function(event){
		event.preventDefault();
		that.get(0).submit();
	});
	$('#updateform').on('click','table tr td #delete',function(){
		$.getJSON("/aboard/delete/board/"+pk,function(data){
			makelist();
			})
		})
	});


</script>
</body>
</html>