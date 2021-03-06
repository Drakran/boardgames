package boardgame;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class createMeetup
 */
@WebServlet("/createMeetup")
public class createMeetup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createMeetup() {
        super();
        
        // TODO Auto-generated constructor stub
    }
    
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String createMeetupError = null;
		HttpSession session = request.getSession();
		System.out.println("print1: "+request.getSession().getId());
		String forwardUrl = "/MeetupServlet";
		String gameName = request.getParameter("gameName");
		String meetTime = request.getParameter("meetTime");
		String frequency = request.getParameter("frequency");
		int capacity = Integer.parseInt(request.getParameter("capacity"));
		String location = request.getParameter("location");
		accessData access = new accessData();
		User user = (User) session.getAttribute("userObject");
		access.createMeetup(user, gameName, capacity, location, meetTime, frequency);
		request.setAttribute("createMeetupError", createMeetupError);
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher(forwardUrl);
        dispatch.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
