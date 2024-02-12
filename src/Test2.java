import dao.UserDao;

public class Test2 {

	public static void main(String[] args) {

		String userId="0000001";
		String userPw="aaaaa";
		
		System.out.println(UserDao.certificate(userId,userPw));
		
	}

}


<%for(int k=0;k<list.size()-1;k++){
	ReservationBean pre=list.get(k);%>
	<input type="submit"  name="time"  value=<%=PERIOD[j]%>
		<%if(roomId.equals(pre.getRoomId())&&PERIOD[j].equals(pre.getStart())){ %>
		disabled="disabled"
		<%} %>
	>
	<% }%>