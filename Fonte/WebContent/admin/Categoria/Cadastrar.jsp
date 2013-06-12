<%@ include file="/WEB-INF/cabecalho.jsp"%>
	<div class="container">
	<div class="container-fluid">
	<form class="form-horizontal" method="post" action="/Canoinha/CategoriaServlet" id="form_prepare">
		<fieldset>
			<legend>Cadastro de Categoria</legend>
			<label for="nome">Nome:</label> 
			<input id="nome" type="text" name="nome"><br> 
			<input class="btn btn-primary" type="submit" value="Cadastrar" name="cadastrar"> 
			<input type="hidden" name="acao" value="CadastrarCategoria">
			<input class="btn" type="reset" value="Limpar">
		</fieldset>
	</form>
	</div>
	</div>
<%@ include file="/WEB-INF/rodape.jsp"%>