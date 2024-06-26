<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="/static/css/layout.css" />
</head>
<body>
<%@ include file="/views/layout/header.jsp" %>

<div id="container">
	<h1>회원가입</h1>
	<form action="/insertUser.do" method="POST" id="insertForm" onsubmit="checkData()">
		<table>
		    <tr>
		    	<td width="200" align="center">
		        	<b>아이디</b>
		        </td>
		        <td width="450" align="center">
		        	<input type="text" name="accountId" size="30" placeholder="아이디">
		        </td>
		    </tr>
		    <tr>
		    	<td width="200" align="center">
		        	<b>비밀번호</b>
		        </td>
		        <td width="450" align="center">
		        	<input type="password" name="passwd" size="30" placeholder="비밀번호">
		        </td>
		    </tr>
		    <tr>
		    	<td width="200" align="center">
		        	<b>이름</b>
		        </td>
		        <td width="450" height="20" align="center">
		        	<input type="text" name="username" size="30" placeholder="이름">
		        </td>
		    </tr>
		    <tr>
		    	<td width="200" align="center">
		        	<b>생년월일</b>
		        </td>
		        <td width="450" align="center">
		        	<input type="date" id="birthday" name="birthday" size="30">
		        </td>
		    </tr>
		    <tr>
		        <td colspan="2" align="center">
		        	<input type="submit" value="가입">
				</td>
		    </tr>
		</table>
	</form>
</div>

<%@ include file="/views/layout/footer.jsp" %>

<script type="text/javascript">
	// 생년월일 오늘 날짜 이후로 지정 못하게 설정
	window.onload = function() {
		today = new Date();
		today = today.toISOString().slice(0, 10);
		birthday = document.getElementById("birthday");
		birthday.max = today;
		birthday.value = today;
	}
	
	// 회원 정보 입력 값 검증
	function checkData() {
		let insertForm = document.getElementById("insertForm");
		
		if(insertForm.accountId.value == "") {
			
		}
	}	
</script>
</body>
</html>