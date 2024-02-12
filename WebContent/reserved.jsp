<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="bean.MeetingRoom,bean.UserBean,bean.ReservationBean,bean.RoomBean,dao.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>会議室予約確認</title>
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

    String roomId=reservation.getRoomId();
    String start=reservation.getStart();


    RoomBean[] rooms=mr.getRooms();
    String[] PERIOD=MeetingRoom.getPeriod();
	ReservationBean[][] pre= new ReservationBean[rooms.length][PERIOD.length];
	pre=mr.getReservations();

    int resId=-1;
    for(int i=0;i<rooms.length;i++){
    	for(int j=0;j<PERIOD.length;j++){
    		if(pre[i][j]!=null){
	    		if(start.equals(pre[i][j].getStart().substring(0, 5))&&roomId.equals(pre[i][j].getRoomId())){
    				resId=pre[i][j].getId();
					}
    			}
    		}
    	}

    %>


    <div class="name">ログイン中：<%= mr.getUser().getName()%>さん</div>
    <hr>
    <div class="Confirmation">
      <h1>予約完了</h1>
        <p>予約ID　<%=resId %></p>
        <p>予約日　<%= reservation.getDate()%></p>
        <p>会議室　<%=  room.getName() %></p>
        <p>予約時刻　<%= reservation.getStart()%>～<%=reservation.getEnd()%></p>
        <p>予約者　<%= mr.getUser().getName()%>
        <br>
        <div class="ButtonGroup">
          <div class="BackButton">
          <form action ="menu.jsp" method="post">
                  <button type="submit" class="BackButtonText">完了</button>
              </form>
            </div>
    </div>


  </div>
</body>
</html>
