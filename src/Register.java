

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String registerError = null;
		HttpSession session = request.getSession();
		String forwardUrl = "/register.jsp";
		//Get all the parameters
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String cPassword = request.getParameter("cPassword");
		User user = new User(username,password);
		accessData access = new accessData();
		int userExist = access.registerUser(user, cPassword);
		if(userExist == 0) {
			registerError = "This username is already taken";
		}else if (userExist == 1) {
			registerError = "The passwords don't match partner";
		}else if (userExist == 2) {
			forwardUrl = "/homePage.jsp";
			session.setAttribute("connected", "true");
			session.setAttribute("username", username);
		}else if(userExist == 3) {
			registerError = "No username put";
		}else if(userExist == 4) {
			registerError = "No password put";
		}
		else {
			//Shouldn't be here, like the method only returns 0,1,or 2. So probably userExist never set
		}
		request.setAttribute("registerError", registerError);
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
