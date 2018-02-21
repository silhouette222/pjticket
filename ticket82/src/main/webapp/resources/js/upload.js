$(".thumb").on("dragenter dragover", function(event) {
	event.preventDefault();
});
$(".thumb").on(
		"drop",
		function(event) {
			event.preventDefault();
			if($('.thumb div small').attr('data-src')==null){
				
			
			var files = event.originalEvent.dataTransfer.files;
			var file = files[0];

			var formData = new FormData();
			formData.append("file", file);

			$.ajax({
				url : '/upload',
				type : 'post',
				data : formData,
				contentType : false,
				processData : false,
				success : function(data) {

					var str = "";
					if (checkImageType(data)) {
						str = "<div><img src='/displayFile?fileName=" + data
								+ "'/><small data-src='" + data
								+ "'><input type='hidden' name='thumb_name' value='"+data+"'><button type='button'>X</button></small></div>"
					}

					$(".thumb").append(str);
				}

			});
			}
		});
$(".seatmap").on("dragenter dragover", function(event) {
	event.preventDefault();
});
$(".seatmap").on(
		"drop",
		function(event) {
			event.preventDefault();
			if($('.seatmap div small').attr('data-src')==null){
			var files = event.originalEvent.dataTransfer.files;
			var file = files[0];

			var formData = new FormData();
			formData.append("file", file);

			$.ajax({
				url : '/upload',
				type : 'post',
				data : formData,
				contentType : false,
				processData : false,
				success : function(data) {

					var str = "";
					if (checkImageType(data)) {
						
						str = "<div><img src='/displayFile?fileName=" + data
								+ "'/><small data-src='" + data
								+ "'><input type='hidden' name='seatmap_name' value='"+data+"'><button type='button'>X</button></small></div>"
					}

					$(".seatmap").append(str);
				}

			});
			}
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
				url : '/upload',
				type : 'post',
				data : formData,
				contentType : false,
				processData : false,
				success : function(data) {

					var str = "";
					if (checkImageType(data)) {
						str = "<div style='display:inline;'><img src='/displayFile?fileName=" + data
								+ "'/><small class='file_submit' data-src='" + data
								+ "'><button type='button'>X</button></small></div>"
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
$('.thumb,.seatmap,.file').on('click', 'small ', function(event) {
	event.preventDefault();
	var that = $(this);

	$.ajax({
		url : "/deleteFile",
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
		str+="<input type='hidden' name='files'"
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
		
		var front=fullName.substr(0,12); // /2017/12/15/
		var end=fullName.substr(14); // s_ 이후
		getLink="/displayFile?fileName="+front+end;
	
	return getLink;
}

function getThumb(fullName){
	
	var imgsrc
		imgsrc="/displayFile?fileName="+fullName;
	
	return imgsrc;
	
}
$('#addseat').on('click',function(event){
	if($('.seat_info').length<11){
		
	
	var str="<tr class='seat_info'><input type='hidden' name='seat_id'><td><input type='text' name='seat_grd'></td><td><input type='number' name='seat_no'></td><td><input type='number' name='seat_pri'></td><td><input type='date' name='seat_date'></td><td><input type='time' name='seat_time'></td><td><input type='button' class='delseat btn btn-primary' value='-'></tr>";
	$('#seat_table').append(str);
	}
})
$('#seat_table').on('click','.delseat',function(){
	$(this).parent('td').parent('tr').remove();
})
//asdasd