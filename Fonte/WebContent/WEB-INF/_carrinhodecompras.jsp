<%@page import="model.ItemVenda" %>
<%@page import="java.util.List" %>

<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button id="botaoLogout" type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
    <h3 id="myModalLabel">Carrinho de compras</h3>
  </div>
  <div class="modal-body">
    <table class="table table-hover">
    	<thead>    	        	    
    		<tr>
    			<th>Produto</th>
    			<th>Quant.</th>
    			<th>Pre&ccedil;o Unit.</th>
    			<th>Total R$</th>
    			<th></th>
    		</tr>    		
    	</thead>
    	<tbody>
    			<c:forEach items="${carrinhoDeCompras}" var="itemCarrinhoDeCompra">    	
    				<tr>
    		 			<td>${itemCarrinhoDeCompra.getProduto().getNome()}</td>
    		 			<td>${itemCarrinhoDeCompra.getQuantidade()}</td>    		 			
    		 			<td><fmt:formatNumber value="${itemCarrinhoDeCompra.getProduto().getPrecoDeVenda()}" type="currency" currencySymbol="$" /></td>
    		 			<td><fmt:formatNumber value="${itemCarrinhoDeCompra.getQuantidade() * itemCarrinhoDeCompra.getProduto().getPrecoDeVenda()}" type="currency" currencySymbol="$" /></td>
    		 			<td><a href="CheckoutServlet?codigoProduto=${itemCarrinhoDeCompra.getProduto().getId()}&acao=removerItemCarrinhoDeCompras"><i class="icon-trash"></i></a></td>
    				</tr>
    			</c:forEach>
    	</tbody>
    
    </table>
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">Fechar</button>
    <a href="finalizarVenda.jsp" class="btn btn-primary">Finalizar Compra</a>
  </div>
</div>