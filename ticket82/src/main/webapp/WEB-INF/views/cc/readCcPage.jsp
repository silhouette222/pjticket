<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="/com/resources/js/upload.js" ></script>
<div>
	<div>
		<h3>콘서트</h3>
	</div>

<form method="post">
	<input type='text' name='ttr_cat' value="${musicalVO.ttr_cat}" readonly/>
	<input type='text' name='ttr_no' value="${musicalVO.ttr_no}" readonly/>
	<input type='text' name='com_id' value="${musicalVO.com_id}" readonly/>
	

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
	<label>사진자료</label>
	<div class="file"
		style="width: 800px;  background-color: white;">
	</div>
</div>
</form>
<label>평점 :</label><label id="scoreavg"></label>
<div id="replyarea">
		<table id="replylist">
		</table>
		<table id="replywrite">
			<tr>
				<td>평점</td>
				<td class="scorein">
					<input class="1" type="checkbox" value="1">
					<input class="2" type="checkbox" value="2">
					<input class="3" type="checkbox" value="3">
					<input class="4" type="checkbox" value="4">
					<input class="5" type="checkbox" value="5">
				</td>
				<td>
					<label class="scoreshow">&nbsp;&nbsp;</label>
				</td>
			</tr>
			<tr>
				<td colspan="3"><textarea id="reply_text" style="width: 600px; height: 200px;"></textarea></td>
			</tr>
		</table>
		<input id="rep_sub" type="button" value="확인">
	</div>

	<div class="popup back" style="display: none;"></div>
	<div id="popup_front" class='popup front' style="display: none;">
		<img id="popup_img" />
	</div>
<!-- /.box-body -->
<a href="/com/res/createRes?ttr_no=${musicalVO.ttr_no }"><button>예약</button></a>
<a href="/com/mclistA"><button>목록</button></a>
<a href="/com/removeMusical?ttr_no=${musicalVO.ttr_no }"><button>삭제</button></a>
<a href="/com/modifyPageForm?ttr_no=${musicalVO.ttr_no } "><button>수정</button></a>
<button id="zzim">찜</button>


