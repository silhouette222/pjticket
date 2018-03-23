<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
  <head>

  </head>

  <body>
    <!-- Page Content -->
    <div class="container">

      <!-- Page Heading/Breadcrumbs -->
      <h1 class="mt-4 mb-3">QnA
        <small>Ticket82</small>
      </h1>

      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="/member/index">Home</a>
        </li>
        <li class="breadcrumb-item active">QnA</li>
      </ol>

      <div class="mb-4" id="accordion" role="tablist" aria-multiselectable="true">
      <select name="searchType">
						<option value="n"
							<c:out value="${cri.searchType == null?'selected':''}"/>>
							---</option>
						<option value="t"
							<c:out value="${cri.searchType eq 't'?'selected':''}"/>>
							Title
						</option>
						<option value="c"
							<c:out value="${cri.searchType eq 'c'?'selected':''}"/>>
							Content
						</option>
						<option value="w"
							<c:out value="${cri.searchType eq 'w'?'selected':''}"/>>
							ID
						</option>
						<option value="tc"
							<c:out value="${cri.searchType eq 'tc'?'selected':''}"/>>
							Title OR Content
						</option>
						<option value="cw"
							<c:out value="${cri.searchType eq 'cw'?'selected':''}"/>>
							Content OR ID
						</option>
						<option value="tcw"
							<c:out value="${cri.searchType eq 'tcw'?'selected':''}"/>>
							Title OR Content OR ID
						</option>
					</select> 
					<input type="text" name='keyword' id="keywordInput"
						value='${cri.keyword }'>
					<button id='searchBtn' class="btn btn-primary">Search</button>
					<button id='newBtn' class="btn btn-primary">New QNA</button>
					
								
       <c:forEach items="${list}" var="qnaVO"> 
        <div class="card">
          <div class="card-header" role="tab" id="headingOne">
            <h5 class="mb-0">
              <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">${qnaVO.qna_title}</a>
              <button id='del' onclick="javascript:location.href='<%=request.getContextPath() %>/mboard/qna/delete'" class="btn btn-primary">삭제</button>
            </h5>
          </div>

          <div id="collapseOne" class="collapse show" role="tabpanel" aria-labelledby="headingOne">
            <div class="card-body">
              ${qnaVO.qna_date}<br/>
              ${qnaVO.qna_content}
            </div>
            <!-- 답변 -->
            <div class="row">
				<div class="col-md-12">
					<div class="box box-success">
						<div class="box-header">
							<h3 class="box-title">ADD NEW ANSWER</h3>
						</div>
						<div class="box-body">
							<label for="newAnswerWriter">Writer</label>
								<input class="form-control" type='text' 
									placeholder="USER ID" id="newAnswerWriter" />
							<label for="newAnswerText">Answer Text</label>
								<input class="form-control" type="text"
								placeholder="ANSWER TEXT" id="newAnswerText">
						</div>
						<!-- /. box-body -->
						<div class="box-footer">
							<button type="button" class="btn btn-primary"
							id="answerAddBtn">답변하기</button>
						</div>
						<!-- /. box-footer -->
					</div>
			
					<!-- The time line -->
					<ul class="timeline">
						<li class="time-label" id="answersDiv">
							<span class="bg-green">답변</span>
						</li>
					</ul>
					<div class="text-center">
						<ul id="pagination" class="pagination pagination-sm no-margin">
						</ul>
					</div>			
			
				</div>
			</div>
            
            <div class="box-footer">
            	<button id="removeQNABtn" type="submit" class="btn btn-danger">REMOVE</button>
            </div>
          </div>
        </div>
        
        <!-- Modify Modal -->
		<div id="modifyModal" class="modal modal-primary fade" role="dialog">
			<div class="modal-dialog">
			<!-- Modal content  -->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
					&times;
					</button>
					<h4 class="modal-title"></h4>				
				</div>
				<div class="modal-body" data-ano>
					<p>
						<input type='text' id="answertext" class="form-control">
					</p>				
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-info" id="answerModBtn">
						Modify</button>
					<button type="button" class="btn btn-danger" id="answerDelBtn">
						Delete</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">
						Close</button>
				</div>
			</div>
			</div>
		</div>
        
       </c:forEach> 
      </div>
      
      

    </div>
    <!-- /.container -->

	<script type="text/javascript" src="/resources/js/upload.js"></script>
	<script	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.11/handlebars.js"></script>

	<script id="template" type="text/x-handlebars-template">
		{{#each .}}
			<li class="answerLi" data-ano={{ano}} >
				<i class="fa fa-comments bg-blue" ></i>
				<div class="timeline-item">
					<span class="time">
						<i class="fa fa-clock-o"></i>{{prettifyDate ans_date}}
					</span>
					<h3 class="timeline-header"><strong>{{ano}}</strong>-{{am_id}}</h3>
					<div class="timeline-body">{{ans_content}}</div>
					<div class="timeline-footer">
						<a class="btn btn-primary btn-xs"
						data-toggle="modal" data-target="#modifyModal">Modify</a>
					</div>
				</div>
			</li>
		{{/each}}
	</script>
<script>
	Handlebars.registerHelper("prettifyDate", function(timeValue) {
		var dateObj = new Date(timeValue);
		var year = dateObj.getFullYear();
		var month = dateObj.getMonth();
		var date = dateObj.getDate();
		return year + "/" + month + "/" + date;
	});

	var printData = function(answerArr, target, templateObject) {
		var template = Handlebars.compile(templateObject.html());
		var html = template(answerArr);
		$('.answerLi').remove();
		target.after(html);
	};
	
	var qna_no=${qnaVO.bno};
	
	var answerPage=1;
	
	function getPage(pageInfo){
		$.getJSON(pageInfo,function(data){
			printData(data.list,$('#answersDiv'),$('#template'));
			printPaging(data.pageMaker,$('.pagination'));
			
			//$('#modifyModal').modal('hide');
			
		});
	};
	
	getPage("/answer/"+qna_no+"/1");
	
	var printPaging=function(pageMaker,target){
		var str="";
		if(pageMaker.prev){
			str+="<li><a href='"+(pageMaker.startPage-1)
				  +"'> << </a></li>";
		};
		for(var i=pageMaker.startPage,len=pageMaker.endPage;i<=len;i++){
			var strClass=pageMaker.cri.page==i?'class=active':'';
			str+="<li "+strClass+"><a href='"+i+"'>"+i+"</a></li>";
		};
		if(pageMaker.next){
			str+="<li><a href='"+(pageMaker.endPage+1)
			  +"'> >> </a></li>";
		};
		target.html(str);
	};
	$('.pagination').on('click','li a',function(event){
		event.preventDefault();
		answerPage=$(this).attr('href');
		getPage("/answer/"+qna_no+"/"+answerPage);
		
		
	});
	
	$('#answerAddBtn').on('click',function(event){
						
		var am_id=$('#newAnswerWriter').val();
		var ans_content=$('#newAnswerText').val();
		
		$.ajax({
			type:'post',
			url:'/answer',
			headers:{
				"Content-Type":"application/json",
				"X-HTTP-Method-Override":"post"
			},
			dataType:'text',
			data:JSON.stringify({
				qna_no:qna_no,
				am_id:am_id,
				ans_content:ans_content
			}),
			success:function(data){
				if(data=='SUCCESS'){
					alert('등록 되었습니다.');
					answerPage=1;
					getPage("/answer/"+qna_no+"/"+answerPage);
					$('#newAnswerWriter').val("");
					$('#newAnswerText').val("");
				}
			}
		});
	});
	
	$('.timeline').on('click','.answerLi',function(event){
		var answer=$(this);
		$('#answertext').val(answer.find('.timeline-body').text());
		$('.modal-title').html(answer.attr('data-ano'));
	});
	
	
	$('#answerModBtn').on('click',function(){
		var ano=$('.modal-title').html();
		var ans_content=$("#answertext").val();
		$.ajax({
			type:'put',
			url:"/answer/"+ano,
			headers:{
				"Content-Type":"application/json",
				"X-HTTP-Method-Override":"PUT"
			},
			data:JSON.stringify({ans_content:ans_content}),
			dataType:'text',
			success:function(result){
				if(result=='SUCCESS'){
					alert("수정되었습니다.");
					$('#modifyModal').modal('hide');
					getPage("/answer/"+qna_no+"/"+answerPage);
				}	
			}
		});
	});
	
	$('#answerDelBtn').on('click',function(){
		var ano=$('.modal-title').html();
		
		$.ajax({
			type:'delete',
			url:"/answer/"+ano,
			headers:{
				"Content-Type":"application/json",
				"X-HTTP-Method-Override":"DELETE"
			},
			dataType:"text",
			success:function(result){
				if(result='SUCCESS'){
					alert("삭제되었습니다.");
					$('#modifyModal').modal('hide');
					getPage("/answer/"+qna_no+"/"+answerPage);
				}	
			}
		
		});		
	});
</script>
<script>
	var result = '${msg}';

	if (result == 'SUCCESS') {
		alert("처리가 완료되었습니다.");
	}
</script>

<script>
	$(document).ready(function(){
		$('#searchBtn').on("click",function(event){
			self.location = "list"
							+ '${pageMaker.makeQuery(1)}'
							+ "&searchType="
							+ $("select option:selected").val()
							+ "&keyword=" + $('#keywordInput').val();
		});
		
		$('#newBtn').on("click", function(evt){
				self.location = "createQNA";
		});
	});
</script>
  </body>

</html>
