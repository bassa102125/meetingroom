<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="bean.MeetingRoom,bean.UserBean"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>会議室予約メニュー</title>
        <link rel="icon" href="favicon.ico">
        <link rel="stylesheet" href="css/style.css">
</head>

<% MeetingRoom mr = (MeetingRoom)session.getAttribute("meetingRoom"); %>

<body>
    <div class="container">
        <h1>会議室予約<br>メニュー</h1>
        <div class="name">ようこそ、<%= mr.getUser().getName()%>さん</div>
        <hr>
        <img class="top" src="images/kaigi.png">
        <div class="buttons">
            <!-- 会議室予約画面へ-->
            <form action="reserveInput.jsp" method="post">
                <button type="submit" class="button">会議室予約</button>
            </form>

            <!--予約キャンセルへ-->
            <form action="cancelInput.jsp" method="post">
                <button type="submit" class="button">予約キャンセル</button>
            </form>

            <!--ログアウトはログイン画面に-->
            <form action="login.jsp" method="post">
                <button type="submit" class="button">ログアウト</button>
            </form>
        </div>
    </div>
</body>
</html>
