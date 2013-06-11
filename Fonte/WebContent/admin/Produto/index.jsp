<%@ include file="/WEB-INF/cabecalho.jsp"%>
<script>
	function carregarMetodosDeRequisicaoAssincrona(){
		listarProdutos();		
	}
	
	function listarProdutos() {
		$.post('http://localhost:8080/Canoinha/ProdutoServlet',{acao:'ListarProdutos'},
				function(data) {
					$('#lista_produtos').html(data);
				});
	}
</script>
<c:if test="${not empty param.msg}">
	<div class="alert alert-error">
		${param.msg}
	</div>
</c:if>
   <div class="container">
    <form method="post" id="form_prepare">
	<a href="Cadastrar.jsp">Novo Cadastro</a>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Preço</th>			
			</tr>
		</thead>
			<tbody id="lista_produtos">
			
			</tbody>
		</form>
	</table>
	</div>
<%@ include file="/WEB-INF/rodape.jsp"%>