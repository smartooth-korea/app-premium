<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<link rel="shortcut icon" type="image/x-icon"
	href="/imgs/common/logo_img_ori.png">
<link rel="stylesheet" href="/css/common/layout.css">
<link rel="stylesheet" href="/css/premium/statistics/main.css">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
</head>
<body>
	<div class="menu">
		<button class="btn_download">스크린샷</button>
	</div>
	<div class="container" id="container">

		<div class="container-top">
			<div class="container-margin"></div>
			<div id="userName" class="a">${userInfo[0].userName} 원아</div>

			<div id="measuerDt" class="b">${dataList[0].measureDt}</div>
		</div>

		<div class="contentsWrap">
			<div class="contentsWrap1 toothCondition">

				<div id="t51">
					<img id="tooth51" class="teeth" alt="51번치아" src="/imgs/tooth/empty.png">
				</div>
				<div id="t52">
					<img id="tooth52" class="teeth" alt="52번치아" src="/imgs/tooth/empty.png">
				</div>
				<div id="t53">
					<img id="tooth53" class="teeth" alt="53번치아" src="/imgs/tooth/empty.png">
				</div>
				<div id="t54">
					<img id="tooth54" class="teeth" alt="54번치아" src="/imgs/tooth/empty.png">
				</div>
				<div id="t55">
					<img id="tooth55" class="teeth" alt="55번치아" src="/imgs/tooth/empty.png">
				</div>
				<div id="t61">
					<img id="tooth61" class="teeth" alt="61번치아" src="/imgs/tooth/empty.png">
				</div>
				<div id="t62">
					<img id="tooth62" class="teeth" alt="62번치아" src="/imgs/tooth/empty.png">
				</div>
				<div id="t63">
					<img id="tooth63" class="teeth" alt="63번치아" src="/imgs/tooth/empty.png">
				</div>
				<div id="t64">
					<img id="tooth64" class="teeth" alt="64번치아" src="/imgs/tooth/empty.png">
				</div>
				<div id="t65">
					<img id="tooth65" class="teeth" alt="65번치아" src="/imgs/tooth/empty.png">
				</div>
				<div id="t71">
					<img id="tooth71" class="teeth" alt="71번치아" src="/imgs/tooth/empty.png">
				</div>
				<div id="t72">
					<img id="tooth72" class="teeth" alt="72번치아" src="/imgs/tooth/empty.png">
				</div>
				<div id="t73">
					<img id="tooth73" class="teeth" alt="73번치아" src="/imgs/tooth/empty.png">
				</div>
				<div id="t74">
					<img id="tooth74" class="teeth" alt="74번치아" src="/imgs/tooth/empty.png">
				</div>
				<div id="t75">
					<img id="tooth75" class="teeth" alt="75번치아" src="/imgs/tooth/empty.png">
				</div>
				<div id="t81">
					<img id="tooth81" class="teeth" alt="81번치아" src="/imgs/tooth/empty.png">
				</div>
				<div id="t82">
					<img id="tooth82" class="teeth" alt="82번치아" src="/imgs/tooth/empty.png">
				</div>
				<div id="t83">
					<img id="tooth83" class="teeth" alt="83번치아" src="/imgs/tooth/empty.png">
				</div>
				<div id="t84">
					<img id="tooth84" class="teeth" alt="84번치아" src="/imgs/tooth/empty.png">
				</div>
				<div id="t85">
					<img id="tooth85" class="teeth" alt="85번치아" src="/imgs/tooth/empty.png">
				</div>

			</div>
			<div class="contentsWrap2 cavityAmount">
				<div class="cavityValueHeight-top"></div>
				<div class="cavityValueHeight-button">
					<!-- 버튼 넣어야함 -->
				</div>
				<div class="cavityValueHeight-header"></div>

				<div class="cavityValue danger">${dataList[0].cavityDanger}</div>

				<div class="cavityValueHeight"></div>

				<div class="cavityValue caution">
					${dataList[0].cavityCaution}</div>

				<div class="cavityValueHeight"></div>

				<div class="cavityValue normal">${dataList[0].cavityNormal}</div>

				<div class="commonWidth125Height25"></div>
				<div class="commonWidth125Height25"></div>
				<div class="commonWidth125Height25"></div>
				<div class="commonWidth125Height25"></div>
				<div class="commonWidth125Height25"></div>
			</div>
			<div class="contentsWrap3 comment">
				<div class="comment-top"></div>
				<div class="title">
					<div id="title">"세심한 주의가 필요합니다. 꾸준히 관리해주세요."</div>
				</div>
				<div class="contents">
					<div id="contents">${dataList[0].memoTxt}</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript-->
	<script src="vendor/bootstrap/js/bootstrap.bundle.js"></script>
	<script src="vendor/jquery-easing/jquery.easing.js"></script>
	<script src="js/common/sb-admin-2.js"></script>
	<script src="js/common.js"></script>
	<script src="js/premium/statistics/main.js"></script>
	<script src="js/premium/statistics/html2canvas.js"></script>
	<script type="text/javascript">
		
	
	// 값을 가져와서 데이터를 확인하여 어떤 색으로 변경할지 정해줘야함
	var dataList = new Array();
	
	var index = 0;
	
	<c:forEach items="${dataList}" var="list">
		dataList.push({
			userId:"${list.userId}"
			,t51:"${list.t04}"
			,t52:"${list.t05}"
			,t53:"${list.t06}"
			,t54:"${list.t07}"
			,t55:"${list.t08}"
			,t61:"${list.t09}"
			,t62:"${list.t10}"
			,t63:"${list.t11}"
			,t64:"${list.t12}"
			,t65:"${list.t13}"
			,t71:"${list.t20}"
			,t72:"${list.t21}"
			,t73:"${list.t22}"
			,t74:"${list.t23}"
			,t75:"${list.t24}"
			,t81:"${list.t25}"
			,t82:"${list.t26}"
			,t83:"${list.t27}"
			,t84:"${list.t28}"
			,t85:"${list.t29}"
			,cavityNormal:"${list.cavityNormal}"
			,cavityCaution:"${list.cavityCaution}"
			,cavityDanger:"${list.cavityDanger}"
			,memoTxt:"${list.memoTxt}"	
			,measureDt:"${list.measureDt}"
		});
	</c:forEach>
		
	// 치아 색상 변경		
	changeToothColorByLevel(dataList, index);
	
	
	// 버튼 클릭시 스크린샷 후 저장
	$(".btn_download").click(function(e){
		html2canvas(document.getElementById("container")).then(function(canvas) {
        // html2canvas(document.body).then(function(canvas) {
            var el = document.createElement("a")
            el.href = canvas.toDataURL("image/jpeg");
            el.download = '이미지.jpg'; //다운로드 할 파일명 설정
            el.click();
        })
    })
	
	</script>
</body>
</html>