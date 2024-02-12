package bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dao.ReservationDao;
import dao.RoomDao;
import dao.UserDao;

public class MeetingRoom implements Serializable {

	private static final int INTERVAL = 60;
	private static final String[] PERIOD = { "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00" };
	private static final long serialVersionsUID = 1L;
	private RoomBean[] rooms;
	private String date;
	private UserBean user;

	public MeetingRoom() {
		rooms = RoomDao.findAll();
		Calendar cal = Calendar.getInstance();
		Date DATE = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		date = sdf.format(DATE);
	}

	//getter
	public String getDate() {
		return date;
	}

	public RoomBean getRoom(String roomId) {
		RoomBean room = null;
		for (RoomBean a : rooms) {
			if (a.getId().equals(roomId)) {
				room = a;
			}
		}
		return room;
	}

	public static String[] getPeriod() {
		return PERIOD;
	}

	public ReservationBean[][] getReservations() {
		ReservationBean[][] reservations = new ReservationBean[rooms.length][PERIOD.length];
		List<ReservationBean> list = ReservationDao.findByDate(date);
		for (int i = 0; i < rooms.length; i++) {
			for (int j = 0; j < PERIOD.length; j++) {
				for (int k = 0; k < list.size(); k++) {
					ReservationBean res = list.get(k);
					if (rooms[i].getId().equals(res.getRoomId()) && PERIOD[j].equals(res.getStart().substring(0, 5))) {
						reservations[i][j] = list.get(k);
					}
				}
			}
		}
		return reservations;
	}

	public UserBean getUser() {
		return user;
	}

	public RoomBean[] getRooms() {
		return rooms;
	}

	//setter
	public void setDate(String date) {
		this.date = date;
	}

	//その他
	private int startPeriod(String start) throws IndexOutOfBoundsException {
		int i = 0;
		int x = 0;
		try {
			for (String p : PERIOD) {
				if (p.equals(start)) {
					x = i;
				}
				i++;
			}
			return x;
		} catch (IndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
			return -1;
		}
	}

	public ReservationBean createReservation(String roomId, String start) {
		try {
			int i = this.startPeriod(start);
			ReservationBean reservation = new ReservationBean(roomId, this.date, start, PERIOD[i + 1], user.getId());
			return reservation;
		} catch (IndexOutOfBoundsException e) {
			String end = "17:00";
			ReservationBean reservation = new ReservationBean(roomId, this.date, start, end, user.getId());
			return reservation;
		}
	}

	public void reserve(ReservationBean reservation) throws Exception {

		String strReservation = reservation.getDate() + "/" + reservation.getStart();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd/HH:mm");
		Date dateReservation = sdf.parse(strReservation);

		Calendar cal = Calendar.getInstance();
		Date dateNow = cal.getTime();

		if (dateNow.compareTo(dateReservation) < 0) {
			if (ReservationDao.insert​(reservation)) {
			} else {
				throw new Exception("すでに予約が存在しています。");
			}
		} else {
			throw new Exception("時間が過ぎているため予約が完了しませんでした。");
		}
	}

	public void cancel(ReservationBean reservation) throws Exception {
		String strReservation = reservation.getDate() + "/" + reservation.getStart();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd/HH:mm");
		Date dateReservation = sdf.parse(strReservation);

		Calendar cal = Calendar.getInstance();
		Date dateNow = cal.getTime();
		if (dateNow.compareTo(dateReservation) < 0) {
			if (ReservationDao.delete​(reservation)) {
			} else {
				throw new Exception("既にキャンセルされています。");
			}
		} else {
			throw new Exception("時刻が過ぎているためキャンセルできません。");
		}
	}

	public boolean login(String id, String password) {
		user = UserDao.certificate(id, password);
		if (user != null) {
			return true;
		} else {
			return false;
		}
	}

	private int roomIndex(String roomId) throws IndexOutOfBoundsException {
		int i = 0;
		int x = 0;
		for (RoomBean a : rooms) {
			if (roomId.equals(a.getId())) {
				x = i;
			}
			i++;
		}
		return x;
	}

	public String toString() {
		return "meetingroom.java";
	}

}
