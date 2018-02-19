<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<div>
	<div>
		<h3>뮤지컬</h3>
	</div>

<form role="form" method="post">
	<input type='hidden' name='ttr_no' value="${musicalVO.ttr_no}" />
	
	

<div>
	<div>
		<img src="/displayFile?fileName=${musicalVO.thumb_name}">
	</div>	
	<div>
		<img src="/displayFile?fileName=${musicalVO.seatmap_name}">
	</div>	
	<div>
		<label>제목</label> 
		<input type="text" name='ttr_title' value="${musicalVO.ttr_title}" readonly="readonly">
	</div>
	<div>
		<label>내용</label>
		<textarea name="ttr_content" rows="30" readonly="readonly">
			${musicalVO.ttr_content}
		</textarea>
	</div>
	<div>
		<label>장소</label> 
		<input type="text" name="ttr_place" value="${musicalVO.ttr_place}" readonly="readonly">
	</div>
	<div>
		<label>알림</label> 
		<input type="text" name="ttr_alert" value="${musicalVO.ttr_alert}" readonly="readonly">
	</div>
	<div>
		<label>시간정보</label> 
		<input type="text" name="ttr_time" value="${musicalVO.ttr_time}" readonly="readonly">
	</div>
	
	<div>
		<label>시작일</label>
		<input type="text" name="ttr_sdate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${musicalVO.ttr_sdate}" />" readonly="readonly">~
	</div>
	
	<div>
		<label>종료일</label>
		<input type="text" name="ttr_edate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${musicalVO.ttr_edate}" />" readonly="readonly">	
	</div>
</div>
</form>
<!-- /.box-body -->

<div>
	
	<a href=""><button type="submit">예약</button></a>
	<button type="submit" class="btn btn-warning">수정</button>
	<button type="submit" class="btn btn-danger">삭제</button>
	<a href="mclistA"><button type="submit">목 록</button></a>
	<a href="zzim"><button type="submit">찜하기</button></a>
</div>

				<script>
				
$(document).ready(function(){
	
	var formObj = $("form[role='form']");
	
	console.log(formObj);
	
	$(".btn-warning").on("click", function(){		
		formObj.attr("action", "modifyPageForm");		
		formObj.attr("method", "get");		
		formObj.submit();
	});
	
	$(".btn-danger").on("click", function(){
		formObj.attr("action", "removeMusical");
		formObj.submit();
	});
	
	$(".btn-primary").on("click", function(){
		self.location = "listAll";
	});
	
});

</script>

<script>
				
$(document).ready(function(){
	
	var formObj = $("form[role='form']");
	
	console.log(formObj);
	
	
	
	
	
});

</script>



</div>
		
		