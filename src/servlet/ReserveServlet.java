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

@WebServlet("/ReserveServlet")
public class ReserveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
	    rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session=request.getSession();
		MeetingRoom mr=(MeetingRoom)session.getAttribute("meetingRoom");
		ReservationBean reservation=(ReservationBean)session.getAttribute("reservation");
//		ReservationBean reservation=new ReservationBean("0301","2023-10-25","14:00","15:00","0000001");

		String errorReason=null;
		String nextpage;

		try{
			mr.reserve(reservation);
		}catch(Exception e) {
			e.getStackTrace();
			errorReason=e.getMessage();
			request.setAttribute("Ereason",errorReason);
		}

		if(errorReason==null) {
			nextpage="reserved.jsp";
		}else {
			nextpage="reserveError.jsp";
		}

		RequestDispatcher rd = request.getRequestDispatcher(nextpage);
		rd.forward(request, response);

	}
}
