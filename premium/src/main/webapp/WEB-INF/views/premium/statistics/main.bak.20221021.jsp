<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="㈜스마트코리아" />
<meta name="description" content="Smartooth" />
<title>Smartooth 치아 모니터링 시스템 :: main</title>
<!-- FAVICON ICO ERROR 방지 -->
<link rel="shortcut icon" type="image/x-icon" href="/imgs/common/logo_img_ori.png">
<link rel="stylesheet" href="/css/common/layout.css">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript">
	const circle = document.querySelector(".circle");
	document.addEventListener("mousemove", (e) => { // mousemove이벤트를 이용해 움
        // 마우스의 좌표는 clientX와 clientY를 이용해 알수 있다. -> 브라우저 window의 좌표값 위치를 전달한다.
        // pageX, pageY와는 다름.
        const mouseX = e.clientX;
        const mouseY = e.clientY;
        console.log("X >>> "+ e.clientX + ", e.clientY >>> "+ e.clientY);
    });

</script>
<style>
	@font-face {
		font-family: 'AppleSDGothicNeoB';
		src: url(/fonts/AppleSDGothicNeoB.ttf) format('truetype');
	}
	
	body {
		/* 	background-image: url("/imgs/layout/main/statistics_main_bg.jpg"); */
		background-image: url("/imgs/layout/main/statistics_main_bg.png");
		background-size: 405px 890px;
		background-repeat: no-repeat;
		background-position-x: center;
		background-color: #EBEBEB;
		font-family: AppleSDGothicNeoB;
		overflow: hidden;
	}
	
	.container {
		width: 100%;
		height: 880px;
	}
	
	.tmp {
		display: flex;
	}
	
	.a {
		width: 40%;
	    float: left;
	    margin-left: 10%;
		font-size: 22px;
	}
	
	.b {
		width: 40%;
	    float: left;
	    margin-right: 10%;
	    padding-top: 10px;
	    text-align: right;
	    font-size: 17px;
	}
	
	.c {
	    background-color: skyblue;
	    position: absolute;
	    top: 320px;
	    left: 31px;
	    width: 43px;
	    height: 46px;
	}
	
	.d {
	    background-color: skyblue;
	    position: absolute;
	    top: 283px;
	    left: 40px;
	    width: 42px;
	    height: 37px;
	}
	
	
	.e {
	    background-color: skyblue;
	    position: absolute;
	    top: 254px;
	    left: 56px;
	    width: 29px;
	    height: 30px;
	}
	
	
	.f {
		background-color: skyblue;
	    position: absolute;
	    top: 235px;
	    left: 75px;
	    width: 27px;
	    height: 29px;
	}
	
	
	.g {
		background-color: skyblue;
	    position: absolute;
	    top: 224px;
	    left: 100px;
	    width: 34px;
	    height: 27px;
	}
	
	
	.h {
	    background-color: skyblue;
	    position: absolute;
	    top: 224px;
	    left: 134px;
	    width: 34px;
	    height: 27px;
	}
	
	
	.i {
		background-color: skyblue;
	    position: absolute;
	    top: 233px;
	    left: 168px;    width: 26px;
	    height: 30px;
	}
	
	
	.j {
		background-color: skyblue;
	    position: absolute;
	    top: 256px;
	    left: 184px;
	    width: 28px;
	    height: 30px;
	}
	
	
	.k {
		background-color: skyblue;
	    position: absolute;
	    top: 285px;
	    left: 187px;
	    width: 40px;
	    height: 35px;
	}
	
	
	.l {
		background-color: skyblue;
	    position: absolute;
	    top: 320px;
	    left: 195px;
	    width: 42px;
	    height: 46px;
	}
</style>
</head>
<body>
	
	
	<div class="commonHeight90"></div>
	<div class="commonHeight10"></div>
	<div class="container" id="container">
		<div class="a">
			강리하 원아
		</div>
		<div class="b">
			2022. 10. 10
		</div>
		<div class="commonHeight60"></div>
		<div class="tmp">
			<div class="c">
			</div>
			<div class="d">
			</div>
			<div class="e">
			</div>
			<div class="f">
			</div>
			<div class="g">
			</div>
			<div class="h">
			</div>
			<div class="i">
			</div>
			<div class="j">
			</div>
			<div class="k">
			</div>
			<div class="l">
			</div>
		</div>
<!-- 		<div class="commonHeight100"></div> -->
<!-- 		<div class="c"> -->
<!-- 			9 -->
<!-- 		</div> -->
<!-- 		<div class="commonHeight100"></div> -->
<!-- 		<div class="c"> -->
<!-- 			30 -->
<!-- 		</div> -->
<!-- 		<div class="f"> -->
<!-- 			세심한 주의가 필요합니다. 꾸준히 관리해 주세요. -->
<!-- 		</div> -->
	
	</div>
	
	
	<div class="circle">
	</div>

<!-- Bootstrap core JavaScript-->
<script src="vendor/bootstrap/js/bootstrap.bundle.js"></script>
<!-- Core plugin JavaScript-->
<script src="vendor/jquery-easing/jquery.easing.js"></script>
<!-- Custom scripts for all pages-->
<script src="js/common/sb-admin-2.js"></script>
<!-- Page level plugins -->
<!-- <script src="vendor/chart.js/Chart.js"></script> -->
<!-- Page level custom scripts -->
<!-- <script src="js/demo/chart-area-demo.js"></script> -->
<!-- <script src="js/demo/chart-pie-demo.js"></script> -->
<!-- 공통적으로 사용하는 method (common.js)  -->
<script src="js/common.js"></script>
</body>
</html>