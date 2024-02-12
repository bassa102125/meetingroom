package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.RoomBean;

public class RoomDao {

	public static RoomBean[] findAll() {
		String URL="jdbc:mysql://localhost:3309/meetingroom?characterEncoding=UTF-8&serverTimezone=JST";
		String USER="root";
		String PASSWORD="root";

		RoomBean[] rooms=null;

		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql="SELECT * FROM room";
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();

			String id;
			String name;

			 int rowCount = 0;
		        if (rs.last()) {
		            rowCount = rs.getRow();
		            rs.beforeFirst();
		        }

		        rooms=new RoomBean[rowCount];

			int i=0;

			while(rs.next()) {
				id=rs.getString("id");
				name=rs.getString("name");
				rooms[i]=new RoomBean(id,name);
				i++;
			}
		}catch(Exception e) {
				System.out.println(e.getMessage());
		}finally {
			try{
				if(rs!=null) {
					rs.close();
				}
				if(ps!=null) {
					ps.close();
				}
				if(con!=null) {
					con.close();
				}
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return rooms;
	}
}


