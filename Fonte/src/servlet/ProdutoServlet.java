package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			Conexao conexao = new Conexao();
			DaoProduto daoProduto = new DaoProduto(conexao);
			List<Produto> produtos = daoProduto.buscar();
			
			for (Produto categoria : produtos) {
				out.println("<li>" +
							"<div class='product'>" +
								"<a href='#' class='info'>" +
									"<span class='holder'>" +
										"<img src='css/images/image01.jpg' alt='' />" +
										"<span class='book-name'>Book Name</span>" +
										"<span class='author'>by John Smith</span>" +
										"<span class='description'>Maecenas vehicula ante eu enim pharetra<br />scelerisque dignissim <br />sollicitudin nisi</span>" +
									"</span>" +
								"</a>" +
								"<a href='#' class='buy-btn'>BUY NOW <span class='price'><span class='low'>$</span>22<span class='high'>00</span></span></a>" +
							"</div>" +
							"</li>");				  
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			Conexao conexao = new Conexao();
			DaoProduto daoProduto = new DaoProduto(conexao);
			List<Produto> produtos = daoProduto.buscar();
			
			for (Produto produto : produtos) {
				out.println("<li>" +
							"<div class='product'>" +
								"<a href='#' class='info'>" +
									"<span class='holder'>" +
										"<img src='css/images/image01.jpg' alt='' />" +
										"<span class='book-name'>"+produto.getNome()+"</span>" +
										//"<span class='author'>by John Smith</span>" +
										"<span class='description'>"+produto.getInformacoes()+"</span>" +										
									"</span>" +
								"</a>" +
								"<a href='#' class='buy-btn'>now<span class='price'><span class='low'>$</span>"+produto.getPrecoDeVenda()+"<span class='high'>00</span></span></a>" +								
							"</div>" +
							"</li>");				  
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}

}
