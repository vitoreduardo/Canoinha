<%@ include file="/WEB-INF/admin/cabecalho.jsp"%>
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
<%@ include file="/WEB-INF/admin/rodape.jsp"%>