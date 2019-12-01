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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String loginError = null;
		HttpSession session = request.getSession();
		System.out.println("print1: "+request.getSession().getId());
		String forwardUrl = "/login.jsp";
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = new User(username,password);
		accessData access = new accessData();
		int userExist = access.validateUser(user);
		if(userExist == 0) {
			loginError = "This user does not exist";
		}else if (userExist == 1) {
			loginError = "The password is wrong partner";
		}else if (userExist == 2) {
			loginError = "Success";
			forwardUrl = "/homepage.jsp";
			session.setAttribute("connected", "true");
			session.setAttribute("username", username);
			session.setAttribute("ownList", user.getOwned());
			session.setAttribute("wishList", user.getWish());
			session.setAttribute("meetList", user.getMeetups());
		}
		else {
			//Shouldn't be here, like the method only returns 0,1,or 2. So probably userExist never set
		}
		request.setAttribute("loginError", loginError);
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
