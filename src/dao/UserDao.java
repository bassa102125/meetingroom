package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.UserBean;

public class UserDao {

	public static UserBean certificate(String id, String password) {
		String URL="jdbc:mysql://localhost:3309/meetingroom?characterEncoding=UTF-8&serverTimezone=JST";
		String USER="root";
		String PASSWORD="root";

		UserBean user=null;

		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			String sql="SELECT * FROM user WHERE id=? AND password=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, password);

			rs=ps.executeQuery();

			if(rs.next()) {
				String address=rs.getString("address");
				String name=rs.getString("name");
				user=new UserBean(address,id,name,password);
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
	return user;
}

}
