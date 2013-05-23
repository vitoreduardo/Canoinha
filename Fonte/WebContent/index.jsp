<%@ include file="/WEB-INF/_cabecalho.jsp"%>
<%@ include file="/WEB-INF/_menu.jsp"%>
<script>
	function postProdutos() {
		$.post('http://localhost:8080/Canoinha/ProdutoServlet',{acao:'BuscarTodos'}, 
			function(data) {
				$('#itens_produtos').html(data);
			});
	}
</script>
<!-- Content -->
<div id="content">
	<!-- Products -->
	<div class="products">
		<h3>Lista de Produtos</h3>
		<ul id="itens_produtos">
			<img id="img_loading" src="img/loading.gif" width="50" height="50">
		</ul>
		<!-- End Products -->
	</div>
	<!-- Products -->
</div>
<%@ include file="/WEB-INF/_rodape.jsp"%>
