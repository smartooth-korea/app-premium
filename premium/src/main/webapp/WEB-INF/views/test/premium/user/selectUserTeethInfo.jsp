<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 치아 정보 조회</title>
</head>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#submit').click(function(){ 
		$.ajax({
			type:'POST',   //post 방식으로 전송
			url:'/premium/user/selectUserTeethInfo.do',   //데이터를 주고받을 파일 주소
			data:JSON.stringify ({
				
				"userId" : "test"
				,"userNo" : "KR-G-0010"
				,"userAuthToken" : 
					"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJ0ZXN0MDBAdGVzdDAwLmNvbSIsImlhdCI6MTY1Mzk1NzQ5NCwiZXhwIjoxNjUzOTcxODk1fQ.XNREAIghAIaAqID2GfeUiMFRBOH9fLkXe34s6mJ1qbI"
				
			}),
			dataType:'JSON', //데이터 타입 JSON
			contentType : "application/json; charset=UTF-8",
			success : function(data){   //파일 주고받기가 성공했을 경우. data 변수 안에 값을 담아온다.
				$('#message').html(data);  //현재 화면 위 id="message" 영역 안에 data안에 담긴 html 코드를 넣어준다. 
			}
		});
	});
});
</script>
<body>
		회원 치아 정보 조회 >>> <input type="button" id="submit" value="버튼"/>
</body>
</html>