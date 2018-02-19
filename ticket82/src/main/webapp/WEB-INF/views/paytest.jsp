<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<button id="bt">asd</button>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script src="/resources/js/jquery.ajax-cross-origin.min.js"></script>
<script>
window.onload=function(){
/* 	var IMP=window.IMP;
	IMP.init('iamport'); */
	bt.onclick=function(){
		/* $.ajax({
			//crossOrigin: true,
			url : "https://cors-anywhere.herokuapp.com/api.iamport.kr/users/getToken",
			type : "post",
			/* Accept : 'application/json',
			dataType : 'json',
			contentType:'application/json',
			data: {
	    		imp_key:"imp_apikey",
	    		imp_secret:"ekKoeW8RyKuT0zgaZsUtXXTLQ4AhPFW3ZGseDA6bkA5lamv9OqDMnxyeB9wqOsuO9W3Mx9YSJ4dTqJ3f"
	    		//기타 필요한 데이터가 있으면 추가 전달
    		},
    	complete:function(result){
    		console.log(result.responseJSON.response.access_token)
    		 var token=result.responseJSON.response.access_token
    		$.ajax({
    			crossOrigin: true,
				url : "https://api.iamport.kr/payments/cancel?_token="+token,
				type : "post",
				dataType : 'json',
				data: {
		    		imp_uid : rsp.imp_uid
		    		//기타 필요한 데이터가 있으면 추가 전달
	    		} 
			}); 
    	}
		})
		 var xml=new XMLHttpRequest();
		xml.onreadystatechange=function(){
			if(xml.readyState==XMLHttpRequest.DONE){
				if(xml.status==200){
				}
			}
		} 
		var json={
	    		imp_key:"imp_apikey",
	    		imp_secret:"ekKoeW8RyKuT0zgaZsUtXXTLQ4AhPFW3ZGseDA6bkA5lamv9OqDMnxyeB9wqOsuO9W3Mx9YSJ4dTqJ3f"
			}
		xml.open("POST","https://api.iamport.kr/users/getToken",true);
		xml.setRequestHeader('Accept', 'application/json');
		xml.setRequestHeader('Content-Type', 'application/json');
		xml.send(JSON.stringify(json)) */
		location.href=history.back();
	}
}
	
</script>
</body>
</html>