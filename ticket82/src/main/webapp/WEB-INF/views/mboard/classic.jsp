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
	<div class="container">
		<div class='box'>
			<div class="box-header with-border">
				<h1 style="font-size: 30px" class="box-title"><strong>연주회</strong></h1>
			</div>
			<div class='box-body'>
				<table class="table table-striped">
					<tr>
						<th style="width: 120px">대표이미지</th>
						<th>제목</th>
						<th style="width: 120px">작성일</th>
					</tr>
					<c:forEach items="${list}" var="boardVO">
					<tr onclick="location.href='/mboard/classic/classicdetail?ttr_no=${boardVO.ttr_no}'">
						<td><img src="/displayFile?fileName=${boardVO.thumb_name}"></td>
						<td>
							<p>${boardVO.ttr_title }</p>
							<small>${boardVO.ttr_content }</small>
						</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.ttr_date}" /></td>
					</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		
		<div class="box">
			<div class="box-body text-right">
				<select name="searchType">
					<option value="n"<c:out value="${cri.searchType == null?'selected':''}"/>>---</option>
					<option value="t"<c:out value="${cri.searchType eq 't'?'selected':''}"/>>제목</option>
					<option value="c"<c:out value="${cri.searchType eq 'c'?'selected':''}"/>>내용</option>
					<option value="s"<c:out value="${cri.searchType eq 'p'?'selected':''}"/>>장소</option>
				</select>
				<input type="text" id="key">
				<input class="btn btn-primary" id="search" type="button" value="검색">
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script>
	$(document).ready(function(){
		$('#search').on('click',function(event){
			self.location = "etc"
				+ "?searchType="
				+ $("select option:selected").val()
				+ "&keyword=" + $('#key').val();
		})
	})
	</script>
</body>
</html>