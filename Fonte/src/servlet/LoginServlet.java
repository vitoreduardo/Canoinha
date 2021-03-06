package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.TipoUsuario;
import model.Usuario;

import controller.LoginController;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String email = request.getParameter("login_email");
			String senha = request.getParameter("login_senha");
			HttpSession session = request.getSession();

			LoginController loginController = new LoginController();
			Usuario usuario = loginController.logar(email, senha);

			if (usuario == null) {
				response.sendRedirect("index.jsp");
			} else {				
				if (usuario.getTipo().equals(TipoUsuario.USUARIO)) {
					session.setAttribute("usuario", usuario);
					response.sendRedirect("index.jsp");
				} else if (usuario.getTipo().equals(TipoUsuario.ADMINISTRADOR)) {
					session.setAttribute("administrador", usuario);
					response.sendRedirect("admin/dashboard.jsp");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
