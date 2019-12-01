package boardgame;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Meetup
 */
@WebServlet("/MeetupServlet")
public class MeetupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MeetupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String createMeetupError = null;
		HttpSession session = request.getSession();
		System.out.println("print1: "+request.getSession().getId());
		String forwardUrl = "/homepage.jsp";
		String username = (String) session.getAttribute("username");
		accessData access = new accessData();
		if(username == null || username == "") {
			if(access.getAllMeetups() == null) {
				System.out.println("servlet null");
			}
			else {
				System.out.println("servlet not null");
			}
			request.setAttribute("meetupArray", access.getAllMeetups());
		}
		else {
			//method to call meetups
			User user = (User)session.getAttribute("userObject");
			request.setAttribute("meetupArray", access.getMeetupResults(user));
		}
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
