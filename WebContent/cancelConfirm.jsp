<%@page import="bean.MeetingRoom,bean.UserBean,bean.ReservationBean,bean.RoomBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>会議室予約キャンセル</title>
		<link rel="icon" href="favicon.ico">
		<link rel="stylesheet" href="css/style.css">
	</head>
<body>
<div class="container">
	<h1>会議室予約キャンセル</h1>

	<%
    MeetingRoom mr = (MeetingRoom)session.getAttribute("meetingRoom");
    RoomBean room=(RoomBean)session.getAttribute("room");
    ReservationBean reservation=(ReservationBean)session.getAttribute("reservation");
    %>

		<div class="name">
			ログイン中：<%= mr.getUser().getName()%>さん
		</div>
		<hr>
		<div class="Confirmation">
			<h1>この予約をキャンセルしますか？</h1>
			<p>予約日　<%= mr.getDate()%></p>
			<p>会議室　<%= room.getName() %></p>
			<p>予約時刻　<%= reservation.getStart()%></p>
			<p>予約者　<%= mr.getUser().getName()%></p>
			<br>
			<div class="ButtonGroup">
				<div class="BackButton">
					<form action="cancelInput.jsp" method="post">
						<button type="submit" class="BackButtonText">戻る</button>
					</form>
				</div>
				<div class="DecisionBtn">
 				  	<form action="CancelServlet" method="post">
 						<button type="submit"  class = "YesButtonText">決定</button>
 					</form>
				</div>
			</div>


		</div>
</body>
</html>
