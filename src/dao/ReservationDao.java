package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ReservationBean;

public class ReservationDao {

	public static List<ReservationBean> findByDate(String date){
		String URL="jdbc:mysql://localhost:3309/meetingroom?characterEncoding=UTF-8&serverTimezone=JST";
		String USER="root";
		String PASSWORD="root";

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<ReservationBean> list = new ArrayList<ReservationBean>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "SELECT * FROM reservation WHERE date = ? ORDER BY roomId";
			ps = con.prepareStatement(sql);
			ps.setString(1, date);
			rs = ps.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("id");
				String roomId=rs.getString("roomId");
				String start=rs.getString("start");
				String end=rs.getString("end");
				String userId=rs.getString("userId");

				ReservationBean rb = new ReservationBean(id,roomId,date,start,end,userId);
				list.add(rb);
			}
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}if(ps != null) {
					ps.close();
				}if(con != null) {
					con.close();
				}
			}catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
		return list;
	}


	public static boolean insert​(ReservationBean reservation) {
		String URL="jdbc:mysql://localhost:3309/meetingroom?characterEncoding=UTF-8&serverTimezone=JST";
		String USER="root";
		String PASSWORD="root";

		Connection con = null;
		PreparedStatement ps = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "INSERT INTO reservation (roomId,date,start,end,userId) VALUES(?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, reservation.getRoomId());
			ps.setString(2, reservation.getDate());
			ps.setString(3, reservation.getStart());
			ps.setString(4, reservation.getEnd());
			ps.setString(5, reservation.getUserId());

			int ret = ps.executeUpdate();
			return ret != 0;
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}finally {
			try {
				if(ps != null) {
					ps.close();
				}
				if(con != null) {
					con.close();
				}
			}catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	public static boolean delete​(ReservationBean reservation){
		String URL="jdbc:mysql://localhost:3309/meetingroom?characterEncoding=UTF-8&serverTimezone=JST";
		String USER="root";
		String PASSWORD="root";

		Connection con = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql = "DELETE FROM reservation WHERE roomId=? AND date = ? AND start = ? AND end = ? AND userId=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, reservation.getRoomId());
			ps.setString(2, reservation.getDate());
			ps.setString(3, reservation.getStart());
			ps.setString(4, reservation.getEnd());
			ps.setString(5, reservation.getUserId());

			int ret = ps.executeUpdate();
			return ret != 0;
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}finally {
			try {
				if(ps != null) {
					ps.close();
				}
				if(con != null) {
					con.close();
				}
			}catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

}


