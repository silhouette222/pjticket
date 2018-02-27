<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<div class="box">
	<div class="row">
		<div class="col-3"></div>
		<div class="col-6">
			<label>예매율</label>
			<div class="progress">
  				<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" 
  				aria-valuemin="0" aria-valuemax="100" style="width:${seat}%">
			</div>
			${seat}%
			</div>
		</div>
		<div class="col-3"></div>
	</div>
	<div class="row">
		<div class="col-1"></div>
		<div class="col-5">
		<canvas id="gender" width="100" height="70"></canvas>
		</div>
		<div class="col-5">
		<canvas id="age" width="100" height="70"></canvas>
		</div>
		<div class="col-1"></div>
	</div>
	<div class="row">
		<div class="col-1"></div>
			<a href="javascript:history.go(-1)"><button class="btn btn-primary">뒤로가기</button></a>
		<div class="col-1"></div>
	</div>
</div>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.bundle.js"></script>
<script>
var ctx = document.getElementById("gender").getContext('2d');
var myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: ["남성", "여성"],
        datasets: [{
            label: '성별',
            data: [${man},${woman}],
            backgroundColor: [
				'rgba(54, 162, 235, 0.2)',
                'rgba(255, 99, 132, 0.2)'
            ],
            borderColor: [
				'rgba(54, 162, 235, 1)',
                'rgba(255,99,132,1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero:true
                }
            }]
        }
    }
});

</script>
<script>
var ctx = document.getElementById("age").getContext('2d');
var myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: ["10대", "20대", "30대", "40대", "50대", "60대 이상"],
        datasets: [{
            label: '연령',
            data: [${age10},${age20},${age30},${age40},${age50},${age60}],
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255,99,132,1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero:true
                }
            }]
        }
    }
});
</script>
</body>
</html>