package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.TipoUsuario;
import model.Usuario;

import controller.LoginController;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		/*
		 * PrintWriter out = response.getWriter();
		 * 
		 * if(email.equals("email") && senha.equals("senha")){
		 * out.println(email); out.println(senha); }else{
		 * response.sendRedirect("index.jsp"); }
		 */

		try {
			String email = request.getParameter("login_email");
			String senha = request.getParameter("login_senha");

			LoginController loginController = new LoginController();
			Usuario usuario = loginController.logar(email, senha);

			if (usuario == null) {
				response.sendRedirect("index.jsp");
			} else {
				if (usuario.getTipo().equals(TipoUsuario.USUARIO)) {
					response.sendRedirect("index.jsp");
				} else if (usuario.getTipo().equals(TipoUsuario.ADMINISTRADOR)) {
					response.sendRedirect("admin/dashboard.jsp");
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
