<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메모 업데이트</title>
</head>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function memoUpdate(str){
		var userId = $('#userId').val();
		var measureDt = $('#measureDt').val(); 
		$.ajax({
			type:'POST',   //post 방식으로 전송
			url:'/premium/user/updateDiagCd.do',   //데이터를 주고받을 파일 주소
			data:JSON.stringify ({ //변수에 담긴 데이터를 전송해준다 (JSON 방식)
			
				"userId" : userId
				,"measureDt" : measureDt
				,"diagCd" : str
				
			}),
			dataType:'JSON', //데이터 타입 JSON
			contentType : "application/json; charset=UTF-8",
			success : function(data){   //파일 주고받기가 성공했을 경우. data 변수 안에 값을 담아온다.
				$('#message').html(data);  //현재 화면 위 id="message" 영역 안에 data안에 담긴 html 코드를 넣어준다. 
			}
		});
	}
</script>
<body>
		회원 아이디 : <input type="text" id="userId" name="userId"/>
		<br/>
		측정일(ex : 2022-01-01) : <input type="text" id="measureDt" name="measureDt"/>
		<br/>
		<input type="button" id="memo" value="치태" onclick="memoUpdate('A:01:1')" />		<br/>
		<input type="button" id="memo" value="치은염" onclick="memoUpdate('A:02:1')"/>			<br/>
		<input type="button" id="memo" value="부식" onclick="memoUpdate('B01')">              <br/>
		<input type="button" id="memo" value="유치앞니" onclick="memoUpdate('B02')"/>          <br/>
		<input type="button" id="memo" value="유치어금니" onclick="memoUpdate('B03')"/>        <br/>
		<input type="button" id="memo" value="유치다발성우식" onclick="memoUpdate('B04')"/>    <br/>
		<input type="button" id="memo" value="영구치어금니" onclick="memoUpdate('B05')"/>      <br/>
		<input type="button" id="memo" value="충치불소" onclick="memoUpdate('B06')"/>          <br/>
		<input type="button" id="memo" value="치료받은치아" onclick="memoUpdate('C01')"/>      <br/>
		<input type="button" id="memo" value="영구치어금니맹출" onclick="memoUpdate('C02')"/>  <br/>
		<input type="button" id="memo" value="혼합치열기" onclick="memoUpdate('C03')"/>        <br/>
		<input type="button" id="memo" value="깊은치아형태" onclick="memoUpdate('C04')"/>      <br/>
		<input type="button" id="memo" value="반대교합" onclick="memoUpdate('D01')"/>          <br/>
		<input type="button" id="memo" value="형태이상" onclick="memoUpdate('D02')"/>          <br/>
		<input type="button" id="memo" value="개수이상" onclick="memoUpdate('D03')"/>          <br/>
		<input type="button" id="memo" value="이갈이" onclick="memoUpdate('D04')"/>            <br/>
		<input type="button" id="memo" value="변색치" onclick="memoUpdate('D05')"/>            <br/>
		<input type="button" id="memo" value="반점치" onclick="memoUpdate('D06')"/>            <br/>
		<input type="button" id="memo" value="착색" onclick="memoUpdate('D07')"/>              <br/>
		<input type="button" id="memo" value="이상없음" onclick="memoUpdate('E01')"/>          <br/>
		<input type="button" id="memo" value="치과공포" onclick="memoUpdate('E02')"/>          <br/>
		<input type="button" id="memo" value="결석" onclick="memoUpdate('E03')"/>              <br/>
</body>
</html>


