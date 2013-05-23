package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ProdutoController;

import model.Conexao;
import model.Produto;
import dao.DaoProduto;

/**
 * Servlet implementation class AlterarProdutoServlet
 */
public class AlterarProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlterarProdutoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		double precoDeCompra = Double.parseDouble(request.getParameter("preco_compra"));
		double precoDeVenda = Double.parseDouble(request.getParameter("preco_venda"));
		double valorDesconto = Double.parseDouble(request.getParameter("valorDesconto"));
		String informacoes = request.getParameter("informacoes");
		//HashMap<String, String> lista = new HashMap<String, String>();
		int quantidadeDisponivel = Integer.parseInt(request.getParameter("quantidade_disponivel"));
		HttpSession session = request.getSession(true);
		
		Produto produto = new Produto();
		produto.setNome(nome);
		produto.setPrecoDeCompra(precoDeCompra);
		produto.setPrecoDeVenda(precoDeVenda);
		produto.setValorDesconto(valorDesconto);
		produto.setInformacoes(informacoes);
		produto.setQuantidadeDisponivel(quantidadeDisponivel);
		
		ProdutoController controller = new ProdutoController();
		try {
			controller.inserir(produto);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		response.sendRedirect("index.jsp?msg=Produto Alterado com Sucesso");
	}

}
