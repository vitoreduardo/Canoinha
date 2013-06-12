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

public class CategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private void alterarCategoriaGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try
		{
			Categoria categoria = new Categoria();
			CategoriaController controller = new CategoriaController();
			categoria = controller.buscar(Integer.parseInt(request.getParameter("id")));
		
			request.setAttribute("categoria", categoria);
			RequestDispatcher disp = request.getRequestDispatcher("/admin/Categoria/Alterar.jsp");
			disp.forward(request, response);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
	}
	
	private void excluirCategoria(HttpServletRequest request, HttpServletResponse response) throws IOException{	
		try 
		{
			CategoriaController controller = new CategoriaController();
			controller.excluir(Integer.parseInt(request.getParameter("id")));

			response.sendRedirect("/Canoinha/admin/Categoria/index.jsp?msg=Categoria Excluido com Sucesso");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		if(acao.equals("Alterar")){
			alterarCategoriaGet(request, response);
		}else if(acao.equals("Excluir")){
			excluirCategoria(request, response);    
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		if (acao.equals("CadastrarCategoria")){
			adicionarCategoria(request, response);
		}else if(acao.equals("AlterarCategoria")){
			alterarCategoriaPost(request, response);
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
 						    "<td><a href=/Canoinha/CategoriaServlet?id="+categoria.getId()+"&acao=Alterar>Atualizar <i class='icon-edit'></i></a></td>"+
 					    	"<td><a href=/Canoinha/CategoriaServlet?id="+categoria.getId()+"&acao=Excluir>Excluir <i class='icon-trash'></i></a></td>"+
 					    	"</tr>"
 						   );				  
 			}
 		} catch (SQLException e) {			
 			e.printStackTrace();
 		}
	} 

	private void alterarCategoriaPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try
		{
			Categoria categoria  = new Categoria();
			categoria.setId(Integer.parseInt(request.getParameter("id")));
			categoria.setNome(request.getParameter("nome"));
		
			CategoriaController controller = null;
			controller = new CategoriaController();
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

	private void adicionarCategoria(HttpServletRequest request, HttpServletResponse response) throws IOException {						
		try {
			DaoCategoria daoCategoria = new DaoCategoria(Conexao.getConexao());
			Categoria categoria = new Categoria();
			categoria.setNome(request.getParameter("nome"));
			daoCategoria.inserir(categoria);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		response.sendRedirect("/Canoinha/admin/Categoria/index.jsp?msg=Categoria Cadastrado com Sucesso");
	}
}
