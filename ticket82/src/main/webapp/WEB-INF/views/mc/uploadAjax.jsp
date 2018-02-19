<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style>
.fileDrop{
	width:100%;
	height:200px;
	border:1px dotted blue;
}
small{
	margin-left:3px;
	font-weight:bold;
	color:gray;
}
</style>
</head>
<body>
	<h3>Ajax File Upload, Drag&Drop</h3>
	<div class='fileDrop'></div>
	
	<div class='uploadedList' ></div>
	
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script>
		$(".fileDrop").on("dragenter dragover",function(event){
			event.preventDefault();
		});	
		$(".fileDrop").on("drop",function(event){
			event.preventDefault();
			
			var files=event.originalEvent.dataTransfer.files;
			var file=files[0];
			
			//console.log(file);
			
			var formData=new FormData();
			formData.append("file",file);
			
			$.ajax({
				url:'/uploadAjax',
				type:'post',
				data:formData,
				contentType:false,
				processData:false,
				success:function(data){
					//alert(data);
					
					var str="";
					if(checkImageType(data)){
						str="<div><a href='displayFile?fileName="+getImageLink(data)+"'>"
								+"<img src='displayFile?fileName="+data+"'/>"
								+"</a><small data-src='"+data+"'><button>X</button></small></div>"
					}else{
						str="<div><a href='displayFile?fileName="+data+"'>"
								+getOriginalName(data)+"</a>"
								+"<small data-src="+data+"><button>X</button></small></div>"						
					}
					
					$('.uploadedList').append(str);
				}
				
			});
			
		});
		function checkImageType(fileName){
			var pattern=/jpg|gif|png|jpeg/i;
			return fileName.match(pattern);
		}
		function getOriginalName(fileName){
			if(checkImageType(fileName)){
				return;
			}
			
			var idx=fileName.indexOf("_")+1;
			return fileName.substr(idx);
		}
		function getImageLink(fileName){
			if(!checkImageType(fileName)){
				return;
			}
			var front=fileName.substr(0,12); //날짜폴더 ex)2017/12/13/
			var end=fileName.substr(14); //저장파일명 
			
			return front+end;
		}
		
		$('.uploadedList').on('click','small ',function(){
			var that=$(this);
			
			//alert("click");
			$.ajax({
				url:"deleteFile",
				type:"post",
				dataType:"text",
				data : {fileName : $(this).attr("data-src")},
				success:function(result){
					if(result=='deleted'){
						that.parent("div").remove();
					}
				}
			}); 
			
		});
	</script>
</body>
</html>







