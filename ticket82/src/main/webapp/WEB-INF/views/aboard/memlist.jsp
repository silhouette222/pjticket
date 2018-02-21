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
			<h1 style="font-size: 30px" class="box-title"><strong>회원 목록</strong></h1>
		</div>
		
		<button class="btn btn-primary" id="mailformbutton">+</button>
		<div id="mailform1">
			<div id="mailform2">
				<form action="mail" method="post" >
					<div id="mailsend"></div>
					<label>보내는사람</label>
					<input id='from1' type='email' name="from"  placeholder="example@naver.com"/><br/>
					<label>제목 </label>
					<input id='title1' type='text' name='title'/><br/>
					<label>내용</label>
					<textarea id="content1" rows="25" cols="100" name='content' ></textarea><br/>
					<input type="submit" class="btn btn-primary"  value="보내기" />
				</form>
				<button class="btn btn-primary" id='mailall'>전체보내기</button>
			</div>
		</div>
		
		<div class="box-body text-right">
			<select id="searchtype">
				<option value="n"<c:out value="${cri.search == null?'selected':''}"/>>---</option>
				<option value="id"<c:out value="${cri.search eq 'id'?'selected':''}"/>>아이디</option>
				<option value="name"<c:out value="${cri.search eq 'name'?'selected':''}"/>>이름</option>
				<option value="gender"<c:out value="${cri.search eq 'gender'?'selected':''}"/>>성별</option>
				<option value="birth"<c:out value="${cri.search eq 'birth'?'selected':''}"/>>생일</option>
				<option value="mail"<c:out value="${cri.search eq 'mail'?'selected':''}"/>>메일</option>
				<option value="mobile"<c:out value="${cri.search eq 'mobile'?'selected':''}"/>>전화번호</option>
				<option value="addr"<c:out value="${cri.search eq 'addr'?'selected':''}"/>>주소</option>
				<option value="date"<c:out value="${cri.search eq 'date'?'selected':''}"/>>가입일</option>
				<option value="enabled"<c:out value="${cri.search eq 'enabled'?'selected':''}"/>>상태</option>
			</select>
			<input id="key" type="text" id="keyword">
			<input id="search" type="button" class="btn btn-primary" value="검색">
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
				<th><label id="mem_id">아이디</label></th>
				<th><label id="mem_name">이름</label></th>
				<th><label id="mem_gender">성별</label></th>
				<th><label id="mem_date">가입일</label></th>
				<th><label id="enabled">상태</label></th>
			</tr>
		</table>
	</div>
</div>
<div class="popup back" style="display: none;"></div>
	<div id="popup_front" class='popup front text-center' style="display: none;">
		<form id="updateform" method="post" >
		</form>
	</div>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
var search;
var keyword;
var ord;
var orders=["id_asc","name_asc","gender_asc","birth_asc","mail_asc","mobile_asc","addr_asc","date_asc","enabled_asc"]
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
		$.getJSON("/aboard/mem/"+search+"/"+keyword+"/"+ord+"/"+page+"/"+pagenum,function(data){
				var str="<tr>"+
				"<th><label id='mem_id'>아이디</label></th>"+
				"<th><label id='mem_name'>이름</label></th>"+
				"<th><label id='mem_gender'>성별</label></th>"+
				"<th><label id='mem_date'>가입일</label></th>"+
				"<th><label id='enavled'>상태</label></th>"+
				"<th><label>메일추가</label></th>"+
			"</tr>";
				var list=data.list
				for(i in list){
					str+="<tr><td id='pk' class='mas'>"+list[i].mem_id+"</td>"+
					"<td class='mas'>"+list[i].mem_name+"</td>"+
					"<td class='mas'>"+list[i].mem_gender+"</td>"+
					"<td class='mas'>"+date(list[i].mem_date)+"</td>"+
					"<td class='mas'>"+list[i].enabled+"</td>"+
					"<td class='mail'><button class='mailadd btn btn-primary' mail='"+list[i].mem_mail+"' mem_id='"+list[i].mem_id+"'>+</button></td>"+
					"</tr>";
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
					str+="<li><button class='btn btn-primary'>"+(pageMaker.endPage+1)
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
	$('#mailsend').on('click','div .mailremove',function(){
		event.preventDefault();
		$(this).parent('div').remove()
	})
	$('#mailall').on('click',function(){
		$.ajax({
			url : "/aboard/mailall",
			type : "post",
			dataType : 'json',
			data: {
	    		from:$('#from1').val(),
	    		title:$('#title').val(),
	    		content:$('textarea#content1').text()
    		}
		})
	})
	makelist()
	$('#list').on('click','tr .mas',function(event){
		that=$(this).parent()
		pk=that.children('#pk').text()
		$.getJSON("/aboard/mem/"+pk,function(data){
			data=data.res
			var str=""
			str+="<table class='table table-striped'><tr><td><input type='text' value="+data.mem_id+" name='mem_id' readonly></th></tr>"+
			"<tr><td><input type='text' value="+data.mem_name+" name='mem_name'></th></tr>"+
			"<tr><td><input type='text' value="+data.mem_gender+" name='mem_gender'></th></tr>"+
			"<tr><td><input type='date' value="+new Date(data.mem_birth).toDateInputValue()+" name='mem_birth'></th></tr>"+
			"<tr><td><input type='text' value="+data.mem_mail+" name='mem_mail'/></th></tr>"+
			"<tr><td><input type='text' value="+data.mem_mobile+" name='mem_mobile'></th></tr>"+
			"<tr><td><input type='text' value="+data.mem_addr+" name='mem_addr'></th></tr>"+
			"<tr><td><input type='date' value="+new Date(data.mem_date).toDateInputValue()+" name='mem_date'></th></tr>"+
			"<tr><td><input type='text' value="+data.enabled+" name='enabled'></th></tr>"+
			"<tr><td><input type='submit' id='update' class='btn btn-primary' value='수정'>"+
			"<input type='button' class='btn btn-primary' id='delete' value='삭제'></th></tr></table>";
			
			$('#updateform').html(str);
			$('.popup').show();
		})
	});
	
	$('.back').on('click',function(){
		$('.popup').hide();
	});
	$('#updateform').on('click','table tr td #delete',function(){
		$.getJSON("/aboard/delete/mem/"+pk)
		makelist();
	});
	$('#list').on('click','tr td .mailadd',function(){
		event.preventDefault();
		var str=''
		str+="<div class='user'><input type='hidden' name='to' value='"+$(this).attr('mail')+"'>"+$(this).attr('mem_id')+
		"<input type='button' class='mailremove btn btn-primary' value='-'></div>"
		$('#mailsend').append(str)
		$('.popup').hide();
		$('.popup').hide();$('.popup').hide();
	})
	var detach=$('#mailform2')
	$('#mailformbutton').on('click',function(){
		if($(this).text()=='+'){
			$('#mailform1').append(detach)
			$(this).text('-');
		}else if($(this).text()=='-'){
			$('#mailform2').detach()
			$(this).text('+');
		}
	})
	$('#mailform2').detach();
})
</script>
</body>
</html>