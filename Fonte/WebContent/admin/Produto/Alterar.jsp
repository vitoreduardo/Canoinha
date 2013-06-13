<%@ include file="/WEB-INF/admin/cabecalho.jsp"%>
	   <div class="container">
		  <form method="post" action="/Canoinha/ProdutoServlet" id="form_prepare">
			 <fieldset>
				<legend>Altera��o de Produtos</legend>
				  Id:<input id="id" type="text" name="id" value="${produtos.id}" readonly> <br />
				  <label for="nome">Nome:</label>
				  <input id="nome" type="text" name="nome" value="${produtos.nome}"> <br />
				  <label for="pcompra">Pre�o de compra: </label>
				  <input id="pcompra" type="text" name="preco_compra" value="${produtos.precoDeCompra}"> <br/> 
				  <label for="pvenda">Pre�o de Venda: </label>
				  <input id="pvenda" type="text" name="preco_venda" value="${produtos.precoDeVenda}"><br/> 
				  <label for="vlrdesconto">Valor Desconto </label>
				  <input id="vlrdesconto" type="text" name="valor_desconto" value="${produtos.valorDesconto}"><br/>
				  <label for="foto">Foto:</label>
				  <input id="foto" name=""> <br>
				  Informa��es: <br>
				  <textarea rows="10" cols="50" name="informacoes">"${produtos.informacoes}"</textarea><br>
				  <label>Caracteristicas: </label>
				  <input type="text" name="caracteristicas1" id="caracteristicas1">
				  <input type="text" name="caracteristicas2" id="caracteristicas2">
				  <input type="button" id="btnAdicionarCaracteristica" value="Adicionar Caracteristicas"><br>
					<table id="grid" class="table table-hover">
						<thead>
						</thead>
						<tbody>
						</tbody>
					</table>
				  <label for="categoria">Categoria</label>
				  
				  <select name="categoria">
				  	<c:forEach var="categoria" items="${categorias}">
				  		<option value="${categoria.getId()}">${categoria.getNome()}</option>
				  	</c:forEach>
				  </select>
				  <label for="quantidade">Quantidade Disponivel: </label>
				  <input id="quantidade" type="text" name="quantidade_disponivel" value="${produtos.quantidadeDisponivel}"><br>
				  <input type="hidden" name="acao" value="AlterarProduto">				  
				  <input class="btn btn-primary" type="submit" value="Alterar" name="alterar">
			  </fieldset>
		  </form>
		  </div>
<%@ include file="/WEB-INF/admin/rodape.jsp"%>