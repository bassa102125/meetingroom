<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.*,java.util.*,java.text.SimpleDateFormat,dao.*" %>


<%
HttpSession ses = request.getSession();
MeetingRoom mr=(MeetingRoom)session.getAttribute("meetingRoom");

RoomBean[] rooms=mr.getRooms();
String[] PERIOD=MeetingRoom.getPeriod();

Calendar cal=Calendar.getInstance();
SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
Date DATE=cal.getTime();

String mindate=sdf.format(DATE);
cal.add(Calendar.YEAR,1);
DATE=cal.getTime();
String maxdate=sdf.format(DATE);
List <ReservationBean> list=ReservationDao.findByDate(mr.getDate());
ReservationBean[][] pre=mr.getReservations();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>キャンセル入力画面</title>
 <link rel="stylesheet" href="css/style.css">
</head>

<body>
<div class="container">
<h2>利用日</h2>

<div class="frmbtn">
<div class="cds">
<form action="ChangeDateServlet" method="post">
<input type="hidden" name="url" value="cancel">
<input type="date" value="<%=mr.getDate()%>" min="<%=mindate%>" max="<%=maxdate%>" name="date" >
	<br>
	<div class="rescheduleBtn">
	<input type="submit"  class="button schedule" value="日付変更">
	</div>
</form>
</div>
</div>

<h2>キャンセル可能時間帯(<%=mr.getUser().getName() %>)</h2>
<table border="1">
<tr>
<th>会議室＿時間帯</th>
<%for(int i=0;i<PERIOD.length;i++){%>
<td><%=PERIOD[i]%></td>
<%} %>
</tr>
<%for(int i=0;i<rooms.length;i++){%>
	<%String roomId=rooms[i].getId(); %>
	<tr>
	<th>
	<%=rooms[i].getName()%>
	</th>
		<%for(int j=0;j<PERIOD.length;j++){%>
			<td>
			<form action="CancelCreateServlet" method="post">
				<input type="hidden" name="roomId" value=<%=roomId%>>
				<input type="submit"  name="time"  value=<%=PERIOD[j]%>
				<%if(pre[i][j]==null){%>
				disabled
				<%}else if(!pre[i][j].getUserId().equals(mr.getUser().getId())){ %>
				disabled
				<%} %>>
			</form>
			</td>
		<% }%>
	</tr>
<% }%>
</table>
<br>
<form action="menu.jsp"  method="post">
<div class="BackButton">
<button class="button" type="submit" >戻る</button>
</div>
</form>
</div>
</body>
</html>