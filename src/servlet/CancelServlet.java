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

@WebServlet("/CancelServlet")
public class CancelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}


	//サーブレット仕様書が誤っている？
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session=request.getSession();
		MeetingRoom mr=(MeetingRoom)session.getAttribute("meetingRoom");
		ReservationBean reservation=(ReservationBean) session.getAttribute("reservation");

		String errorReason=null;
		String nextpage;

		try {
			mr.cancel(reservation);
		}catch(Exception e) {
			errorReason=e.getMessage();
		}
		if(errorReason!=null) {
			nextpage="cancelError.jsp";
		}else {
			nextpage="canceled.jsp";
		}

		RequestDispatcher rd = request.getRequestDispatcher(nextpage);
		rd.forward(request, response);

	}

}
