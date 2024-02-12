<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="bean.MeetingRoom,bean.UserBean,bean.ReservationBean,bean.RoomBean"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>会議室予約</title>
        <link rel="icon" href="favicon.ico">
        <link rel="stylesheet" href="css/style.css">
</head>
<body>
  <div class="container">
    <h1>会議室予約</h1>

    <%
    MeetingRoom mr = (MeetingRoom)session.getAttribute("meetingRoom");
    RoomBean room=(RoomBean)session.getAttribute("room");
    ReservationBean reservation=(ReservationBean)session.getAttribute("reservation");
    %>

    <div class="name">ログイン中：<%= mr.getUser().getName()%>さん</div>
    <hr>
    <div class="Confirmation">
      <h1>予約エラー</h1>
      <%=request.getAttribute("Ereason")%>
        <p>予約日　<%= mr.getDate()%></p>
        <p>会議室　<%=  room.getName() %></p>
        <p>予約時刻　<%= reservation.getStart()%>～<%=reservation.getEnd()%></p>
        <p>予約者　<%= mr.getUser().getName()%></p>
        <br>
        <div class="ButtonGroup">
          <div class="BackButton">
          <form action ="menu.jsp" method="post">
                  <button type="submit" class="BackButtonText">確認</button>
              </form>
            </div>
    </div>


  </div>
</body>
</html>
