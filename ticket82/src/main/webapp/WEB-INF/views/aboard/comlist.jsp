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
li{
list-style:none;
}
.popup_img{
	margin:auto;
}
</style>
<div class="container">
	<div class='box'>
		<div class="box-header with-border">
			<h1 style="font-size: 30px" class="box-title"><strong>회사 목록</strong></h1>
		</div>
		<div class="box-body text-right">
			<select id="searchtype">
				<option value="n"<c:out value="${cri.search == null?'selected':''}"/>>---</option>
				<option value="no"<c:out value="${cri.search eq 'no'?'selected':''}"/>>사업자등록번호</option>
				<option value="id"<c:out value="${cri.search eq 'id'?'selected':''}"/>>회사아이디</option>
				<option value="name"<c:out value="${cri.search eq 'name'?'selected':''}"/>>이름</option>
				<option value="dname"<c:out value="${cri.search eq 'dname'?'selected':''}"/>>대표이름</option>
				<option value="mobile"<c:out value="${cri.search eq 'mobile'?'selected':''}"/>>전화번호</option>
				<option value="mail"<c:out value="${cri.search eq 'mail'?'selected':''}"/>>메일</option>
				<option value="addr"<c:out value="${cri.search eq 'addr'?'selected':''}"/>>주소</option>
				<option value="enabled"<c:out value="${cri.search eq 'enabled'?'selected':''}"/>>상태</option>
			</select>
			<input id="key" type="text" id="keyword">
			<input id="search" class="btn btn-primary" type="button" value="검색">
			<select id="pagenum">
				<option value="10"<c:out value="${cri.search eq '10'?'selected':''}"/>>10</option>
				<option value="30"<c:out value="${cri.search eq '30'?'selected':''}"/>>30</option>
				<option value="50"<c:out value="${cri.search eq '50'?'selected':''}"/>>50</option>
				<option value="999999999999999999999999"<c:out value="${cri.search eq '--'?'selected':''}"/>>--</option>
			</select>
		</div>
	</div>
	<div class="box">
		<table class="table table-striped" id="list">
			<tr>
				<th><label id="com_id">회사아이디</label></th>
				<th><label id="com_name">이름</label></th>
				<th><label id="com_dname">대표이름</label></th>
				<th><label id="com_mobile">전화번호</label></th>
				<th><label id="enabled">상태</label></th>
			</tr>
		</table>
	</div>
</div>
<div class="popup back" style="display: none;"></div>
	<div id="popup_front" class='popup front text-center' style="display: none;">
		<form id="updateform" method="post">
		</form>
	</div>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
var search;
var keyword;
var ord;
var orders=["no_asc","id_asc","name_asc","dname_asc","mobile_asc","mail_asc","addr_asc","enabled_asc"]
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
		$.getJSON("/aboard/com/"+search+"/"+keyword+"/"+ord+"/"+page+"/"+pagenum,function(data){
				var str="<tr>"+
				"<th><label id='com_id'>회사아이디</label></th>"+
				"<th><label id='com_name'>이름</label></th>"+
				"<th><label id='com_dname'>대표이름</label></th>"+
				"<th><label id='com_mobile'>전화번호</label></th>"+
				"<th><label id='enabled'>상태</label></th>"+
			"</tr>";
				var list=data.list
				for(i in list){
					str+="<tr>"+
					"<td id='pk'>"+list[i].com_id+"</td>"+
					"<td>"+list[i].com_name+"</td>"+
					"<td>"+list[i].com_dname+"</td>"+
					"<td>"+list[i].com_mobile+"</td>"+
					"<td>"+list[i].enabled+"</td>"+
					"</tr>"
				}
				str+="<tr><td id='pagearea' colspan='7'></td></tr>"
				$('#list').html(str);
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
					str+="<li><button>"+(pageMaker.endPage+1)
					  +"'> >> </button class='btn btn-primary'></li>";
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
		alert('asd')
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
		$.getJSON("/aboard/com/"+pk,function(data){
			data=data.res
			var str=""
			str+="<table class='table table-striped'><tr><td><label>사업자등록번호</label><input type='text' value="+data.com_no+" name='com_no' readonly></th></tr>"+
			"<tr><td><label>아이디</label><input type='text' value="+data.com_id+" name='com_id'></th></tr>"+
			"<tr><td><label>회사명</label><input type='text' value="+data.com_name+" name='com_name'></th></tr>"+
			"<tr><td><label>대표이름</label><input type='text' value="+data.com_dname+" name='com_dname'></th></tr>"+
			"<tr><td><label>전화번호</label><input type='text' value="+data.com_mobile+" name='com_mobile'></th></tr>"+
			"<tr><td><label>이메일</label><input type='text' value="+data.com_mail+" name='com_mail'></th></tr>"+
			"<tr><td><label>주소</label><input type='text' value="+data.com_addr+" name='com_addr'></th></tr>"+
			"<tr><td><label>상태</label><input type='text' value="+data.enabled+" name='enabled'></th></tr>"+
			"<tr><td><input type='submit' class='btn btn-primary' id='update' value='수정'>"+
			"<input type='button' id='delete' class='btn btn-primary' value='삭제'></th></tr></table>";
			
			$('#updateform').html(str);
			$('.popup').show();
		})
	});
	
	$('.back').on('click',function(){
		$('.popup').hide();
	});
	$('#updateform').on('click','table tr td #delete',function(){
		$.getJSON("/aboard/delete/com/"+pk)
		makelist();
	});
})
</script>
</body>
</html>