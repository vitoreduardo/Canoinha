package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoProduto;

import model.Conexao;
import model.ItemVenda;
import model.Venda;

/**
 * Servlet implementation class CheckoutServlet
 */
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		if(acao.equals("removerItemCarrinhoDeCompras")){
			removerProdutoCarrinhoDeCompras(request, response);
		}
		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		if(acao.equals("inserirCarrinhoDeCompras")){
			try {
				inserirProdutoCarrinhoDeCompras(request, response);
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if(acao.equals("removerItemCarrinhoDeCompras")){
			removerProdutoCarrinhoDeCompras(request, response);
		}
		
		response.sendRedirect("index.jsp");
	}
	
	private void inserirProdutoCarrinhoDeCompras(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, SQLException{
		HttpSession session = request.getSession();
		List<ItemVenda> itensCarrinhoDeCompra = (List<ItemVenda>)session.getAttribute("carrinhoDeCompras");
		if(itensCarrinhoDeCompra==null){
			itensCarrinhoDeCompra = new ArrayList<ItemVenda>();
		}
		
		Conexao conexao = new Conexao();
		DaoProduto daoProduto = new DaoProduto(conexao);
		ItemVenda itemVenda = new ItemVenda();
		itemVenda.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
		itemVenda.setValor(Double.parseDouble(request.getParameter("precoDeVenda")));
		itemVenda.setProduto(daoProduto.buscar(Integer.parseInt(request.getParameter("codigoProduto"))));
		itensCarrinhoDeCompra.add(itemVenda);
		session.setAttribute("carrinhoDeCompras", itensCarrinhoDeCompra);
	}

	private void removerProdutoCarrinhoDeCompras(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		List<ItemVenda> itensCarrinhoDeCompra = (List<ItemVenda>)session.getAttribute("carrinhoDeCompras");
		
		int codigoProduto = Integer.parseInt(request.getParameter("codigoProduto"));
		
		itensCarrinhoDeCompra.remove(retornaIndiceItemCarrinhoDeCompras(codigoProduto, itensCarrinhoDeCompra));
		
		session.setAttribute("carrinhoDeCompras", itensCarrinhoDeCompra);
	}
	
	private int retornaIndiceItemCarrinhoDeCompras(int IdProduto, List<ItemVenda> itensCarrinhoDeCompra){
		int indiceItemCarrinhodeCompra = -1;
		for (int i = 0; i < itensCarrinhoDeCompra.size(); i++) {
			if(itensCarrinhoDeCompra.get(i).getProduto().getId()==IdProduto){
				indiceItemCarrinhodeCompra = i;
			}
		}
		return indiceItemCarrinhodeCompra;
	}
}
