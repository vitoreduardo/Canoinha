<%@ include file="/WEB-INF/admin/cabecalho.jsp"%>
	<div class="span10">
      <form method="post" action="/Canoinha/CategoriaServlet">
	     <fieldset>
		    <legend>Cadastro Categoria</legend>
			  Id:<input id="id" type="text" name="id" value="${categoria.id}" readonly >
			  <label for="nome">Nome:</label>
			  <input id="nome" type="text" name="nome" value="${categoria.nome}">
			  <input type="hidden" name="acao" value="AlterarCategoria">
			  <input class="btn btn-primary" type="submit" value="Alterar" name="alterar">
		  </fieldset>
	  </form>
	</div>
<%@ include file="/WEB-INF/admin/rodape.jsp"%>