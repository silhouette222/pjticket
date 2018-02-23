<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

			
			<div>
				<div>
					<h3>콘서트 글 등록</h3>
				</div>
				<form id="write" method="post">
					<div>
						<div>
							<input type="text" name='com_id' value="com">
						</div>
						<div>							
							<input type="text" name='ttr_cat' value="콘서트" readonly/>
						</div>					
						<label>썸네일</label>
						<div class="thumb"
							style="width: 200px; height: 200px; background-color: gray;">
						</div>
						<label>좌석배치도</label>
						<div class="seatmap"
							style="width: 200px; height: 200px; background-color: gray;">
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
							<label>좌석정보</label>
							<button id="addseat" type="button">+</button>
							<table id="seat_table">
								<tr>
									<td>아이디</td>
									<td>등급</td>
									<td>좌석수</td>
									<td>가격</td>
									<td>날짜</td>
									<td>시간</td>
								</tr>
							</table>
						</div>
						<div>
							<label>사진첨부</label>
							<div class="file" 
							style="width:800px; height:200px; background-color:gray;">
						</div>							
						</div>
					</div>
					<div>
						<button type="submit">제출</button>
					</div>
				</form>
			</div>
			<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
			<script src="/resources/js/upload.js"></script>

</body>
	

























