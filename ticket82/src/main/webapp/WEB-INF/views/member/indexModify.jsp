<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%
	String cp = request.getContextPath();
	request.setCharacterEncoding("UTF-8");//쿠키 가져오기
	Cookie[] ck = request.getCookies();
%>
  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Ticket82</title>

    <!-- Bootstrap core CSS -->
    <link href="<%=request.getContextPath() %>/resources/main/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<%=request.getContextPath() %>/resources/main/css/modern-business.css" rel="stylesheet">
	
	<!-- 사이드메뉴 css -->
	<link href="//fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	
	<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/collapzion.min.css">

	
	
	
	<style>
		
	
		.card-img-top{
			width:228px;
			height:340px;
			display: block;
			margin-left: auto;
			margin-right: auto;
		}
		
		
		
		/* 티켓인기작 전체 */
		.ticketFamous{
			overflow: hidden;
			width: 960px;
			margin-bottom: 45px;
			margin-right: auto;
			margin-left:auto;
		}
		
		.ticketFamous h2{
			text-align: center;
			color: #dc3545;
		}
		
		/* 티켓인기작배너 */
		.ticketFamous .ticketFamousBanner{
			width: 976px;
			height: 340px;
		}
		
		/* 티켓인기작 사진 */
		.ticketFamous .ticketFamousCube{
			float: left;
			width: 228px;
			height: 340px;
			margin-right: 16px;
		}
		
		.ticketFamous .ticketFamousCube.hasRoll{
			position: relative;
		}
		
		.ticketFamous .ticketFamousCube.hasRoll .item.selected{
			display:block;
		}
		
		.ticketFamous .ticketFamousCube.hasRoll .item.tempTop{
			z-index: 2;
		}
		
		.ticketFamous .ticketFamousCube .item{
			position: relative;	
		}
		
		.ticketFamous .ticketFamousCube .item .contents{
			position: absolute;
			top: 43px;
			width: 228px;
		}
		
		.ticketFamous .ticketFamousCube .item .contents .label{
			height: 27px;
			background: url("//ticketimage.interpark.com/TicketImage/2015/20150429_main/img/cubeLabels.png") center 100px no-repeat;
			background-position: center -41px;
		}
		
		.ticketFamous .ticketFamousCube .item .contents .contentTxt{
			display: block;
  			padding-top: 12px;
  			background: url("//ticketimage.interpark.com/TicketImage/2015/20150429_main/img/cubeLabels.png") center -429px no-repeat;
  			text-decoration: none;
  			font-family: "malgun gothic";
  			letter-spacing: -1px;
		}
		
		.ticketFamous .ticketFamousCube .item .contents .contentTxt b{
			overflow: hidden;
  			display: block;
  			width: 174px;
  			height: 18px;
  			margin: 0 auto;
  			font-size: 14px;
  			text-overflow: ellipsis;
  			white-space: nowrap;
  			color: #333;
		}
		
			
				
		
	</style>
  </head>

  <body>


    <header>
      <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
          <li data-target="#carouselExampleIndicators" data-slide-to="0"  class="active"></li>
          <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
          <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner" role="listbox">
          <!-- Slide One - Set the background image for this slide in the line below -->
          <div class="carousel-item active" style="background-image: url('/displayFile?fileName=${mainVO.main_name}')">
            <div class="carousel-caption d-none d-md-block">
              <h3>First Slide</h3>
              <button class="btn btn-danger">상세보기</button>
            </div>
          </div>
          <!-- Slide Two - Set the background image for this slide in the line below -->
          <div class="carousel-item" style="background-image: url('/displayFile?fileName=${mainVO.main_name}')">
            <div class="carousel-caption d-none d-md-block">
              <h3>Second Slide</h3>
              <button class="btn btn-danger">상세보기</button>
            </div>
          </div>
          <!-- Slide Three - Set the background image for this slide in the line below -->
          <div class="carousel-item" style="background-image: url('/displayFile?fileName=${mainVO.main_name}')">
            <div class="carousel-caption d-none d-md-block">
              <h3>Third Slide</h3>
              <button class="btn btn-danger">상세보기</button>
            </div>
          </div>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="sr-only">Next</span>
        </a>
      </div>
    </header>
    
    
   	<!-- 인기작 -->
   	<div class="ticketFamous">
   		<!-- 뮤지컬 -->
   		<div class="ticketFamousBanner">
   			<h2>Musical</h2>
   			<div class="ticketFamousCube">
   				<div class="item">
   					<div class="contents">
   						<div class="label"></div>
   						<a class="contentTxt" href="#">
   						</a>
   					</div>
   					<a href="/mc/readMcPage?ttr_no=${boardVO.ttr_no}">
   						<img src="/displayFile?fileName=${boardVO.thumb_name}">
   					</a>
   				</div>
   			</div>
   			<div class="ticketFamousCube">
   				<div class="item">
   					<div class="contents">
   						<div class="label"></div>
   						<a class="contentTxt" href="#">
   						</a>
   					</div>
   					<a href="/mc/readMcPage?ttr_no=${boardVO.ttr_no}">
   						<img src="/displayFile?fileName=${boardVO.thumb_name}">
   					</a>
   				</div>
   			</div>
   			<div class="ticketFamousCube">
   				<div class="item">
   					<div class="contents">
   						<div class="label"></div>
   						<a class="contentTxt" href="#">
   						</a>
   					</div>
   					<a href="/mc/readMcPage?ttr_no=${boardVO.ttr_no}">
   						<img src="/displayFile?fileName=${boardVO.thumb_name}">
   					</a>
   				</div>
   			</div>
   			<div class="ticketFamousCube">
   				<div class="item">
   					<div class="contents">
   						<div class="label"></div>
   						<a class="contentTxt" href="#">
   						</a>
   					</div>
   					<a href="/mc/readMcPage?ttr_no=${boardVO.ttr_no}">
   						<img src="/displayFile?fileName=${boardVO.thumb_name}">
   					</a>
   				</div>
   			</div>
   		</div>
   		<!-- 콘서트 -->
   		<div class="ticketFamousBanner">
   			<h2>Concert</h2>
   			<div class="ticketFamousCube">
   				<div class="item">
   					<div class="contents">
   						<div class="label"></div>
   						<a class="contentTxt" href="#">
   						</a>
   					</div>
   					<a href="/cc/readCcPage?ttr_no=${boardVO.ttr_no}">
   						<img src="/displayFile?fileName=${boardVO.thumb_name}">
   					</a>
   				</div>
   			</div>
   			<div class="ticketFamousCube">
   				<div class="item">
   					<div class="contents">
   						<div class="label"></div>
   						<a class="contentTxt" href="#">
   						</a>
   					</div>
   					<a href="/cc/readCcPage?ttr_no=${boardVO.ttr_no}">
   						<img src="/displayFile?fileName=${boardVO.thumb_name}">
   					</a>
   				</div>
   			</div>
   			<div class="ticketFamousCube">
   				<div class="item">
   					<div class="contents">
   						<div class="label"></div>
   						<a class="contentTxt" href="#">
   						</a>
   					</div>
   					<a href="/cc/readCcPage?ttr_no=${boardVO.ttr_no}">
   						<img src="/displayFile?fileName=${boardVO.thumb_name}">
   					</a>
   				</div>
   			</div>
   			<div class="ticketFamousCube">
   				<div class="item">
   					<div class="contents">
   						<div class="label"></div>
   						<a class="contentTxt" href="#">
   						</a>
   					</div>
   					<a href="/cc/readCcPage?ttr_no=${boardVO.ttr_no}">
   						<img src="/displayFile?fileName=${boardVO.thumb_name}">
   					</a>
   				</div>
   			</div>
   		</div>
   		<!-- 연극 -->
   		<div class="ticketFamousBanner">
   			<h2>Play</h2>
   			<div class="ticketFamousCube">
   				<div class="item">
   					<div class="contents">
   						<div class="label"></div>
   						<a class="contentTxt" href="#">
   						</a>
   					</div>
   					<a href="#">
   						<img src="/displayFile?fileName=${boardVO.thumb_name}">
   					</a>
   				</div>
   			</div>
   			<div class="ticketFamousCube">
   				<div class="item">
   					<div class="contents">
   						<div class="label"></div>
   						<a class="contentTxt" href="#">
   						</a>
   					</div>
   					<a href="#">
   						<img src="/displayFile?fileName=${boardVO.thumb_name}">
   					</a>
   				</div>
   			</div>
   			<div class="ticketFamousCube">
   				<div class="item">
   					<div class="contents">
   						<div class="label"></div>
   						<a class="contentTxt" href="#">
   						</a>
   					</div>
   					<a href="#">
   						<img src="/displayFile?fileName=${boardVO.thumb_name}">
   					</a>
   				</div>
   			</div>
   			<div class="ticketFamousCube">
   				<div class="item">
   					<div class="contents">
   						<div class="label"></div>
   						<a class="contentTxt" href="#">
   						</a>
   					</div>
   					<a href="#">
   						<img src="/displayFile?fileName=${boardVO.thumb_name}">
   					</a>
   				</div>
   			</div>
   		</div>
   		<!-- Classic -->
   		<div class="ticketFamousBanner">
   			<h2>Classic</h2>
   			<div class="ticketFamousCube">
   				<div class="item">
   					<div class="contents">
   						<div class="label"></div>
   						<a class="contentTxt" href="#">
   						</a>
   					</div>
   					<a href="#">
   						<img src="/displayFile?fileName=${boardVO.thumb_name}">
   					</a>
   				</div>
   			</div>
   			<div class="ticketFamousCube">
   				<div class="item">
   					<div class="contents">
   						<div class="label"></div>
   						<a class="contentTxt" href="#">
   						</a>
   					</div>
   					<a href="#">
   						<img src="/displayFile?fileName=${boardVO.thumb_name}">
   					</a>
   				</div>
   			</div>
   			<div class="ticketFamousCube">
   				<div class="item">
   					<div class="contents">
   						<div class="label"></div>
   						<a class="contentTxt" href="#">
   						</a>
   					</div>
   					<a href="#">
   						<img src="/displayFile?fileName=${boardVO.thumb_name}">
   					</a>
   				</div>
   			</div>
   			<div class="ticketFamousCube">
   				<div class="item">
   					<div class="contents">
   						<div class="label"></div>
   						<a class="contentTxt" href="#">
   						</a>
   					</div>
   					<a href="#">
   						<img src="/displayFile?fileName=${boardVO.thumb_name}">
   					</a>
   				</div>
   			</div>
   		</div>
   		<!-- 전시회 -->
   		<div class="ticketFamousBanner">
   			<h2>Exhibition</h2>
   			<div class="ticketFamousCube">
   				<div class="item">
   					<div class="contents">
   						<div class="label"></div>
   						<a class="contentTxt" href="#">
   						</a>
   					</div>
   					<a href="/mboard/gal/galdetail?ttr_no=${boardVO.ttr_no}">
   						<img src="/displayFile?fileName=${boardVO.thumb_name}">
   					</a>
   				</div>
   			</div>
   			<div class="ticketFamousCube">
   				<div class="item">
   					<div class="contents">
   						<div class="label"></div>
   						<a class="contentTxt" href="#">
   						</a>
   					</div>
   					<a href="/mboard/gal/galdetail?ttr_no=${boardVO.ttr_no}">
   						<img src="/displayFile?fileName=${boardVO.thumb_name}">
   					</a>
   				</div>
   			</div>
   			<div class="ticketFamousCube">
   				<div class="item">
   					<div class="contents">
   						<div class="label"></div>
   						<a class="contentTxt" href="#">
   						</a>
   					</div>
   					<a href="/mboard/gal/galdetail?ttr_no=${boardVO.ttr_no}">
   						<img src="/displayFile?fileName=${boardVO.thumb_name}">
   					</a>
   				</div>
   			</div>
   			<div class="ticketFamousCube">
   				<div class="item">
   					<div class="contents">
   						<div class="label"></div>
   						<a class="contentTxt" href="#">
   						</a>
   					</div>
   					<a href="/mboard/gal/galdetail?ttr_no=${boardVO.ttr_no}">
   						<img src="/displayFile?fileName=${boardVO.thumb_name}">
   					</a>
   				</div>
   			</div>
   		</div>
   		<!-- 기타 -->
   		<div class="ticketFamousBanner">
   			<h2>The Others</h2>
   			<div class="ticketFamousCube">
   				<div class="item">
   					<div class="contents">
   						<div class="label"></div>
   						<a class="contentTxt" href="#">
   						</a>
   					</div>
   					<a href="/mboard/etc/etcdetail?ttr_no=${boardVO.ttr_no}">
   						<img src="/displayFile?fileName=${boardVO.thumb_name}">
   					</a>
   				</div>
   			</div>
   			<div class="ticketFamousCube">
   				<div class="item">
   					<div class="contents">
   						<div class="label"></div>
   						<a class="contentTxt" href="#">
   						</a>
   					</div>
   					<a href="/mboard/etc/etcdetail?ttr_no=${boardVO.ttr_no}">
   						<img src="/displayFile?fileName=${boardVO.thumb_name}">
   					</a>
   				</div>
   			</div>
   			<div class="ticketFamousCube">
   				<div class="item">
   					<div class="contents">
   						<div class="label"></div>
   						<a class="contentTxt" href="#">
   						</a>
   					</div>
   					<a href="/mboard/etc/etcdetail?ttr_no=${boardVO.ttr_no}">
   						<img src="/displayFile?fileName=${boardVO.thumb_name}">
   					</a>
   				</div>
   			</div>
   			<div class="ticketFamousCube">
   				<div class="item">
   					<div class="contents">
   						<div class="label"></div>
   						<a class="contentTxt" href="#">
   						</a>
   					</div>
   					<a href="/mboard/etc/etcdetail?ttr_no=${boardVO.ttr_no}">
   						<img src="/displayFile?fileName=${boardVO.thumb_name}">
   					</a>
   				</div>
   			</div>
   		</div>

   	</div>
   	<!-- 인기작 끝 -->

    <!-- Page Content -->
    <div class="container">

      <h1 class="my-4">공지사항</h1>

      <!-- Marketing Icons Section -->
      <div class="row">
        <div class="col-lg-4 mb-4">
          <div class="card h-100">
            <h4 class="card-header">Card Title</h4>
            <div class="card-body">
              <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sapiente esse necessitatibus neque.</p>
            </div>
            <div class="card-footer">
              <a href="#" class="btn btn-danger">상세보기</a>
            </div>
          </div>
        </div>
        <div class="col-lg-4 mb-4">
          <div class="card h-100">
            <h4 class="card-header">Card Title</h4>
            <div class="card-body">
              <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Reiciendis ipsam eos, nam perspiciatis natus commodi similique totam consectetur praesentium molestiae atque exercitationem ut consequuntur, sed eveniet, magni nostrum sint fuga.</p>
            </div>
            <div class="card-footer">
              <a href="#" class="btn btn-danger">상세보기</a>
            </div>
          </div>
        </div>
        <div class="col-lg-4 mb-4">
          <div class="card h-100">
            <h4 class="card-header">Card Title</h4>
            <div class="card-body">
              <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sapiente esse necessitatibus neque.</p>
            </div>
            <div class="card-footer">
              <a href="#" class="btn btn-danger">상세보기</a>
            </div>
          </div>
        </div>
      </div>
      <!-- /.row -->

      <!-- Portfolio Section -->
      <h2>이벤트</h2>
	  <div class="row">
      <c:forEach items="${elist}" var="eventVO" end="3">
        <div class="col-lg-4 mb-4">
          <div class="card h-100">
            <h4 class="card-header">${eventVO.et_title}</h4>
            <div class="card-body">
              <p class="card-text">${eventVO.et_content}</p>
            </div>
            <div class="card-footer">
              <a href="/event/read?et_no=${eventVO.et_no}" class="btn btn-danger">상세보기</a>
            </div>
          </div>
        </div>
      </c:forEach>
      </div>
      
      <!-- /.row -->
    </div>
    <!-- /.container -->
    
  </body>
