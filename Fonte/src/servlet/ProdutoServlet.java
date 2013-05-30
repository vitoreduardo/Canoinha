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

import dao.DaoProduto;

import model.Conexao;
import model.Produto;

/**
 * Servlet implementation class ProdutoServlet
 */
public class ProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProdutoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    private String formatarNomeDoProduto(String nomeDoProduto){
		if (nomeDoProduto.length() > 40){
			return nomeDoProduto.substring(0,40);
		}
		else
			return nomeDoProduto;
	}
	
	private String formatarInformacaoDoProduto(String informacaoDoProduto){
		if (informacaoDoProduto.length() > 90){
			return informacaoDoProduto.substring(0,90);
		}
		else
			return informacaoDoProduto;
	}

	private void buscarTodos(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			Conexao conexao = new Conexao();
			DaoProduto daoProduto = new DaoProduto(conexao);
			List<Produto> produtos = daoProduto.buscar();
			
			for (Produto produto : produtos) {
				out.println("<li>" +
							"<div class='product'>" +
								"<a href='ProdutoServlet?codigoProduto="+produto.getId()+"&acao=BuscarProduto' class='info'>" +
									"<span class='holder'>" +
										"<img src='css/images/image01.jpg' alt='' />" +
										"<span class='book-name'>"+formatarNomeDoProduto(produto.getNome())+"</span>" +
										//"<span class='author'>by John Smith</span>" +
										"<span class='description'>"+formatarInformacaoDoProduto(produto.getInformacoes())+"</span>" +										
									"</span>" +
								"</a>" +
								"<a href='#' class='buy-btn'><span class='price'><span class='low'>$</span>"+produto.getPrecoDeVenda()+"<span class='high'>00</span></span></a>" +								
							"</div>" +
							"</li>");				  
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}
	
	private void buscarProduto(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		response.setContentType("text/html");
		try {
			String codigoProduto = request.getParameter("codigoProduto");

			Conexao conexao = new Conexao();
			DaoProduto daoProduto = new DaoProduto(conexao);
			Produto produto = daoProduto.buscar(Integer.parseInt(codigoProduto));
				
			request.setAttribute("produtoDetalhe", produto);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher ("detalheProduto.jsp"); 
			requestDispatcher.forward(request, response); 
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String acao = request.getParameter("acao");
		
		if(acao.equals("BuscarTodos")){
			buscarTodos(request, response);
		}
		
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String acao = request.getParameter("acao");
		
		if(acao.equals("BuscarProduto")){
			buscarProduto(request, response);
		}
	}

}
