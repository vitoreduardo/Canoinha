<%@ include file="/WEB-INF/admin/cabecalho.jsp"%>
<script>
	function carregarMetodosDeRequisicaoAssincrona(){
		listarUsuarios();		
	}
	
	function listarUsuarios() {		
		$.post('http://localhost:8080/Canoinha/UsuarioServlet',{acao:'ListarUsuarios'},
				function(data) {
					$('#lista_usuarios').html(data);
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
				<th>CPF</th>
			</tr>
		</thead>
		<tbody id="lista_usuarios">
			
		</tbody>
		</form>
	</table>
	</div>
<%@ include file="/WEB-INF/admin/rodape.jsp"%>