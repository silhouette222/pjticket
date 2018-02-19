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
	<h1>전시</h1>
	<div>
		<table border="1px">
			<tr>
				<th>대표이미지</th>
				<th>제목</th>
				<th>작성일</th>
			</tr>

			<c:forEach items="${list}" var="boardVO">

				<tr>
					<td><img src="/displayFile?fileName=${boardVO.thumb_name}"></td>
					<td><a href="/cboard/gal/galdetail?ttr_no=${boardVO.ttr_no}">${boardVO.ttr_title }</a></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
							value="${boardVO.ttr_date}" /></td>
				</tr>

			</c:forEach>

		</table>
		<a href="/cboard/write/gal"><button>글 작성</button></a>
	</div>
	<div>
		<select name="searchType">
			<option value="n"
				<c:out value="${cri.searchType == null?'selected':''}"/>>
				---</option>
				<option value="t"
				<c:out value="${cri.searchType eq 't'?'selected':''}"/>>
				제목</option>
				<option value="c"
				<c:out value="${cri.searchType eq 'c'?'selected':''}"/>>
				내용</option>
				<option value="s"
				<c:out value="${cri.searchType eq 'p'?'selected':''}"/>>
				장소</option>
		</select>
		<input type="text" id="key">
		<input id="search" type="button" value="검색">
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