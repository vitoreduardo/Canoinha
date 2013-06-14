package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoProduto;
import dao.DaoUsuario;
import dao.DaoVenda;

import model.Conexao;
import model.ItemVenda;
import model.Usuario;
import model.Venda;

/**
 * Servlet implementation class CheckoutServlet
 */
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		if(acao.equals("removerItemCarrinhoDeCompras")){
			removerProdutoCarrinhoDeCompras(request, response);
		}
		response.sendRedirect("index.jsp");
	}

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
		else if(acao.equals("finalizarVenda")){
			try {
				finalizarVenda(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}
	
	private void finalizarVenda(HttpServletRequest request, HttpServletResponse response) throws SQLException{
		HttpSession session = request.getSession();
		List<ItemVenda> itensCarrinhoDeCompra = (List<ItemVenda>)session.getAttribute("carrinhoDeCompras");
		Usuario usuario = (Usuario)session.getAttribute("usuario");
		DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getInstance();
		
		Venda venda = new Venda();
		venda.setData(new Date());
		venda.setNumeroCartaoDeCredito(request.getParameter("numeroCartao"));
		venda.setQuantidadeParcelas(Integer.parseInt(request.getParameter("parcelas")));
		venda.setTipoPagamento(request.getParameter("tipoDePagamento"));
		venda.setValor(Double.parseDouble(request.getParameter("valorTotal").replace(",", ".")));
		venda.setValorFrete(0);		
		venda.setItensVendas(itensCarrinhoDeCompra);			
		venda.setUsuario(usuario);		
		
		DaoVenda daoVenda = new DaoVenda(Conexao.getConexao());
		daoVenda.inserir(venda);
		
		request.setAttribute("menssagemSucesso", "venda realizada com sucesso");
		request.getSession().removeAttribute("carrinhoDeCompras");
	}
	
	private void inserirProdutoCarrinhoDeCompras(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, SQLException{
		HttpSession session = request.getSession();
		List<ItemVenda> itensCarrinhoDeCompra = (List<ItemVenda>)session.getAttribute("carrinhoDeCompras");
		if(itensCarrinhoDeCompra==null){
			itensCarrinhoDeCompra = new ArrayList<ItemVenda>();
		}
		
		DaoProduto daoProduto = new DaoProduto(Conexao.getConexao());
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
