<%@ include file="/WEB-INF/_cabecalho.jsp"%>
<%@ include file="/WEB-INF/_menu.jsp"%>

<style type="text/css">
#cartao {
	width: 50px;
	height: 35px;
}
</style>
<!-- Content -->
<div id="content">
	<!-- Products -->
	<div class="products">
		<h3>Finalizar Venda</h3>
		<form action="CheckoutServlet" method="post">
			Nome: <input class="input-xxlarge" name="nomeCliente" type="text" readonly value="${usuario.getNome()}"></br> 
			Rua: <input class="input-xxlarge" name="ruaCliente" type="text" readonly value="${usuario.getEndereco().getRua()}"></br> 
			Bairro: <input class="input-medium" name="bairroCliente" type="text" readonly value="${usuario.getEndereco().getBairro()}"> 
			Número: <input class="input-mini" name="numeroCliente" type="text" readonly value="${usuario.getEndereco().getNumero()}">
			<hr>
			Valor a Pagar: <input class="input-medium" name="valorTotal" type="text" value=<fmt:formatNumber value="<%=valorTotal%>" pattern="#,#00.00#"></fmt:formatNumber>  >
			<hr>
			<input type="radio" name="tipoDePagamento" value="visa"><img id="cartao" src="img/visa.jpeg"> 
			<input type="radio" name="tipoDePagamento" value="master"><img id="cartao" src="img/master.jpeg"> 
			<input type="radio" name="tipoDePagamento" value="visaElectron"><img id="cartao" src="img/visa_electron.jpeg"> 
			<input type="radio" name="tipoDePagamento" value="masterMaestro"><img id="cartao" src="img/master_maestro.jpeg"> 
			<input type="radio" name="tipoDePagamento" value="americanExpress"><img id="cartao" src="img/american_express.jpeg">
			<input type="radio" name="tipoDePagamento" value="diners"><img id="cartao" src="img/diners.jpeg">
			<input type="radio" name="tipoDePagamento" value="hipercard"><img id="cartao" src="img/hipercard.jpeg">
			<input type="hidden" name="acao" value="finalizarVenda"><br>
			Número do Cartão: <input class="input-medium" type="text" name="numeroCartao" >
			Parcelas: <select class="input-mini" name="parcelas">
						  <option value="1">1X</option>
  						  <option value="2">2x</option>
  						  <option value="3">3x</option>
    	    			  <option value="4">4x</option>
    	    			  <option value="4">5x</option>
    	    			  <option value="4">6x</option>
    	    			  <option value="4">7x</option>
    	    			  <option value="4">8x</option>
    	    			  <option value="4">9x</option>
    	    			  <option value="4">10x</option>
    	    			  <option value="4">11x</option>
    	    			  <option value="4">12x</option>
					  </select> Sem Juros
			<hr>
			<button class="btn btn-primary">Finalizar Venda</button>
		</form>
	</div>
</div>
<%@ include file="/WEB-INF/_rodape.jsp"%>
