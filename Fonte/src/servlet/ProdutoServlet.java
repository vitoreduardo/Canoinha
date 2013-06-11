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
		String acao = request.getParameter("acao");
		
		try {
			DaoProduto daoProduto = new DaoProduto(Conexao.getConexao());			
			List<Produto> produtos = (List<Produto>) daoProduto.buscar();
			request.setAttribute("produtos", produtos);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		if(acao.equals("Alterar")){
			Produto produto = new Produto();
			
			Integer idProduto = Integer.parseInt(request.getParameter("id"));
			try {
				ProdutoController controller = new ProdutoController();
				produto = controller.buscar(idProduto);
				
				request.setAttribute("produtos", produto);
				RequestDispatcher disp = request.getRequestDispatcher("/admin/Produto/Alterar.jsp");
				disp.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if( acao.equals("Excluir") ){
			
			Integer idProduto = Integer.parseInt(request.getParameter("id"));
			try {
				ProdutoController controller = new ProdutoController();
				controller.excluir(idProduto);

				response.sendRedirect("/Canoinha/admin/Produto/index.jsp?msg=Produto Excluido com Sucesso");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(acao.equals("MostrarDetalheProduto")){
			mostrarDetalheProduto(request, response);
		}
	}
	
	private void mostrarDetalheProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
						String codigoProduto = request.getParameter("codigoProduto");
			
						DaoProduto daoProduto = new DaoProduto(Conexao.getConexao());
						Produto produto = daoProduto.buscar(Integer.parseInt(codigoProduto));
						
						request.setAttribute("produtoDetalhe", produto);
						
						RequestDispatcher requestDispatcher = request.getRequestDispatcher ("detalheProduto.jsp"); 
						requestDispatcher.forward(request, response); 
						
					} catch (SQLException e) {			
						e.printStackTrace();
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
			DaoProduto daoProduto = new DaoProduto(Conexao.getConexao());
			List<Produto> produtos = daoProduto.buscar();
			
			for (Produto produto : produtos) {
				out.println("<li>" +
							"<div class='product'>" +
							"<a href='ProdutoServlet?codigoProduto="+produto.getId()+"&acao=MostrarDetalheProduto' class='info'>" +
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
		}else if (acao.equals("ListarProdutos")){
			listarProdutos(request, response);
		}
	}
	
	private void listarProdutos(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			DaoProduto daoProduto = new DaoProduto(Conexao.getConexao());
			List<Produto> produtos = daoProduto.buscar();
			
			for (Produto produto : produtos) {
				out.println("<tr>"+
						    "<td>"+produto.getId()+"</td>"+
							"<td>"+produto.getNome()+"</td>"+
							"<td>"+produto.getPrecoDeVenda()+"</td>"+
							"<td><a href='/Canoinha/ProdutoServlet?id='"+produto.getId()+"'&acao='Alterar'>Atualizar <i class='icon-edit'></i></a></td>"+
							"<td><a href='/Canoinha/ProdutoServlet?id='"+produto.getId()+"'&acao=Excluir'>Excluir <i class='icon-trash'></i></a></td>"+
							"</tr>"
							);											  
			}
		} catch (SQLException e) {			
			e.printStackTrace();
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
		
		try {
			ProdutoController controller = new ProdutoController();
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
				
		try {
			ProdutoController controller = new ProdutoController();
			controller.inserir(produto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("/Canoinha/admin/Produto/index.jsp?msg=Produto Cadastrado com Sucesso");
	}
}
