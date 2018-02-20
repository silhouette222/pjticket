<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


	<div>
		<h3>예약확인</h3>
	</div>
	<div>
		<table>		
			<tr>
				<td>예약아이디</td>
				<td>예약날짜</td>
				<td>예약번호</td>
				<td>좌석아이디</td>
				<td>회원아이디</td>
				<td>글번호</td>
			</tr>		
			<c:forEach items="${reslist}" var="reservationVO">
				<tr>									
					<td>${reservationVO.res_id }</td>
					<td>${reservationVO.res_date }</td>
					<td>${reservationVO.res_nom }</td>
					<td>${reservationVO.seat_id }</td>
					<td>${reservationVO.mem_id }</td>
					<td>${reservationVO.ttr_no }</td>
				</tr>
			</c:forEach>
		</table>

	</div>
		
	<div>
		<table>
			<tr>
				<td>좌석아이디</td>
				<td>글번호</td>
				<td>좌석등급</td>
				<td>좌석번호</td>
				<td>좌석가격</td>
				<td>날짜</td>
				<td>시간</td>
			</tr>		
			<c:forEach items="${list}" var="ReservationVO">
			<tr>
			<td>${ReservationVO.res_id }</td>
			<td>${ReservationVO.res_date }</td>
			<td>${ReservationVO.res_nom }</td>
			<td>${ReservationVO.seat_id }</td>
			<td>${ReservationVO.mem_id }</td>
			<td>${ReservationVO.ttr_no }</td>
			</tr>
				<%-- <tr>									
					<td>${seatinfo.seat_id }</td>
					<td>${seatinfo.ttr_no }</td>
					<td>${seatinfo.seat_grd }</td>
					<td>${seatinfo.seat_no }</td>
					<td>${seatinfo.seat_pri }</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${seatinfo.seat_date }"/></td>
					<td><fmt:formatDate pattern='HH:mm'
						value="${seatinfo.seat_time }" /></td>
				</tr> --%>
			</c:forEach>
		</table>

	</div>
	<div>총 가격 ${pri }</div>
<script>
</script>

















