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

@WebServlet("/ChangeDateServlet")
public class ChangeDateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		MeetingRoom mr=(MeetingRoom) session.getAttribute("meetingRoom");

		String date=request.getParameter("date");

		mr.setDate(date);

		session.setAttribute("meetingRoom", mr);

		String rURL=request.getParameter("url");
        String page = null;

        if (rURL != null) {
            if (rURL.contains("reserve")) {
                page = "reserveInput.jsp";
            } else if (rURL.contains("cancel")) {
            	page = "cancelInput.jsp";
            }
            RequestDispatcher rd = request.getRequestDispatcher(page);
            rd.forward(request, response);
        }
	}
}