package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.MeetingRoom;
import bean.ReservationBean;
import bean.RoomBean;

@WebServlet("/ReserveCreateServlet")
public class ReserveCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
try {
		HttpSession session=request.getSession();
		MeetingRoom mr=(MeetingRoom)session.getAttribute("meetingRoom");

		String roomId = request.getParameter("roomId");
		String start = request.getParameter("time");

		RoomBean room=mr.getRoom(roomId);
		ReservationBean reservation=mr.createReservation(roomId, start);

		session.setAttribute("reservation",reservation );
		session.setAttribute("room",room );

		RequestDispatcher rd = request.getRequestDispatcher("reserveConfirm.jsp");
		rd.forward(request,response);
	}catch(Exception e) {
		e.getStackTrace();
	}
	}
}
