package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.CategoriaController;

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
		Integer idCategoria = Integer.parseInt(request.getParameter("id"));
		String acao = request.getParameter("acao");
		
		if(acao.equals("Alterar")){
			Categoria categoria = new Categoria();
			CategoriaController controller = null;
			try {
				controller = new CategoriaController();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				categoria = controller.buscar(idCategoria);
				
				request.setAttribute("categoria", categoria);
				RequestDispatcher disp = request.getRequestDispatcher("/admin/Categoria/Alterar.jsp");
				disp.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(acao.equals("Excluir")){
			CategoriaController controller = null;
			try {
				controller = new CategoriaController();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				controller.excluir(idCategoria);

				response.sendRedirect("/Canoinha/admin/Categoria/index.jsp?msg=Categoria Excluido com Sucesso");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		if (acao.equals("CadastrarCategoria")){
			adicionarCategoria(request, response);
		}else if(acao.equals("AlterarCategoria")){
			alterarCategoria(request, response);
		}else if(acao.equals("RetornarMenudeCategorias")){
			retornarMenuDeCategorias(request, response);
		}else if(acao.equals("ListarCategorias")){
			listarCategorias(request, response);
		}
	}
	
	private void listarCategorias(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
 			DaoCategoria daoCategoria = new DaoCategoria(Conexao.getConexao());
 			List<Categoria> categorias = daoCategoria.buscar();
 			
 			for (Categoria categoria : categorias) {
 				out.println(
 							"<tr>"+
 						    "<td>"+categoria.getId()+"</td>"+
 						    "<td>"+categoria.getNome()+"</td>"+
 						    "<td><a href=/Canoinha/CategoriaServlet?id='"+categoria.getId()+"'&acao='Alterar'>Atualizar <i class='icon-edit'></i></a></td>"+
 					    	"<td><a href=/Canoinha/CategoriaServlet?id='"+categoria.getId()+"&acao='Excluir'>Excluir <i class='icon-trash'></i></a></td>"+
 					    	"</tr>"
 						   );				  
 			}
 		} catch (SQLException e) {			
 			e.printStackTrace();
 		}
	} 

	private void alterarCategoria(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");
		HttpSession session = request.getSession(true);
		
		Categoria categoria  = new Categoria();
		categoria.setId(id);
		categoria.setNome(nome);
		
		CategoriaController controller = null;
		try {
			controller = new CategoriaController();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			controller.atualizar(categoria);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("/Canoinha/admin/Categoria/index.jsp?msg=Categoria alterado com Sucesso");	
	}
	
	private void retornarMenuDeCategorias(HttpServletRequest request,
	HttpServletResponse response) throws IOException{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
 			DaoCategoria daoCategoria = new DaoCategoria(Conexao.getConexao());
 			List<Categoria> categorias = daoCategoria.buscar();
 			
 			for (Categoria categoria : categorias) {
 				out.println("<li><a href='#'>"+categoria.getNome()+"</a></li>");				  
 			}
 		} catch (SQLException e) {			
 			e.printStackTrace();
 		}		
	} 

	private void adicionarCategoria(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String nome = request.getParameter("nome");
		HttpSession session = request.getSession(true);
		
		Categoria categoria = new Categoria();
		categoria.setNome(nome);		
		
		DaoCategoria daoCategoria = null;
		try {
			daoCategoria = new DaoCategoria(Conexao.getConexao());
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			daoCategoria.inserir(categoria);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		response.sendRedirect("/Canoinha/admin/indexCategoria.jsp?msg=Categoria Cadastrado com Sucesso");
	}
}
