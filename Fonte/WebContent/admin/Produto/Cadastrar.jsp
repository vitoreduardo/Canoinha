<%@ page import="java.util.*,
dao.*,
model.*,
controller.*"%>
<%@ include file="/WEB-INF/cabecalho.jsp"%>
	<div class="container">
	<div class="container-fluid">
		  <form method="post" action="/Canoinha/ProdutoServlet" id="form_prepare">
			 <fieldset>
				<legend>Cadastro de Produtos</legend>
				  <label>Nome:</label>
				  <input id="nome" type="text" name="nome" placeholder="Nome..."><br>
				  <label for="pcompra">Preço de compra: </label>
				  <input id="pcompra" type="text" name="preco_compra"> 
				  <label for="pvenda">Preço de Venda: </label>
				  <input id="pvenda" type="text" name="preco_venda"> 
				  <label for="vlrdesconto">Valor Desconto </label>
				  <input id="vlrdesconto" type="text" name="valor_desconto"><br>
				  <label for="foto">Foto:</label>
				  <input type="text" id="foto" name=""> <br>
				  Informações: <br>
				  <textarea rows="10" cols="50" name="informacoes"></textarea><br>
				  <label>Caracteristicas: </label>
				  <input type="text" name="caracteristicas1" id="caracteristicas1">
				  <input type="text" name="caracteristicas2" id="caracteristicas2">
				  <input class="btn btn-primary" type="button" id="btnAdicionarCaracteristica" value="Adicionar Caracteristicas"><br>
					<table id="grid" class="table table-hover">
						<thead>
						</thead>
						<tbody>
						</tbody>
					</table>
				  <label for="categoria">Categoria</label>
				  <%
				     CategoriaController controllerCat = new CategoriaController();
					 List<Categoria> listaCategoria = controllerCat.buscar();
				  %>
				  <select name="categoria">
				   <% for (Categoria cat : listaCategoria) {%>						  
						  	<option  value=<%=cat.getId()%>><%=cat.getNome()%></option>
				   <%}%>	  
				  </select>
				  <label for="quantidade">Quantidade Disponivel: </label>
				  <input id="quantidade" type="text" name="quantidade_disponivel"><br>
				  <input type="hidden" name="acao" value="AdicionarProduto">
				  <input class="btn btn-primary" type="submit" value="Cadastrar" name="cadastrar">
				  <input class="btn" type="reset" value="Limpar">
			  </fieldset>
		  </form>
		  </div>
		  </div>
<%@ include file="/WEB-INF/rodape.jsp"%>