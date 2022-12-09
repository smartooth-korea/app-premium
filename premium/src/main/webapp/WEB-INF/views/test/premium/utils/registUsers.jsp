<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 회원 CVS 등록</title>
</head>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
// $(document).ready(function(){
// 	$('#submit').click(function(){
// 		var queryString = $("form[name=frm]").serialize() ;
// 		$.ajax({
// 			type:'POST',   //post 방식으로 전송
// 			url:'/premium/utils/registUsers.do',   //데이터를 주고받을 파일 주소
// 			data : queryString,
// 			dataType:'JSON', //데이터 타입 JSON
// 			contentType : "application/json; charset=UTF-8",
// 			success : function(data){   //파일 주고받기가 성공했을 경우. data 변수 안에 값을 담아온다.
// 				alert(data);  //현재 화면 위 id="message" 영역 안에 data안에 담긴 html 코드를 넣어준다. 
// 			},
// 			error: function(xhr, status, error){
// 				alert(error);
// 			}
// 		});
// 	});
// });
</script>
<body>
	<form name="frm" id="frm" action="/premium/utils/registUsers.do" method="post" enctype="multipart/form-data">
		회원 등록 양식<br/>
		<input type="file" id="file" name="file"/><br/>
		<button type="submit">업로드</button>
	</form>
		
</body>
</html>