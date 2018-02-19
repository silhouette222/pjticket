<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

			
			<div>
				<div>
					<h3>뮤지컬 글 등록</h3>
				</div>
				

				<form id="write" method="post">
					<div>						
						<label>썸네일</label>
						<div class="thumb"
							style="width: 200px; height: 200px; background-color: gray;">
						</div>
						<label>좌석배치도</label>
						<div class="seatmap"
							style="width: 200px; height: 200px; background-color: gray;">
						</div>
						<div>
							<label>기업회원 아이디</label>
							<input type="text" name='com_id' placeholder="기업회원 아이디">
						</div>
						<div>
							<label>공연 종류</label>
							<input type="text" name='ttr_cat' placeholder="공연 종류를 작성하세요.">
						</div>
						<div>
							<label>뮤지컬 제목</label> 
							<input type="text"
								name='ttr_title' placeholder="제목을 입력하세요.">
						</div>
						<div>
						<label>뮤지컬 시작날짜</label> 							
							<input type="date" name='ttr_sdate' placeholder="시작 날짜 작성">
						</div>
						<div>
						<label>뮤지컬 종료날짜</label> 
							<input type="date" name='ttr_edate' placeholder="종료 날짜 작성">
						</div>
						<div>
						<label>뮤지컬 장소</label> 
							<input type="text" name='ttr_place' placeholder="장소 작성">
						</div>
						<div>
						<label>뮤지컬 시간정보</label> 
							<input type="text" name='ttr_time' placeholder="시간정보">
						</div>
						<div>
						<label>뮤지컬 알릴말</label> 
							<input type="text" name='ttr_alert' placeholder="알리는말">
						</div>
						<div>
							<label>뮤지컬 내용</label><br>
							<textarea name="ttr_content" rows="30" cols="170"
								placeholder="내용을 입력하세요."></textarea>
						</div>
						<div>
							<label>사진첨부</label>
							<div class="file" 
							style="width:800px; height:200px; background-color:gray;"></div>
							
						</div>
					</div>
					
				
					<div>
						<button type="submit">제출</button>
					</div>
				</form>
			<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
			<!-- <script type="text/javascript" src="/resources/js/upload.js"></script> -->
			<script type="text/javascript">
			
			$(".thumb").on("dragenter dragover", function(event) {
				event.preventDefault();
			});
			$(".thumb").on(
					"drop",
					function(event) {
						event.preventDefault();

						var files = event.originalEvent.dataTransfer.files;
						var file = files[0];

						var formData = new FormData();
						formData.append("file", file);

						$.ajax({
							url : '/com/upload',
							type : 'post',
							data : formData,
							contentType : false,
							processData : false,
							success : function(data) {
																
								var str = "";
								if (checkImageType(data)) {
									str = "<div><img src='/com/displayFile?fileName=" + data
											+ "'/><small data-src='" + data
											+ "'><input type='hidden' name='thumb_name' value='"+data+"'><button type='button'>X</button></small></div>"
								}

								$(".thumb").append(str);
							}

						});

					});
			$(".seatmap").on("dragenter dragover", function(event) {
				event.preventDefault();
			});
			$(".seatmap").on(
					"drop",
					function(event) {
						event.preventDefault();

						var files = event.originalEvent.dataTransfer.files;
						var file = files[0];

						var formData = new FormData();
						formData.append("file", file);

						$.ajax({
							url : '/com/upload',
							type : 'post',
							data : formData,
							contentType : false,
							processData : false,
							success : function(data) {

								var str = "";
								if (checkImageType(data)) {									
									str = "<div><img src='/com/displayFile?fileName=" + data
											+ "'/><small data-src='" + data
											+ "'><input type='hidden' name='seatmap_name' value='"+data+"'><button>X</button></small></div>"
								}

								$(".seatmap").append(str);
							}

						});

					});
			$(".file").on("dragenter dragover", function(event) {
				event.preventDefault();
				
			});
			$(".file").on(
					"drop",
					function(event) {
						event.preventDefault();

						var files = event.originalEvent.dataTransfer.files;
						var file = files[0];

						var formData = new FormData();
						formData.append("file", file);

						$.ajax({
							url : '/com/upload',
							type : 'post',
							data : formData,
							contentType : false,
							processData : false,
							success : function(data) {

								var str = "";
								if (checkImageType(data)) {
									str = "<div style='display:inline;'><img src='/com/displayFile?fileName=" + getImageLink(data)
											+ "'/><small class='file_submit' data-src='" + data
											+ "'><button>x</button></small></div>"
								}

								$(".file").append(str);
							}

						});

					});
			function checkImageType(fileName) {
				var pattern = /jpg|gif|png|jpeg/i;
				return fileName.match(pattern);
			}
			function getOriginalName(fileName) {
				if (checkImageType(fileName)) {
					return;
				}

				var idx = fileName.indexOf("_") + 1;
				return fileName.substr(idx);
			}
			function getImageLink(fileName) {
				if (!checkImageType(fileName)) {
					return;
				}
				var front = fileName.substr(0, 12);
				var end = fileName.substr(14);

				return front + end;
			}
			$('.file,.seatmap,.thumb').on('click', 'small ', function(event) {
				var that = $(this);

				$.ajax({
					url : "/com/deleteFile",
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

			$('#write').submit(function(event){
				event.preventDefault();
				
				var that=$(this);
				var str="";
				$('.file_submit').each(function(index){
					str+="<input type='hidden' name='file'"
					+"value='"+$(this).attr('data-src')+"'>";
				});
				
				that.append(str);
				that.get(0).submit();
			});
			function checkImageType(fileName){
				var pattern=/jpg|gif|png|jpeg/i;
				
				return fileName.match(pattern);
			}

			function getOri(fullName){
				
				var getLink;
					
					var front=fullName.substr(0,12); 
					var end=fullName.substr(14);  
					getLink="/displayFile?fileName="+front+end;
				
				return getLink;
			}

			function getThumb(fullName){
				
				var imgsrc
					imgsrc="/displayFile?fileName="+fullName;
				
				return imgsrc;
				
			}



			</script>
			</div>
			
</body>
	

























