<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>join.jsp</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
<script type="text/javascript" src="script/scripts.js" charset="utf-8"></script>
</head>
<body>
	<p class="titleStr">
		Join Member
	</p>
	<form name="joinForm">
		<div class="centerBox">
			<label for="u_id">ID :</label>
			<input type="text" name="u_id" title="ID" /><br/>
			<label for="u_pw">PW :</label>
			<input type="password" name="u_pw" title="Password" /><br/>
			<label for="u_re">Retry :</label>
			<input type="password" name="u_re" title="Retry" /><br/>
			<label for="u_name">Name :</label>
			<input type="text" name="u_name" title="Name" /><br/>
			<label for="u_nick">Name :</label>
			<input type="text" name="u_nick" title="Nickname" /><br/>
			<div class="btns">
				<input type="button" value="회원가입"
					onclick="chkAndMove(this.form, 'doJoin', true)"/>
				<input type="button" value="취소"
					onclick="justMove(this.form, 'canclJoin')"/>
			</div>
		</div>
		<div class="msgBox" id="msg">
			<%= session.getAttribute("msg") %>
		</div>
		<input type="hidden" name="cmd"/>
	</form>
</body>
</html>
<%@ include file="jspfs/removeMsg.jspf"%>