<script>
	var ttr_no=${musicalVO.ttr_no};
	var sdate=new Date('${musicalVO.ttr_sdate}');
	$(document).ready(function(){
		var seats=null;
		var str=""
		<c:forEach var="seat" items="${musicalVO.seat_grd}" varStatus="status">
			str+="<tr>"+
			"<td id='seat_info'><input type='text' name='seat_grd' value='${musicalVO.seat_grd[status.count-1]}' readonly></td>"+
			"<td><input type='int' name='seat_no' value='${musicalVO.seat_no[status.count-1]}' readonly></td>"+
			"<td><input type='int' name='seat_pri' value='${musicalVO.seat_pri[status.count-1]}' readonly></td>"+
			"<td><input type='date' name='seat_date' value='<fmt:formatDate pattern='yyyy-MM-dd' value='${musicalVO.seat_date[status.count-1]}' />' readonly></td>"+
			"<td><input type='time' name='seat_time' value='<fmt:formatDate pattern='HH:mm' value='${musicalVO.seat_time[status.count-1]}' />' readonly></td>"+
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
			
			$.getJSON("/com/getFiles/"+ttr_no,function(list){
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
		})
		$('.scorein').children('input').on('click',function(index){
				var score=$(this).val()
				$('.scoreshow').text(score)
				$('.scorein').children('input').each(function(index){
					if($(this).val()<=score){
						$(this).prop('checked', true);
					}else{
						$(this).prop('checked',false)
					}
				})	
			})
			var zzim_url="/my/zzim/${loginUser.mem_id}/${musicalVO.ttr_no}"
			$.ajax({
					type:'get',
					url:"/my/selzzim/${loginUser.mem_id}/${musicalVO.ttr_no}",
					success:function(data){
						if(data=='zzimexist'){
							zzim_url="/my/delzzim/${loginUser.mem_id}/${musicalVO.ttr_no}";
							$('#zzim').text('찜취소')
						}else if(data=='zzimnull'){
							zzim_url="/my/addzzim/${loginUser.mem_id}/${musicalVO.ttr_no}";
							$('#zzim').text('찜하기')
						}
					}
				});
			$('#zzim').on('click',function(){
				$.ajax({
					type:'get',
					url:zzim_url,
					success:function(data){
						if(data=='addzzim'){
							zzim_url="/my/delzzim/${loginUser.mem_id}/${musicalVO.ttr_no}";
							$('#zzim').text('찜취소')
						}else if(data=='delzzim'){
							zzim_url="/my/addzzim/${loginUser.mem_id}/${musicalVO.ttr_no}";
							$('#zzim').text('찜하기')
						}
					}
				});
			}) 
			$.ajax({
				type:'get',
				url:"/com/scoreavg/${musicalVO.ttr_no}",
				success:function(data){
					$('#scoreavg').text(data)
				}
			});
		
</script>
<script>
	
	var replyPage=1;
	var upttr_score;
	var uptr_rno;
	var uptr_content;
	$('#rep_sub').on('click',function(){
		var replyer='${loginUser.mem_id}'
		var replytext=$('textarea#reply_text').val();
		var ttr_score=$('.scoreshow').text();
		$.ajax({
			type:'post',
			url:'/com/replies',
			headers:{
				"Content-Type":"application/json",
				"X-HTTP-Method-Override":"post"
			},
			dataType:'text',
			data:JSON.stringify({
				ttr_no:ttr_no,
				mem_id:replyer,
				tr_content:replytext,
				ttr_score:ttr_score
			}),
			success:function(data){
				if(data=='SUCCESS'){
					replyPage=1;
					getPage("/replies/"+ttr_no+"/"+replyPage);
				}
			}
		});
	})
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
			"<td tr_rno="+rep[i].tr_rno+">";
			if(rep[i].mem_id=='${loginUser.mem_id}'){
				str+="<button class='updaterepform'>수정</button><button class='deleterep'>삭제</button>";
			}
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
	getPage("/com/replies/"+ttr_no+"/1");
	$('#replylist').on('click','tr td .updaterepform',function(index){
		uptr_rno=$(this).parent().attr('tr_rno');
		uptr_content=$(this).parent().parent().children('.ttr_content').text()
		uptr_score=$(this).parent().parent().children('.ttr_score').text()
		var str="<td class='upscore' colspan='2'>"+
				"<input class='1' type='checkbox' value='1'>"+
				"<input class='2' type='checkbox' value='2'>"+
				"<input class='3' type='checkbox' value='3'>"+
				"<input class='4' type='checkbox' value='4'>"+
				"<input class='5' type='checkbox' value='5'>"+
				"</td>"+
				"<td colspan='2'><textarea name='uptext' style='width: 600px; height: 50px;'>"+uptr_content+"</textarea></td>"+
				"<td><button class='repupdate'>수정</button><button class='updatecan'>취소</button></td>"
		$(this).parent().parent().html(str)
	})
	$('#replylist').on('click','tr td .updatecan',function(index){
		getPage("/replies/"+ttr_no+"/"+replyPage);
	})
	$('#replylist').on('click','tr td .repupdate',function(index){
		var uptext=$('textarea[name="uptext"]').val()
		$.ajax({
			type:'put',
			url:"/replies/"+uptr_rno,
			headers:{
				"Content-Type":"application/json",
				"X-HTTP-Method-Override":"PUT"
			},
			data:JSON.stringify({tr_content:uptext,ttr_score:upttr_score,ttr_no:ttr_no}),
			dataType:'text',
			success:function(result){
				if(result=='SUCCESS'){
					getPage("/replies/"+ttr_no+"/"+replyPage);
				}	
			}
		});
		
	})
	$('#replylist').on('click','tr td .deleterep',function(index){
		var tr_rno=$(this).parent().attr('tr_rno');
		$.ajax({
			type:'delete',
			url:"/replies/"+tr_rno,
			headers:{
				"Content-Type":"application/json",
				"X-HTTP-Method-Override":"DELETE"
			},
			dataType:"text",
			success:function(result){
				if(result='SUCCESS'){
					getPage("/replies/"+ttr_no+"/"+replyPage);
				}	
			}
		});
	})
	$('#replylist').on('click','tr .upscore input',function(index){
		upttr_score=$(this).val()
		$(this).parent().children('input').each(function(index){
			if($(this).val()<=upttr_score){
				$(this).prop('checked', true);
			}else{
				$(this).prop('checked',false)
			}
		})	
	})
</script>
</div>
		
		