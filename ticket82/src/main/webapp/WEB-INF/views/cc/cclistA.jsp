<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script> -->

				<div>
					<div>
						<h3>콘서트</h3>
					</div>
					<div>
						<a href="createConcert"><button>등록</button></a>
						<table>
						
							<c:forEach items="${clist}" var="musicalVO">
								<tr>									
									<td>${musicalVO.ttr_no}</td>									
									<td><img src="/com/displayFile?fileName=${musicalVO.thumb_name}"></td>
									<td><a href='readMcPage?ttr_no=${musicalVO.ttr_no}'>${musicalVO.ttr_title}</a></td>
									<td><fmt:formatDate pattern="yyyy-MM-dd"
											value="${musicalVO.ttr_sdate}" /> ~</td>
									<td><fmt:formatDate pattern="yyyy-MM-dd"
											value="${musicalVO.ttr_edate}" /></td>
									<td>${musicalVO.ttr_content }</td>
								</tr>

							</c:forEach>

						</table>

					</div>
				</div>

















