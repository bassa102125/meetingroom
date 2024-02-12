import java.util.List;

import bean.MeetingRoom;
import bean.ReservationBean;
import bean.RoomBean;
import dao.ReservationDao;

public class Test {

	public static void main(String[] args) {

		List<ReservationBean> list=ReservationDao.findByDate("2023-10-25");
		for(int i=0;i<=list.size()-1;i++) {
		System.out.println(list.get(i));
		}
		System.out.println();

		try {
		MeetingRoom mr=new MeetingRoom();
		mr.login("0000001", "aaaaa");
		mr.setDate("2023-10-25");
		ReservationBean[][] pre=mr.getReservations();

		RoomBean[] rooms=mr.getRooms();
		String[] PERIOD=MeetingRoom.getPeriod();

		for(int i=0;i<rooms.length;i++) {
			for(int j=0;j<PERIOD.length;j++) {
				System.out.println(pre[i][j]);
			}
			System.out.println();
		}

//		RoomBean room=mr.getRoom("0201");
//		System.out.println(room.getName());
//		ReservationBean reservation=mr.createReservation("0301", "16:00");
//		System.out.println(reservation);
//		mr.reserve(reservation);




		}catch(Exception e) {
			e.printStackTrace();
		}
		}
	}

