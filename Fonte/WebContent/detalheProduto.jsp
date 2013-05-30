<%@ include file="/WEB-INF/_cabecalho.jsp"%>
<%@ include file="/WEB-INF/_menu.jsp"%>

<div id='content'>
	<div class='products'>
	   <h3>${produtoDetalhe.getNome()}</h3>						
       <img src='css/images/image01.jpg' alt='' align='left'/>						
       <span class='low'>${produtoDetalhe.getInformacoes()}</span></br>
       <form action="CheckoutServlet" method="post">
	   		Qtde.: <input name="quantidade" type="text" maxlength="2" value="1" class="input-mini">
       		<h5><fmt:formatNumber value="${produtoDetalhe.getPrecoDeVenda()}" type="currency" currencySymbol="$" /></h5></br>       
       		<span>Quant. Disponível: ${produtoDetalhe.getQuantidadeDisponivel()}</span></br>			   
	   		<input type='hidden' name="acao" value='inserirCarrinhoDeCompras' />
	   		<input type='hidden' name='codigoProduto' value=${produtoDetalhe.getId()} />
	   		<input type='hidden' name='precoDeVenda' value=${produtoDetalhe.getPrecoDeVenda()} />	  
	   		<button class="btn btn-info" type="submit" >Comprar</button>	   
	   </form>
	</div>
</div>

<%@ include file="/WEB-INF/_rodape.jsp"%>
