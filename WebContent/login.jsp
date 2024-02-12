<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会議室予約ログイン</title>
<link rel="icon" href="favicon.ico">
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="container">
		<img class="kaigi" src="images/kaigi.png" alt="会議室予約システム">
		<div class="login-form">
			<h1>会議室予約</h1>
			<form action="LoginServlet" method="post">
				<div class="form-group">
					<input type="text" class="input-field" name="userId"
						placeholder="利用者ID" value="0000001">
				</div>
				<div class="form-group">
					<input type="password" class="input-field" name="userPw"
						placeholder="パスワード" value="aaaaa">
				</div>
				<button class="login-button" type="submit">ログイン</button>
		</div>
		</form>
	</div>
</body>
</html>
