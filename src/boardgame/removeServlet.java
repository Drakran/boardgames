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
 * Servlet implementation class removeServlet
 */
@WebServlet("/removeServlet")
public class removeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public removeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Get session
		
		HttpSession session = request.getSession(false);
		String forwardUrl = "/MeetupServlet";
		System.out.println(request.getParameter("id"));
		int meetupID = Integer.valueOf(request.getParameter("id"));
		accessData access = new accessData();
		User user = (User) session.getAttribute("userObject");
		access.removeMeetup(user, meetupID);
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
