<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<div class="container">
	<div class="box text-center">
	<form id="pay">
		<table class="table table-striped">
			<tr><td><label>공연이름<input name="title" id="title" type="text" value="${boardVO.ttr_title }" readonly></label></td></tr>
			<tr><td>
				<label>결제방식
					<select name="type" id="type">
    					<option value="card" selected>신용카드</option>
    					<option value="phone">휴대폰소액결제</option>
    					<option value="samsung">삼성페이</option>
    					<option value="kpay">kPay</option>
    					<option value="cultureland">문화상품권</option>
    					<option value="smartculture">스마트문상</option>
    					<option value="happymoney">해피머니</option>
					</select>
				</label>
			</td></tr>
			<tr><td><label>이름<input name="name" id="name" type="text" value="${pay.name }"></label></td></tr>
			<tr><td><label>이메일<input name="email" id="email" type="text" value="${pay.email }"></label></td></tr>
			<tr><td><label>휴대폰번호<input name="tel" id="tel" type="text" value="${pay.tel }"></label></td></tr>
			<tr><td><label>주소<input name="addr" id="addr" type="text" value="${pay.addr }"></label></td></tr>
			<tr><td><input type="submit" class="btn btn-primary" value="결제"></td></tr>
		</table>
	</form>
	</div>
</div>
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>
	<script>
	$('#pay').submit(function(event){
		event.preventDefault();
		pay($("#type option:selected").val(),$('#title').val(),'${pay.pay}',$('#email').val(),$('#name').val(),$('#tel').val(),$('#addr').val(),null);
	});
	</script>
	<script>
	var imp_admin="https://admin.iamport.kr/";
	var imp_api="https://api.iamport.kr/";

	var json = [];

	var IMP=window.IMP;
	IMP.init('iamport');
	function pay(type,title,pay,email,name,tel,addr,post){
		IMP.request_pay({
			pg : 'inicis', // version 1.1.0부터 지원.
			pay_method : type,
			merchant_uid : 'merchant_' + new Date().getTime(),
			name : title,
			amount : pay,
			buyer_email : email,
			buyer_name : name,
			buyer_tel : tel,
			buyer_addr : addr,
			buyer_postcode : post,
			m_redirect_url : '/mboard/rescomplet'
			}, function(rsp) {
				<c:forEach var="resVO" items="${reslist}" varStatus="status">
				json[${status.count-1}]={
						res_id:"",
						res_date:"",
						res_nom:${resVO.res_nom},
						seat_id:'${resVO.seat_id}',
						mem_id:'${resVO.mem_id}',
						ttr_no:${resVO.ttr_no},
						imp_uid:rsp.imp_uid
											}
				</c:forEach>
				if ( rsp.success ) {
					$.ajax({
						url : "/mboard/respay",
						type : "post",
						dataType : "json",
						data : {
								reslist :JSON.stringify(json), 		
								imp_uid : rsp.imp_uid
								},
						complete : function(result) {
							if (result.responseText == 'everythings_fine') {
								alert("예약이 완료되었습니다");
								location.href="/member/my/reslist";
							}else{
								$.ajax({
									url : "https://cors-anywhere.herokuapp.com/api.iamport.kr/users/getToken",
									type : "post",
									dataType : 'json',
									data: {
				    		    		imp_key:"imp_apikey",
				    		    		imp_secret:"ekKoeW8RyKuT0zgaZsUtXXTLQ4AhPFW3ZGseDA6bkA5lamv9OqDMnxyeB9wqOsuO9W3Mx9YSJ4dTqJ3f"
				    	    		},
				    	    	complete:function(result){
				    	    		var token=result.responseJSON.response.access_token
				    	    		$.ajax({
										url : "https://cors-anywhere.herokuapp.com/api.iamport.kr/payments/cancel?_token="+token,
										type : "post",
										dataType : 'json',
										data: {
					    		    		imp_uid : rsp.imp_uid
					    	    		}
									});
				    	    		location.href="/member/index";
				    	    	}
								})
								
							}
						}
					});
			    } else {
			        var msg = '결제에 실패하였습니다.';
			        msg += '에러내용 : ' + rsp.error_msg;

			        alert(msg);
			    }
			});
	}
	</script>
</body>
</body>
</html>