package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Categoria;
import model.Conexao;

import dao.DaoCategoria;

/**
 * Servlet implementation class CategoriaServlet
 */
public class CategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoriaServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Conexao conexao = new Conexao();
		try {
			DaoCategoria daoCategoria = new DaoCategoria(conexao);
			List<Categoria> categorias = daoCategoria.buscar();
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			
			for (Categoria categoria : categorias) {
				out.println("<li><a href='#'>"+categoria.getNome()+"</a></li>");				  
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}	
	}
}
