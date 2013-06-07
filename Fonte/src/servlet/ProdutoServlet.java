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

import model.Categoria;
import model.Conexao;
import model.Produto;
import controller.ProdutoController;
import dao.DaoProduto;

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
		Integer idProduto = Integer.parseInt(request.getParameter("id"));
		String acao = request.getParameter("acao");
		
		Conexao conexao = new Conexao();
		DaoProduto daoProduto = new DaoProduto(conexao);
		try {
			List<Produto> produtos = null;
			produtos = (List<Produto>) daoProduto.buscar();
			request.setAttribute("produtos", produtos);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		if(acao.equals("Alterar")){
			Produto produto = new Produto();
			ProdutoController controller = new ProdutoController();
			try {
				produto = controller.buscar(idProduto);
				
				request.setAttribute("produtos", produto);
				RequestDispatcher disp = request.getRequestDispatcher("/admin/Produto/Alterar.jsp");
				disp.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if( acao.equals("Excluir") ){
			ProdutoController controller = new ProdutoController();
			try {
				controller.excluir(idProduto);

				response.sendRedirect("/Canoinha/admin/Produto/index.jsp?msg=Produto Excluido com Sucesso");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
								"<a href='#' class='info'>" +
									"<span class='holder'>" +
										"<img src='css/images/image01.jpg' alt='' />" +
										"<span class='book-name'>"+formatarNomeDoProduto(produto.getNome())+"</span>" +
										//"<span class='author'>by John Smith</span>" +
										"<span class='description'>"+formatarInformacaoDoProduto(produto.getInformacoes())+"</span>" +										
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String acao = request.getParameter("acao");
		
		if(acao.equals("BuscarTodos")){
			buscarTodos(request, response);
		}else if (acao.equals("AdicionarProduto")) {
			adicionarProdutos(request, response);
		}else if (acao.equals("AlterarProduto")){
			alterarProdutos(request, response);
		}
	}
	
	private void alterarProdutos(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Integer id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");
		double precoDeCompra = Double.parseDouble(request.getParameter("preco_compra"));
		double precoDeVenda = Double.parseDouble(request.getParameter("preco_venda"));
		double valorDesconto = Double.parseDouble(request.getParameter("valor_desconto"));
		String informacoes = request.getParameter("informacoes");
		Integer idCategoria = Integer.parseInt(request.getParameter("categoria"));
		//HashMap<String, String> lista = new HashMap<String, String>();
		int quantidadeDisponivel = Integer.parseInt(request.getParameter("quantidade_disponivel"));
		HttpSession session = request.getSession(true);
		
		Produto produto = new Produto();
		produto.setId(id);
		produto.setNome(nome);
		produto.setPrecoDeCompra(precoDeCompra);
		produto.setPrecoDeVenda(precoDeVenda);
		produto.setValorDesconto(valorDesconto);
		produto.setInformacoes(informacoes);
		produto.setQuantidadeDisponivel(quantidadeDisponivel);
		produto.setFotos("");
		
		Categoria cat = new Categoria();
		cat.setId(idCategoria);
		produto.setCategoria(cat);
		
		ProdutoController controller = new ProdutoController();
		try {
			controller.atualizar(produto);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		response.sendRedirect("/Canoinha/admin/Produto/index.jsp?msg=Produto Alterado com Sucesso");
	}
	
	private void adicionarProdutos(HttpServletRequest request, HttpServletResponse response) throws IOException{  
		String nome = request.getParameter("nome");
		double precoDeCompra = Double.parseDouble(request.getParameter("preco_compra"));
		double precoDeVenda = Double.parseDouble(request.getParameter("preco_venda"));
		double valorDesconto = Double.parseDouble(request.getParameter("valor_desconto"));
		String informacoes = request.getParameter("informacoes");
		Integer idCategoria = Integer.parseInt(request.getParameter("categoria"));
		String[] listaCaracteristica = request.getParameterValues("caracteristica");
		String[] listaValorCaracteristica = request.getParameterValues("valorCaracteristica");
		
		for (int i = 0; i < listaCaracteristica.length; i++) {
			System.out.println("C: "+listaCaracteristica[i] + " - V:"+ listaValorCaracteristica[i]);
		}
//		HashMap<String, String> lista = new HashMap<String, String>();

		int quantidadeDisponivel = Integer.parseInt(request.getParameter("quantidade_disponivel"));
		HttpSession session = request.getSession(true);
		
		Produto produto = new Produto();
		produto.setNome(nome);
		produto.setPrecoDeCompra(precoDeCompra);
		produto.setPrecoDeVenda(precoDeVenda);
		produto.setValorDesconto(valorDesconto);
		produto.setInformacoes(informacoes);
		produto.setQuantidadeDisponivel(quantidadeDisponivel);
		produto.setFotos("");
		
		Categoria cat = new Categoria();
		cat.setId(idCategoria);
		produto.setCategoria(cat);
		
		ProdutoController controller = new ProdutoController();
		try {
			controller.inserir(produto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("/Canoinha/admin/Produto/index.jsp?msg=Produto Cadastrado com Sucesso");
	}
}
